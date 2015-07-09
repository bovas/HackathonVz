package org.i3.smartmeter.engine.tax;

import org.i3.smartmeter.engine.pricing.PricingRule;

public class TaxPricingRule extends PricingRule {

	public final static double TAX_PERCENT = 10D/100D;
	
	private double applicableAmount, taxAmount;
	
	public TaxPricingRule() {
		// TODO Auto-generated constructor stub
	}
	
	public TaxPricingRule(double applicableAmount) {
		// TODO Auto-generated constructor stub
		this.applicableAmount = applicableAmount;
	}
	
	public boolean isSatisfied() {
		// TODO Auto-generated method stub
		taxAmount = applicableAmount * TAX_PERCENT;
		priceIncurred = taxAmount;
		return true;
	}

	public double getApplicableAmount() {
		return applicableAmount;
	}

	public void setApplicableAmount(double applicableAmount) {
		this.applicableAmount = applicableAmount;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

}
