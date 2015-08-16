package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.VatRate;
import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
public class VatRateDao {

	@PersistenceContext
	private EntityManager em;

	public void create(final String vatRateIdn, final String vatRateDesc, final Integer value){
		em.persist(new VatRate(vatRateIdn, vatRateDesc, value));
	}

	public List<VatRate> findAll() {
		return em.createQuery("from VatRate v order by v.vatRateIdn").getResultList();
	}

	public VatRate getByIdn(final String vatRateIdn) {
		return em.find(VatRate.class, vatRateIdn);
	}
}
