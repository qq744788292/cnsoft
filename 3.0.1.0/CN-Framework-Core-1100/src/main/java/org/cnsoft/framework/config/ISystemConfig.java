package org.cnsoft.framework.config;

/**
 * 系统开发框架启动配置
 * 
 * @author CnSoft
 * @version 0.1.0 2018/8/22
 * @since 2.1.0 2018/8/22
 */
public interface ISystemConfig {

	/**
	 * 完整启动（加载配置）
	 * 
	 * @throws Exception
	 */
	void start() throws Exception;

	/**
	 * 简单运行
	 * 
	 * @throws Exception
	 */
	void run() throws Exception;

}