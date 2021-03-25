package org.cnsoft.framework.support;

import org.cnsoft.framework.common.ISLoginer;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.framework.utils.RandomHelper;

/**
 * 数据业务操作接口定义超类<br>
 * 事务性质业务逻辑<br>
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class MyBusinessSupport extends MyServiceSupport implements ISLoginer {

	/**
	 * 根据手机号获取验证码
	 * 
	 */
	public String loadPhoneNumberSecurityCode(String phoneNumber) {
		String code = RandomHelper.getRandomNumerical(4);
		logger.debug("loadPhoneNumberSecurityCode=====phoneNumber>>>>>" + phoneNumber);
		myCacheService.putObject(PHONE_SECURITY_CODE + phoneNumber, code, 600);
		return code;
	}

	/**
	 * 校验手机号和验证码
	 */
	public boolean checkPhoneNumSecurityCode(String phoneNumber, String curCode, String token) {
		try {
			if (EmptyHelper.isEmpty(curCode))
				return false;
			String secCode = myCacheService.getObject(PHONE_SECURITY_CODE + phoneNumber);
			boolean check = secCode.equals(curCode);
			if (check)
				myCacheService.removeKey(PHONE_SECURITY_CODE + token);
			logger.debug("checkPhoneNumSecurityCode=====curCode>>>>>" + curCode + "=====SecurityCode>>>>>" + secCode);
			return check;
		} catch (Exception e) {
			return false;
		}
	}

}
