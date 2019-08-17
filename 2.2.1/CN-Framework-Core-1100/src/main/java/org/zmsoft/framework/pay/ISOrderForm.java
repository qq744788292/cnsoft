package org.zmsoft.framework.pay;

import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.common.RESTResultBean;

/**
 * 用户下单拦截器
 * 
 * @author Administrator
 *
 */
public interface ISOrderForm {

	/**
	 * 创建订单动作
	 * 
	 * @param order
	 * @return
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
