package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.ContractDuration;
import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
public class ContractDurationDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<ContractDuration> findAll() {
		return em.createQuery("from ContractDuration").getResultList();
	}
}
