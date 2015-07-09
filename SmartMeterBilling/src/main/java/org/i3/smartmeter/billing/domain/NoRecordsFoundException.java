package org.i3.smartmeter.billing.domain;

public class NoRecordsFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "No Records Found!!!!";
	}
}
