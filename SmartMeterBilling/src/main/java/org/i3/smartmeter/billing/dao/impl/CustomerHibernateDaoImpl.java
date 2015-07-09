package org.i3.smartmeter.billing.dao.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.i3.smartmeter.billing.dao.CustomerDao;
import org.i3.smartmeter.billing.dao.UsageInfoDao;
import org.i3.smartmeter.billing.domain.Customer;
import org.i3.smartmeter.billing.domain.NoRecordsFoundException;
import org.i3.smartmeter.billing.domain.UsageLineDO;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class CustomerHibernateDaoImpl extends HibernateDaoSupport implements CustomerDao{

	private UsageInfoDao usageDao;
	public CustomerHibernateDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public CustomerHibernateDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	@Transactional(readOnly = false)
	public void save(Customer customer) {
		getHibernateTemplate().save(customer);		
	}

	@Transactional(readOnly = false)
	public void update(Customer customer) {
		getHibernateTemplate().update(customer);
	}

	@Transactional(readOnly = false)
	public void delete(Customer customer) {
		getHibernateTemplate().delete(customer);	
	}
	
	@Transactional(readOnly = true)
	public Customer findBySmartMeterId(Long smartMeterId) {
		List list = getHibernateTemplate().find("from Customer where smartMeterId=" + smartMeterId);
		if(list!=null && list.size()>0){
			return  (Customer) list.get(0);
		}
		return new Customer();
	}

	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UsageLineDO> getUsageInformation(Long smartMeterID,
			Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public UsageLineDO getTotalUnitsUsage(Long smartMeterID, Date fromDate,
			Date toDate) throws ParseException, NoRecordsFoundException {
		// TODO Query DB and get the UsageLineDO
	
		List<UsageLineDO> usageLineDOs = (List<UsageLineDO>) usageDao.getUsageInformation(smartMeterID, fromDate, toDate);
		//List<UsageLineDO> usageLineDOs = new CustomerDaoImpl().getUsageInformation(smartMeterID, fromDate, toDate);
		if(usageLineDOs==null || usageLineDOs.size() < 2){
			throw new NoRecordsFoundException();
		}
		UsageLineDO response = new UsageLineDO();
		
		UsageLineDO firstRecord = usageLineDOs.get(0);
		UsageLineDO lastRecord = usageLineDOs.get(usageLineDOs.size()-1);
		
		response.setSmartMeterID(firstRecord.getSmartMeterID());
		response.setReadingStart(firstRecord.getReadingStart());
		response.setReadingEnd(lastRecord.getReadingEnd());
		response.setStartTime(firstRecord.getStartTime());
		response.setEndTime(lastRecord.getEndTime());
		
		return response;
	}

	public void setUsageDao(UsageInfoDao usageInfoDao) {
		this.usageDao = usageInfoDao;
	}

}
