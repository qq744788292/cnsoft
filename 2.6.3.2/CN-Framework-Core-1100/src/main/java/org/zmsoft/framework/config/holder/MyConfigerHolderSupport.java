package org.zmsoft.framework.config.holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.framework.beans.common.FrameworkDataBean;
import org.zmsoft.framework.constants.ICDBConstants;
import org.zmsoft.framework.constants.ICFrameworkConstants;

public abstract class MyConfigerHolderSupport extends FrameworkDataBean implements ICFrameworkConstants, ICDBConstants {
	// 日志
	protected static Logger logger = LoggerFactory.getLogger(MyConfigerHolderSupport.class);

}
