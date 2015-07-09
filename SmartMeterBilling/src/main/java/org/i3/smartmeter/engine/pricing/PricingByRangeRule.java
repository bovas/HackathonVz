package org.i3.smartmeter.engine.pricing;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="RATE",uniqueConstraints={@UniqueConstraint(columnNames="RATE_ID")})
public class PricingByRangeRule extends PricingRule implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final double INFINITE = -1;

	@Id
	@Column(name="RATE_ID")
	int ruleId;
	
	@Column(name="MIN_VAL")
	double minValue;
	
	@Column(name="MAX_VAL")
	double maxValue;
	
	@Column(name="PRICE")
	double price;
	
	@Column(name="OFFSET")
	double offset;
	
	public PricingByRangeRule() {
		// TODO Auto-generated constructor stub
	}
	
	public PricingByRangeRule(double minValue, double maxValue, double price, double offset) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.price = price;
		this.offset = offset;
	}

	public boolean isSatisfied() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isSatisfied(double rangeValue) {
		// TODO Auto-generated method stub
		if (rangeValue >= maxValue && maxValue != INFINITE) {
			return true;
		} else if ((minValue <= rangeValue && rangeValue <= maxValue)) {
			return true;
		}else if(minValue <= rangeValue && maxValue == INFINITE){
			return true;
		}
		return false;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}

}
