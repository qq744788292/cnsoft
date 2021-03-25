package org.zmsoft.pay3.wx.check;

import org.zmsoft.config.wxpay.ICWeixinPayConstants;
import org.zmsoft.framework.ObjectBean;
import org.zmsoft.framework.security.MD5SecurityHelper;

/**
 * 微信H5订单参数
 * 
 * @author Administrator
 *
 */
public class WeixinOrderCheckBean extends ObjectBean implements ICWeixinPayConstants {
	String key;// 商户API密钥
	String appid; // 应用ID
	String mch_id;// 商户号
	String nonce_str;// 随机字符串
	String out_trade_no;// 商户订单号
	String sign;// 签名

	public String creatOrderCheckSign() throws Exception {
		StringBuffer param = new StringBuffer();
		param.append("appid=").append(appid);
		param.append("&mch_id=").append(mch_id);
		param.append("&nonce_str=").append(nonce_str);
		param.append("&out_trade_no=").append(out_trade_no);
		param.append("&key=").append(key);
		//System.out.println("Sign=====>>>>>>>" + param.toString());
		return MD5SecurityHelper.encrypt(param.toString()).toUpperCase();
	}

	public StringBuffer creatOrderCheckXML() throws Exception {
		StringBuffer param = new StringBuffer();
		param.append("<xml>");
		param.append("<appid><![CDATA[").append(appid).append("]]></appid>");
		param.append("<mch_id><![CDATA[").append(mch_id).append("]]></mch_id>");
		param.append("<nonce_str><![CDATA[").append(nonce_str).append("]]></nonce_str>");
		param.append("<out_trade_no><![CDATA[").append(out_trade_no).append("]]></out_trade_no>");
		param.append("<sign><![CDATA[").append(creatOrderCheckSign()).append("]]></sign>");
		param.append("</xml>");
		return param;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
