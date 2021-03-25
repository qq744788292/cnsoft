package org.cnsoft.tag.common;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.tag.AMyTagSupport;

/**
 * 业务分类数据显示文本
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class ConfigDataTextTag extends AMyTagSupport {

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
			StringBuilder sb = new StringBuilder();

//			if (TAG_PARAMETER.equals(tableName)) {
//				ASystemDataCacheSupport<SystemParameterDBO> _SystemParameterService_ = MyBeanFactoryHelper
//						.getBean(SystemParameterCacheService.class);
//				if (EmptyHelper.isEmpty(getConfigType()))
//					sb.append(EMPTY);
//				else if (EmptyHelper.isEmpty(getConfigKey()))
//					sb.append(EMPTY);
//				else
//					sb.append(_SystemParameterService_.loadCacheData(false, configDate).getValue());
//			} else if (TAG_CLASSIFY.equals(tableName)) {
//				ASystemDataCacheSupport<SystemClassifyDBO> _SystemClassifyService_ = MyBeanFactoryHelper
//						.getBean(SystemClassifyCacheService.class);
//				SystemClassifyDBO param = new SystemClassifyDBO();
//				param.setClassifyId(getConfigKey());
//				sb.append(_SystemClassifyService_.loadCacheData(false, param).getClassifyName());
//			} else if (TAG_CONFIG.equals(tableName)) {
//				ASystemDataCacheSupport<SystemConfigDBO> _SystemConfigCacheService_ = MyBeanFactoryHelper
//						.getBean(SystemConfigCacheService.class);
//				SystemConfigDBO param = new SystemConfigDBO();
//				param.setKey(getConfigKey());
//				sb.append(_SystemConfigCacheService_.loadCacheData(false, param).getValue());
//			}
			
			out.print(sb.toString());
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

//	private SystemParameterDBO configDate = new SystemParameterDBO();
//
//	public String getConfigType() {
//		return configDate.getType();
//	}
//
//	public void setConfigType(String configType) {
//		this.configDate.setType(configType);
//	}
//
//	public String getConfigKey() {
//		return configDate.getKey();
//	}
//
//	public void setConfigKey(String configKey) {
//		this.configDate.setKey(configKey);
//	}

	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
