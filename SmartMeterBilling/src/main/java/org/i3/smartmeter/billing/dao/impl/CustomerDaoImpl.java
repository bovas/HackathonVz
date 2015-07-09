package org.i3.smartmeter.billing.dao.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.i3.smartmeter.billing.dao.CustomerDao;
import org.i3.smartmeter.billing.dao.UsageInfoDao;
import org.i3.smartmeter.billing.db.DataBaseConnUtil;
import org.i3.smartmeter.billing.domain.Customer;
import org.i3.smartmeter.billing.domain.UsageLineDO;

//@Component
public class CustomerDaoImpl implements CustomerDao{
	
	DataBaseConnUtil dbconnUtil;
	
	public void save(Customer customer) {
		dbconnUtil = new DataBaseConnUtil();		
		String sqlQuery = "insert into customer(customer_fname,customer_lname,sm_identifer,customer_address1,zip_code)";
		
		sqlQuery+=" values('"+ customer.getCustomerFirstName() +"',"
				+ "'"+ customer.getCustomerLastName() +"',"
				+ customer.getSmartMeterId() +","
				+ "'"+ customer.getAddressLine1() +"',"
				+  customer.getZipCode()
				+ ")";
		System.out.println("SQL Query:: " + sqlQuery);
		executeQuery(sqlQuery);
	}
	public boolean executeQuery(String sqlQuery){
		try {
			dbconnUtil.execute(sqlQuery);
			dbconnUtil.closeConnection();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public Customer findBySmartMeterId(long smartMeterId){
		String sqlQuery = "select customer_fname,customer_lname,sm_identifer,customer_address1,zip_code from customer"
				+"where sm_identifer=" + smartMeterId;
		try {
			dbconnUtil.execute(sqlQuery);
			dbconnUtil.closeConnection();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Deprecated
	public List<Customer> getAllCustomers() {
		String sqlQuery = "select customer_fname,customer_lname,sm_identifer,customer_address1,zip_code from customer";
		List<Customer> customerList =null;
		try {
			dbconnUtil = new DataBaseConnUtil();
			dbconnUtil.setAutoCommit(true);
			List<Map<String,String>> customerResult = dbconnUtil.executeSQLQuery(sqlQuery);
			java.util.Iterator<Map<String, String>> itr =  customerResult.iterator();
			customerList = new Vector<Customer>();
			while(itr.hasNext()){
				Map<String,String> map = itr.next();
				Customer customer = new Customer();
				customer.setCustomerFirstName(map.get("customer_fname"));
				customer.setCustomerLastName(map.get("customer_lname"));
				customer.setSmartMeterId(Long.getLong(map.get("sm_identifier")));
				customer.setAddressLine1(map.get("customer_address1"));
				customer.setZipCode(Long.getLong(map.get("zip_code")));
				customerList.add(customer);
			}
			dbconnUtil.closeConnection();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	public void delete(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	public Customer findBySmartMeterId(Long smartMeterId) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<UsageLineDO> getUsageInformation(Long smartMeterID, Date fromDate, Date toDate) throws ParseException {
		// TODO Auto-generated method stub
		List<UsageLineDO> usageLineDOs = new LinkedList<UsageLineDO>();
		
		// TODO - Query the DB and pull the values
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		usageLineDOs.add(new UsageLineDO("12345678,06/30/15 5:00 AM,50012,06/30/15 5:15 AM,50023"));
		
		
		return usageLineDOs;
	}
	
	public UsageLineDO getTotalUnitsUsage(Long smartMeterID, Date fromDate,
			Date toDate) throws ParseException {
		// TODO Query the DB to get the information
		List<UsageLineDO> usageLineDOs = getUsageInformation(null, null, null);
		
		UsageLineDO response = new UsageLineDO();
		
		UsageLineDO firstRecord = usageLineDOs.get(0);
		UsageLineDO lastRecord = usageLineDOs.get(usageLineDOs.size() - 1);
		
		response.setSmartMeterID(firstRecord.getSmartMeterID());
		response.setReadingStart(firstRecord.getReadingStart());
		response.setReadingEnd(lastRecord.getReadingEnd());
		response.setStartTime(firstRecord.getStartTime());
		response.setEndTime(lastRecord.getEndTime());
	
		return response;
	}
	public void setUsageDao(UsageInfoDao usageInfoDao) {
		// TODO Auto-generated method stub
		
	}
}
