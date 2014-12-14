package org.ab.service.generator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import org.ab.entity.Address;
import org.ab.entity.Contract;
import org.ab.entity.ContractPackage;
import org.ab.entity.Service;
import org.ab.entity.Subscriber;
import org.ab.model.dictionary.AddressType;
import org.ab.model.dictionary.ClientType;
import org.ab.service.generator.Invoice.Builder;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

@Component
public class InvoicesGenerator {

	private static final String ONE = "1";

	// 2. for each contract:
	//	- wygeneruj fakturê z uslug¹/uslugami z kontraktu
	// 	- dodaj oplate instalacyjna i aktywacyjna jesli nie byla pobrana.
	// 	- encja faktury w bazie powinna miec pole albo mapowanie do pola z contentem faktury w HTMLu
	public List<Invoice> generateInvoices(final List<Contract> contracts) {
		final Properties props = loadProperties("companyDetails.properties");
		final String city = props.getProperty("company.city");
		final String currentDate = LocalDate.now().toString();
		final List<Invoice> results = Lists.newArrayList();
		for(final Contract contract : contracts){
			final Invoice.Builder invoiceBuilder = new Invoice.Builder()
				.withSeller(getSeller(contract, props))
				.withBuyer(getBuyer(contract))
				.withCreateDate(currentDate)
				.withReceiveDate(currentDate)
				.withDateHeader(city + ", " + currentDate);
			final ContractPackage contractPackage = contract.getContractPackage();
			final List<Service> services = contractPackage.getServices();
			generateServices(services, contract, invoiceBuilder);

			results.add(invoiceBuilder.build());
		}
		return results;
	}

	private void generateServices(final List<Service> services, final Contract contract,
			final Builder invoiceBuilder) {

//		BigDecimal total
		for(final Service service: services){
			final BigDecimal netAmount = service.getSubscriptionNet().setScale(2);
			final BigDecimal vatRate = convertToRate(service.getVat());
			final BigDecimal vatAmount = netAmount.multiply(vatRate).setScale(2);
			final InvoiceServiceRecord.Builder serviceBuilder = new InvoiceServiceRecord.Builder()
				.withServiceName(service.getServiceName())
				.withQuantity(ONE)
				.withNetAmount(netAmount.toPlainString())
				.withVatAmount(vatAmount.toPlainString())
				.withGrossAmount(netAmount.add(vatAmount).toPlainString());
			invoiceBuilder.withServiceRecord(serviceBuilder.build());
		}
		// TODO Auto-generated method stub

	}

	private BigDecimal convertToRate(final Integer rate) {
		final BigDecimal decimalPart = new BigDecimal(rate).divide(new BigDecimal(100)).setScale(2);
		return BigDecimal.ONE.add(decimalPart);
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