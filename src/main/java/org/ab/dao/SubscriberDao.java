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
	
	public void save(final Subscriber subscriber) {
		em.persist(subscriber);
	}
	
}
