package org.zmsoft.jfp.framework.biz.common;

import java.sql.ResultSet;

/**
 * 数据整理
 * 
 * @author zmsoft
 * @version 3.4.1
 * @since 3.4.1 2016/11/1
 * 
 */
public interface ISPrepare {

	/**
	 * 业务处理
	 */
	boolean doPrepare(ResultSet resultSet) throws Exception;
}
