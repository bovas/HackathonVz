package org.i3.smartmeter.billing.controller.response;

import java.util.List;

import org.i3.smartmeter.billing.domain.UsageLineDO;

public class UsageInfoResponse extends ControllerResponse {

	private static final long serialVersionUID = 1L;

	List<UsageLineDO> usageLineDOs;

	public List<UsageLineDO> getUsageLineDOs() {
		return usageLineDOs;
	}

	public void setUsageLineDOs(List<UsageLineDO> usageLineDOs) {
		this.usageLineDOs = usageLineDOs;
	}
}
