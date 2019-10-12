package org.zmsoft.framework.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * SQL数据源管理器
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <MyFrameworkQueryPlugin><MyDataSourceHolder><ISDataSourceName>
 */
public class MyDataSourceManager extends AbstractRoutingDataSource implements ICFrameworkConstants {
	
	/**
	 * 切换数据源
	 */
	protected Object determineCurrentLookupKey() {
		return MyDataSourceHolder.getDataSourceName();
	}

}
