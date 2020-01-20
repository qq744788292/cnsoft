package org.zmsoft.service.player;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 短信模板
 */
public interface ISPlayerSMSCodeService extends ICFrameworkConstants {

	/**
	 * 获取验证码短信
	 * @param userPhone
	 * @param roolType
	 * @return
	 * @throws Exception
	 */
	public String loadCheckCodeMessage(UserBean user) throws Exception;

}
