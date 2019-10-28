package org.zmsoft.framework.support;

import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.framework.cache.session.SessionHelper;

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
	/**
	 * 页面返回（token自动添加）
	 * 
	 * @param viewName
	 * @return
	 */
	public ModelAndView getModelAndView(String viewName) {
		ModelAndView model = new ModelAndView(viewName);
		model.addObject("token", SessionHelper.getSessionAttribute().getToken());
		return model;
	}
	
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
