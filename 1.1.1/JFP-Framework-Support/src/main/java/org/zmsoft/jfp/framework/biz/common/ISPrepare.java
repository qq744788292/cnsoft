package org.zmsoft.jfp.framework.biz.common;

import java.sql.ResultSet;

/**
 * 数据整理
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public interface ISPrepare {

	/**
	 * 业务处理
	 */
	boolean doPrepare(ResultSet resultSet) throws Exception;
}
