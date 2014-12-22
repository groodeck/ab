package org.ab.service.generator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import org.ab.dao.InvoiceDao;
import org.ab.entity.Address;
import org.ab.entity.Contract;
import org.ab.entity.ContractPackage;
import org.ab.entity.Service;
import org.ab.entity.Subscriber;
import org.ab.model.dictionary.AddressType;
import org.ab.model.dictionary.ClientType;
import org.ab.service.generator.Invoice.Builder;
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
public class InvoicesGenerator {

	@Autowired
	private InvoiceContentGenerator contentGenerator;

	@Autowired
	private InvoiceDao invoiceDao;

	private static final Integer ONE = 1;

	public List<Invoice> generateInvoices(final List<Contract> contracts, final LocalDate dateFrom, final LocalDate dateTo) {
		final Properties props = loadProperties("companyDetails.properties");
		final String city = props.getProperty("company.city");
		final LocalDate currentDate = LocalDate.now();
		final List<Invoice> results = Lists.newArrayList();
		for(final Contract contract : contracts){
			final Invoice.Builder invoiceBuilder = new Invoice.Builder()
				.withInvoiceNumber(getInvoiceNumber(dateFrom, dateTo))
				.withContract(contract)
				.withSettlementPeriodStart(dateFrom)
				.withSettlementPeriodEnd(dateTo)
				.withSeller(getSeller(contract, props))
				.withBuyer(getBuyer(contract))
				.withCreateDate(currentDate)
				.withReceiveDate(currentDate)
				.withDateHeader(city + ", " + currentDate);
			final ContractPackage contractPackage = contract.getContractPackage();
			final List<Service> services = contractPackage.getServices();
			generateServices(services, contract, invoiceBuilder);
			final Invoice invoice = invoiceBuilder.build();
			final String html = generateHtmlContent(invoice);
			invoice.setHtmlContent(html);
			results.add(invoice);
		}
		return results;
	}

	String getInvoiceNumber(final LocalDate dateFrom, final LocalDate dateTo) {
		final long invoicesGenerated = this.invoiceDao.getInvoiceCount(dateFrom, dateTo);

		final String sequenceNumber = String.format("%06d", invoicesGenerated+1);
		final String month = String.format("%02d", dateFrom.getMonthOfYear());
		return String.format("FVAT/%s/%s/%s", sequenceNumber, month, dateFrom.getYear());
	}

	private String generateHtmlContent(final Invoice invoice) {
		return this.contentGenerator.generateHtml(invoice);
	}

	private void generateServices(final List<Service> services, final Contract contract,
			final Builder invoiceBuilder) {

		BigDecimal totalNetAmount = BigDecimal.ZERO;
		BigDecimal totalVatAmount = BigDecimal.ZERO;
		BigDecimal totalGrossAmount = BigDecimal.ZERO;
		int serviceCounter = 1;
		for(final Service service: services){

			if(!service.isDisposable()
					||(service.isDisposable() && !contract.getDisposableFeePaid())){
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
		}
		invoiceBuilder.withNetAmount(totalNetAmount)
			.withVatAmount(totalVatAmount)
			.withGrossAmount(totalGrossAmount)
			.withGrossAmountWords(DecimalWriter.getDecimalSpoken(totalGrossAmount.toPlainString()));
	}

	private InvoiceParticipant getBuyer(final Contract contract) {
		final InvoiceParticipant.Builder builder = new InvoiceParticipant.Builder();
		final Subscriber subscriber = contract.getSubscriber();
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

	private String getAddressStreet(final Address address) {
		if(address == null){
			return StringUtils.EMPTY;
		} else {
			final StringBuilder sb = new StringBuilder(address.getStreet());
			sb.append(Joiner.on("/").skipNulls()
					.join(address.getHouseNo(), address.getApartmentNo()));
			return sb.toString();
		}
	}

	private String getAddressCity(final Address address) {
		return Joiner.on("/").skipNulls()
				.join(address.getZipCode(), address.getCity());
	}

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

	private InvoiceParticipant getSeller(final Contract contract, final Properties props) {
		return new InvoiceParticipant.Builder()
			.withName(props.getProperty("company.name"))
			.withAddressCity(props.getProperty("company.addressCity"))
			.withAddressStreet(props.getProperty("company.addressStreet"))
			.withRegon(props.getProperty("company.regon"))
			.withNip(props.getProperty("company.nip"))
			.withPhone(props.getProperty("company.phone"))
			.build();
	}

	private Properties loadProperties(final String fileName) {
		final Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("companyDetails.properties");
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

