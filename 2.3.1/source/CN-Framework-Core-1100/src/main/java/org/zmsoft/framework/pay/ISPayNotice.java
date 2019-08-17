package org.zmsoft.framework.pay;

import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.common.RESTResultBean;

/**
 * 到账通知拦截器
 * 
 * @author Administrator
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
