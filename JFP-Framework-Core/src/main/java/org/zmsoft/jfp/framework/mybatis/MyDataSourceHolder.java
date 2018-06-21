package org.zmsoft.jfp.framework.mybatis;
/**
 * SQL数据源管理器
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * @see <MyDataSourceManager><MyFrameworkQueryPlugin><ISDataSourceName>
 */
public class MyDataSourceHolder {
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();

	public static void putDataSourceName(String name) {
		holder.set(name);
	}

	public static String getDataSourceName() {
		return holder.get();
	}

}
