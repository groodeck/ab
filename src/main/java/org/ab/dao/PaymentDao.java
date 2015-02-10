package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ab.entity.Payment;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PaymentDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Payment> findAll() {
		return em.createQuery("from Payment").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Payment> findPayments(final String userNumber, final LocalDate createDateFrom, final LocalDate createDateTo) {
		final Query query = em.createQuery("SELECT DISTINCT OBJECT(p) FROM Payment p "
				+ "JOIN p.invoicePayments ip "
				+ "WHERE (:userNumber is null OR ip.invoice.contract.subscriber.subscriberIdn = :userNumber) "
				+ (createDateFrom == null ? "" : "AND p.createDate >= :dateFrom ")
				+ (createDateTo == null ? "" : "AND p.createDate <= :dateTo ")
				+ "ORDER BY p.createDate ASC")
				.setParameter("userNumber", userNumber);
		if(createDateFrom != null){
			query.setParameter("dateFrom", createDateFrom);
		}
		if(createDateTo != null){
			query.setParameter("dateTo", createDateTo);
		}
		return query.getResultList();
	}

	public Payment getPayment(final int paymentId) {
		return em.find(Payment.class, paymentId);
	}

	public Integer save(final Payment entity) {
		em.persist(entity);
		return entity.getPaymentId();
	}
}
