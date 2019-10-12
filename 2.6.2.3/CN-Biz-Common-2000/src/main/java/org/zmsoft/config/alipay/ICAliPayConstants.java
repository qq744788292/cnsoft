package org.zmsoft.config.alipay;

/**
 * 蚂蚁金服支付配置文件
 */
public interface ICAliPayConstants {
	// 销售产品码，与支付宝签约的产品码名称。 注：目前仅支持FAST_INSTANT_TRADE_PAY
	public static String MAYI_PRODUCT_CODE = "FAST_INSTANT_TRADE_PAY";
	// 蚂蚁金服接口地址 正式环境
	public static String MAYI_SERVER_URL = "https://openapi.alipay.com/gateway.do";
	// 仅支持JSON
	public static String MAYI_FORMAT = "json";
	// 请求使用的编码格式
	public static String MAYI_CHARSET = "utf-8";
	// 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
	public static String MAYI_SIGN_TYPE = "RSA2";
	// 服务器异步通知页面路径
	public static String MAYI_NOTIFY_URL = "/api/1.0/pay/alinotice/%s";
	// 需http://格式的完整路径，不能加?id=123这类自定义参数
	// 页面跳转同步通知页面路径
	public static String MAYI_BACK_URL = "/api/1.0/pay/alinotice/%s/back";
	
	public static void main(String[] args){
		System.out.println(String.format(MAYI_BACK_URL, "orderToken"));
	}
}
