package org.zmsoft.framework.common.buzzinezz;

/**
 * 定时作业任务处理
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * 
 */
public interface ISTaskSupport {
	
	/**
	 * 业务处理开始动作
	 */
	boolean beforeProcess() throws Exception;
	
	/**
	 * 业务处理结束动作
	 */
	boolean afterProcess() throws Exception;
}
