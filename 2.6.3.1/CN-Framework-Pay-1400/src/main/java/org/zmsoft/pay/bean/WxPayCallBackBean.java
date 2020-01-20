package org.zmsoft.pay.bean;

import org.zmsoft.config.wx.ICWeixinPayConstants;
import org.zmsoft.framework.ObjectBean;

/**
 * 微信返回订单信息
 * 
 * @author zxm
 *
 */
public class WxPayCallBackBean extends ObjectBean implements ICWeixinPayConstants {
	private String state;// 订单充值状态 1.充值成功 2.充值失败
	private String customerid;// 商户 ID 商户注册的时候，网关自动分配的商户 ID
	private String sd51no;// 订单在网关的订单号 该订单在网关系统的订单号
	private String sdcustomno;// 商户订单号 该订单在商户系统的流水号
	private String ordermoney;// 订单实际金额 商户订单实际金额 单位：（元）
	private String cardno;// 支付类型 固定值 32 为（微信，手机网页微信） 36 为（手机 QQ）
	private String mark;// 商户自定义信息 提交原值返回，请不要传人汉字，否则可能导致加密可能不一致
	private String sign;// 签名字符串 发送给商户的签名字符串
	private String resign;// 二次签名字符串 发送给商户的签名字符串
	private String des;// 支付备注 描述订单支付成功或失败的系统备
	private String nonce_str;// 随机字符串
	private String out_trade_no;// 商户订单号

	public WxPayCallBackBean() {

	}

	public WxPayCallBackBean(String state, String customerid, String sd51no, String sdcustomno, String ordermoney, String cardno, String mark, String sign, String resign, String des) {
		this.state = state;
		this.customerid = customerid;
		this.sd51no = sd51no;
		this.sdcustomno = sdcustomno;
		this.ordermoney = ordermoney;
		this.cardno = cardno;
		this.mark = mark;
		this.sign = sign;
		this.resign = resign;
		this.des = des;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getSd51no() {
		return sd51no;
	}

	public void setSd51no(String sd51no) {
		this.sd51no = sd51no;
	}

	public String getSdcustomno() {
		return sdcustomno;
	}

	public void setSdcustomno(String sdcustomno) {
		this.sdcustomno = sdcustomno;
	}

	public String getOrdermoney() {
		return ordermoney;
	}

	public void setOrdermoney(String ordermoney) {
		this.ordermoney = ordermoney;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResign() {
		return resign;
	}

	public void setResign(String resign) {
		this.resign = resign;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

}
