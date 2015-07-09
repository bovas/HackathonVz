package org.i3.smartmeter.billing.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.i3.smartmeter.billing.domain.UsageLineDO;

public interface UsageInfoDao {
	void save(UsageLineDO usageLineDO);
	void update(UsageLineDO usageLineDO);
	void delete(UsageLineDO usageLineDO);
	List<?> findAllUsageLineDO();
	List<?> getUsageInformation(Long smartMeterID, Date fromDate, Date toDate) throws ParseException;
	List<?> getUsageInformation(Long smartMeterID) throws ParseException;
}
