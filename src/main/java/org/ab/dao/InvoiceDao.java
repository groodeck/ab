package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		return em.createQuery("from Invoice i where "
				+ "(:userNumber = null OR i.contract.subscriber.subscriberIdn = :userNumber) "
				+ "and (:dateFrom = null OR i.createDate >= :dateFrom) "
				+ "and (:dateTo = null OR i.createDate <= :dateTo) ")
				.setParameter("userNumber", userNumber)
				.setParameter("dateFrom", createDateFrom)
				.setParameter("dateTo", createDateTo)
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

	public void save(final List<Invoice> entities) {
		for(final Invoice singleObject : entities){
			em.persist(singleObject);
		}
	}
}
