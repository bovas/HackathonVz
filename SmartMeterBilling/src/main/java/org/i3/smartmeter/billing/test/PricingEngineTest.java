package org.i3.smartmeter.billing.test;

import java.util.LinkedList;
import java.util.List;

import org.i3.smartmeter.engine.calculation.CalculationFramework;
import org.i3.smartmeter.engine.pricing.PricingByRangeRule;
import org.i3.smartmeter.engine.pricing.PricingRule;
import org.i3.smartmeter.engine.tax.TaxPricingRule;

public class PricingEngineTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<PricingRule> rules = new LinkedList<PricingRule>();
		// TODO - Query from the DB to get the RUles
		rules.add(new PricingByRangeRule(0, 300, 1.25, 0));
		rules.add(new PricingByRangeRule(301, 600, 1.75, 1));
		rules.add(new PricingByRangeRule(601, 1000, 2.25, 1));
		rules.add(new PricingByRangeRule(1001, -1, 2.75, 1));
		
		List<PricingRule> taxRules = new LinkedList<PricingRule>();
		// TODO - Query from the DB to get the RUles
		taxRules.add(new TaxPricingRule());
		
		CalculationFramework cal = CalculationFramework.getInstance();
		cal.setTotalUnits(1458);
		
		cal.addRules(rules);
		cal.addTaxRules(taxRules);
		cal.doCalculation();
		
		System.out.println(cal.getTotalUnits());
		System.out.println(cal.getUnitsPrice());
		System.out.println(cal.getTaxAmount());
		System.out.println(cal.getTotalPrice());
	}

}
