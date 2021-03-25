package org.zmsoft.framework.support;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.zmsoft.framework.beans.common.FrameworkDataBean;
import org.zmsoft.framework.cache.redis.master.MyCacheService;
import org.zmsoft.framework.constants.ICDBConstants;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.RandomHelper;

/**
 * 核心框架超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 1.0.0 2018/02/02
 * @see <ObjectBean>
 */
public class MyFrameWorkSupport extends FrameworkDataBean implements ICFrameworkConstants, ICDBConstants {
	// 日志
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	// 缓存中心
	@Resource
	protected MyCacheService myCacheService;

	@Value("${spring.application.name}")
	protected String myAppName = "ZMSoft";// 服务名称（项目名称）
	@Value("${model.id}")
	protected String myModelId = "10";// 服务名称（项目名称）

	public String getMyModelId() {
		return myModelId;
	}

	public String getMyAppName() {
		return myAppName + TRANSVERSE_LINE + myModelId;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 进程阻塞时间（分钟）
	 */
	protected int waitTimeMinute = 15;

	public void setWaitTimeMinute(int waitTimeMinute) {
		this.waitTimeMinute = waitTimeMinute;
		this.waitTimeSecond = 60 * this.waitTimeMinute;
	}

	/**
	 * 进程阻塞时间（秒）
	 */
	protected int waitTimeSecond = 60 * waitTimeMinute;

	public void setWaitTimeSecond(int waitTimeSecond) {
		this.waitTimeSecond = waitTimeSecond;
	}


	//////////////////////////////////////////////////////////

	/**
	 * 根据手机号获取验证码
	 * 
	 */
	public String loadPhoneNumberSecurityCode(String phoneNumber) {
		String code = RandomHelper.getRandomNumerical(4);
		logger.debug("loadPhoneNumberSecurityCode=====phoneNumber>>>>>" + phoneNumber);
		myCacheService.putObject(PHONE_SECURITY_CODE + phoneNumber, code, 600, false);
		return code;
	}

	/**
	 * 校验手机号和验证码
	 */
	public boolean checkPhoneNumSecurityCode(String phoneNumber, String curCode) {
		try {
			if (EmptyHelper.isEmpty(curCode))
				return false;
			String secCode = (String) myCacheService.getObject(PHONE_SECURITY_CODE + phoneNumber, false);
			boolean check = secCode.equals(curCode);
			if (check)
				myCacheService.removeKey(PHONE_SECURITY_CODE + super.getToken());
			logger.debug("checkPhoneNumSecurityCode=====curCode>>>>>" + curCode + "=====SecurityCode>>>>>" + secCode);
			return check;
		} catch (Exception e) {
			return false;
		}
	}

}
