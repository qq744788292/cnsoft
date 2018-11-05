package org.zmsoft.framework.config;

/**
 * 定时作业任务处理
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * 
 */
public interface ISProcess {

	/**
	 * 业务 数据准备
	 */
	boolean doProcess() throws Exception;
}
