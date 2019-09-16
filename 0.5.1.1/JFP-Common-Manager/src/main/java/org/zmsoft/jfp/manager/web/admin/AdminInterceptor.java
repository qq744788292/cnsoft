package org.zmsoft.jfp.manager.web.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.jfp.framework.support.MyInterceptorAdapterSupport;
import org.zmsoft.jfp.framework.utils.PasswordHelper;

/**
 * 系统默认管理员
 * 
 * @author zmsoft
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @since 3.2.1 2016/08/28
 *
 */
public class AdminInterceptor extends MyInterceptorAdapterSupport {
	String zmsoft;
	String password;

	public String getzmsoft() {
		return zmsoft;
	}

	public void setzmsoft(String zmsoft) {
		this.zmsoft = zmsoft;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(MD5SecurityHelper.encrypt("46f94c8de14fb"));
	}

	public boolean doCheckAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String usr = request.getParameter(CONSTANT_LOGINER);
		String pwd = request.getParameter(CONSTANT_PASSWORD);
		try {
			if (zmsoft.equals(usr) && password.equals(PasswordHelper.getPassword(pwd))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
