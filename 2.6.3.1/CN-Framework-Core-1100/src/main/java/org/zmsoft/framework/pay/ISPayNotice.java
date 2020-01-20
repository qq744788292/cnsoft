package org.zmsoft.framework.pay;

import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.common.RESTResultBean;

/**
 * 到账通知拦截器(支付成功后续动作)<br>
 * 框架处理持久化逻辑
 * @see <ISPayOrderPersistentService>
 *
 */
public interface ISPayNotice {

	/**
	 * 渠道通知
	 * @throws Exception 
	 */
	public RESTResultBean<PayOrderBean> channelNoticeProcess(PayOrderBean order) throws Exception;

	/**
	 * 通知成功
	 */
	public RESTResultBean<PayOrderBean> noticeAfterProcess(PayOrderBean order) throws Exception;
}
