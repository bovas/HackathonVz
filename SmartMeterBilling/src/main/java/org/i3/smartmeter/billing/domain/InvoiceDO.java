package org.i3.smartmeter.billing.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;


public class InvoiceDO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	private PricingDO priceDO;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("toDate")
	private Date toDate;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PricingDO getPriceDO() {
		return priceDO;
	}

	public void setPriceDO(PricingDO priceDO) {
		this.priceDO = priceDO;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "InvoiceDO [customer=" + customer + ", priceDO=" + priceDO
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
}
