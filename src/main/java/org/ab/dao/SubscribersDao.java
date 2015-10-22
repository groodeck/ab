package org.ab.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ab.entity.Subscriber;
import org.ab.model.dictionary.ContractStatus;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class SubscribersDao {

	@PersistenceContext
	private EntityManager em;

	public List<Subscriber> findAll() {
		return em.createQuery("from Subscriber").getResultList();
	}

	public List<Subscriber> findSubscribers(final String phrase, final LocalDate dateFrom, final LocalDate dateTo,
			final Boolean showInactive) {
		final StringBuilder sb = new StringBuilder("select object(s) from Subscriber s, Contract c ");
		final Query query;
		if(StringUtils.isNotBlank(phrase)){
			sb.append("where "
					+ "c.subscriber = s "
					+ "and (lower(s.name) like lower(:phrase) "
					+ "or lower(s.surname) like lower(:phrase) "
					+ "or lower(s.companyName) like lower(:phrase) "
					+ "or lower(s.subscriberIdn) like lower(:phrase) "
					+ "or lower(s.clientIdNumber) like lower(:phrase) "
					+ "or s.pesel like :phrase "
					+ "or s.regon like :phrase "
					+ "or s.nip like :phrase) ");
			sb.append("and c.contractSignDate between :dateFrom and :dateTo ");
			sb.append("and c.contractStatus in (:allowedStatuses) ");
			sb.append("ORDER BY s.subscriberId ");
			query = em.createQuery(sb.toString())
					.setParameter("phrase", "%"+phrase+"%");
		} else {
			sb.append("where c.subscriber = s "
					+ "and c.contractSignDate between :dateFrom and :dateTo ");
			sb.append("and c.contractStatus in (:allowedStatuses) ");
			sb.append("ORDER BY s.subscriberId ");
			query = em.createQuery(sb.toString());
		}
		return query.setParameter("dateFrom", dateFrom)
				.setParameter("dateTo", dateTo)
				.setParameter("allowedStatuses", Lists.newArrayList(getAllowedStatuses(showInactive)))
				.getResultList();
	}

	private Collection<ContractStatus> getAllowedStatuses(final Boolean showInactive) {
		if(showInactive == null || !showInactive.booleanValue()){
			return ContractStatus.activeSubscriberStatuses();
		} else {
			return Lists.newArrayList(ContractStatus.values());
		}
	}

}
