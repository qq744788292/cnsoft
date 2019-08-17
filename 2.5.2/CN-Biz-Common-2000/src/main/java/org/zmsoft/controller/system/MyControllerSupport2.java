package org.zmsoft.controller.system;

import org.zmsoft.framework.support.MyControllerSupport;

/**
 * 接口控制层超类<br>
 * 需要用户登录
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public class MyControllerSupport2 extends MyControllerSupport {
	
	//是否开启游客访问 （true:不允许）
	protected boolean disableGuest = true;
	public void setDisableGuest(boolean disableGuest) {
		this.disableGuest = disableGuest;
	}

	/**
	 * 校验游客(disableGuest = true:不允许)
	 * @see #disableGuest
	 */
	public boolean doCheckToken(String bizToken) throws Exception {
		boolean result = super.doCheckToken(bizToken);
		if (result == true && disableGuest == true && doCheckGuest(bizToken) == true) {
			return false;
		}
		return result;
	}
}
