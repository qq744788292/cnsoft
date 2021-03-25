package org.zmsoft.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.zmsoft.config.system.utils.MyConfigHelper;
import org.zmsoft.framework.ISystemConfig;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.job.MyQuartzTaskManagement;
import org.zmsoft.framework.job.task.AJobTaskSupport;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.PKHelper;

/**
 * 版权声明文件
 * 
 * @author CnSoft
 * @version 2.6.2.6 2019/11/09
 * @since 0.1.0 2018/8/22
 */
@Service("ISystemConfig")
public class ZMSystemConfig implements ISystemConfig, ICFrameworkConstants {
	protected static Logger logger = LoggerFactory.getLogger(ZMSystemConfig.class);

	@Value("${server.port}")
	private String port;// 实体Bean

	@Value("${model.id}")
	protected String myModelId = "ID";// 服务器ID

	private String version = "2.6.3";

	public void start() throws Exception {
		loadPort();
		loadVersion();
		loadQuartzManagement();
		MyConfigHelper.prepareSystemConfig(true);
	}

	public void run() throws Exception {
		loadPort();
		loadVersion();
	}

	public void loadPort() {
		// 初始化服务器ID
		PKHelper.setServerId(myModelId);
	}

	public void loadVersion() {
		System.out.println("");
		// 初始化端口和模式
		System.out.println("    当前框架版本号：" + version + "   项目主页https://gitee.com/zzzmmmsoft/jfp");
		System.out.println("        ________  __            __ _   ");
		System.out.println("       |___  /  \\/  |          / _| |  ");
		System.out.println("          / /| \\  / |___  ___ | |_| |_ ");
		System.out.println("         / / | |\\/| / __|/ _ \\|  _| __|");
		System.out.println("        / /__| |  | \\__ \\ (_) | | | |_ ");
		System.out.println("       /_____|_|  |_|___/\\___/|_|  \\__|");
		System.out.println("");
		System.out.println("    本服务端口：" + port + "  本服务编号：" + myModelId);
		System.out.println("");
	}

	public void loadQuartzManagement() {
		try {
			AJobTaskSupport job = MyBeanFactoryHelper.getBean(MyQuartzTaskManagement.Quartz_Management_Name);
			if (EmptyHelper.isEmpty(job)) {
				logger.warn("当前模式下定时任务管理器没有实现......[" + MyQuartzTaskManagement.Quartz_Management_Name + "]");
			} else {
				job.doProcess();
			}
		} catch (Exception e) {
			// logger.error("定时任务管理加载失败", e);
		}
	}
}
