package org.zmsoft.pay.service;

import javax.annotation.Resource;

import org.zmsoft.config.order.OrderConfigService;
import org.zmsoft.framework.constants.ICPayDataConstants;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 支付框架基类
 */
public class MyOrderServiceSupport extends MyServiceSupport implements ICPayDataConstants, ISOrderService {

	@Resource
	OrderConfigService myOrderConfig;

	public boolean isLoadDB() {
		if (ONE.equals(myOrderConfig.getLoadDB()))
			return true;
		return false;
	}

	public int configOrderTimeMinute() {
		if (EmptyHelper.isNotEmpty(myOrderConfig.getOrderTimeMinute()))
			this.setWaitTimeMinute(Integer.parseInt(myOrderConfig.getOrderTimeMinute()));
		return this.waitTimeSecond;
	}

}