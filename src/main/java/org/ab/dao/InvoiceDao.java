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
		return em.createQuery("SELECT OBJECT(i) FROM Invoice i "
				+ "LEFT JOIN i.invoicePayments ip "
				+ "GROUP BY i.invoiceId "
				+ "HAVING i.contract.subscriber.subscriberId = :subscriberId "
				+ "AND ((sum(ip.paymentAmount) IS NULL) "
				+ "		OR (sum(ip.paymentAmount) != i.grossAmount)) "
				+ "ORDER BY i.settlementPeriodStart")
				.setParameter("subscriberId", Integer.parseInt(subscriberId))
				.getResultList();
	}

	public Invoice getInvoice(final int invoiceId) {
		return em.find(Invoice.class, invoiceId);
	}

	@SuppressWarnings("unchecked")
	public List<String> getInvoiceNumbers(final LocalDate dateFrom, final LocalDate dateTo) {
		return em.createQuery("select i.invoiceNumber "
				+ "from Invoice i where "
				+ "i.createDate >= :dateFrom "
				+ "and i.createDate <= :dateTo ")
				.setParameter("dateFrom", dateFrom)
				.setParameter("dateTo", dateTo)
				.getResultList();
	}

	public Integer save(final Invoice entity) {
		em.persist(entity);
		return entity.getInvoiceId();
	}
}
