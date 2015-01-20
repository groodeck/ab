package org.ab.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.Subscriber;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SubscriberDao {

	@PersistenceContext
	private EntityManager em;

	public String getLastSubscriberIdn() {
		return (String) em.createQuery("select max(s.subscriberIdn) from Subscriber s")
				.getSingleResult();
	}

	public Subscriber getSubscriber(final int subscriberId) {
		return em.find(Subscriber.class, subscriberId);
	}

	public Integer save(final Subscriber subscriber) {
		if(subscriber.getSubscriberId() != null){
			em.merge(subscriber);
		} else {
			em.persist(subscriber);
		}
		return subscriber.getSubscriberId();
	}

}
