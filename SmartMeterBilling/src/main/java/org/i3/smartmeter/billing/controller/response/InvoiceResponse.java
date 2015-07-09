package org.i3.smartmeter.billing.controller.response;

import org.i3.smartmeter.billing.domain.InvoiceDO;

public class InvoiceResponse extends ControllerResponse {

	private static final long serialVersionUID = 1L;

	InvoiceDO invoiceDO;

	public InvoiceDO getInvoiceDO() {
		return invoiceDO;
	}

	public void setInvoiceDO(InvoiceDO invoiceDO) {
		this.invoiceDO = invoiceDO;
	}
}
