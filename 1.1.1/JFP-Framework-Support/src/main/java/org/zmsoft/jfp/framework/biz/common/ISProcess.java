package org.zmsoft.jfp.framework.biz.common;

/**
 * 定时作业任务处理
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public interface ISProcess {

	/**
	 * 业务 数据准备
	 */
	boolean doProcess() throws Exception;
}
