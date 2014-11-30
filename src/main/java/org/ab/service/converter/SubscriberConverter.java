package org.ab.service.converter;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.ab.entity.Contract;
import org.ab.entity.Subscriber;
import org.ab.model.Address;
import org.ab.model.dictionary.ClientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

@Component
public class SubscriberConverter {

	@Autowired
	private ContractConverter contractConverter;
	
	@Autowired
	private ContactConverter contactConverter;
	
	@Autowired
	private AddressConverter addressConverter;
	
	public Subscriber convert(org.ab.model.SubscriberModel model) {
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
			entity.setBalance(new BigDecimal(balance));
		}
		
		final Set<Contract> contracts = Sets.newHashSet();
		org.ab.model.Contract currentContract = model.getCurrentContract();
		if(currentContract != null){
			contracts.add(contractConverter.convert(currentContract));
		}
		entity.setContracts(contracts);
		
		final List<String> phones = model.getPhoneNumbers();
		final List<String> emails = model.getEmails();
		entity.setContacts(contactConverter.convert(phones, emails));
		
		final Address mainAddress = model.getMainAddress();
		final Address serviceAddress = model.getServiceAddress();
		final Address correspondanceAddress = model.getCorrespondenceAddress();
		entity.setAddresses(
				addressConverter.convert(mainAddress, serviceAddress, correspondanceAddress));
		
		return entity;
	}

}
