package org.cnsoft.framework.mybatis;

import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * SQL数据源管理器
 * 
 * @author CNSoft
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
