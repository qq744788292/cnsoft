package org.zmsoft.framework.support;

import org.zmsoft.framework.config.ISManagerNameTransSupport;
import org.zmsoft.framework.holder.SpringContextHolder;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 名称转换器称转换
 * 
 * @author spookfcy
 *
 */
public class MyManagerNameTransHelper extends MyFrameWorkSupport {

	public static String convertName(String convertServiceName, String id) {
		if (EmptyHelper.isNotEmpty(id)) {
			ISManagerNameTransSupport _ISManagerNameTransSupport_ = SpringContextHolder.getBean(convertServiceName);
			if (EmptyHelper.isNotEmpty(_ISManagerNameTransSupport_)) {
				return _ISManagerNameTransSupport_.converName(id);
			}
		}

		return id;
	}
}
