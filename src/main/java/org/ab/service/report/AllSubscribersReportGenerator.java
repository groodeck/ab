package org.ab.service.report;

import static org.ab.model.dictionary.ReportType.ALL_SUBSCRIBERS;

import java.util.ArrayList;
import java.util.List;

import org.ab.annotation.ReportGeneratorOfType;
import org.ab.dao.SubscribersDao;
import org.ab.entity.Address;
import org.ab.entity.Contact;
import org.ab.entity.Contract;
import org.ab.entity.Subscriber;
import org.ab.model.dictionary.AddressType;
import org.ab.model.dictionary.ClientType;
import org.ab.model.dictionary.ContactType;
import org.ab.service.converter.AddressConverter;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

@Component
@ReportGeneratorOfType(ALL_SUBSCRIBERS)
public class AllSubscribersReportGenerator extends BaseReportGenerator implements ReportGenerator {

	@Autowired
	private SubscribersDao subscribersDao;

	@Autowired
	private AddressConverter addressConverter;

	private List<DataCell> extractDataRow(final Subscriber subscriber, final int xlsRowNumber){

		final List<DataCell> row = new ArrayList<DataCell>();
		final Optional<Contract> activeContract = getActiveContract(subscriber.getContracts());
		if(activeContract.isPresent()){
			row.add(new DataCell(xlsRowNumber, CellType.INT));
			row.add(new DataCell(subscriber.getSubscriberIdn(),CellType.TEXT));
			final ClientType clientType = subscriber.getClientType();
			row.add(new DataCell(clientType.getDesc(), CellType.TEXT));
			final String name;
			final String peselRegon;
			final String subscriberId;
			if(clientType == ClientType.INDIVIDUAL){
				name = subscriber.getName().concat(subscriber.getSurname());
				peselRegon = subscriber.getPesel();
				subscriberId = subscriber.getClientIdNumber();
			} else {
				name = subscriber.getCompanyName();
				peselRegon= subscriber.getRegon();
				subscriberId = StringUtils.EMPTY;
			}
			row.add(new DataCell(name, CellType.TEXT));
			row.add(new DataCell(peselRegon, CellType.TEXT));
			row.add(new DataCell(subscriberId, CellType.TEXT));
			row.add(new DataCell(subscriber.getNip(), CellType.TEXT));
			final List<Object> phoneList = Lists.newArrayList();
			final List<Object> emailList = Lists.newArrayList();
			for(final Contact contact : subscriber.getContacts()){
				if(contact.getContactType() == ContactType.EMAIL){
					emailList.add(contact.getContact());
				}else {
					phoneList.add(contact.getContact());
				}
			}
			row.add(new DataCell(Joiner.on(", ").join(phoneList), CellType.TEXT));
			row.add(new DataCell(Joiner.on(", ").join(emailList), CellType.TEXT));

			final org.ab.model.Address mainAddress = getAddress(subscriber.getAddresses(), AddressType.MAIN);
			row.add(new DataCell(getCityOrEmpty(mainAddress), CellType.TEXT));
			row.add(new DataCell(getAddressDetailsOrEmpty(mainAddress), CellType.TEXT));

			final org.ab.model.Address serviceAddress = getAddress(subscriber.getAddresses(), AddressType.SERVICE);
			row.add(new DataCell(getCityOrEmpty(serviceAddress), CellType.TEXT));
			row.add(new DataCell(getAddressDetailsOrEmpty(serviceAddress), CellType.TEXT));

			final org.ab.model.Address correspondenceAddress = getAddress(subscriber.getAddresses(), AddressType.CORRESPONDENCE);
			row.add(new DataCell(getAddressDetailsOrEmpty(correspondenceAddress), CellType.TEXT));
			row.add(new DataCell(subscriber.getBalance(), CellType.BIG_DECIMAL));

			final Contract contract = activeContract.get();
			row.add(new DataCell(contract.getContractIdn(), CellType.TEXT));
			row.add(new DataCell(contract.getContractSignDate(), CellType.LOCAL_DATE));
			row.add(new DataCell(contract.getContractActivationDate(), CellType.LOCAL_DATE));
			row.add(new DataCell(contract.getContractEndDate(), CellType.LOCAL_DATE));
			row.add(new DataCell(contract.getContractPeriod(), CellType.TEXT));
			row.add(new DataCell(contract.getContractStatus().getDesc(), CellType.TEXT));
			row.add(new DataCell(contract.getContractPackage().getPackageName(), CellType.TEXT));
			row.add(new DataCell(contract.getContractSubscription(), CellType.BIG_DECIMAL));
			row.add(new DataCell(contract.getInstallationType().getDesc(), CellType.TEXT));

			row.add(new DataCell(contract.getUser().getName(), CellType.TEXT));

			row.add(new DataCell(subscriber.getComment(), CellType.TEXT));
			row.add(new DataCell(subscriber.getAdditionalComment(), CellType.TEXT));

		}
		return row;
	}

	private Optional<Contract> getActiveContract(final List<Contract> contracts) {
		return FluentIterable.from(contracts).filter(new Predicate<Contract>(){
			@Override
			public boolean apply(final Contract input) {
				return input.isActive();
			}

		}).first();
	}

	private org.ab.model.Address getAddress(final List<Address> addresses, final AddressType addressType) {
		final Optional<Address> result = FluentIterable.from(addresses).filter(new Predicate<Address>(){

			@Override
			public boolean apply(final Address input) {
				return input.getAddressType() == addressType;
			}
		}).first();
		if(result.isPresent()){
			return addressConverter.convert(result.get());
		} else {
			return null;
		}
	}

	private String getAddressDetailsOrEmpty(final org.ab.model.Address address) {
		if(address == null){
			return null;
		} else {
			return Joiner.on(", ").skipNulls().join(address.getStreetDetails(), address.getZipCode());
		}
	}

	private String getCityOrEmpty(final org.ab.model.Address address) {
		if(address == null){
			return null;
		} else {
			return address.getCityDesc();
		}
	}

	@Override
	protected String getOutputFileName() {
		return String.format(ALL_SUBSCRIBERS.getOutputFilenameTemplate(), LocalDate.now().toString());
	}

	@Override
	protected List<DataSheet> getReportData() {
		final List<Subscriber> subscribers = subscribersDao.findAll();

		final int dataRowsHeaderOffset = 2;
		final DataSheet dataSheet = new DataSheet(ALL_SUBSCRIBERS.getDesc(), dataRowsHeaderOffset);

		int xlsRowNumber = 1;
		for(final Subscriber subscriber : subscribers){

			final List<DataCell> row = extractDataRow(subscriber, xlsRowNumber++);
			if(!row.isEmpty()){
				dataSheet.getRows().add(row);
			}
		}
		return Lists.newArrayList(dataSheet);
	}

	@Override
	protected String getTemplateFileName() {
		return ALL_SUBSCRIBERS.getTemplateFileName();
	}

}
