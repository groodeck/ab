package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.Service;
import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
public class ServiceDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Service> findAll() {
		return em.createQuery("from Service").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Service> findAllForSubscriber(final String subscriberIdn) {
		return em.createQuery("select OBJECT(sv) from Subscriber s "
				+ "join s.contracts as c "
				+ "join c.contractPackage.services as sv "
				+ "where "
				+ "c.active is true "
				+ "and s.subscriberIdn = :subscriberIdn ")
				.setParameter("subscriberIdn", subscriberIdn)
				.getResultList();
	}

	public Service getById(final String serviceId) {
		return em.find(Service.class, Integer.parseInt(serviceId));
	}

	public Integer save(final Service entity) {
		em.persist(entity);
		return entity.getServiceId();
	}
}
