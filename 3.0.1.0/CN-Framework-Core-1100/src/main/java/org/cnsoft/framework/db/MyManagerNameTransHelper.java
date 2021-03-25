package org.cnsoft.framework.db;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.common.buzzinezz.ISManagerNameTransSupport;
import org.cnsoft.framework.support.MyFrameWorkSupport;
import org.cnsoft.framework.utils.EmptyHelper;

/**
 * 名称转换器称转换
 * 
 * @author CNSoft
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
