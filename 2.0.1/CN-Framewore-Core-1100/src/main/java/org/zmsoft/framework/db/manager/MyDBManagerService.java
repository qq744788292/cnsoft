package org.zmsoft.framework.db.manager;

import javax.annotation.Resource;

import org.zmsoft.framework.support.MyServiceSupport;

/**
 * 内部初始化数据库模式
 * 
 * @author ZmSoft
 * @since 0.1.0 2018/2/8
 * @version 0.1.0 2018/2/8
 */
public class MyDBManagerService extends MyServiceSupport{

	@Resource
	protected MyDBManagerDao myDBManagerDao;

	/**
	 * 创建空的数据库环境
	 */
	public boolean creat() throws Exception {
		try {
			myDBManagerDao.creat(false, true);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	/**
	 * 初始化数据库环境
	 */
	public boolean init() throws Exception {
		try {
		//	dao_.init();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	/**
	 * 重构数据库环境
	 */
	public boolean build() throws Exception {
		try {
			myDBManagerDao.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

}
