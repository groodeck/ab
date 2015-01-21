package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.ContractPackage;
import org.ab.model.dictionary.ClientType;
import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
public class ContractPackageDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ContractPackage> findAll() {
		return em.createQuery("from ContractPackage").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ContractPackage> findAllOfClientType(final ClientType clientType) {
		return em.createQuery("from ContractPackage cp where cp.clientType = :clientType")
				.setParameter("clientType", clientType)
				.getResultList();
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
