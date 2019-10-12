package org.zmsoft.framework.mybatis.dialect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 默认分页工具<br>
 * 类似hibernate的Dialect,但只精简出分页部分
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <spring-db.xml>
 */
public class DefaultDialect implements ICFrameworkConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	// 数据库名字
	protected String dbName = "";
	// 自定义orderby
	private String supportsOrderby = " ";

	public String getSupportsOrderby() {
		return supportsOrderby;
	}

	public void setSupportsOrderby(String orderby) {
		if(BLANK.equals(orderby)) {
			this.supportsOrderby = BLANK;
			return;
		}
		if (EmptyHelper.isNotEmpty(orderby)) {
			if(orderby.toUpperCase().indexOf("ORDER BY")>-1){
				this.supportsOrderby = " " + orderby;
			}else{
				this.supportsOrderby = " ORDER BY " + orderby;
			}
		}		
	}

	public boolean supportsLimit() {
		return false;
	}

	public boolean supportsLimitOffset() {
		return supportsLimit();
	}

	/**
	 * 将sql变成分页sql语句,直接使用offset,limit的值作为占位符. </br> 源代码为:
	 * getLimitString(sql,offset
	 * ,String.valueOf(offset),limit,String.valueOf(limit))
	 */
	public String getLimitString(String sql, int offset, int limit) {
		return getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
	}

	/**
	 * 将sql变成分页sql语句,提供将offset及limit使用占位符(placeholder)替换.
	 * 
	 * <pre>
	 * 如mysql
	 * dialect.getLimitString("select * from user", 12, ":offset",0,":limit") 将返回
	 * select * from user limit :offset,:limit
	 * </pre>
	 * 
	 * @return 包含占位符的分页sql
	 */
	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		logger.error("当前配置不支持分页处理");
		return sql;
	}

}
