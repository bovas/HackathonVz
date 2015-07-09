package org.i3.smartmeter.engine.pricing;

import org.i3.smartmeter.engine.rules.Rule;


public abstract class PricingRule implements Rule{

	protected double priceIncurred;

	public double getPriceIncurred() {
		return priceIncurred;
	}

	public void setPriceIncurred(double priceIncurred) {
		this.priceIncurred = priceIncurred;
	}

}
