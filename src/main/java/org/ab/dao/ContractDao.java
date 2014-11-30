package org.ab.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.Contract;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ContractDao {

	@PersistenceContext
	private EntityManager em;
	
	public long getContractsSignCount(final LocalDate date) {
		return (Long)em.createQuery("select count(*) from Contract c where c.contractSignDate = :signDate")
				.setParameter("signDate", date)
				.getSingleResult();
	}

	public void save(Contract contract) {
		em.persist(contract);
	}
}
