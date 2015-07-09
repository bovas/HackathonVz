package org.i3.smartmeter.engine.calculation;

import java.util.LinkedList;
import java.util.List;

import org.i3.smartmeter.billing.pricingengine.ExtentedPriceRangeRulingEngineImpl;
import org.i3.smartmeter.engine.pricing.PriceRulingEngineImpl;
import org.i3.smartmeter.engine.pricing.PricingRule;
import org.i3.smartmeter.engine.rules.Rule;
import org.i3.smartmeter.engine.rules.RulingEngine;
import org.i3.smartmeter.engine.rules.RulingEngineFactory;
import org.i3.smartmeter.engine.tax.TaxPricingRule;

public class CalculationFramework {

	private List<PricingRule> rules = new LinkedList<PricingRule>();
	private List<PricingRule> taxRules = new LinkedList<PricingRule>();
	private List<Rule> applicableRules;
	
	private double unitsPrice, taxAmount, totalPrice, totalUnits;
	
	public static CalculationFramework getInstance(){
		return new CalculationFramework();
	}
	
	public void doCalculation(){
		
		applicableRules = applyPricingRules(rules, new ExtentedPriceRangeRulingEngineImpl(totalUnits));		
		unitsPrice = getPriceIncurred(applicableRules);
		
		for(Rule rule : taxRules){
			TaxPricingRule taxRule = (TaxPricingRule) (rule);
			taxRule.setApplicableAmount(unitsPrice);
		}
		
		List<Rule> applicableTaxRules = applyPricingRules(taxRules, new PriceRulingEngineImpl());
		taxAmount = getPriceIncurred(applicableTaxRules);
		
		totalPrice = unitsPrice + taxAmount;
	}
	
	protected static double getPriceIncurred(List<Rule> rules){
		double totalPrice = 0;
		for(Rule rule : rules){
			PricingRule pricingRule = (PricingRule) (rule);
			totalPrice += pricingRule.getPriceIncurred();
		}
		return totalPrice;
	}
	
	protected static List<Rule> applyPricingRules(List<PricingRule> rules, RulingEngine rulingEngine){
		List<Rule> freshRules = new LinkedList<Rule>();
		freshRules.addAll(rules);		
		List<Rule> applicableRules = applyRules(freshRules, rulingEngine);
		return applicableRules;
	}
	
	protected static List<Rule> applyRules(List<Rule> rules, RulingEngine rulingEngine){
		RulingEngineFactory factory = RulingEngineFactory.getInstance();
		factory.setRules(rules);
		factory.setRulingEngine(rulingEngine);
		List<Rule> applicableRules = factory.performRuling();
		return applicableRules;
	}
	
	public void addRule(PricingRule rule){
		this.rules.add(rule);
	}
	
	public void addRules(List<PricingRule> rules){
		this.rules.addAll(rules);
	}
	
	public void addTaxRule(PricingRule rule){
		this.taxRules.add(rule);
	}
	
	public void addTaxRules(List<PricingRule> rules){
		this.taxRules.addAll(rules);
	}

	public double getUnitsPrice() {
		return unitsPrice;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public double getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(double totalUnits) {
		this.totalUnits = totalUnits;
	}

	public List<Rule> getApplicableRules() {
		return applicableRules;
	}
	
}
