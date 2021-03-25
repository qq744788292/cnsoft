package org.zmsoft.order.beans;

import java.math.BigDecimal;

import org.zmsoft.framework.beans.OrderBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.PKHelper;

/** 会员订单信息 */
public class UserOrderRechargeBean extends OrderBean implements ICFrameworkConstants {

	// 订单创建、订单支付、订单生产、订单确认、订单完成、取消订单
	// 0、基本信息1、用户信息2、商品信息3、支付信息（优惠券）4、收件人信息5、配送信息6、售后服务信息7、客服与补偿
	// A、电子货品B、实物货品

	/**
	 * 第三方订单返回结果
	 */
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	///////////////////////////////////////////////////////////////
	/**
	 * 用户当前IP
	 */
	private String userIp = null;

	/**
	 * OpenID
	 */
	private String openid = null;

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	////////////////////////////////////////////////////////////////////////
	private String orderId = null;

	public String getOrderId() {
		if (EmptyHelper.isEmpty(orderId))
			orderId = userId + shopOrderId + rechargeOrderId;
		return orderId;
	}

	public void setOrderId(String orderid) {
		this.orderId = orderid;
	}

	////////////////////////////////////////////////////////////////////////
	/**
	 * 用户当前ID
	 */
	private String userId = EMPTY;

	/**
	 * 玩家商户订单ID
	 */
	private String shopOrderId = EMPTY;
	/**
	 * 玩家支付订单ID
	 */
	private String rechargeOrderId = EMPTY;

	/**
	 * 商户订单数据
	 * 
	 * @see <UserOrderDBO>
	 */
	private String orderJsonObject = JsonObject;
	/**
	 * 订单商品详情
	 * 
	 * @see <UserOrderGoodsBean>
	 */
	private String goodsJsonArray = JsonArray;

	/**
	 * 支付数据信息
	 * 
	 * @see <UserOrderDBO>
	 */
	private String rechargeJsonObject = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getShopOrderId() {
		return shopOrderId;
	}

	public void setShopOrderId(String shopOrderId) {
		this.shopOrderId = shopOrderId;
	}

	public String getRechargeOrderId() {
		return rechargeOrderId;
	}

	public void setRechargeOrderId(String rechargeOrderId) {
		this.rechargeOrderId = rechargeOrderId;
	}

	public String getOrderJsonObject() {
		return orderJsonObject;
	}

	public void setOrderJsonObject(String orderJsonObject) {
		this.orderJsonObject = orderJsonObject;
	}

	public String getGoodsJsonArray() {
		return goodsJsonArray;
	}

	public void setGoodsJsonArray(String goodsJsonArray) {
		this.goodsJsonArray = goodsJsonArray;
	}

	public String getRechargeJsonObject() {
		return rechargeJsonObject;
	}

	public void setRechargeJsonObject(String rechargeJsonObject) {
		this.rechargeJsonObject = rechargeJsonObject;
	}

	////////////////////////////////////////////////
	/**
	 * 订单校验码
	 */
	private String nonceStr = PKHelper.creatUUKey();

	/**
	 * 订单描述
	 */
	private String body = "消费订单";

	/**
	 * 支付方式编码(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
	 */
	private String payTypeCode = null;
	/**
	 * 支付金额
	 */
	private BigDecimal payAmount = BigDecimal.ZERO;

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPayTypeCode() {
		return payTypeCode;
	}

	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	//////////////////////////////////////////////////////////////////
	/**
	 * 支付状态编码（1已支付,2未支付）
	 */
	private String payStateCode = null;

	/**
	 * 第三方支付流水号
	 */
	private String payNumber = null;

	/**
	 * 到账状态编码（1已到账,2未到账）
	 */
	private String orderStateCode = null;

	public String getPayStateCode() {
		return payStateCode;
	}

	public void setPayStateCode(String payStateCode) {
		this.payStateCode = payStateCode;
	}

	public String getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}

	public String getOrderStateCode() {
		return orderStateCode;
	}

	public void setOrderStateCode(String orderStateCode) {
		this.orderStateCode = orderStateCode;
	}

	/////////////////////////////////////////////
	/**
	 * 订单状态编码(1待付款, 2已取消, 3已付款待处理, 4申请退款, 5退款确认,6已发货, 7物流配送中, 8申请售后, 9售后退款,
	 * 10已完成)
	 */
	private String orderStatusCode = null;

	public String getOrderStatusCode() {
		return orderStatusCode;
	}

	public void setOrderStatusCode(String orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}
}
