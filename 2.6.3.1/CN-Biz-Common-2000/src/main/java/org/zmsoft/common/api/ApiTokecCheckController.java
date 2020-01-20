package org.zmsoft.common.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;

/**
 * 游客检查
 * 
 * @author ZMSoft
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/token", method = { RequestMethod.POST })
public class ApiTokecCheckController extends MyTokenCommonSupport {

	/**
	 * 游客检查
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public RESTResultBean<UserBean> userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RESTResultBean<UserBean> result = new RESTResultBean<UserBean>();
		String token = super.loadToken(request);
		result.setCode(1);// 不是
		if (doCheckToken(token) && doCheckGuest(token))
			result.setCode(0);// 是游客
		// 返回最新用户数据
		result.setData(SessionHelper.currentUser());
		return result;
	}
}
