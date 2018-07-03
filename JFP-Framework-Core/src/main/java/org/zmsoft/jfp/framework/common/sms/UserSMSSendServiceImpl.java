package org.zmsoft.jfp.framework.common.sms;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.zmsoft.jfp.framework.beans.pub.SMSBean;
import org.zmsoft.jfp.framework.common.CommonChannelConfig;
import org.zmsoft.jfp.framework.constants.pub.ISMSConstants;
import org.zmsoft.jfp.framework.support.common.ISMSSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

/**
 * 短信发送SDK
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public class UserSMSSendServiceImpl extends CommonChannelConfig implements ISMSSupport, ISMSConstants {
	public UserSMSSendServiceImpl() {
		this.channelKey = CONFIG_KEY;
	}

	@Override
	public boolean send(SMSBean sms) {
		logger.debug(sms.toString());
		// 压入队列
		if (myCacheService == null)
			logger.error(">>>>>缓存服务没有定义...xxx");
		else {
			myCacheService.addObjectInList(channelKey, sms, false);
		}
		// 直接保存到短信队列
		return true;

	}

	@Override
	public boolean send(String schoolId, String phoneNum, String message) {
		if (EmptyHelper.isEmpty(schoolId) || EmptyHelper.isEmpty(phoneNum) || EmptyHelper.isEmpty(message))
			return false;
		// 直接保存到短信队列
		return send(schoolId, phoneNum, message, EMPTY);
	}

	@Override
	public boolean send(String schoolId, String phoneNum, String message, String userId) {
		logger.debug(schoolId + "..." + phoneNum + "..." + message + "..." + userId);
		SMSBean sms = new SMSBean();
		sms.setCompanyId(schoolId);
		sms.setPhoneNum(phoneNum);
		sms.setMessage(message);
		if (SYSTEM_NAME.equals(schoolId))
			sms.setSourceCmp(ONE);
		else
			sms.setSourceCmp(TWO);
		// 压入队列
		if (myCacheService == null) {

			// Jedis jedis = new Jedis("", 6379);
			// jedis.auth("");
			// jedis.select(2);
			// jedis.rpush("SMSList", JSON.toJSONString(sms));
			// System.out.println(JSON.toJSONString(sms));
			// jedis.close();

			logger.error(">>>>>缓存服务没有定义...xxx" + JSON.toJSONString(sms));
		} else {
			myCacheService.addObjectInList(channelKey, JSON.toJSONString(sms), false);
		}
		// 直接保存到短信队列
		return true;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		UserSMSSendServiceImpl sms = new UserSMSSendServiceImpl();
		sms.send("123456789", "15057177411", "【千校云】尊敬的用户，您的验证码为123456，本次验证码30分钟内有效，感谢您的使用。");
	}
}
