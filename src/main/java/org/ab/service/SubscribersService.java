package org.ab.service;

import java.util.List;

import org.ab.dao.SubscribersDao;
import org.ab.entity.Subscriber;
import org.ab.model.SubscriberModel;
import org.ab.service.converter.SubscriberConverter;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SubscribersService {

	@Autowired
	private SubscriberConverter subscriberConverter;
	
	@Autowired
	private SubscribersDao subscriberDao;
	
	@Transactional
	public List<SubscriberModel> findSubscribers(final String phrase, final String dateFromStr, final String dateToStr) {
		
		final LocalDate dateFrom = getLocalDate(dateFromStr, LocalDate.parse("2000-01-01"));
		final LocalDate dateTo = getLocalDate(dateToStr, LocalDate.now().plusMonths(1));
		final List<Subscriber> subscribers = subscriberDao.findSubscribers(phrase, dateFrom, dateTo);
		return convertToModel(subscribers);
	}

	private List<SubscriberModel> convertToModel(List<Subscriber> subscribers) {
		return subscriberConverter.convert(subscribers);
	}

	private LocalDate getLocalDate(final String dateStr, final LocalDate defaultValue) {
		if(StringUtils.isBlank(dateStr)){
			return defaultValue;
		} else {
			return LocalDate.parse(dateStr);
		}
	}
}
