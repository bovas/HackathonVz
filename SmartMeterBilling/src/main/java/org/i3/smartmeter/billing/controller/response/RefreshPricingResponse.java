package org.i3.smartmeter.billing.controller.response;

import java.util.List;

import org.i3.smartmeter.engine.pricing.PricingByRangeRule;

public class RefreshPricingResponse extends ControllerResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<PricingByRangeRule> rules;

	public List<PricingByRangeRule> getRules() {
		return rules;
	}

	public void setRules(List<PricingByRangeRule> rules) {
		this.rules = rules;
	}

}
