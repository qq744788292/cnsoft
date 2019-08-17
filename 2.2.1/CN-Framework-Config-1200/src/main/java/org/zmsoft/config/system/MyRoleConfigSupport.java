package org.zmsoft.config.system;

import org.springframework.stereotype.Service;

/**
 * 容器级别下面的YML配置参数读取
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Service("MyRoleConfigSupport")
public class MyRoleConfigSupport {
	//@Value("${role.check}")
	private boolean roleCheck = false;// 服务名称（项目名称）

	public boolean isRoleCheck() {
		return roleCheck;
	}


}
