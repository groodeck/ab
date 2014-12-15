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
	
	public Integer save(final Subscriber subscriber) {
		//TODO sprawdz poprzednie podejscie bez wyciagania encji przed konwersj¹
		if(subscriber.getSubscriberId() != null){
			em.merge(subscriber);
		} else {
			em.persist(subscriber);
		}
		return subscriber.getSubscriberId();
	}

	public Subscriber getSubscriber(int subscriberId) {
		return em.find(Subscriber.class, subscriberId);
	}
	
}
