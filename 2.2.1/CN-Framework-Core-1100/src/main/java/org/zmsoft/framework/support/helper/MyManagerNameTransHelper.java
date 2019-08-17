package org.zmsoft.framework.support.helper;

import org.zmsoft.framework.config.ISManagerNameTransSupport;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyFrameWorkSupport;
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
			ISManagerNameTransSupport _ISManagerNameTransSupport_ = MyBeanFactoryHelper.getBean(convertServiceName);
			if (EmptyHelper.isNotEmpty(_ISManagerNameTransSupport_)) {
				return _ISManagerNameTransSupport_.converName(id);
			}
		}

		return id;
	}
}
