package org.i3.smartmeter.billing.dao.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.i3.smartmeter.billing.dao.UsageInfoDao;
import org.i3.smartmeter.billing.domain.UsageLineDO;
import org.i3.smartmeter.billing.test.DateFormatter;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class UsageInfoDaoImpl extends HibernateDaoSupport implements UsageInfoDao{

	public UsageInfoDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	public UsageInfoDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Transactional(readOnly = false)
	public void save(UsageLineDO usageLineDO) {
		getHibernateTemplate().save(usageLineDO);
	}

	@Transactional(readOnly = false)
	public void update(UsageLineDO usageLineDO) {
		getHibernateTemplate().update(usageLineDO);
	}

	@Transactional(readOnly = false)
	public void delete(UsageLineDO usageLineDO) {
		getHibernateTemplate().delete(usageLineDO);
	}
	
	@Transactional(readOnly = true)
	public List findAllUsageLineDO() {
		getHibernateTemplate().setMaxResults(10000);
		List usageLineList = getHibernateTemplate().find("from UsageLineDO");
		return usageLineList;
	}
	
	@Transactional(readOnly = true)
	public List<?> getUsageInformation(Long smartMeterID, Date fromDate,
			Date toDate) throws ParseException {
		HibernateTemplate ht = new HibernateTemplate(getHibernateTemplate().getSessionFactory());
		ht.setMaxResults(1); 
		String frmDate = DateFormatter.getSQLDate(fromDate);
		String tDate = DateFormatter.getSQLDate(toDate);
		List<?> usageLineList = ht.find(
				"from UsageLineDO lineDO where smartMeterID="+smartMeterID+" and "
			    + "startTime >= '"+frmDate +"' order by lineDO.startTime asc");
		List<?> usageLineList1 = ht.find(
				"from UsageLineDO lineDO1 where smartMeterID="+smartMeterID+" and "
				+ "endTime <= '"+tDate +"' order by lineDO1.endTime desc"
				);
		List<UsageLineDO> orgUsageLineList = new LinkedList<UsageLineDO>();
		findAndGetUsageLineDO(orgUsageLineList,usageLineList); 
		findAndGetUsageLineDO(orgUsageLineList,usageLineList1);
		return orgUsageLineList;
	}
	@SuppressWarnings("unused")
	private void findAndGetUsageLineDO(List<UsageLineDO> orgUsageLineList,List<?> usageLineList1){
		if(usageLineList1!=null && usageLineList1.size() > 0){
			if(usageLineList1.get(0) instanceof UsageLineDO){
				UsageLineDO usageLineDo1 = (UsageLineDO) usageLineList1.get(0);
				orgUsageLineList.add(usageLineDo1);
			}
		}
	}
	public List<?> getUsageInformation(Long smartMeterID) throws ParseException {
		List<?> usageLineList = getHibernateTemplate().find(
				"UsageLineDO lineDO where smartMeterID="+smartMeterID);		
		return usageLineList;
	}
	
}
