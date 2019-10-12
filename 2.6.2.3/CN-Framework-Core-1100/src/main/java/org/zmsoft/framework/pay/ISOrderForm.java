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
	 * 支付路由，统一下单<br>
	 * payType:支付方式<br>
	 * 10:支付宝支付，11:支付宝h5支付<br>
	 * 20:微信付款码支付，21:微网页JSAPI支付，23:微信扫一扫，24:APP发起微信支付，25:H5支付，26:小程序支付
	 */
	public RESTResultBean<PayOrderBean> creatOrderProcess(PayOrderBean order) throws Exception;

	/**
	 * 成功支付后续动作（验证是否支付成功）
	 * 
	 * @param order
	 * @return
	 */
	public RESTResultBean<PayOrderBean> orderPayAfterProcess(PayOrderBean order) throws Exception;
}
