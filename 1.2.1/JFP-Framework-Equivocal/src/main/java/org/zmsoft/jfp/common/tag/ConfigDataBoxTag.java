package org.zmsoft.jfp.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.zmsoft.jfp.framework.cache.ISCacheData;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.constants.ITagConstants;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.persistent.common.SystemClassify.SystemClassifyDBO;
import org.zmsoft.jfp.persistent.common.SystemParameter.SystemParameterDBO;

/**
 * 业务分类数据下拉框
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class ConfigDataBoxTag extends TagSupport implements IFrameworkConstants, ITagConstants {

	private static final long serialVersionUID = 4070563013716274089L;// 缓存

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

			// 是否默认添加第一条,比如 --请选择--
			if (EmptyHelper.isNotEmpty(firstOption)) {
				sb.append("<option value>").append(firstOption).append("</option>");
			}
			// 缓存
			// 直接从数据库里读取
			if (TAG_PARAMETER.equals(tableName)) {
				ISCacheData<SystemParameterDBO> SystemParameterService_ = BeanFactoryHelper.getBean("SystemParameterCacheService");
				SystemParameterDBO configDate = new SystemParameterDBO();
				configDate.setType(type);
				configDate.setDelFlag(ZERO);
				List<SystemParameterDBO> configDatas = SystemParameterService_.getDataFromDB(configDate);

				for (SystemParameterDBO item : configDatas) {
					sb.append("<option");
					// 默认数据
					{
						sb.append(" value=\"" + item.getKey());
						sb.append("\"");
						if (item.getKey().equals(value))
							sb.append(" selected ");
						sb.append(">");
					}
					sb.append(item.getValue());
					sb.append("</option>");
				}
			} else if (TAG_CLASSIFY.equals(tableName)) {
				ISCacheData<SystemClassifyDBO> SystemClassifyService_ = BeanFactoryHelper.getBean("SystemClassifyCacheService");
				SystemClassifyDBO systemClassifyDBO = new SystemClassifyDBO();
				systemClassifyDBO.setDelFlag(ZERO);
				List<SystemClassifyDBO> configDatas = SystemClassifyService_.loadCacheDatas(systemClassifyDBO);
				for (SystemClassifyDBO item : configDatas) {
					sb.append("<option");
					// 默认数据
					{
						sb.append(" value=\"" + item.getPuk());
						sb.append("\"");
						if (item.getPuk().equals(value))
							sb.append(" selected ");
						sb.append(">");
					}
					sb.append(item.getClassifyName());
					sb.append("</option>");
				}
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
	private String onclick;
	private String onchange;
	private String clazz;
	private String style;
	private String name;
	private String firstOption;
	private String tableName;
	private String type;
	private String value;

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

	public String getFirstOption() {
		return firstOption;
	}

	public void setFirstOption(String firstOption) {
		this.firstOption = firstOption;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
