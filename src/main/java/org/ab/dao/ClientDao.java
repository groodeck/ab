package org.ab.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
public class ClientDao {

	@PersistenceContext
	private EntityManager em;
	
	public long getMaxClientId(){
		//TODO
		return 0;
	}
	
}
