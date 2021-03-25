package org.zmsoft.framework.support;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面控制层超类<br>
 * 需要用户操作权限
 * 
 * @author ZmSoft
 * @version 2.6.2 2019/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public class MyPageControllerSupport extends MyControllerSupport {
	
	//是否开启游客访问 （true:允许）
	protected boolean allowGuest = false;

	/**
	 * 校验游客(disableGuest = true:不允许)
	 * @see #disableGuest
	 */
	public boolean doCheckToken(HttpServletRequest request, String jobId, String bizToken) throws Exception {
		boolean result = super.doCheckToken(request, jobId, bizToken);
		if (result == true && allowGuest == false && doCheckGuest(request, jobId, bizToken) == true) {
			return false;
		}
		return result;
	}
}
