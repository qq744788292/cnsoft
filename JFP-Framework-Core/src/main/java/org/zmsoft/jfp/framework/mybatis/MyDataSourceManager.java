package org.zmsoft.jfp.framework.mybatis;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * SQL数据源管理器
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
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
