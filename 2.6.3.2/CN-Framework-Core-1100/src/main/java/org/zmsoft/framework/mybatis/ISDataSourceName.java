package org.zmsoft.framework.mybatis;

/**
 * 获得数据源名称
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <MyDataSourceManager><MyDataSourceHolder><MyDataBaseOperateSupport>
 */
public interface ISDataSourceName {
	/**
	 * 获得当前数据库名称<br>
	 * 参考<spring-custom.xml>、<spring-db.xml # myDateSourceManager>
	 * 
	 */
	public String currentDataSourceName();
}
