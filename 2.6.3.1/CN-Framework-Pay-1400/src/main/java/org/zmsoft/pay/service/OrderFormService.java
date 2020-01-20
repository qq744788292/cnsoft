package org.zmsoft.pay.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.PKHelper;
import org.zmsoft.pay.support.MyOrderSupport;
import org.zmsoft.pay3.ali.AliCreatPayOrderService;
import org.zmsoft.pay3.wx.WeixinCreatPayOrderService;

/**
 * 用户下单（业务实现）
 * 
 * @author tasoft.com
 * @version 1.2.1 2017/06/13
 * @since 1.2.1 2017/06/13
 */
@Service("OrderFormService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderFormService extends MyOrderSupport {

	/**
	 * 用户下单（服务入口）
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public RESTResultBean<PayOrderBean> creatOrder(PayOrderBean order) throws Exception {
		RESTResultBean<PayOrderBean> result = new RESTResultBean<PayOrderBean>();
		UserBean user = SessionHelper.currentUser();
		// 创建订单
		{
			// 创建主键
			order.preparePrimarykey();
			order.setUserId(user.getUserId());
			order.setUserNickname(user.getUserNameCN());
			// 商户订单号
			if (EmptyHelper.isEmpty(order.getPayTradeNo()))
				order.setPayTradeNo(PKHelper.creatUUKey());// UUID
			// 支付状态
			order.setPayState(TWO);
			// 到账状态
			order.setOrderState(TWO);
			// 到账类别
			order.setOrderType(ONE);
			// 第三方支付路由：统一下单
			result = creatOrderProcess(order);
			// 持久化订单
			myPayingOrderCacheSupport.saveOrder(order, true);
			result.setMsg("下单成功！");
		}
		return result;
	}
	

	@Resource
	private AliCreatPayOrderService myAliCreatPayOrderService;
	@Resource
	private WeixinCreatPayOrderService myWeixinCreatPayOrderService;

	/**
	 * 支付路由，统一下单<br>
	 * payType:支付方式<br>
	 * 10:支付宝支付，11:支付宝h5支付<br>
	 * 20:微信付款码支付，21:微网页JSAPI支付，23:微信扫一扫，24:APP发起微信支付，25:H5支付，26:小程序支付
	 */
	public RESTResultBean<PayOrderBean> creatOrderProcess(PayOrderBean order) throws Exception {
		// 支付方式
		// 11:支付宝支付，12:支付宝h5支付
		// 交易类型，JSAPI、NATIVE、APP
		// 21:JSAPI--JSAPI支付（或小程序支付）、22：NATIVE--Native支付、23：APP--app支付，24：MWEB--H5支付（网页），25：MICROPAY--付款码支付

		// 交易类型（JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里）
		if ("11".equals(order.getPayType())) {

		} else if ("12".equals(order.getPayType())) {

		} else if ("22".equals(order.getPayType())) {

		} else if ("24".equals(order.getPayType())) {

		} else if ("25".equals(order.getPayType())) {

		} else if ("26".equals(order.getPayType())) {

		} else {

		}
		
		// 支付宝
		if (order.getPayType().startsWith(ONE)) {
			return myAliCreatPayOrderService.createAliPayOrder(order);
		} else
		// 微信
		if (order.getPayType().startsWith(TWO)) {
			return myWeixinCreatPayOrderService.createWeinxinOrder(order);
		} else {
			throw new Exception("支付类别不正确！！！");
		}
	}

}