package org.cnsoft.framework.db.support.ext;

import org.cnsoft.framework.beans.FrameworkDataBean;
import org.cnsoft.framework.beans.user.UserBean;
import org.cnsoft.framework.cache.session.SessionHelper;
import org.cnsoft.framework.db.support.ADataBaseOperateSupport;

/**
 * 数据持久层超类
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class MyDataBaseOperateSupport2<T> extends ADataBaseOperateSupport<T> {

	/**
	 * 当前登录用户
	 */
	public UserBean currentUser() {
		UserBean loginer = SessionHelper.currentUser();
		if (loginer == null) {
			throw new RuntimeException("当前用户没有登录");
		}
		return loginer;
	}

	/**
	 * 数据库分表
	 * 
	 * @param data
	 */
	public void changeTable(FrameworkDataBean formParamBean, int dbType) {
		// if (formParamBean instanceof MyDataBaseObjectSupport) {
		// MyDataBaseObjectSupport data = (MyDataBaseObjectSupport)
		// formParamBean;d
	}
}
