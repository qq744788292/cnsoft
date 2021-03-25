package org.zmsoft.pay3.wx.bean;

import org.zmsoft.framework.ObjectBean;
import org.zmsoft.framework.security.MD5SecurityHelper;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 微信统一下单参数<br>
 * 除付款码支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，<br>
 * 返回正确的预支付交易会话标识后再按Native、JSAPI、APP等不同场景生成交易串调起支付。<br>
 * 支付产品名字变更：<br>
 *     公众号支付-变更为-JSAPI支付<br>
 *     扫码支付-变更为-Native支付<br>
 *     刷卡支付-变更为-付款码支付<br>
 * 交易类型trade_type<br>
 *     JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付<br>
 * 企业付款接口规则变更：<br>
 *     付款到零钱-商户号单日出款总额，由100万降低至10万<br>
 *     付款到零钱-商户号单人单日付款额度，由2万降低至5000<br>
 *     付款到银行卡-商户号单日出款总额，由100万降低至10万<br>
 *     付款到银行卡-商户号单人单日付款额度，由5万降低至2万<br>
 *     付款到零钱-新增两个商户资金安全相关的错误码<br>
 *     付款到银行卡-新增三个商户资金安全相关的错误码<br>
 * 支付结果通知回调频率调整为15s/15s/30s/3m/10m/20m/30m/30m/30m/60m/3h/3h/3h/6h/6h - 总计24h4m<br>
 * 财付通10位海关注册编码变更为：4403169D3W（原编码440316T004不再使用）
 * 
 * @version 2.6.3.1 2020.01.28
 */
public class WxUnionOrderBean extends ObjectBean {
	String key;// 商户API密钥
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
	 * JSAPI -JSAPI支付 NATIVE -Native支付 APP -APP支付
	 */
	String trade_type;// 交易类型
	String sign;// 签名
	String scene_info;// 场景信息<SceneInfoBean>

	public String creatOrderPaySign() throws Exception {
		StringBuffer param = new StringBuffer();
		if (EmptyHelper.isEmpty(appid))
			throw new Exception("appid 不能为空");
		param.append("appid=").append(appid);
		if (EmptyHelper.isEmpty(attach))
			throw new Exception("attach 不能为空");
		param.append("&attach=").append(attach);
		if (EmptyHelper.isEmpty(body))
			throw new Exception("body 不能为空");
		param.append("&body=").append(body);
		if (EmptyHelper.isEmpty(fee_type))
			throw new Exception("fee_type 不能为空");
		param.append("&fee_type=").append(fee_type);
		if (EmptyHelper.isEmpty(mch_id))
			throw new Exception("mch_id 不能为空");
		param.append("&mch_id=").append(mch_id);
		if (EmptyHelper.isEmpty(nonce_str))
			throw new Exception("nonce_str 不能为空");
		param.append("&nonce_str=").append(nonce_str);
		if (EmptyHelper.isEmpty(notify_url))
			throw new Exception("notify_url 不能为空");
		param.append("&notify_url=").append(notify_url);
		if (EmptyHelper.isEmpty(openid))
			throw new Exception("openid 不能为空");
		param.append("&openid=").append(openid);
		if (EmptyHelper.isEmpty(out_trade_no))
			throw new Exception("out_trade_no 不能为空");
		param.append("&out_trade_no=").append(out_trade_no);
//		if (EmptyHelper.isEmpty(scene_info))
//			throw new Exception("scene_info 不能为空");
//		param.append("&scene_info=").append(scene_info);
		if (EmptyHelper.isEmpty(sign_type))
			throw new Exception("sign_type 不能为空");
		param.append("&sign_type=").append(sign_type);
		if (EmptyHelper.isEmpty(spbill_create_ip))
			throw new Exception("spbill_create_ip 不能为空");
		param.append("&spbill_create_ip=").append(spbill_create_ip);
		if (EmptyHelper.isEmpty(total_fee))
			throw new Exception("total_fee 不能为空");
		param.append("&total_fee=").append(total_fee);
		if (EmptyHelper.isEmpty(trade_type))
			throw new Exception("trade_type 不能为空");
		param.append("&trade_type=").append(trade_type);
		if (EmptyHelper.isEmpty(key))
			throw new Exception("key 不能为空");
		param.append("&key=").append(key);
		return param.toString();
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
//		param.append("<scene_info><![CDATA[").append(scene_info).append("]]></scene_info>");
		param.append("<sign_type><![CDATA[").append(sign_type).append("]]></sign_type>");
		param.append("<spbill_create_ip><![CDATA[").append(spbill_create_ip).append("]]></spbill_create_ip>");
		param.append("<total_fee><![CDATA[").append(total_fee).append("]]></total_fee>");
		param.append("<trade_type><![CDATA[").append(trade_type).append("]]></trade_type>");
		param.append("<sign><![CDATA[").append(MD5SecurityHelper.encrypt(creatOrderPaySign()).toUpperCase()).append("]]></sign>");
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

	public String getScene_info() {
		return scene_info;
	}

	public void setScene_info(String scene_info) {
		this.scene_info = scene_info;
	}

}
