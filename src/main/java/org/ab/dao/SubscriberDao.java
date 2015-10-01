package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.Subscriber;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;

@Repository
@Transactional
public class SubscriberDao {

	@PersistenceContext
	private EntityManager em;

	public Optional<Subscriber> findByIdn(final String subscriberIdn) {
		final List customerList = em.createQuery("from Subscriber where subscriberIdn=:subscriberIdn")
				.setParameter("subscriberIdn", subscriberIdn)
				.getResultList();
		return Optional.fromNullable(Iterables.<Subscriber>getFirst(customerList, null));

	}

	public String getLastSubscriberIdn() {
		final String maxIdn = (String) em.createQuery("select max(s.subscriberIdn) from Subscriber s")
				.getSingleResult();
		return maxIdn == null ? "0" : maxIdn;
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
