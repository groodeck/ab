package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ab.entity.City;
import org.ab.ui.ResultPage;
import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
public class CityDao extends SortableDataPageFetch {

	@PersistenceContext
	private EntityManager em;

	public void create(final String cityIdn, final String cityDesc){
		em.persist(new City(cityIdn, cityDesc));
	}

	@SuppressWarnings("unchecked")
	public ResultPage<City> findAll(final PageInfo pageInfo) {
		final String queryText = String.format("from City c order by lower(%s) %s",
				pageInfo.getSortColumn(), pageInfo.getSortOrder().getClause());
		final Query query = em.createQuery(queryText);
		final int allRecordsCount = query.getResultList().size();
		final List<City> resultList = em.createQuery(queryText)
				.setMaxResults(MAX_RECORDS_ON_PAGE)
				.setFirstResult(pageInfo.getFirstResult())
				.getResultList();
		return new ResultPage<City>(resultList, pageInfo.getPageNo(), countPages(allRecordsCount));
	}

	public City getByIdn(final String cityIdn) {
		return em.find(City.class, cityIdn);
	}
}
