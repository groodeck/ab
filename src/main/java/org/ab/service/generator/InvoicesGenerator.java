package org.ab.service.generator;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import javax.transaction.Transactional;

import org.ab.dao.InvoiceDao;
import org.ab.entity.Address;
import org.ab.entity.Contract;
import org.ab.entity.ContractPackage;
import org.ab.entity.Service;
import org.ab.entity.Subscriber;
import org.ab.model.InvoiceModel;
import org.ab.model.InvoiceModel.Builder;
import org.ab.model.dictionary.AddressType;
import org.ab.model.dictionary.ClientType;
import org.ab.util.DecimalWriter;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

@Component
@Transactional
public class InvoicesGenerator {

	@Autowired
	private InvoiceContentGenerator contentGenerator;

	@Autowired
	private InvoiceDao invoiceDao;

	@Autowired
	private InvoiceNumberGenerator numberGenerator;

	private static final Integer ONE = 1;

	private Address determineInvoiceAddress(final List<Address> addresses) {
		final Address result;
		final Optional<Address> correspondenceAddress = filterFirstOfType(addresses, AddressType.CORRESPONDENCE);
		if(correspondenceAddress.isPresent()){
			result = correspondenceAddress.get();
		} else {
			result = filterFirstOfType(addresses, AddressType.MAIN).orNull();
		}
		return result;
	}

	private Optional<Address> filterFirstOfType(final List<Address> addresses, final AddressType addressType) {
		return FluentIterable.from(addresses).filter(new Predicate<Address>(){
			@Override
			public boolean apply(final Address input) {
				return input.getAddressType() == addressType;
			}

		}).first();
	}

	private String generateHtmlContent(final InvoiceModel invoice) {
		return contentGenerator.generateHtml(invoice);
	}

	@Transactional
	public List<InvoiceModel> generateInvoices(final List<Contract> contracts, final LocalDate dateFrom, final LocalDate dateTo) {
		final Properties props = loadProperties("companyDetails.properties");
		final String city = props.getProperty("company.city");
		final LocalDate currentDate = LocalDate.now();
		final LocalDate firstOfCurrentMonth = currentDate.dayOfMonth().withMinimumValue();
		final LocalDate lastOfCurrentMonth = currentDate.dayOfMonth().withMaximumValue();
		long lastInvoiceSequence = numberGenerator.getLastDocumentSequence(firstOfCurrentMonth, lastOfCurrentMonth);
		final List<InvoiceModel> results = Lists.newArrayList();
		for(final Contract contract : contracts){
			final InvoiceModel.Builder invoiceBuilder = new InvoiceModel.Builder()
			.withInvoiceNumber(numberGenerator.generate(lastInvoiceSequence++, currentDate))
			.withSubscriberIdn(contract.getSubscriber().getSubscriberIdn())
			.withContract(contract)
			.withSettlementPeriodStart(dateFrom)
			.withSettlementPeriodEnd(dateTo)
			.withSeller(getSeller(props))
			.withSubscriber(getInvoiceParticipant(contract.getSubscriber()))
			.withCreateDate(currentDate)
			.withReceiveDate(currentDate)
			.withDateHeader(city + ", " + currentDate)
			.withPaymentDate(currentDate.plusWeeks(2));
			final ContractPackage contractPackage = contract.getContractPackage();
			final List<Service> services = contractPackage.getServices();
			generateServices(services, contract, invoiceBuilder);
			final InvoiceModel invoice = invoiceBuilder.build();
			final String html = generateHtmlContent(invoice);
			invoice.setHtmlContent(html);
			results.add(invoice);
		}
		return results;
	}

