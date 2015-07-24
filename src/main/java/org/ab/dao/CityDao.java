package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.City;
import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
public class CityDao {

	@PersistenceContext
	private EntityManager em;

	public void create(final String cityIdn, final String cityDesc){
		em.persist(new City(cityIdn, cityDesc));
	}

	public List<City> findAll() {
		return em.createQuery("from City c order by c.cityIdn").getResultList();
	}

	public City getByIdn(final String cityIdn) {
		return em.find(City.class, cityIdn);
	}
}
