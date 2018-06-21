package org.zmsoft.jfp.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.zmsoft.jfp.common.cache.ConfigCacheService;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

/**
 * 数据字典下拉框
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class ConfigDataTextTag extends TagSupport implements IFrameworkConstants {

	private static final long serialVersionUID = 4070563013716274089L;

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();

			ConfigCacheService _ConfigDataCacheService_ = BeanFactoryHelper.getBean("ConfigDataCacheService");
			if (EmptyHelper.isEmpty(configType))
				out.println("");
			else if (EmptyHelper.isEmpty(configKey))
				out.println("");
			else
				out.println(_ConfigDataCacheService_.loadConfigData(configType, configKey).getValue());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("ConfigDataTag标签渲染出错，请配置数据字典");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	/////////////////////////////////////////////////////////////////////////////
	private String configType;
	private String configKey;

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

}
