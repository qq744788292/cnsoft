package org.cnsoft.framework.config;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.config.system.utils.MyConfigHelper;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.job.AJobTaskSupport;
import org.cnsoft.framework.job.MyQuartzTaskManagement;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.framework.utils.PKHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 版权声明文件
 * 
 * @author CnSoft
 * @version 3.0.1 2021/02/25
 * @since 2.1.0 2018/8/22
 */
@Service("ISystemConfig")
public class ZMSystemConfig implements ISystemConfig, ICFrameworkConstants {
	protected static Logger logger = LoggerFactory.getLogger(ZMSystemConfig.class);

	@Value("${server.port}")
	private String port;// 实体Bean

	@Value("${model.id}")
	protected String myModelId = "ID";// 服务器ID

	private String version = "3.0.1";

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

//	@Async("threadPoolTaskExecutor")
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
