package org.zmsoft.jfp.framework.biz.custom;

import org.zmsoft.jfp.framework.biz.common.ISProcess;

/**
 * 业务处理过程
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public interface ISProcessCustomBusiness extends ISProcess {

	/**
	 * 业务处理前
	 */
	boolean beforeProcess() throws Exception;

	/**
	 * 业务处理中
	 */
	boolean doProcess() throws Exception;

	/**
	 * 业务处理后
	 */
	boolean afterProcess() throws Exception;
}
