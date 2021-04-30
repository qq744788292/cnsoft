package org.cnsoft.tag.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.cnsoft.common.cache.SystemDictionaryCacheService;
import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.beans.common.KeyValueBean;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.persistent.common.SystemDictionary.SystemDictionaryDBO;
import org.cnsoft.tag.AMyTagSupport;

/**
 * 业务分类数据下拉框
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class DictionaryDataBoxTag extends AMyTagSupport {

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
			
			if (EmptyHelper.isNotEmpty(options)) {
				for (KeyValueBean item : options) {
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
			}else if (EmptyHelper.isNotEmpty(dicType)) {
				//寻找数据字典配置
				SystemDictionaryCacheService sdc = MyBeanFactoryHelper.getBean(SystemDictionaryCacheService.class);
				List<SystemDictionaryDBO> loadDictionariess = sdc.loadDictionariess(true, dicType);
				if (EmptyHelper.isNotEmpty(loadDictionariess)) {
					for (SystemDictionaryDBO item : loadDictionariess) {
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
				}
			}

			sb.append("</select>");

			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.write("<span>ConfigDataBoxTag标签渲染出错，请配置数据字典场所options</span>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	/////////////////////////////////////////////////////////////////////////////
	private String onclick;
	private String value = EMPTY;
	private String onchange;
	private String clazz;
	private String style;
	private String name;
	private String firstOption;
	private String dicType;
	private List<KeyValueBean> options;

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getDicType() {
		return dicType;
	}

	public void setDicType(String dicType) {
		this.dicType = dicType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public List<KeyValueBean> getOptions() {
		return options;
	}

	public void setOptions(List<KeyValueBean> options) {
		this.options = options;
	}

}
