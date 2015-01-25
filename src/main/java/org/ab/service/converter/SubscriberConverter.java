package org.ab.service.converter;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.ab.entity.Contact;
import org.ab.entity.Contract;
import org.ab.entity.Subscriber;
import org.ab.model.Address;
import org.ab.model.SubscriberModel;
import org.ab.model.dictionary.AddressType;
import org.ab.model.dictionary.ClientType;
import org.ab.model.dictionary.ContactType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

@Component
public class SubscriberConverter {

	@Autowired
	private ContractConverter contractConverter;

	@Autowired
	private ContactConverter contactConverter;

	@Autowired
	private AddressConverter addressConverter;

	private final Function<Subscriber, SubscriberModel> entityToModel = new Function<Subscriber, SubscriberModel>(){
		@Override
		public SubscriberModel apply(final Subscriber entity) {
			final SubscriberModel model = new SubscriberModel();
			model.setSubscriberId(entity.getSubscriberId().toString());
			model.setSubscriberIdn(entity.getSubscriberIdn());
			model.setName(entity.getName());
			model.setSurname(entity.getSurname());
			model.setCompanyName(entity.getCompanyName());
			model.setClientType(entity.getClientType().name());
			model.setClientTypeDesc(entity.getClientType().getDesc());
			model.setPesel(entity.getPesel());
			model.setRegon(entity.getRegon());
			model.setNip(entity.getNip());
			model.setClientIdNumber(entity.getClientIdNumber());
			model.setComment(entity.getComment());
			model.setAdditionalComment(entity.getAdditionalComment());
			if(entity.getBalance() != null){
				model.setBalance(entity.getBalance().toPlainString());
			}
			model.setCurrentContract(getCurrentContract(entity.getContracts()));
			model.setMainAddress(getAddress(entity.getAddresses(), AddressType.MAIN));
			final Address serviceAddress = getAddress(entity.getAddresses(), AddressType.SERVICE);
			model.setServiceAddress(serviceAddress);
			model.setServiceAddressSet(serviceAddress != null);
			final Address correspondanceAddress = getAddress(entity.getAddresses(), AddressType.CORRESPONDENCE);
			model.setCorrespondenceAddress(correspondanceAddress);
			model.setCorrespondenceAddressSet(correspondanceAddress != null);
			model.setPhoneNumbers(getContactValues(entity.getContacts(), ContactType.PHONE));
			model.setEmails(getContactValues(entity.getContacts(), ContactType.EMAIL));
			return model;
		}

		private Address getAddress(final List<org.ab.entity.Address> addresses, final AddressType addressType) {
			final Optional<org.ab.entity.Address> address = FluentIterable.from(addresses)
					.filter(new Predicate<org.ab.entity.Address>(){
						@Override
						public boolean apply(final org.ab.entity.Address input) {
							return input.getAddressType() == addressType;
						}

					}).first();
			final Address result;
			if(address.isPresent()){
				result = addressConverter.convert(address.get());
			} else {
				result = null;
			}
			return result;
		}

		private List<String> getContactValues(final List<Contact> contacts, final ContactType contactType) {
			return FluentIterable.from(contacts).filter(new Predicate<Contact>(){
				@Override
				public boolean apply(final Contact input) {
					return input.getContactType() == contactType;
				}
			}).transform(new Function<Contact, String>(){
				@Override
				public String apply(final Contact input) {
					return input.getContact();
				}

			}).toList();
		}

		private org.ab.model.Contract getCurrentContract(final List<Contract> contracts) {
			final Optional<Contract> entity = FluentIterable.from(contracts)
					.filter(new Predicate<Contract>(){
						@Override
						public boolean apply(final Contract input) {
							return input.isActive();
						}

					}).first();
			final org.ab.model.Contract model;
			if(entity.isPresent()){
				model = contractConverter.convert(entity.get());
			} else {
				model = null;
			}
			return model;
		}

	};

	@Transactional
	public List<SubscriberModel> convert(final List<Subscriber> subscribers) {
		return FluentIterable.from(subscribers).transform(entityToModel).toList();
	}

	public Subscriber convert(final org.ab.model.SubscriberModel model, final String userName) {
		final Subscriber entity = new Subscriber();
		final String subscriberId = model.getSubscriberId();
		if(isNotBlank(subscriberId)){
			entity.setSubscriberId(Integer.parseInt(subscriberId));
		}
		entity.setSubscriberIdn(model.getSubscriberIdn());

		final String clientTypeName = model.getClientType();
		if(isNotBlank(clientTypeName)){
			final ClientType clientType = ClientType.valueOf(clientTypeName);
			entity.setClientType(clientType);
		}

		entity.setName(model.getName());
		entity.setSurname(model.getSurname());
		entity.setCompanyName(model.getCompanyName());

		entity.setClientIdNumber(model.getClientIdNumber());
		entity.setPesel(model.getPesel());
		entity.setRegon(model.getRegon());
		entity.setNip(model.getNip());

		final String balance = model.getBalance();
		if(isNotBlank(balance)){
			entity.setBalance(new BigDecimal(balance.replaceAll(",", ".")));
		}

		final List<Contract> contracts = Lists.newArrayList();
		final org.ab.model.Contract currentContract = model.getCurrentContract();
		if(currentContract != null){
			contracts.add(contractConverter.convert(currentContract, userName));
		}
		entity.getContracts().clear();
		entity.getContracts().addAll(contracts);

		final List<String> phones = model.getPhoneNumbers();
		final List<String> emails = model.getEmails();
		entity.getContacts().clear();
		entity.getContacts().addAll(contactConverter.convert(phones, emails));

		final Address mainAddress = model.getMainAddress();
		final Address serviceAddress = getServiceAddressIfSet(model);
		final Address correspondanceAddress = getCorrespondenceAddressIfSet(model);
		entity.getAddresses().clear();
		entity.getAddresses().addAll(
				addressConverter.convert(mainAddress, serviceAddress, correspondanceAddress));

		entity.setComment(model.getComment());
		entity.setAdditionalComment(model.getAdditionalComment());

		return entity;
	}

	public SubscriberModel convert(final Subscriber subscriber) {
		return entityToModel.apply(subscriber);
	}

	private Address getCorrespondenceAddressIfSet(final org.ab.model.SubscriberModel model) {
		if(model.isCorrespondenceAddressSet()){
			return model.getCorrespondenceAddress();
		} else {
			return null;
		}
	}

	private Address getServiceAddressIfSet(final org.ab.model.SubscriberModel model) {
		if(model.isServiceAddressSet()){
			return model.getServiceAddress();
		} else {
			return null;
		}
	}

}
