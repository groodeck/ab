package org.ab.service;

import org.ab.dao.SubscriberDao;
import org.ab.entity.Subscriber;
import org.ab.model.SubscriberModel;
import org.ab.service.converter.SubscriberConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;

@Component
@Transactional
public class SubscriberService {

	@Autowired
	private SubscriberConverter subscriberConverter;

	@Autowired
	private SubscriberDao subscriberDao;

	@Autowired
	private ContractGenerator contractGenerator;

	public Optional<String> getCurrentContractFile(final String subscriberIdn) {
		final Optional<Subscriber> subscriber = subscriberDao.findByIdn(subscriberIdn);
		if(subscriber.isPresent()){
			return Optional.fromNullable(contractGenerator.generate(subscriber.get()));
		} else {
			return Optional.absent();
		}
	}

	public String getMaxClientId() {
		return subscriberDao.getLastSubscriberIdn();
	}

	public SubscriberModel getSubscriberDetails(final int subscriberId) {
		final Subscriber subscriber = subscriberDao.getSubscriber(subscriberId);
		if(subscriber == null){
			return null;
		} else {
			return subscriberConverter.convert(subscriber);
		}
	}

	@Transactional
	public void save(final SubscriberModel subscriberModel, final String userName) {
		final Subscriber subscriber = subscriberConverter.convert(subscriberModel, userName);
		final Integer id = subscriberDao.save(subscriber);
		subscriberModel.setSubscriberId(id.toString());
		contractGenerator.generate(subscriber);
	}

}
