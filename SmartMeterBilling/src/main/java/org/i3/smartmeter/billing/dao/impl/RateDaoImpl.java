package org.i3.smartmeter.billing.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.i3.smartmeter.billing.dao.RateDao;
import org.i3.smartmeter.engine.pricing.PricingByRangeRule;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class RateDaoImpl extends HibernateDaoSupport implements RateDao{

	public RateDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	public RateDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Transactional(readOnly = false)
	public void save(PricingByRangeRule rule) {
		getHibernateTemplate().save(rule);
	}

	@Transactional(readOnly = false)
	public void update(PricingByRangeRule rule) {
		getHibernateTemplate().update(rule);
	}

	@Transactional(readOnly = false)
	public void delete(PricingByRangeRule rule) {
		getHibernateTemplate().delete(rule);
	}
	
	@Transactional(readOnly = true)
	public List<?> findAllRules() {
		List rulesList = getHibernateTemplate().find("from PricingByRangeRule");
		return rulesList;
	}
}
