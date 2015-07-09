package org.i3.smartmeter.billing.controller.response;

import java.io.Serializable;

public class ControllerResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	boolean isSuccess;
	String errorMessage, deepErrorMessage;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDeepErrorMessage() {
		return deepErrorMessage;
	}

	public void setDeepErrorMessage(String deepErrorMessage) {
		this.deepErrorMessage = deepErrorMessage;
	}
}
