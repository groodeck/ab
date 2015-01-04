package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.ContractPackage;
import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
public class ContractPackageDao {

	@PersistenceContext
	private EntityManager em;

	public List<ContractPackage> findAll() {
		return em.createQuery("from ContractPackage").getResultList();
	}

	public ContractPackage getById(final String contractPack) {
		return em.find(ContractPackage.class, Integer.parseInt(contractPack));
	}

	public Integer save(final ContractPackage entity) {
		if(entity.getPackageId() != null){
			em.merge(entity);
		} else {
			em.persist(entity);
		}
		return entity.getPackageId();
	}
}