	private void generateServices(final List<Service> services, final Contract contract,
			final Builder invoiceBuilder) {

		BigDecimal totalNetAmount = BigDecimal.ZERO;
		BigDecimal totalVatAmount = BigDecimal.ZERO;
		BigDecimal totalGrossAmount = BigDecimal.ZERO;
		int serviceCounter = 1;
		for(final Service service: services){
			final BigDecimal netAmount = service.getSubscriptionNet().setScale(2);
			final Integer vatRate = service.getVatRate();
			final BigDecimal vatAmount = service.getVatAmount().setScale(2);
			final BigDecimal grossAmount = netAmount.add(vatAmount);
			final InvoiceServiceRecord.Builder serviceBuilder = new InvoiceServiceRecord.Builder()
			.withLp(serviceCounter++)
			.withServiceName(service.getServiceName())
			.withQuantity(ONE)
			.withVatRate(vatRate)
			.withNetAmount(netAmount)
			.withVatAmount(vatAmount)
			.withGrossAmount(grossAmount);
			invoiceBuilder.withServiceRecord(serviceBuilder.build());
			totalNetAmount = totalNetAmount.add(netAmount);
			totalVatAmount = totalVatAmount.add(vatAmount);
			totalGrossAmount = totalGrossAmount.add(grossAmount);
		}
		if(!contract.getDisposableFeePaid()){
			final InvoiceServiceRecord installationFeeRecord = getInstallationFeeRecord(contract, serviceCounter++);
			final InvoiceServiceRecord activationFeeRecord = getActivationFeeRecord(contract, serviceCounter++);
			invoiceBuilder
			.withServiceRecord(installationFeeRecord)
			.withServiceRecord(activationFeeRecord);
			totalNetAmount = totalNetAmount.add(installationFeeRecord.getNetAmount()).add(activationFeeRecord.getNetAmount());
			totalVatAmount = totalVatAmount.add(installationFeeRecord.getVatAmount()).add(activationFeeRecord.getVatAmount());
			totalGrossAmount = totalGrossAmount.add(installationFeeRecord.getGrossAmount()).add(activationFeeRecord.getGrossAmount());

			contract.setDisposableFeePaid(true);
		}
		invoiceBuilder.withNetAmount(totalNetAmount)
		.withVatAmount(totalVatAmount)
		.withGrossAmount(totalGrossAmount)
		.withGrossAmountWords(DecimalWriter.getDecimalSpoken(totalGrossAmount.toPlainString()));
	}

	private InvoiceServiceRecord getActivationFeeRecord(final Contract contract, final int serviceNumber) {
		final InvoiceServiceRecord.Builder serviceBuilder = new InvoiceServiceRecord.Builder()
		.withLp(serviceNumber)
		.withServiceName("Op³ata aktywacyjna")
		.withQuantity(ONE)
		.withVatRate(contract.getActivationFeeVatRate().getRate())
		.withNetAmount(contract.getActivationFeeNet())
		.withVatAmount(contract.getActivationFeeVat())
		.withGrossAmount(contract.getActivationFeeGross());
		return serviceBuilder.build();
	}

	private String getAddressCity(final Address address) {
		return Joiner.on("/").skipNulls()
				.join(address.getZipCode(), address.getCity());
	}

	private String getAddressStreet(final Address address) {
		if(address == null){
			return StringUtils.EMPTY;
		} else {
			final StringBuilder sb = new StringBuilder(address.getStreet());
			sb.append(" ").append(Joiner.on("/").skipNulls()
					.join(address.getHouseNo(), address.getApartmentNo()));
			return sb.toString();
		}
	}

	private InvoiceServiceRecord getInstallationFeeRecord(final Contract contract, final int serviceNumber) {
		final InvoiceServiceRecord.Builder serviceBuilder = new InvoiceServiceRecord.Builder()
		.withLp(serviceNumber)
		.withServiceName("Op³ata instalacyjna")
		.withQuantity(ONE)
		.withVatRate(contract.getInstallationFeeVatRate().getRate())
		.withNetAmount(contract.getInstallationFeeNet())
		.withVatAmount(contract.getInstallationFeeVat())
		.withGrossAmount(contract.getInstallationFeeGross());
		return serviceBuilder.build();
	}

	public InvoiceParticipant getInvoiceParticipant(final Subscriber subscriber) {
		final InvoiceParticipant.Builder builder = new InvoiceParticipant.Builder();
		final Address address = determineInvoiceAddress(subscriber.getAddresses());
		if(subscriber.getClientType() == ClientType.INDIVIDUAL){
			builder.withName(Joiner.on(" ").join(subscriber.getName(), subscriber.getSurname()));
		} else {
			builder
			.withName(subscriber.getCompanyName())
			.withRegon(subscriber.getRegon());
		}
		builder.withNip(subscriber.getNip());
		builder.withAddressCity(getAddressCity(address));
		builder.withAddressStreet(getAddressStreet(address));
		return builder.build();
	}

	public InvoiceParticipant getSeller() {
		final Properties props = loadProperties("companyDetails.properties");
		return getSeller(props);
	}

	public InvoiceParticipant getSeller(final Properties props) {
		return new InvoiceParticipant.Builder()
		.withName(props.getProperty("company.name"))
		.withAddressCity(Joiner.on("/").skipNulls()
				.join(props.getProperty("company.addressZipCode"), props.getProperty("company.addressCity")))
				.withAddressStreet(props.getProperty("company.addressStreet"))
				.withRegon(props.getProperty("company.regon"))
				.withNip(props.getProperty("company.nip"))
				.withPhone(props.getProperty("company.phone"))
				.build();
	}

	private Properties loadProperties(final String fileName) {
		final Properties prop = new Properties();
		InputStream input=null;
		try {
			final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			input = classLoader.getResourceAsStream("companyDetails.properties");
			prop.load(input);
		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}

