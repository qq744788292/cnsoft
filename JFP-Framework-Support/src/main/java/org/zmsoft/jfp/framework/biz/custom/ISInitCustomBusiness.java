package org.zmsoft.jfp.framework.biz.custom;

import org.zmsoft.jfp.framework.biz.common.ISInit;

/**
 * 业务初始化过程
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public interface ISInitCustomBusiness extends ISInit {

	/**
	 * 业务初始化前
	 */
	boolean beforeInit() throws Exception;

	/**
	 * 业务初始化中
	 */
	boolean doInit() throws Exception;

	/**
	 * 业务初始化后
	 */
	boolean afterInit() throws Exception;
}
