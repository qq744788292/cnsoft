package org.zmsoft.pay3.ali.check;

import org.springframework.stereotype.Service;
import org.zmsoft.config.alipay.AliPayConfigService;
import org.zmsoft.config.alipay.ICAliPayConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.pay.bean.AliPayCallBackBean;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;

/**
 * 阿里订单检查
 */
@Service("AliOrderCheckService")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AliOrderCheckService extends MyServiceSupport implements ICAliPayConstants {
	// 阿里配置
	private AliPayConfigService aliConfigService;

	private AliPayConfigService getAliConfigService() {
		if (EmptyHelper.isEmpty(aliConfigService))
			aliConfigService = MyBeanFactoryHelper.getBean(AliPayConfigService.class.getSimpleName());
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

	/**
	 * 下单结果检查
	 * 
	 * @param aliPayCallBackBean
	 * @return
	 * @throws Exception
	 */
	public boolean doUserOrderCheck(AliPayCallBackBean aliPayCallBackBean) throws Exception {
		try {
			AlipayTradeQueryRequest queryRequest = new AlipayTradeQueryRequest();// 创建API对应的request
			try {
				JSONObject bizContentJson = new JSONObject();
				bizContentJson.put("out_trade_no", aliPayCallBackBean.getOut_trade_no());
				bizContentJson.put("trade_no", aliPayCallBackBean.getTrade_no());
				queryRequest.setBizContent(bizContentJson.toJSONString());
				AlipayTradeQueryResponse response = getAlipayClient().execute(queryRequest);
				if (response.isSuccess()) {
					String body = response.getBody();
					JSONObject resultJson = JSONObject.parseObject(body);
					String tradeStatus = resultJson.getJSONObject("alipay_trade_query_response").getString("trade_status");
					
					//TODO 检查支付成功结果
					//result.setData(tradeStatus);
					return true;
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		AliPayCallBackBean callback = new AliPayCallBackBean();
		callback.setOut_trade_no("34b575b0d5274ab4b095750d5e87349d");
		callback.setTrade_no("2018010321001004990265718630");
		try {
			// String result = MayiCreatPayOrderService.queryOrder(callback);
			// System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
