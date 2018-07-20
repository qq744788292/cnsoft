package org.zmsoft.jfp.framework.common.mail;

import java.util.HashMap;
import java.util.Map;

import org.zmsoft.jfp.framework.utils.EmptyHelper;

/**
 * 邮件模版设置
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class MailTemplateConfig {

	/**
	 * 日常监听业务
	 */
	Map<String, String> templateConfig =  new HashMap<String, String> ();

	public Map<String, String> getTemplateConfig() {
		return templateConfig;
	}

	public void setTemplateConfig(Map<String, String> templateConfig) {
		this.templateConfig = templateConfig;
	}

	/**
	 * 获得一个模版的内容
	 * @param typeId
	 * @return
	 */
	public String getTemplate(String typeId) {
		return templateConfig.get(typeId);
	}	

	/**
	 * 基于模版进行信息转换
	 * @param typeId 模版ID
	 * @param param 模版内使用的参数
	 * @return 发送结果
	 */
	public String getMessageWithTemplate(String typeId, Object ... param){
		String temp = getTemplate(typeId);
		if(EmptyHelper.isNotEmpty(temp)){
			return String.format(temp,param);
		}
		return typeId + " ... 不存在";
	}
}
