package org.zmsoft.pay;

import org.zmsoft.framework.constants.ICPayDataConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.pay.cache.PayOrderCacheService;

/**
 * 用户下单
 * 
 * @author tasoft.com
 * @version 1.2.1 2017/06/13
 * @since 1.2.1 2017/06/13
 */
public class MyOrderSupport extends MyServiceSupport implements ICPayDataConstants, ICSystemPayConstants {
	private AUserOrderPayService userOrderPayService;

	public AUserOrderPayService getUserOrderPayService() {
		if (EmptyHelper.isEmpty(userOrderPayService)) {
			userOrderPayService = MyBeanFactoryHelper.getBean(AUserOrderPayService.class.getSimpleName());
		}
		return userOrderPayService;
	}

	private PayOrderCacheService myPayOrderCacheService;

	public PayOrderCacheService getMyPayOrderCacheService() {
		if (EmptyHelper.isEmpty(myPayOrderCacheService)) {
			myPayOrderCacheService = MyBeanFactoryHelper.getBean(PayOrderCacheService.class.getSimpleName());
		}
		return myPayOrderCacheService;
	}

}