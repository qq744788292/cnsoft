package org.zmsoft.jfp.common.sms;

import java.util.HashMap;
import java.util.Map;

import org.zmsoft.jfp.framework.support.MyTokenCommonSupport;
import org.zmsoft.jfp.framework.utils.HttpServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信发送
 * 
 * @author zmsoft
 * @version 0.0.1 2017/12/08
 * @since 0.0.1 2017/12/08
 */

public class SMSOperatrHelper extends MyTokenCommonSupport {
	protected static Logger logger = LoggerFactory.getLogger(SMSOperatrHelper.class);
	public static String Player_Model = "【远程监控系统】故障类型：%s，所属集中器：%s，故障设备：%s， 发生时间：%s。";

	private static String serviceURL = "http://code.58yhkj.com:7862/sms";
	private static String account = "922155";
	private static String password = "xKdKCf";
	private static String extno = "1069080155";//接入号

	public static void main(String[] args) throws Exception {
		doSendSMS("15558009839,15057177411", String.format(SMSOperatrHelper.Player_Model, "1111","2222","33333","4444"));
	}

	public static void doSendSMS(String mobile, String content) throws Exception {
		logger.debug(content);
//		http://code.58yhkj.com:7862/sms?action=send&account=账号&password=密码&mobile=15023239810,13527576163&content=内容&extno=1069012345
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", "send");// 任务命令
		param.put("account", account);// 发送用户帐号
		param.put("password", password);// 发送帐号密码
		param.put("mobile", mobile);// 全部被叫号码
		param.put("content", content);// 发送内容
		param.put("extno", extno);// 接入号

		String result=HttpServiceHelper.doHttpPOST(serviceURL, param);
//		System.out.println(result);
		logger.debug(result);
	}
}
