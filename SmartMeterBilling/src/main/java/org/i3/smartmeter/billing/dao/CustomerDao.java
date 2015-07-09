package org.i3.smartmeter.billing.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.i3.smartmeter.billing.domain.Customer;
import org.i3.smartmeter.billing.domain.NoRecordsFoundException;
import org.i3.smartmeter.billing.domain.UsageLineDO;

public interface CustomerDao {
	void save(Customer customer);
	void update(Customer customer);
	void delete(Customer customer);
	List<UsageLineDO> getUsageInformation(Long smartMeterID, Date fromDate, Date toDate) throws ParseException;
	Customer findBySmartMeterId(Long smartMeterId);
	UsageLineDO getTotalUnitsUsage(Long smartMeterID, Date fromDate, Date toDate) throws ParseException, NoRecordsFoundException;
	void setUsageDao(UsageInfoDao usageInfoDao);
}
