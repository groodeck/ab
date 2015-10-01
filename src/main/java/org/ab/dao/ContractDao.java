package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.Contract;
import org.ab.model.dictionary.ContractStatus;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ContractDao {

	@PersistenceContext
	private EntityManager em;

	public List<Contract> findContractsToInvoiceGenerate(final LocalDate dateFrom, final LocalDate dateTo) {
		final List contractToInvoiceGenerate = findFullMonthInvoiceContracts(dateFrom, dateTo);
		contractToInvoiceGenerate.addAll(findPartMonthInvoiceContracts(dateFrom, dateTo));
		return contractToInvoiceGenerate;
	}

	private List findFullMonthInvoiceContracts(final LocalDate dateFrom,
			final LocalDate dateTo) {
		return em.createQuery("from Contract c where "
				+ "c.contractActivationDate <= :dateFrom "
				+ "and (c.contractEndDate = null "
				+ "		or c.contractEndDate >= :dateTo) "
				+ "and not exists ("
				+ "		 from Invoice i where "
				+ "			i.contract = c"
				+ "			and i.settlementPeriodStart = :dateFrom"
				+ "			and i.settlementPeriodEnd = :dateTo) "
				+ "and c.contractStatus in (:activeSubscriberStatuses)")
				.setParameter("dateFrom", dateFrom)
				.setParameter("dateTo", dateTo)
				.setParameter("activeSubscriberStatuses", ContractStatus.activeSubscriberStatuses())
				.getResultList();
	}

	private List findPartMonthInvoiceContracts(final LocalDate dateFrom,
			final LocalDate dateTo) {
		return em.createQuery("from Contract c where "
				+ "c.contractStatus in (:activeSubscriberStatuses) "
				+ "and (c.contractActivationDate > :dateFrom "
				+ "and c.contractActivationDate < :dateTo "
				+ "and not exists ("
				+ "		 from Invoice i where "
				+ "			i.contract = c"
				+ "			and i.settlementPeriodStart = c.contractActivationDate "
				+ "			and i.settlementPeriodEnd = :dateTo)) "
				+ "or (c.contractEndDate > :dateFrom "
				+ "		and c.contractEndDate < :dateTo "
				+ "and not exists ("
				+ "		 from Invoice i2 where "
				+ "			i2.contract = c "
				+ "			and i2.settlementPeriodStart = :dateFrom "
				+ "			and i2.settlementPeriodEnd = c.contractActivationDate)) ")
				.setParameter("dateFrom", dateFrom)
				.setParameter("dateTo", dateTo)
				.setParameter("activeSubscriberStatuses", ContractStatus.activeSubscriberStatuses())
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
