package org.ab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ab.entity.Subscriber;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SubscribersDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Subscriber> findSubscribers(final String phrase, final LocalDate dateFrom, final LocalDate dateTo) {
		final StringBuilder sb = new StringBuilder("select object(s) from Subscriber s, Contract c ");
		final Query query;
		if(StringUtils.isNotBlank(phrase)){
			sb.append("where "
					+ "c.subscriber = s "
					+ "and (s.name like '%:phrase%' "
					+ "or s.surname like '%:phrase%' "
					+ "or s.companyName like '%:phrase%' "
					+ "or s.subscriberIdn like '%:phrase%' "
					+ "or s.clientIdNumber like '%:phrase%' "
					+ "or s.pesel like '%:phrase%' "
					+ "or s.regon like '%:phrase%' "
					+ "or s.nip like '%:phrase%') ");
			sb.append("and c.contractSignDate between :dateFrom and :dateTo ");
			query = em.createQuery(sb.toString())
				.setParameter("phrase", phrase);
		} else {
			sb.append("where c.subscriber = s "
					+ "and c.contractSignDate between :dateFrom and :dateTo ");
			query = em.createQuery(sb.toString());
		}
		return query.setParameter("dateFrom", dateFrom)
				.setParameter("dateTo", dateTo)
				.getResultList();
	}
	
}
