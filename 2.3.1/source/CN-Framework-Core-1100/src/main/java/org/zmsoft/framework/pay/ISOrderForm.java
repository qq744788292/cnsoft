package org.zmsoft.framework.pay;

import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.common.RESTResultBean;

/**
 * 用户下单拦截器
 * 
 * @author Administrator
 * @see <ISPayOrderPersistentService>
 *
 */
public interface ISOrderForm {
	/**
	 * 统一下单
	 */
	public RESTResultBean<PayOrderBean> creatOrderProcess(PayOrderBean order) throws Exception;

	/**
	 * 成功支付后续动作（验证是否支付成功）
	 * 
	 * @param order
	 * @return
	 */
	public RESTResultBean<PayOrderBean> payAfterProcess(PayOrderBean order) throws Exception;
}
