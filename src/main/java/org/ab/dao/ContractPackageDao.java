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

	public ContractPackage getById(String contractPack) {
		return em.find(ContractPackage.class, Integer.parseInt(contractPack));
	}
}
