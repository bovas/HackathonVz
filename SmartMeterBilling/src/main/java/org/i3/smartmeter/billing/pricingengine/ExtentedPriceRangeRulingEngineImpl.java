package org.i3.smartmeter.billing.pricingengine;

import org.i3.smartmeter.engine.pricing.PriceRangeRulingEngineImpl;
import org.i3.smartmeter.engine.pricing.PricingByRangeRule;
import org.i3.smartmeter.engine.rules.Rule;

public class ExtentedPriceRangeRulingEngineImpl extends PriceRangeRulingEngineImpl {

	public ExtentedPriceRangeRulingEngineImpl(double rangeValue) {
		super(rangeValue);
	}

	@Override
	public void executeRules() {
		// TODO Auto-generated method stub
		super.executeRules();
		for(Rule rule : getApplicableRules()){
			PricingByRangeRule priceRule = (PricingByRangeRule) rule;
			double maxValue = priceRule.getMaxValue();
			double minValue = priceRule.getMinValue();
			double offset = priceRule.getOffset();
			
			double units = 0, totalUnits = rangeValue;
			if (totalUnits >= maxValue && maxValue != PricingByRangeRule.INFINITE) {
				units = maxValue - minValue + offset;
			} else if ((minValue <= totalUnits && totalUnits <= maxValue) || maxValue == PricingByRangeRule.INFINITE) {
				units = totalUnits - minValue + offset;
			}
			priceRule.setPriceIncurred(units * priceRule.getPrice());
		}
	}

}
