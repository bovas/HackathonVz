package org.i3.smartmeter.billing.dao;

import java.util.List;

import org.i3.smartmeter.engine.pricing.PricingByRangeRule;

public interface RateDao {
	void save(PricingByRangeRule PricingByRangeRule);
	void update(PricingByRangeRule PricingByRangeRule);
	void delete(PricingByRangeRule PricingByRangeRule);
	List<?> findAllRules();
}
