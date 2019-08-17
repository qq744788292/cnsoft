package org.zmsoft.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 容器级别下面的YML配置参数读取
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
//@Service("MyYmlConfigSupport")
public class MyYmlConfigSupport {
	//@Value("${rool.check}")
	private boolean roolCheck = false;// 服务名称（项目名称）

	public boolean isRoolCheck() {
		return roolCheck;
	}
	
}
