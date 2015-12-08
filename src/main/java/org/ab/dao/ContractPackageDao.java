package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ab.entity.ContractPackage;
import org.ab.model.dictionary.ClientType;
import org.ab.ui.ResultPage;
import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
public class ContractPackageDao extends SortableDataPageFetch {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public ResultPage<ContractPackage> findAll(final PageInfo pageInfo, final boolean activeOnly) {
		final String queryText = String.format(getQueryTemplate(activeOnly),
				pageInfo.getSortColumn(), pageInfo.getSortOrder().getClause());
		final Query query = em.createQuery(queryText);
		final int allRecordsCount = query.getResultList().size();
		final List<ContractPackage> resultList = em.createQuery(queryText)
				.setMaxResults(MAX_RECORDS_ON_PAGE)
				.setFirstResult(pageInfo.getFirstResult())
				.getResultList();
		return new ResultPage<ContractPackage>(resultList, pageInfo.getPageNo(), countPages(allRecordsCount));
	}

	@SuppressWarnings("unchecked")
	public List<ContractPackage> findAllOfClientType(final ClientType clientType) {
		return em.createQuery("from ContractPackage cp where cp.clientType = :clientType and cp.packageActive is true")
				.setParameter("clientType", clientType)
				.getResultList();
	}

	public ContractPackage getById(final String contractPack) {
		return em.find(ContractPackage.class, Integer.parseInt(contractPack));
	}

	private String getQueryTemplate(final boolean activeOnly) {
		if(activeOnly){
			return "from ContractPackage cp where packageActive is true order by %s %s";
		} else {
			return "from ContractPackage order by %s %s";
		}
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
