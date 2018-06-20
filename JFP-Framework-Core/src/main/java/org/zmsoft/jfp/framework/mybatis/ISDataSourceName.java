package org.zmsoft.jfp.framework.mybatis;

/**
 * 获得数据源名称
 * @author zmsoft
 *
 * @see <MyDataSourceManager><MyDataSourceHolder><MyDataBaseOperateSupport>
 */
public interface ISDataSourceName {
	/**
	 * 获得当前数据库名称<br>
	 * 参考<spring-custom.xml>、<spring-db.xml # myDateSourceManager>
	 * 
	 */
	public String currentDataSourceName() ;
}
