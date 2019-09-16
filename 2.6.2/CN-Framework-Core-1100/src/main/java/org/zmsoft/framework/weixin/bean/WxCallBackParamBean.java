package org.zmsoft.framework.weixin.bean;

/**
 * 微信自定义回调数据实体
 * 
 * @author fcy
 *
 */
public class WxCallBackParamBean extends WxUserBean {

	/**
	 * 会员ID
	 */
	private String playerId;
	/**
	 * 类别（1商品 2活动 3文章）
	 */
	private String classify;
	/**
	 * 数据ID
	 */
	private String itemId;
	/**
	 * 订单ID
	 */
	private String orderId;
	/**
	 * 用户类型（2新注册用户1注册用户0游客）
	 */
	private String roolType;

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRoolType() {
		return roolType;
	}

	public void setRoolType(String roolType) {
		this.roolType = roolType;
	}
	
	/////////////////////////////////////////////////////////////////////////
	private String wxShareToken;

	public String getWxShareToken() {
		return wxShareToken;
	}

	public void setWxShareToken(String wxShareToken) {
		this.wxShareToken = wxShareToken;
	}
	

}
