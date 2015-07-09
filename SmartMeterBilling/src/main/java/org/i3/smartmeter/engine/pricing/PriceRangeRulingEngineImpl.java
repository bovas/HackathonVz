package org.i3.smartmeter.engine.pricing;

import java.util.LinkedList;

import org.i3.smartmeter.engine.rules.Rule;

public class PriceRangeRulingEngineImpl extends PriceRulingEngineImpl {

	protected double rangeValue;
	
	public PriceRangeRulingEngineImpl(double rangeValue) {
		// TODO Auto-generated constructor stub
		this.rangeValue = rangeValue;
	}
	
	@Override
	public void executeRules() {
		// TODO Auto-generated method stub
		applicableRules = new LinkedList<Rule>();
		for(Rule rule : getRules()){
			PricingByRangeRule pricingRangeRule = (PricingByRangeRule) rule;
			if(pricingRangeRule.isSatisfied(rangeValue)){
				applicableRules.add(rule);
			}
		}
	}

	public double getRangeValue() {
		return rangeValue;
	}

	public void setRangeValue(double rangeValue) {
		this.rangeValue = rangeValue;
	}

}
