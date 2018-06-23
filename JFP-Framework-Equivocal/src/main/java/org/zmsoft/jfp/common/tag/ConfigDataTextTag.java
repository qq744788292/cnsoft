package org.zmsoft.jfp.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.zmsoft.jfp.common.cache.ISCacheData;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.persistent.common.SystemParameter.SystemParameterDBO;

/**
 * 业务分类数据显示文本
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class ConfigDataTextTag extends TagSupport implements IFrameworkConstants {

	private static final long serialVersionUID = 4070563013716274089L;
	private ISCacheData<SystemParameterDBO> SystemParameterService_;

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();

			SystemParameterService_ = BeanFactoryHelper.getBean("SystemParameterService");
			if (EmptyHelper.isEmpty(getConfigType()))
				out.println("");
			else if (EmptyHelper.isEmpty(getConfigKey()))
				out.println("");
			else
				out.println(SystemParameterService_.loadCacheData(configDate).getValue());
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

	private SystemParameterDBO configDate = new SystemParameterDBO();

	public String getConfigType() {
		return configDate.getType();
	}

	public void setConfigType(String configType) {
		this.configDate.setType(configType);
	}

	public String getConfigKey() {
		return configDate.getKey();
	}

	public void setConfigKey(String configKey) {
		this.configDate.setKey(configKey);
	}
}
