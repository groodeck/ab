package org.ab.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ab.entity.User;
import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
public class UserDao {

	@PersistenceContext
	private EntityManager em;
	
	public User findByName(final String username) {
		return (User)em.createQuery(
				"from User u where u.username = :username")
				.setParameter("username", username)
				.getSingleResult();
	}
}
