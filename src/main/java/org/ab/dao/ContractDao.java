package org.ab.dao;

import java.util.List;

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

	public List<Contract> findContracts(final LocalDate dateFrom, final LocalDate dateTo) {
		return em.createQuery("from Contract c where "
				+ "c.contractActivationDate <= :dateFrom "
				+ "and (c.contractEndDate = null "
				+ "		or c.contractEndDate >= :dateTo) "
				+ "and not exists ("
				+ "		 from Invoice i where "
				+ "			i.contract = c"
				+ "			and i.settlementPeriodStart = :dateFrom"
				+ "			and i.settlementPeriodEnd = :dateTo)")
				.setParameter("dateFrom", dateFrom)
				.setParameter("dateTo", dateTo)
				.getResultList();
	}

	public long getContractsSignCount(final LocalDate date) {
		return (Long)em.createQuery("select count(*) from Contract c where c.contractSignDate = :signDate")
				.setParameter("signDate", date)
				.getSingleResult();
	}

	public void save(final Contract contract) {
		em.persist(contract);
	}
}
