package org.zmsoft.jfp.framework.biz;

/**
 * 定时作业任务处理
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public interface ISTask {
	
	/**
	 * 业务处理(重复运行)
	 */
	boolean doProcessRepeat() throws Exception;
	
	/**
	 * 业务处理(运行一次)
	 */
	boolean doProcessOnce(Object param) throws Exception;
}
