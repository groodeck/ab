package org.ab.service;

import org.ab.dao.SubscriberDao;
import org.ab.entity.Subscriber;
import org.ab.model.SubscriberModel;
import org.ab.service.converter.SubscriberConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SubscriberService {

	@Autowired
	private SubscriberConverter subscriberConverter;
	
	@Autowired
	private SubscriberDao subscriberDao;
	
	@Transactional
	public void save(final SubscriberModel subscriberModel) {
		final Subscriber subscriber = subscriberConverter.convert(subscriberModel);
		subscriberDao.save(subscriber);
	}
}
