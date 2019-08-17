package org.zmsoft.pay3.wx.check;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zmsoft.config.wx.WxConfigService;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.HttpServiceHelper;
import org.zmsoft.framework.utils.XMLHelper;
import org.zmsoft.pay.MyOrderSupport;
import org.zmsoft.pay.bean.WxPayCallBackBean;

/**
 * 微信H5下单
 * 
 * @author FCY
 * @version 0.0.1 2017-10-19
 * @since 0.0.1 2017-10-19
 */
@Service("WeixinOrderCheckService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WeixinOrderCheckService extends MyOrderSupport {
	public static final String wxh5checkUrl = "https://api.mch.weixin.qq.com/pay/orderquery";// 微信
	// 微信配置
	private WxConfigService wxConfigService;

	private WxConfigService getWxConfigService() {
		if (EmptyHelper.isEmpty(wxConfigService))
			wxConfigService = MyBeanFactoryHelper.getBean(WxConfigService.class.getSimpleName());
		return wxConfigService;
	}

	/**
	 * 下单结果检查
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean doUserOrderCheck(WxPayCallBackBean weiXinCallBackBean) throws Exception {
		try {
			logger.debug("微信统一下单结果检查===doUserOrderCheck>>>>>>" + weiXinCallBackBean);

			WeixinOrderCheckBean _WxOrderCheckBean_ = new WeixinOrderCheckBean();
			_WxOrderCheckBean_.setKey(getWxConfigService().getWxCustomSecret());
			_WxOrderCheckBean_.setAppid(getWxConfigService().getWxAppID());// 应用ID
			_WxOrderCheckBean_.setMch_id(getWxConfigService().getWxCustomid());// 商户号
			_WxOrderCheckBean_.setNonce_str(weiXinCallBackBean.getNonce_str());// 随机字符串
			_WxOrderCheckBean_.setOut_trade_no(weiXinCallBackBean.getOut_trade_no());// 商户订单号

			StringBuffer param = _WxOrderCheckBean_.creatOrderCheckXML();
			logger.debug("微信统一下单数据检查===_WxH5OrderBean_>>>>>>" + _WxOrderCheckBean_);
			// 调用微信统一下单
			String payResult = HttpServiceHelper.doHttpPOST(wxh5checkUrl, param);
			logger.debug("微信统一下单检查结果===payResult>>>>>>" + payResult);

			// 格式化返回结果
			HashMap<String, String> wxResult = XMLHelper.loadXML(new ByteArrayInputStream(payResult.getBytes(SYSTEM_CHARSET)));

			logger.info("微信统一下单检查结果===wxResult>>>>>>" + wxResult);
			// 完成订单支付成功
			if ("SUCCESS".equals(wxResult.get("return_code")) && "SUCCESS".equals(wxResult.get("result_code")) && "SUCCESS".equals(wxResult.get("trade_state"))) {
				logger.info("用户购买成功开始支付===>>>>>>......");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
