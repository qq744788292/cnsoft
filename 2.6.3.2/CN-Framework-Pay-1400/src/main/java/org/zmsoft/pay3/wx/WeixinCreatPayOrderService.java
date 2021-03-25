package org.zmsoft.pay3.wx;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.zmsoft.config.MySystemConfigService;
import org.zmsoft.config.wxpay.ICWeixinPayConstants;
import org.zmsoft.config.wxpay.WxPayConfigService;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.cache.client.ClientHelper;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.framework.token.OrderTokenHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.HttpServiceHelper;
import org.zmsoft.framework.utils.XMLHelper;
import org.zmsoft.order.beans.UserOrderRechargeBean;
import org.zmsoft.pay3.wx.bean.WxUnionOrderBean;
import org.zmsoft.pay3.wx.call.WxJSAPIOrderCallBean;
import org.zmsoft.pay3.wx.call.WxOrderCallBean;
import org.zmsoft.persistent.pay.UserOrderRecharge.UserOrderRechargeDBO;

/**
 * 微信统一下单<br>
 * 交易类型trade_type<br>
 * JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付 企业付款接口规则变更：<br>
 */
@Service("WeixinCreatPayOrderService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WeixinCreatPayOrderService extends MyServiceSupport implements ICWeixinPayConstants {

	// 系统配置
	private MySystemConfigService systemConfigService;
	private MySystemConfigService getSystemConfigService() {
		if (EmptyHelper.isEmpty(systemConfigService))
			systemConfigService = MyBeanFactoryHelper.getBean(MySystemConfigService.class);
		return systemConfigService;
	}

	// 微信配置
	private WxPayConfigService WxPayConfigService;
	private WxPayConfigService getWxPayConfigService() {
		if (EmptyHelper.isEmpty(WxPayConfigService))
			WxPayConfigService = MyBeanFactoryHelper.getBean(WxPayConfigService.class);
		return WxPayConfigService;
	}

	private String loadNotifyUrl(String orderId) throws Exception {
		String orderToken = OrderTokenHelper.loadToken(SessionHelper.currentUserId(), orderId);
		orderToken = getSystemConfigService().getApiServerUrl() + String.format(WX_NOTIFY_URL, orderId);
		return orderToken;
	}

	public RESTResultBean<WxOrderCallBean> createWxUnionOrder(UserOrderRechargeBean param, UserOrderRechargeDBO orderRecharge, String tradeType) throws Exception {
		logger.debug("微信统一下单数据===UserOrderRechargeDBO>>>>>>" + orderRecharge);
		String orderId = orderRecharge.getOrderId();// 商户订单号
		BigDecimal rechargeMoney = orderRecharge.getPayAmount();// 订单金额

		RESTResultBean<WxOrderCallBean> result = new RESTResultBean<WxOrderCallBean>();
		// TODO 开发过程中改为1,发布时改为100,即最少1元
		rechargeMoney = rechargeMoney.multiply(new BigDecimal("100"));
		if (EmptyHelper.isEmpty(orderId)) {
			result.setCode(1);
			result.setMsg("原始订单ID");
			return result;
		}

		if (rechargeMoney.intValue() < 1) {
			result.setCode(1);
			result.setMsg("价格设置错误，请联系管理员！");
			return result;
		}
		String orderAmount = "" + rechargeMoney.intValue();

		WxUnionOrderBean _WxUnionOrderBean_ = new WxUnionOrderBean();
		_WxUnionOrderBean_.setOpenid(orderRecharge.getOpenId());
		_WxUnionOrderBean_.setKey(getWxPayConfigService().getWxCustomSecret());
		_WxUnionOrderBean_.setAppid(getWxPayConfigService().getWxAppID());// 应用ID
		_WxUnionOrderBean_.setAttach(getWxPayConfigService().getWxAttach());// 附加数据
		_WxUnionOrderBean_.setFee_type(WX_FEE_TYPE);// 标价币种
		_WxUnionOrderBean_.setMch_id(getWxPayConfigService().getWxCustomid());// 商户号
		_WxUnionOrderBean_.setNotify_url(loadNotifyUrl(orderId));// 通知地址
		_WxUnionOrderBean_.setSign_type(WX_SIGN_TYPE);// 签名类型

		_WxUnionOrderBean_.setTrade_type(tradeType);// 交易类型（JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里）

		_WxUnionOrderBean_.setBody(orderRecharge.getBody());// 商品描述
		_WxUnionOrderBean_.setNonce_str(orderRecharge.getNonceStr());// 随机字符串
		_WxUnionOrderBean_.setOut_trade_no(orderId);// 商户订单号
		if (EmptyHelper.isEmpty(param.getUserIp())) {
			_WxUnionOrderBean_.setSpbill_create_ip(ClientHelper.getClientBusinessSupport().getClientIp());// 用户IP
		} else {
			_WxUnionOrderBean_.setSpbill_create_ip(param.getUserIp());
		}
		_WxUnionOrderBean_.setTotal_fee(orderAmount);// 标价金额（分）

		// SceneInfoBean sib;
		// _WxUnionOrderBean_.setScene_info(scene_info);
		StringBuffer paramSB = _WxUnionOrderBean_.creatOrderPayXML();

		logger.debug("微信统一下单数据===OrderPayXML>>>>>>" + _WxUnionOrderBean_.creatOrderPaySign());
		logger.debug("微信统一下单数据===OrderPayXML>>>>>>" + paramSB);
		// 调用微信统一下单
		String payResult = HttpServiceHelper.doHttpPOST(WX_PAY_URL, paramSB);
		logger.debug("微信统一下单结果===PayResult>>>>>>" + payResult);

		// 格式化返回结果
		HashMap<String, String> wxResult = XMLHelper.loadXML(new ByteArrayInputStream(payResult.getBytes(SYSTEM_CHARSET)));

		logger.info("微信统一下单结果===wxResult>>>>>>" + wxResult);
		// 返回状态码-通信标识
		if (FAIL.equals(wxResult.get("return_code"))) {
			throw new Exception(wxResult.get("return_msg"));
		}

		// invalid out_trade_no
		// 错误原因：订单的id不正确。 空或者太短

		// 业务结果
		if (FAIL.equals(wxResult.get("result_code"))) {
			throw new Exception(wxResult.get("err_code") + ":" + wxResult.get("err_code_des"));
		}
		// 预支付交易会话标识
		String prepay_id = wxResult.get("prepay_id");

		// 设定页面返回结果
		WxJSAPIOrderCallBean wxOrder = new WxJSAPIOrderCallBean();
		// 重新计算签名.有appId, timeStamp, nonceStr, package, signType
		wxOrder.setAppId(getWxPayConfigService().getWxAppID());
		wxOrder.setNonceStr(orderRecharge.getNonceStr());
		wxOrder.setKey(getWxPayConfigService().getWxCustomSecret());
		wxOrder.setPakkage(prepay_id);
		wxOrder.setCodeUrl(wxResult.get("code_url"));
		wxOrder.setOrderNumber(orderId);

		// param.setData(wxOrder);
		result.setData(wxOrder);
		result.setMsg("下单成功，请完成支付！");
		logger.debug("微信统一下单结果..." + orderId + "," + result.getData());
		return result;

	}
}
