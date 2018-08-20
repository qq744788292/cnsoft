package org.zmsoft.jfp.framework.search.bean;

import org.zmsoft.jfp.framework.beans.ObjectBean;

/**
 * 查询语句
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class QueryBean extends ObjectBean {

	/**
	 * 查询语句ID
	 */
	protected String id = "";
	/**
	 * 索引名字
	 */
	protected String index = "";
	/**
	 * 查询语句
	 */
	protected String dsl = "";

	/**
	 * 字段类型
	 */
	protected String mapping = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getDsl() {
		return dsl;
	}

	public void setDsl(String dsl) {
		this.dsl = dsl;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

}
