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
		final String maxIdn = (String) this.em.createQuery("select max(s.subscriberIdn) from Subscriber s")
				.getSingleResult();
		return maxIdn == null ? "0" : maxIdn;
	}

	public Subscriber getSubscriber(final int subscriberId) {
		return this.em.find(Subscriber.class, subscriberId);
	}

	public Integer save(final Subscriber subscriber) {
		if(subscriber.getSubscriberId() != null){
			this.em.merge(subscriber);
		} else {
			this.em.persist(subscriber);
		}
		return subscriber.getSubscriberId();
	}

}
