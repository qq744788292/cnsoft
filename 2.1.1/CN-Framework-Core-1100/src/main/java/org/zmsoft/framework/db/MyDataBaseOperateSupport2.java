package org.zmsoft.framework.db;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.FrameworkDataBean;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.common.ILoginer;

/**
 * 数据持久层超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class MyDataBaseOperateSupport2<T> extends MyDataBaseOperateSupport<T> implements ILoginer {

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
		// formParamBean;
		// String companyType = ((XXXXXDBO)data).getT01();
		// //分表处理
		// if (ZERO.equals(companyType)) {
		// data.setTableName("0");
		// } else if (ONE.equals(companyType)) {
		// data.setTableName("1");
		// }
		// if (dbType != DB_SELECT) {
		//
		// }
		// }
	}
}
