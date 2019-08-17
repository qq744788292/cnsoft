package org.zmsoft.pay;


/**
 * 
 * @author Administrator
 *
 * @see <ICustomSystemConstants><WxGongzhConstants><WeixinPayConstants>
 */
public interface ICSystemPayConstants {

	
	////////////////////////////////////////////////////////////////////////////////////
	public static String oss_publicServer = "http://jyxy.oss-cn-shanghai.aliyuncs.com/";
	public static String oss_endpoint = "http://oss-cn-shanghai.aliyuncs.com";
	public static String oss_accessKeyId = "ck2E2vzUYJP8yiEM";
	public static String oss_accessKeySecret = "wmdK7hivj6XGUPK50Y8Wtc2fNroYi4";
	public static String oss_bucketName = "jyxy";
	
	//阿里IP定位服务APPCODE
	public static String ALI_IP_QUERY_APPCODE = "61be18a1a7df4502bfbf8d51252ba643";
	//阿里IP定位服务接口地址
	public static String ALI_IP_QUERY_URL = "https://dm-81.data.aliyun.com/rest/160601/ip/getIpInfo.json";
}
