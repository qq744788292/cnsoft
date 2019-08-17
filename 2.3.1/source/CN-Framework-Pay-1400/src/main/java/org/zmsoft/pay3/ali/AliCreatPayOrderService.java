package org.zmsoft.pay3.ali;

import java.math.BigDecimal;

import com.alipay.api.AlipayConstants;
import org.zmsoft.common.MySystemConfigService;
import org.zmsoft.config.ali.AliConfigService;
import org.zmsoft.config.ali.ICAliPayConstants;
import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.framework.token.OrderTokenHelper;
import org.zmsoft.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

/**
 * 蚂蚁金服支付
 */
public class AliCreatPayOrderService extends MyServiceSupport implements ICAliPayConstants {

//	// 服务器异步通知页面路径
//	public static String MAYI_NOTIFY_URL = apiServerUrl + "/99996052";
//	// 需http://格式的完整路径，不能加?id=123这类自定义参数
//
//	// 页面跳转同步通知页面路径
//	public static String MAYI_RETURN_URL = apiServerUrl + "/99996052/back";
//	// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
	// 系统配置
	private MySystemConfigService systemConfigService;

	private MySystemConfigService getSystemConfigService() {
		if (EmptyHelper.isEmpty(systemConfigService))
			systemConfigService = MyBeanFactoryHelper.getBean(MySystemConfigService.class.getSimpleName());
		return systemConfigService;
	}

	// 阿里配置
	private AliConfigService aliConfigService;
	private AliConfigService getAliConfigService() {
		if (EmptyHelper.isEmpty(aliConfigService))
			aliConfigService = MyBeanFactoryHelper.getBean(AliConfigService.class.getSimpleName());
		return aliConfigService;
	}

	// 获得初始化的AlipayClient
	private AlipayClient alipayClient;

	private AlipayClient getAlipayClient() {
		if (EmptyHelper.isEmpty(alipayClient)) {
			alipayClient = new DefaultAlipayClient(MAYI_SERVER_URL, getAliConfigService().getAliAppID(), getAliConfigService().getAliAppPrivateKey(), AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8, getAliConfigService().getAliAppPublicKey(), AlipayConstants.SIGN_TYPE_RSA2);
		}
		return alipayClient;
	}


	private String loadNotifyUrl(String orderToken) {
		return getSystemConfigService().getApiServerUrl() + String.format(MAYI_NOTIFY_URL, "orderToken");
	}

	private String loadBackUrl(String orderToken) {
		return getSystemConfigService().getApiServerUrl() + String.format(MAYI_BACK_URL, "orderToken");

	}

	/**
	 * 支付
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public RESTResultBean<String> createAliPayOrder(PayOrderBean order) throws Exception {
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
		String orderToken = OrderTokenHelper.loadToken(SessionHelper.currentUserId(), order.getPayTradeNo());
		alipayRequest.setReturnUrl(loadBackUrl(orderToken));// 同步回调地址
		alipayRequest.setNotifyUrl(loadNotifyUrl(orderToken));// 异步回调地址
		// 从订单里获取参数
		String orderCode = order.getPayTradeNo();// 订单编号
		String mark = order.getMeno();// 订单名称
		BigDecimal rechargeMoney = order.getPayAmount();// 金额
		RESTResultBean<String> result = new RESTResultBean<String>();
		try {
			JSONObject bizContentJson = new JSONObject();
			bizContentJson.put("out_trade_no", orderCode);
			bizContentJson.put("product_code", MAYI_PRODUCT_CODE);
			bizContentJson.put("total_amount", rechargeMoney);
			bizContentJson.put("subject", mark);
			alipayRequest.setBizContent(bizContentJson.toJSONString());
			String form = "";
			form = getAlipayClient().pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
			result.setData(form);
			result.setMsg("下单成功，请扫码支付！订单号：" + orderCode);
			logger.debug("下单成功..." + orderCode + "," + result.getData());
			/*
			 * ELKLogData eld = new ELKLogData("UserPayLog");
			 * eld.setCountType("order");// 统计分类;平台 eld.setBusinessName("Ali");
			 * eld.setServiceType(ZERO); eld.setExplain1(orderCode);
			 * eld.setExplain2(rechargeMoney.toPlainString());
			 * eld.loadELKLoggerAppender().debug();
			 */
		} catch (AlipayApiException e) {
			e.printStackTrace();
			result.setCode(1);
			result.setMsg("支付通道已经关闭，请联系客服！");
		}
		return result;
	}

}
