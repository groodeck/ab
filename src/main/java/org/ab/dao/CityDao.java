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
	
	public List<City> findAll() {
		return em.createQuery("from City").getResultList();
	}
}
