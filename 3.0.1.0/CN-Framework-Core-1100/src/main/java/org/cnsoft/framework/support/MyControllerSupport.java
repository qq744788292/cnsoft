package org.cnsoft.framework.support;

import org.cnsoft.framework.cache.session.SessionHelper;
import org.springframework.web.servlet.ModelAndView;

/**
 * 接口控制层超类<br>
 * 需要用户登录
 * 
 * @author CNSoft
 * @version 2.6.2 2019/10/10
 * @since 2.0.0 2018/10/10
 * @see <MyPageControllerSupport>
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
