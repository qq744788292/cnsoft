package org.zmsoft.manager.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.config.ISManagerNameTransSupport;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;

@Component("myManagerNameTransSupport")
public class MyManagerNameTransSupportImpl extends MyTokenCommonSupport implements ISManagerNameTransSupport {
	/**
	 * 超级管理员账号
	 */
	@Value("${rool.admin}")
	private String supperAdmin = "admin";

	public static final String CACHE_KEY_USER = "CACHE:USER:";

	//@Resource
	//private SysUserDao sysUserDao;

	@Override
	public String converName(String userId) {
		if (supperAdmin.equals(userId)||"kltadmin".equals(userId))
			return "超级管理员";
		if (SYSTEM.equals(userId))
			return "系统管理员";

		try {
			String name = (String) myCacheService.getObject(CACHE_KEY_USER + userId, false);
			if (EmptyHelper.isEmpty(name)) {
//				SysUserDBO sysUser = new SysUserDBO();
//				sysUser.setId(userId);
//				sysUser = sysUserDao.doRead(sysUser);
//				if(EmptyHelper.isEmpty(sysUser))
//					return userId;
//				myCacheService.putObject(CACHE_KEY_USER + userId, sysUser.getUserName(), 3600, false);
//
//				return sysUser.getUserName();
			}
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.getMessage()+myCacheService);
		}

		return "系统管理员";
	}

}
