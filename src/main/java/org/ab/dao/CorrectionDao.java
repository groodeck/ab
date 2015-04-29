package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ab.entity.Correction;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;

@Repository
@Transactional
public class CorrectionDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Correction> findAll() {
		return em.createQuery("from Correction").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Correction> findCorrections(final ImmutableSet<Integer> invoiceIds) {
		final Query query = em.createQuery("from Correction c where "
				+ "c.invoice.invoiceId IN (:invoiceIds) "
				+ "ORDER BY c.invoice.contract.subscriber.subscriberId, c.invoice.settlementPeriodStart ASC")
				.setParameter("invoiceIds", invoiceIds);
		return query.getResultList();
	}

	public Correction getCorrection(final int correctionId) {
		return em.find(Correction.class, correctionId);
	}

	@SuppressWarnings("unchecked")
	public List<String> getCorrectionNumbers(final LocalDate dateFrom, final LocalDate dateTo) {
		return em.createQuery("select c.correctionNumber "
				+ "from Correction c where "
				+ "c.createDate >= :dateFrom "
				+ "and c.createDate <= :dateTo ")
				.setParameter("dateFrom", dateFrom)
				.setParameter("dateTo", dateTo)
				.getResultList();
	}

	public Integer save(final Correction entity) {
		if(entity.getCorrectionId() == null){
			em.persist(entity);
		} else {
			em.merge(entity);
		}
		return entity.getCorrectionId();
	}
}
