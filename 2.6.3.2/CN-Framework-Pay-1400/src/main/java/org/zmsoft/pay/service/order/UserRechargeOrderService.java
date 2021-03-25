package org.zmsoft.pay.service.order;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.PKHelper;
import org.zmsoft.order.beans.UserOrderRechargeBean;
import org.zmsoft.order.cache.MyUserOrderRechargeCacheSupport;
import org.zmsoft.pay.service.MyOrderServiceSupport;
import org.zmsoft.pay.service.persistent.UserRechargeOrderPersistentService;
import org.zmsoft.pay.service.persistent.UserShopOrderPersistentService;
import org.zmsoft.pay3.ali.AliCreatPayOrderService;
import org.zmsoft.pay3.wx.WeixinCreatPayOrderService;
import org.zmsoft.pay3.wx.call.WxOrderCallBean;
import org.zmsoft.persistent.pay.UserOrder.UserOrderDBO;
import org.zmsoft.persistent.pay.UserOrderRecharge.UserOrderRechargeDBO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 用户订单支付（业务实现）<br>
 * 第三方支付下单-支付路由
 * 
 * @author tasoft.com
 * @version 1.2.1 2017/06/13
 * @since 1.2.1 2017/06/13
 */
@Service("UserRechargeOrderService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserRechargeOrderService extends MyOrderServiceSupport {
	// 商品订单缓存
	@Resource
	private UserShopOrderPersistentService myShopOrderPersistentService;
	// 支付订单缓存
	@Resource
	private UserRechargeOrderPersistentService myRechargeOrderPersistentService;
	// 订单系统缓存
	@Resource
	private MyUserOrderRechargeCacheSupport cacheUserOrderRecharge;

	/**
	 * 用户下单（服务入口）
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public RESTResultBean<UserOrderRechargeBean> creatRechargeOrder(UserOrderRechargeBean param) throws Exception {
		// 创建订单
		UserOrderDBO order;
		UserOrderRechargeDBO orderRecharge;
		if (EmptyHelper.isEmpty(param.getOrderId())) {
			RESTResultBean<UserOrderRechargeBean> result = new RESTResultBean<UserOrderRechargeBean>();
			result.setCode(1);
			result.setMsg("支付失败，原始订单不存在！");
			return result;
		} else if (checkShopOrder(param) == false) {
			RESTResultBean<UserOrderRechargeBean> result = new RESTResultBean<UserOrderRechargeBean>();
			result.setCode(1);
			result.setMsg("支付失败，原始订单不存在！");
			return result;
		} else {
			order = JSONObject.parseObject(param.getOrderJsonObject(), UserOrderDBO.class);

			orderRecharge = new UserOrderRechargeDBO();

			// 玩家订单ID
			orderRecharge.setOrderId(param.getOrderId());

			// 玩家ID
			orderRecharge.setUserId(order.getUserId());
			// 玩家昵称
			orderRecharge.setUserNickname(order.getUserNickname());
			// 玩家手机号
			orderRecharge.setUserPhone(order.getUserPhone());

			// 随机字符串
			orderRecharge.setNonceStr(order.getNonceStr());
			// 商品描述
			orderRecharge.setBody(order.getBody());
		}
		return creatRechargeOrder(param, orderRecharge);
	}

	/**
	 * 原始订单检查（待支付状态）
	 * 
	 * @param ordertoken
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public boolean checkShopOrder(UserOrderRechargeBean param) throws Exception {
		// 根据业务需求，决定是否从数据库加载
		param = cacheUserOrderRecharge.loadOrder(param);
		// 订单合法性检查
		if (EmptyHelper.isEmpty(param)) {
			// 2、存在性检查
			UserOrderDBO order = new UserOrderDBO();
			order.setId(param.getOrderId());
			// 数据库存在性检查
			order = myShopOrderPersistentService.queryShopOrder(order);
			// 3、用户一致性检查
			if (EmptyHelper.isEmpty(order))
				return false;
			else if (ONE.equals(order.getOrderStatusCode()))// 待付款状态检查
				param.setOrderJsonObject(JSON.toJSONString(order));
			else
				return false;
		}

		return true;
	}

	public RESTResultBean<UserOrderRechargeBean> creatRechargeOrder(UserOrderRechargeBean param, UserOrderRechargeDBO orderRecharge) throws Exception {
		RESTResultBean<UserOrderRechargeBean> result = new RESTResultBean<UserOrderRechargeBean>();
		{
			// 创建主键
			orderRecharge.preparePrimarykey();
			if (EmptyHelper.isEmpty(orderRecharge.getUserId())) {// 是否为代付订单
				UserBean user = SessionHelper.currentUser();
				orderRecharge.setUserId(user.getUserId());
				orderRecharge.setUserNickname(user.getUserNameCN());
				orderRecharge.setUserPhone(user.getUserPhone());
			}
			// 商户订单号
			if (EmptyHelper.isEmpty(orderRecharge.getPayTradeNo()))
				orderRecharge.setPayTradeNo(PKHelper.creatUUKey());// UUID
			// 随机字符串
			orderRecharge.setNonceStr(param.getNonceStr());
			if (EmptyHelper.isEmpty(orderRecharge.getNonceStr()))
				orderRecharge.setNonceStr(PKHelper.creatUUKey());

			// 第三方用户唯一标识
			if (EmptyHelper.isEmpty(orderRecharge.getOpenId()))
				orderRecharge.setOpenId(param.getOpenid());

			// 第三方支付路由：统一下单
			RESTResultBean<WxOrderCallBean> wxResult = creatRechargeOrderProcess(param, orderRecharge);
			result.setData(wxResult.getData());
			// 支付状态
			orderRecharge.setPayStateCode(TWO);
			// 到账状态
			orderRecharge.setOrderStateCode(TWO);
			// 订单持久化时间
			cacheUserOrderRecharge.setWaitTimeSecond(this.configOrderTimeMinute());
			cacheUserOrderRecharge.saveOrder(param);
			// 持久化订单
			myRechargeOrderPersistentService.creatRechargeOrder(orderRecharge);
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
	public RESTResultBean<WxOrderCallBean> creatRechargeOrderProcess(UserOrderRechargeBean param, UserOrderRechargeDBO orderRecharge) throws Exception {
		String tradeType = EMPTY;

		// 支付方式
		// 11:支付宝支付，12:支付宝h5支付
		// 交易类型，JSAPI、NATIVE、APP
		// 21:JSAPI--JSAPI支付（或小程序支付）、22:NATIVE--Native支付、23:APP--app支付，24:MWEB--H5支付<br>

		// 交易类型（JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里）
		if ("11".equals(orderRecharge.getPayTypeCode())) {

		} else if ("12".equals(orderRecharge.getPayTypeCode())) {

		} else if ("21".equals(orderRecharge.getPayTypeCode())) {
			tradeType = "JSAPI";
		} else if ("22".equals(orderRecharge.getPayTypeCode())) {
			tradeType = "NATIVE";
		} else if ("23".equals(orderRecharge.getPayTypeCode())) {
			tradeType = "APP";
		} else if ("24".equals(orderRecharge.getPayTypeCode())) {
			tradeType = "MWEB";
		} else {
			throw new Exception("微信支付类别不正确！！！");
		}
		// 支付宝
		if (orderRecharge.getPayTypeCode().startsWith(ONE)) {
			// return myAliCreatPayOrderService.createAliPayOrder(param, order);
		} else
		// 微信
		if (orderRecharge.getPayTypeCode().startsWith(TWO)) {
			return myWeixinCreatPayOrderService.createWxUnionOrder(param, orderRecharge, tradeType);
		} else {
			throw new Exception("支付类别不正确！！！");
		}
		return null;
	}

	public RESTResultBean<UserOrderRechargeBean> queryRechargeOrder(UserOrderRechargeBean order) {
		// TODO Auto-generated method stub
		return null;
	}

}