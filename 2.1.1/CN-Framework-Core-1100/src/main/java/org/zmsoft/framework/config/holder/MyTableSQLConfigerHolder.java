package org.zmsoft.framework.config.holder;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前项目下面所有SQL类名称集合
 * 
 * @author spookfcy
 * @see <MyDBTableSQLSupport>
 */
public class MyTableSQLConfigerHolder {

	private static List<String> myTableSQLs = new ArrayList<String>();

	public static List<String> getMyTableSQLs() {
		return myTableSQLs;
	}

	public static void setMyTableSQLs(List<String> myTableSQLs) {
		MyTableSQLConfigerHolder.myTableSQLs = myTableSQLs;
	}

	public static void addMyTableSQLs(String myTableSQL) {
		MyTableSQLConfigerHolder.myTableSQLs.add(myTableSQL);
	}
}
