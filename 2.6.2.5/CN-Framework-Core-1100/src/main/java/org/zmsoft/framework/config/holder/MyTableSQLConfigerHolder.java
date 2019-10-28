package org.zmsoft.framework.config.holder;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 当前项目下面所有SQL类名称集合
 * 
 * @author ZMSoft
 * @see <MyDBTableSQLSupport>
 */
public class MyTableSQLConfigerHolder {

	private static Set<String> myTableSQLs = new LinkedHashSet<String>(50);

	public static Set<String> getMyTableSQLs() {
		return myTableSQLs;
	}

	public static void setMyTableSQLs(Set<String> myTableSQLs) {
		MyTableSQLConfigerHolder.myTableSQLs = myTableSQLs;
	}

	public static void addMyTableSQLs(String myTableSQL) {
		MyTableSQLConfigerHolder.myTableSQLs.add(myTableSQL);
	}
}
