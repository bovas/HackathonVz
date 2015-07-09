package org.i3.smartmeter.billing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "customer", catalog = "smartmeterbilling", uniqueConstraints = {
		@UniqueConstraint(columnNames = "sm_identifer")})
public class Customer {
	
	private String customerFirstName;
	private String customerLastName;
	
	@Id
	private Long smartMeterId;
	private String addressLine1;
	private String addressLine2;
	private Long zipCode;

	public Customer(Long smartMeterId){
		this.smartMeterId = smartMeterId;
	}
	public Customer(String customerFirstName, String customerLastName,
			Long smartMeterId, String addressLine1, Long zipCode) {
		super();
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.smartMeterId = smartMeterId;
		this.addressLine1 = addressLine1;
		this.zipCode = zipCode;
	}
	
	
	public Customer() {
		
	}
	
	@Column(name = "customer_fname", nullable = false, length = 255)
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	
	@Column(name = "customer_lname", nullable = false, length = 255)
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	
	@Id
	@Column(name = "sm_identifer", unique = true, nullable = false, length = 8)
	public Long getSmartMeterId() {
		return smartMeterId;
	}
	public void setSmartMeterId(Long smartMeterId) {
		this.smartMeterId = smartMeterId;
	}
	
	@Column(name = "customer_address1", nullable = false, length = 512)
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	@Column(name = "customer_address2", length = 512)
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	@Column(name = "zip_code", length = 6)
	public Long getZipCode() {
		return zipCode;
	}
	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
