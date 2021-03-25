package org.zmsoft.order.pay;

import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.common.ISOrder;
import org.zmsoft.order.beans.UserOrderRechargeBean;

/**
 * 到账通知拦截器(支付成功后续动作)<br>
 * 框架处理持久化逻辑
 * 
 * @see <ISPayOrderPersistentService>
 *
 */
public interface ISPayNoticeProcessSupport extends ISOrder {

	/**
	 * 渠道通知
	 * 
	 * @throws Exception
	 */
	public RESTResultBean<UserOrderRechargeBean> channelNoticeProcess(UserOrderRechargeBean order) throws Exception;

	/**
	 * 通知成功
	 */
	public RESTResultBean<UserOrderRechargeBean> noticeAfterProcess(UserOrderRechargeBean order) throws Exception;
}
