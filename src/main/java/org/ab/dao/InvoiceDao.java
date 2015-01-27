package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ab.entity.Invoice;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class InvoiceDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Invoice> findAll() {
		return em.createQuery("from Invoice").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Invoice> findInvoices(final String userNumber, final LocalDate createDateFrom, final LocalDate createDateTo) {
		final Query query = em.createQuery("from Invoice i where "
				+ "(:userNumber is null OR i.contract.subscriber.subscriberIdn = :userNumber) "
				+ (createDateFrom == null ? "" : "AND i.createDate >= :dateFrom ")
				+ (createDateTo == null ? "" : "AND i.createDate <= :dateTo ")
				+ "ORDER BY i.contract.subscriber.subscriberId, i.settlementPeriodStart ASC")
				.setParameter("userNumber", userNumber);
		if(createDateFrom != null){
			query.setParameter("dateFrom", createDateFrom);
		}
		if(createDateTo != null){
			query.setParameter("dateTo", createDateTo);
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Invoice> findUnpaidInvoices(final String subscriberId) {
		return em.createQuery("FROM Invoice i WHERE "
				+ "i.contract.subscriber.subscriberId = :subscriberId "
				+ "AND i.paidAmount <> i.grossAmount "
				+ "ORDER BY i.settlementPeriodStart")
				.setParameter("subscriberId", Integer.getInteger(subscriberId))
				.getResultList();
	}

	public Invoice getInvoice(final int invoiceId) {
		return em.find(Invoice.class, invoiceId);
	}

	public long getInvoiceCount(final LocalDate dateFrom, final LocalDate dateTo) {
		return (Long)em.createQuery("select count(*) "
				+ "from Invoice i where "
				+ "i.settlementPeriodStart = :dateFrom "
				+ "and i.settlementPeriodEnd = :dateTo ")
				.setParameter("dateFrom", dateFrom)
				.setParameter("dateTo", dateTo)
				.getSingleResult();
	}

	public Integer save(final Invoice entity) {
		em.persist(entity);
		return entity.getInvoiceId();
	}
}
