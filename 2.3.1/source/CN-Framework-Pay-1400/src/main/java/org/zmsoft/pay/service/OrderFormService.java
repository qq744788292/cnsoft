package org.zmsoft.pay.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.PKHelper;
import org.zmsoft.pay.MyOrderSupport;

/**
 * 用户下单
 * 
 * @author tasoft.com
 * @version 1.2.1 2017/06/13
 * @since 1.2.1 2017/06/13
 */
@Service("OrderFormService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderFormService extends MyOrderSupport {

	/**
	 * 用户下单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public RESTResultBean<PayOrderBean> creatOrderProcess(PayOrderBean order) throws Exception {
		RESTResultBean<PayOrderBean> result = new RESTResultBean<PayOrderBean>();
		UserBean user = SessionHelper.currentUser();
		// 创建订单
		{
			// 创建主键
			order.preparePrimarykey();
			order.setUserId(user.getUserId());
			order.setUserNickname(user.getUserNameCN());
			// 订单号
			if (EmptyHelper.isEmpty(order.getPayTradeNo()))
				order.setPayTradeNo(PKHelper.creatUUKey());// UUID
			// 支付状态
			order.setPayState(TWO);
			// 到账状态
			order.setOrderState(TWO);
			// 到账类别
			order.setOrderType(ONE);
			// 数据操作
			if (EmptyHelper.isEmpty(getMyPayOrderCacheService())) {
				result.setMsg("当前系统未实现支付业务！");
				result.setCode(1);
			} else if (EmptyHelper.isEmpty(getUserOrderPayService())) {
				result = new RESTResultBean<PayOrderBean>();
				result.setMsg("当前系统未实现支付业务！");
				result.setCode(1);
			} else{
				//统一下单
				getUserOrderPayService().creatOrderProcess(order);
				// 持久化订单
				getMyPayOrderCacheService().saveOrder(order, true);
				result.setMsg("下单成功！");
			}
		}
		return result;
	}

	/**
	 * 支付成功验证
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public RESTResultBean<PayOrderBean> payAfterProcess(PayOrderBean order) throws Exception {
		RESTResultBean<PayOrderBean> result;
		if (EmptyHelper.isEmpty(getUserOrderPayService())) {
			result = new RESTResultBean<PayOrderBean>();
			result.setMsg("当前系统未实现支付业务！");
			result.setCode(1);
		} else {
			result = getUserOrderPayService().payAfterProcess(order);// 下单数据保存
		}
		return result;
	}

	/**
	 * 用户支付（拉起微信APP）
	 * 
	 * @param order
	 * @return
	 */
	public RESTResultBean<PayOrderBean> doOrderPay(PayOrderBean order) {
		// 支付方式
		// 11:支付宝支付，12:支付宝h5支付
		//交易类型，JSAPI、NATIVE、APP
		// 21:JSAPI--JSAPI支付（或小程序支付）、22：NATIVE--Native支付、23：APP--app支付，24：MWEB--H5支付（网页），25：MICROPAY--付款码支付
		
		//交易类型（JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里）
		if("11".equals(order.getPayType())){
			
		}else if("12".equals(order.getPayType())){
			
		}else if("22".equals(order.getPayType())){
			
		}else if("24".equals(order.getPayType())){
			
		}else if("25".equals(order.getPayType())){
			
		}else if("26".equals(order.getPayType())){
			
		}else {
			
		}
		
		
		
		
		return null;
	}

}