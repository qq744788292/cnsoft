package org.zmsoft.jfp.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.zmsoft.jfp.common.cache.ConfigCacheService;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.persistent.common.ConfigData.ConfigDataDBO;

/**
 * 数据字典下拉框
 * 
 * @author zmsoft
 * @version 4.1.3 2017/07/12
 *
 */
public class ConfigDataBoxTag extends TagSupport implements IFrameworkConstants {

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
			sb.append("<select");
			////////////////////////////////
			if (EmptyHelper.isEmpty(clazz))
				clazz = "";
			else
				clazz = " class=\"" + clazz + "\"";
			sb.append(clazz);

			////////////////////////////////
			if (EmptyHelper.isEmpty(onclick))
				onclick = "";
			else
				onclick = " onclick=\"" + onclick + "\"";
			sb.append(onclick);

			////////////////////////////////
			if (EmptyHelper.isEmpty(onchange))
				onchange = "";
			else
				onchange = " onchange=\"" + onchange + "\"";
			sb.append(onchange);

			////////////////////////////////
			if (EmptyHelper.isEmpty(name))
				name = "";
			else
				name = " name=\"" + name + "\"";
			sb.append(name);

			////////////////////////////////
			if (EmptyHelper.isEmpty(id))
				id = "";
			else
				id = " id=\"" + id + "\"";
			sb.append(id);
			////////////////////////////////
			if (EmptyHelper.isEmpty(style))
				style = "";
			else
				style = " style=\"" + style + "\"";
			sb.append(style);

			sb.append(">");

			//缓存
			ConfigCacheService _ConfigDataCacheService_ = BeanFactoryHelper.getBean("ConfigDataCacheService");
			//直接从数据库里读取
			List<ConfigDataDBO> configDatas = _ConfigDataCacheService_.getConfigDatasFromDB(configType);


			String custom_data;
			// 是否默认添加第一条,比如 --请选择--
			if (EmptyHelper.isNotEmpty(firstOption)) {
				sb.append("<option value>" + firstOption + "</option>");
			}
			for (ConfigDataDBO item : configDatas) {
				sb.append("<option");
				// 自定义数据
				if (EmptyHelper.isNotEmpty(data)) {
					String val = BeanUtils.getProperty(item, data);
					if (EmptyHelper.isEmpty(val))
						val = "";
					custom_data = " custom-data='" + val + "'";
					sb.append(custom_data);
				}

				// 默认数据
				{
					sb.append(" value=\"" + item.getKey());
					sb.append("\"");
					if (item.getKey().equals(configKey))
						sb.append(" selected ");
					sb.append(">");
				}
				sb.append(item.getValue());
				sb.append("</option>");
			}

			sb.append("</select>");

			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>ConfigDataTag标签渲染出错，请配置数据字典</span>");
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
	private String onclick;
	private String onchange;
	private String clazz;
	private String style;
	private String name;
	private String data;
	private String firstOption;

	public String getFirstOption() {
		return firstOption;
	}

	public void setFirstOption(String firstOption) {
		this.firstOption = firstOption;
	}

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

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
