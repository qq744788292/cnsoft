package org.zmsoft.jfp.framework.support;

import org.zmsoft.jfp.framework.cache.session.SessionHelper;
import org.springframework.web.servlet.ModelAndView;

/**
 * 接口控制层超类<br>
 * 需要用户登录
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class MyControllerSupport extends MyTokenCommonSupport {

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
}
