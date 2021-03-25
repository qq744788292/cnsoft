package org.cnsoft.framework.config;

import org.cnsoft.framework.beans.FrameworkDataBean;
import org.cnsoft.framework.constants.ICDBConstants;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MyConfigerHolderSupport extends FrameworkDataBean implements ICFrameworkConstants, ICDBConstants {
	// 日志
	protected static Logger logger = LoggerFactory.getLogger(MyConfigerHolderSupport.class);

}
