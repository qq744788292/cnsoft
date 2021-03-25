package org.zmsoft.pay3.wx.call;

import org.zmsoft.config.wxpay.ICWeixinPayConstants;
import org.zmsoft.framework.ObjectBean;
import org.zmsoft.framework.security.MD5SecurityHelper;

/**
 * 微信JSAPI订单参数(微信内H5订单支付)<br>
 * 用户拉起微信<br>
 * 用户通过消息或扫描二维码在微信内打开网页时，可以调用微信支付完成下单购买的流程。
 * @author Administrator
 *
 */
public class WxOrderCallBean extends ObjectBean implements ICWeixinPayConstants {
	String appId;
	String timeStamp;
	String nonceStr;
	String pakkage;
	String signType;
	
	String key;
	String codeUrl;
	String orderNumber;

	public String getPaySign() throws Exception {
		StringBuffer param = new StringBuffer();
		param.append("appId=").append(appId);
		param.append("&nonceStr=").append(nonceStr);
		param.append("&package=").append(pakkage);
		param.append("&signType=").append(signType);
		param.append("&timeStamp=").append(timeStamp);
//		param.append("&key=").append(key);
		System.out.println("WxH5JSOrderBean.Sign=====>>>>>>>" + param.toString());
		return MD5SecurityHelper.encrypt(param.toString()).toUpperCase();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPakkage() {
		return pakkage;
	}

	public void setPakkage(String pakkage) {
		this.pakkage = pakkage;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

}
