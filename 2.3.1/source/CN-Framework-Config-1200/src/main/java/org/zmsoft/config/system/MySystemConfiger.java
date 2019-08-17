package org.zmsoft.config.system;

import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.common.ISConfig;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.PKHelper;

/**
 * 系统配置初始化
 */
@Component
public class MySystemConfiger {
	protected static Logger logger = LoggerFactory.getLogger(MySystemConfiger.class);
	
	@Bean
	public MySystemConfiger init() throws Exception{
		
		//初始化端口和模式
		logger.info("=====>>>>>本服务端口=====>>>>>" + port);
		logger.info("=====>>>>>本服务编号 =====>>>>>" + myModelId);

		//初始化服务器ID
		preparePK();
		
		//初始化系统配置（配置中心）
		prepareCG();
		
		return new MySystemConfiger();
	}

	
	@Value("${server.port}")
	private String port;// 实体Bean

	@Value("${model.id}")
	protected String myModelId = "ID";// 服务器ID

	public void preparePK() {
		PKHelper.setServerId(myModelId);
	}
	
	/**
	 * 在具体子类初始化之后 确认该对象是否是对应父类（ISConfig）的子类
	 * @throws Exception 
	 */
	public void prepareCG() throws Exception {

		logger.debug("=====>>>>>开始加载系统参数=====>>>>>");
		// 遍历所有实现类
		for (Entry<String, ISConfig> item : MyBeanFactoryHelper.getBeans(ISConfig.class).entrySet()) {
			ISConfig config = item.getValue();
			// 获取实现类的所有注解
			logger.debug("========加载系统配置>>>>>>>>"+config);
			config.init();
		}
		logger.debug("<<<<<=====成功加载系统参数<<<<<=====");
	}

}