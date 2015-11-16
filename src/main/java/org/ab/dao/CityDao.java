package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.City;
import org.ab.ui.SortOrder;
import org.ab.ui.SortableColumn;
import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
public class CityDao {

	@PersistenceContext
	private EntityManager em;

	public void create(final String cityIdn, final String cityDesc){
		em.persist(new City(cityIdn, cityDesc));
	}

	public List<City> findAll(final SortableColumn orderColumn) {
		final String orderBy;
		final SortOrder sortOrder;
		if(orderColumn == null){
			orderBy = "c.cityIdn";
			sortOrder = SortOrder.ASC;
		} else {
			orderBy = orderColumn.getColumnName();
			sortOrder = orderColumn.getSortOrder();
		}
		final String sortfulQuery = String.format("from City c order by lower(%s) %s", orderBy, sortOrder.getClause());
		return em.createQuery(sortfulQuery).getResultList();
	}

	public City getByIdn(final String cityIdn) {
		return em.find(City.class, cityIdn);
	}
}
