package org.ab.service;

import org.ab.dao.SubscriberDao;
import org.ab.entity.Subscriber;
import org.ab.model.SubscriberModel;
import org.ab.service.converter.SubscriberConverter;
import org.apache.commons.lang.StringUtils;
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
		final Integer id = subscriberDao.save(subscriber);
		subscriberModel.setSubscriberId(id.toString());
	}

	public SubscriberModel getSubscriberDetails(int subscriberId) {
		final Subscriber subscriber = subscriberDao.getSubscriber(subscriberId);
		if(subscriber == null){
			return null;
		} else {
			return subscriberConverter.convert(subscriber);
		}
	}
}
