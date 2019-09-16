package org.zmsoft.jfp.framework.mybatis;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * SQL数据源管理器
 * 
 * @author zmsoft
 * 
 * @since 0.1 2012-7-13
 * @version 0.1
 * @version 2.1.2.20150422
 * @see <MyFrameworkQueryPlugin><MyDataSourceHolder><ISDataSourceName>
 */
public class MyDataSourceManager extends AbstractRoutingDataSource implements IFrameworkConstants {
	
	/**
	 * 切换数据源
	 */
	protected Object determineCurrentLookupKey() {
		return MyDataSourceHolder.getDataSourceName();
	}

}
