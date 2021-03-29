package org.cnsoft.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnsoft.framework.cache.session.SessionHelper;
import org.cnsoft.framework.utils.EmptyHelper;
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
public class MyPageControllerSupport extends MyControllerSupport {
	/**
	 * 页面返回（token自动添加）
	 * 
	 * @param viewName
	 * @return
	 */
	public ModelAndView getModelAndView(String viewName) {
		return getModelAndView(null, viewName);
	}

	/**
	 * 页面返回（token自动添加）
	 * 
	 * @param viewName
	 * @return
	 */
	public ModelAndView getModelAndView(HttpServletRequest request, String viewName) {
		ModelAndView model = new ModelAndView(viewName);
		// 获得请求头参数
		String token = null;
		if (EmptyHelper.isEmpty(request))
			token = SessionHelper.getSessionAttribute().getToken();
		else
			token = MyHttpRequestHelper.loadToken(request);

		model.addObject("token", token);
		return model;
	}

	// 游客校验等级0-不检查，1-仅仅获取信息，3-有效Token，5-拒绝游客
	protected int allowGuest = 9;

	/**
	 * 校验游客(disableGuest = true:不允许)
	 * 
	 * @see #disableGuest
	 */
	public boolean doCheckToken(HttpServletRequest request, String jobId, String bizToken) throws Exception {
		if (allowGuest == 0) {
			return true;
		}
		//获取用户信息
		if (allowGuest == 1) {
			super.doCheckToken(request, jobId, bizToken);
			return true;
		}
		// 登录检测
		if (allowGuest > 3) {
			return super.doCheckToken(request, jobId, bizToken);
		}
		//游客
		if (allowGuest > 5) {
			return super.doCheckGuest(request, jobId, bizToken);
		}
		return false;
	}
	

	/**
	 * 用户权限检查
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @see #doCheckToken
	 */
	public boolean doCheckRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//游客
		if (allowGuest > 3) {
			return super.doCheckRole(request, response);
		}
		return true;
	}
}
