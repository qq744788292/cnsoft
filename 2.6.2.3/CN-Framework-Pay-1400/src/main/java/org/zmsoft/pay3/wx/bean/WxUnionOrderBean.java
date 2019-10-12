package org.zmsoft.pay3.wx.bean;

import org.zmsoft.framework.ObjectBean;
import org.zmsoft.framework.security.MD5SecurityHelper;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 微信统一下单参数<br>
 * 除付款码支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，<br>
 * 返回正确的预支付交易会话标识后再按Native、JSAPI、APP等不同场景生成交易串调起支付。
 * @author Administrator
 *
 */
public class WxUnionOrderBean extends ObjectBean {
	//String key;// 商户API密钥
	String appid; // 应用ID
	String attach;// 附加数据
	String body;// 商品描述
	String fee_type;// 标价币种
	String mch_id;// 商户号
	String nonce_str;// 随机字符串
	String notify_url;// 通知地址
	String openid;// 用户标识
	String out_trade_no;// 商户订单号
	String sign_type;// 签名类型
	String spbill_create_ip;// 用户IP
	String total_fee;// 标价金额（分）
	/*
	  JSAPI 		-JSAPI支付
	  NATIVE 		-Native支付
	  APP 			-APP支付
	*/
	String trade_type;// 交易类型
	String sign;// 签名

	public String creatOrderPaySign() throws Exception {
		StringBuffer param = new StringBuffer();
		param.append("appid=").append(appid);
		param.append("&attach=").append(attach);
		param.append("&body=").append(body);
		param.append("&fee_type=").append(fee_type);
		param.append("&mch_id=").append(mch_id);
		param.append("&nonce_str=").append(nonce_str);
		param.append("&notify_url=").append(notify_url);
		param.append("&openid=").append(openid);
		param.append("&out_trade_no=").append(out_trade_no);
		param.append("&sign_type=").append(sign_type);
		param.append("&spbill_create_ip=").append(spbill_create_ip);
		param.append("&total_fee=").append(total_fee);
		param.append("&trade_type=").append(trade_type);
//		param.append("&key=").append(key);
		System.out.println("WxH5JSOrderPayBean.Sign=====>>>>>>>" + param.toString());
		return MD5SecurityHelper.encrypt(param.toString()).toUpperCase();
	}

	public StringBuffer creatOrderPayXML() throws Exception {
		StringBuffer param = new StringBuffer();
		param.append("<xml>");
		param.append("<appid><![CDATA[").append(appid).append("]]></appid>");
		param.append("<attach><![CDATA[").append(attach).append("]]></attach>");
		param.append("<body><![CDATA[").append(body).append("]]></body>");
		param.append("<fee_type><![CDATA[").append(fee_type).append("]]></fee_type>");
		param.append("<mch_id><![CDATA[").append(mch_id).append("]]></mch_id>");
		param.append("<nonce_str><![CDATA[").append(nonce_str).append("]]></nonce_str>");
		param.append("<notify_url><![CDATA[").append(notify_url).append("]]></notify_url>");
		param.append("<openid><![CDATA[").append(openid).append("]]></openid>");
		param.append("<out_trade_no><![CDATA[").append(out_trade_no).append("]]></out_trade_no>");
		param.append("<sign_type><![CDATA[").append(sign_type).append("]]></sign_type>");
		param.append("<spbill_create_ip><![CDATA[").append(spbill_create_ip).append("]]></spbill_create_ip>");
		param.append("<total_fee><![CDATA[").append(total_fee).append("]]></total_fee>");
		param.append("<trade_type><![CDATA[").append(trade_type).append("]]></trade_type>");
		param.append("<sign><![CDATA[").append(creatOrderPaySign()).append("]]></sign>");
		param.append("</xml>");
		return param;
	}

//	public String getKey() {
//		return key;
//	}
//
//	public void setKey(String key) {
//		this.key = key;
//	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
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

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		if(EmptyHelper.isEmpty(spbill_create_ip))
			spbill_create_ip = "127.0.0.1";
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
