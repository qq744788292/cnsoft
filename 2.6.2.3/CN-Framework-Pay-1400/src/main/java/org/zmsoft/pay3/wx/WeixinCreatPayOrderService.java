package org.zmsoft.pay3.wx;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.zmsoft.config.MySystemConfigService;
import org.zmsoft.config.wx.ICWeixinPayConstants;
import org.zmsoft.config.wx.WxPayConfigService;
import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.framework.token.OrderTokenHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.HttpServiceHelper;
import org.zmsoft.framework.utils.PKHelper;
import org.zmsoft.framework.utils.RandomHelper;
import org.zmsoft.framework.utils.XMLHelper;
import org.zmsoft.pay3.wx.bean.WxUnionOrderBean;
import org.zmsoft.pay3.wx.call.WxJSAPIOrderCallBean;

/**
 * 微信统一下单
 * 
 * @author zxm
 *
 */
@Service("WeixinCreatPayOrderService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WeixinCreatPayOrderService extends MyServiceSupport implements ICWeixinPayConstants {

	public void main(String[] args) throws Exception {
		WxJSAPIOrderCallBean pcOrder = new WxJSAPIOrderCallBean();
		pcOrder.setNonceStr(RandomHelper.getRandomString(32));// 随机数
		PayOrderBean order = new PayOrderBean();

		order.setPayTradeNo(PKHelper.creatUUKey());// 订单号
		order.setBody("【xxxx】购买交易");// 订单名称
		order.setPayAmount(new BigDecimal("1.01"));

		System.out.println(createWeinxinPcOrder(pcOrder, order));
	}

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

	private String loadNotifyUrl(String orderToken) {
		return getSystemConfigService().getApiServerUrl() + String.format(WX_NOTIFY_URL, "orderToken");
	}
	
	// 20:微信付款码支付，21:微网页JSAPI支付，23:微信扫一扫，24:APP发起微信支付，25:H5支付，26:小程序支付
	public RESTResultBean<PayOrderBean> createWeinxinOrder(PayOrderBean order) throws Exception {
		WxJSAPIOrderCallBean pcOrder = new WxJSAPIOrderCallBean();
		pcOrder.setNonceStr(RandomHelper.getRandomString(32));// 随机数
		if ("20".equals(order.getPayType()) || "23".equals(order.getPayType())) {
			return createWeinxinPcOrder(pcOrder, order);
		} else if ("21".equals(order.getPayType()) || "25".equals(order.getPayType()) || "26".equals(order.getPayType())) {
			return createWeinxinH5Order(pcOrder, order);
		} else if ("24".equals(order.getPayType())) {
			return createWeinxinPcOrder(pcOrder, order);
		} else {
			throw new Exception("微信支付类别不正确！！！");
		}
	}

	/**
	 * 生成微信下单数据//文章素材相关
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public RESTResultBean<PayOrderBean> createWeinxinPcOrder(WxJSAPIOrderCallBean wxOrder, PayOrderBean order) throws Exception {
		logger.debug("微信统一下单数据===WxPcOrderBean>>>>>>" + wxOrder);
		logger.debug("微信统一下单数据===PayOrderBean>>>>>>" + order);
		String orderNumber = order.getPayTradeNo();// 商户订单号
		BigDecimal rechargeMoney = order.getPayAmount();// 订单金额

		RESTResultBean<PayOrderBean> result = new RESTResultBean<PayOrderBean>();
		// TODO 开发过程中改为1,发布时改为100,即最少1元
		rechargeMoney = rechargeMoney.multiply(BigDecimal.ZERO).multiply(BigDecimal.ZERO);
		if (rechargeMoney.intValue() < 1) {
			result.setCode(1);
			result.setMsg("价格设置错误，请联系管理员！");
			return result;
		}
		String orderAmount = "" + rechargeMoney.intValue();
		String orderToken = OrderTokenHelper.loadToken(SessionHelper.currentUserId(), orderNumber);

		WxUnionOrderBean _WxUnionOrderBean_ = new WxUnionOrderBean();
		// _WxUnionOrderBean_.setKey(getWxPayConfigService().getWxCustomSecret());
		_WxUnionOrderBean_.setAppid(getWxPayConfigService().getWxAppID());// 应用ID
		_WxUnionOrderBean_.setAttach(getWxPayConfigService().getWxAttach());// 附加数据
		_WxUnionOrderBean_.setFee_type(WX_FEE_TYPE);// 标价币种
		_WxUnionOrderBean_.setMch_id(getWxPayConfigService().getWxCustomid());// 商户号
		_WxUnionOrderBean_.setNotify_url(loadNotifyUrl(orderToken));// 通知地址
		_WxUnionOrderBean_.setSign_type(WX_SIGN_TYPE);// 签名类型

		_WxUnionOrderBean_.setTrade_type("NATIVE");// 交易类型（JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里）

		_WxUnionOrderBean_.setBody(order.getBody());// 商品描述
		_WxUnionOrderBean_.setNonce_str(wxOrder.getNonceStr());// 随机字符串
		_WxUnionOrderBean_.setOut_trade_no(orderNumber);// 商户订单号
		_WxUnionOrderBean_.setSpbill_create_ip(order.getUserIp());// 用户IP
		_WxUnionOrderBean_.setTotal_fee(orderAmount);// 标价金额（分）

		StringBuffer param = _WxUnionOrderBean_.creatOrderPayXML();
		logger.debug("微信统一下单数据===_WxH5OrderBean_>>>>>>" + _WxUnionOrderBean_);
		// 调用微信统一下单
		String payResult = HttpServiceHelper.doHttpPOST(WX_PAY_URL, param);
		logger.debug("微信统一下单结果===payResult>>>>>>" + payResult);

		// 格式化返回结果
		HashMap<String, String> wxResult = XMLHelper.loadXML(new ByteArrayInputStream(payResult.getBytes(SYSTEM_CHARSET)));

		logger.info("微信统一下单结果===wxResult>>>>>>" + wxResult);
		String prepay_id = wxResult.get("prepay_id");
		if (EmptyHelper.isEmpty(prepay_id)) {
			throw new Exception(wxResult.get("return_msg"));
		}

		// 重新计算签名.有appId, timeStamp, nonceStr, package, signType
		wxOrder.setAppId(getWxPayConfigService().getWxAppID());
		// pcOrder.setKey(getWxPayConfigService().getWxCustomSecret());
		// pcOrder.setPakkage(prepay_id);
		// pcOrder.setCodeUrl(wxResult.get("code_url"));
		// pcOrder.setOrderNumber(orderNumber);

		// 设定页面返回结果
		order.setData(wxOrder);
		result.setData(order);
		result.setMsg("下单成功，请扫码支付！");
		logger.debug("微信统一下单结果..." + orderNumber + "," + result.getData());
		return result;
	}

	/**
	 * 生成微信下单数据
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public RESTResultBean<PayOrderBean> createWeinxinH5Order(WxJSAPIOrderCallBean wxOrder, PayOrderBean order) throws Exception {
		logger.debug("微信统一下单数据===WxH5JSOrderBean>>>>>>" + wxOrder);
		logger.debug("微信统一下单数据===PayOrderBean>>>>>>" + order);
		String orderCode = order.getPayTradeNo();// 商户订单号
		BigDecimal rechargeMoney = order.getPayAmount();// 订单金额

		RESTResultBean<PayOrderBean> result = new RESTResultBean<PayOrderBean>();
		rechargeMoney = rechargeMoney.multiply(new BigDecimal("100"));
		if (rechargeMoney.intValue() < 1) {
			result.setCode(1);
			result.setMsg("价格设置错误，请联系管理员！");
			return result;
		}
		String orderAmount = "" + rechargeMoney.intValue();

		WxUnionOrderBean _WxUnionOrderBean_ = new WxUnionOrderBean();
		// _WxUnionOrderBean_.setKey(getWxPayConfigService().getWxCustomSecret());
		_WxUnionOrderBean_.setAppid(getWxPayConfigService().getWxAppID());// 应用ID
		_WxUnionOrderBean_.setAttach(getWxPayConfigService().getWxAttach());// 附加数据
		_WxUnionOrderBean_.setBody(order.getBody());// 商品描述
		_WxUnionOrderBean_.setFee_type(WX_FEE_TYPE);// 标价币种
		_WxUnionOrderBean_.setMch_id(getWxPayConfigService().getWxCustomid());// 商户号
		_WxUnionOrderBean_.setNonce_str(wxOrder.getNonceStr());// 随机字符串
		_WxUnionOrderBean_.setNotify_url(WX_NOTIFY_URL);// 通知地址???????????????????????
		_WxUnionOrderBean_.setOpenid(order.getOpenid());// 用户标识
		_WxUnionOrderBean_.setOut_trade_no(orderCode);// 商户订单号
		_WxUnionOrderBean_.setSign_type(WX_SIGN_TYPE);// 签名类型
		_WxUnionOrderBean_.setSpbill_create_ip(order.getUserIp());// 用户IP
		_WxUnionOrderBean_.setTotal_fee(orderAmount);// 标价金额（分）
		_WxUnionOrderBean_.setTrade_type("JSAPI");// 交易类型（JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里）

		StringBuffer param = _WxUnionOrderBean_.creatOrderPayXML();
		logger.debug("微信统一下单数据===WxH5JSOrderPayBean>>>>>>" + _WxUnionOrderBean_);
		// 调用微信统一下单
		String payResult = HttpServiceHelper.doHttpPOST(WX_PAY_URL, param);
		logger.debug("微信统一下单结果===payResult>>>>>>" + payResult);

		// 格式化返回结果
		HashMap<String, String> wxResult = XMLHelper.loadXML(new ByteArrayInputStream(payResult.getBytes(SYSTEM_CHARSET)));

		logger.debug("微信统一下单结果===wxResult>>>>>>" + wxResult);
		String prepay_id = wxResult.get("prepay_id");
		if (EmptyHelper.isEmpty(prepay_id)) {
			throw new Exception(wxResult.get("return_msg"));
		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 重新计算签名.有appId, timeStamp, nonceStr, package, signType
		wxOrder.setAppId(getWxPayConfigService().getWxAppID());
		// h5Order.setKey(getWxPayConfigService().getWxCustomSecret());
		wxOrder.setPakkage("prepay_id=" + prepay_id);
		wxOrder.setSignType(WX_SIGN_TYPE);

		// 设定页面返回结果
		order.setData(wxOrder);
		result.setData(order);
		result.setMsg("下单成功，请完成微信支付！");
		logger.info("微信统一下单结果...orderCode=" + orderCode + "," + result.getData());
		return result;
	}
}
