package org.zmsoft.config.wx;

/**
 * 微信支付<br>
 * 
 * @see <ICustomSystemConstants><WxGongzhConstants><WeixinPayConstants>
 */
public interface ICWeixinPayConstants {

	// https://apihk.mch.weixin.qq.com/pay/unifiedorder （建议接入点：东南亚）
	// https://apius.mch.weixin.qq.com/pay/unifiedorder （建议接入点：其它）
	// https://api.mch.weixin.qq.com/pay/unifiedorder （建议接入点：中国国内）

	public static final String WX_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 微信统一支付
																								// pc扫码地址
	//public static final String WX_CARD_NO = "32";// 固定值。 32 为（微信，手机网页微信） 36 为（手机 QQ）
	public static final String WX_NOTIFY_URL = "/api/1.0/pay/txnotice/%s";// 异步通知地址
	public static final String WX_BACK_URL = "/api/1.0/pay/txnotice/%s/back";// 同步通知页面

	public static final String WX_FEE_TYPE = "CNY";
	public static final String WX_SIGN_TYPE = "MD5";

}
