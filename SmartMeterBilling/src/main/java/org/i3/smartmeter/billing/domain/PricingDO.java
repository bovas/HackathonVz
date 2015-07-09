package org.i3.smartmeter.billing.domain;

import java.io.Serializable;
import java.util.List;

import org.i3.smartmeter.engine.rules.Rule;

public class PricingDO implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Rule> rules;
	
	public PricingDO() {
		// TODO Auto-generated constructor stub
	}

	private double totalPrice, totalUnits, taxAmount, unitsPrice;

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(double totalUnits) {
		this.totalUnits = totalUnits;
	}

	@Override
	public String toString() {
		return "PricingDO [rules=" + rules + ", totalPrice=" + totalPrice
				+ ", totalUnits=" + totalUnits + ", taxAmount=" + taxAmount
				+ ", unitsPrice=" + unitsPrice + "]";
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getUnitsPrice() {
		return unitsPrice;
	}

	public void setUnitsPrice(double unitsPrice) {
		this.unitsPrice = unitsPrice;
	}
}
