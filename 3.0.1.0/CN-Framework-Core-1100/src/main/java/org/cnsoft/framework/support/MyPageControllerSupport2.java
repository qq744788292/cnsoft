package org.cnsoft.framework.support;

import javax.servlet.http.HttpServletRequest;

import org.cnsoft.framework.cache.session.SessionHelper;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面控制层超类<br>
 * 需要用户操作权限
 * 
 * @author CNSoft
 * @version 2.6.2 2019/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public class MyPageControllerSupport2 extends MyControllerSupport {
	
	/**
	 * 页面返回（token自动添加）
	 * 
	 * @param viewName
	 * @return
	 */
	public ModelAndView getModelAndView(HttpServletRequest request, String viewName) {
		ModelAndView model = new ModelAndView(viewName);
		model.addObject("token", MyHttpRequestHelper.loadToken(request, null));
		return model;
	}
	
	//是否开启游客访问 （true:允许）
	protected boolean allowGuest = true;

	/**
	 * 校验游客(disableGuest = true:不允许)
	 * @see #disableGuest
	 */
	public boolean doCheckToken(HttpServletRequest request, String jobId, String bizToken) throws Exception {
		super.doCheckToken(request, jobId, bizToken);
		return allowGuest;
	}
}
