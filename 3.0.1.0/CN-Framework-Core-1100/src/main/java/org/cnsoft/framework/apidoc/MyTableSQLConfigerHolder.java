package org.cnsoft.framework.apidoc;

import java.util.LinkedHashSet;
import java.util.Set;

import org.cnsoft.framework.config.MyConfigerHolderSupport;

/**
 * 当前项目下面所有SQL类名称集合
 * 
 * @author CNSoft
 * @see <MyDBTableSQLSupport>
 */
public class MyTableSQLConfigerHolder extends MyConfigerHolderSupport {

	private static Set<String> myTableSQLs = new LinkedHashSet<String>(50);

	public static Set<String> getTableSQLs() {
		return myTableSQLs;
	}

	public static void setTableSQLs(Set<String> myTableSQLs) {
		MyTableSQLConfigerHolder.myTableSQLs = myTableSQLs;
	}

	public static void addTableSQLs(String myTableSQL) {
		if (logger.isDebugEnabled())
			logger.debug("====tableDat>>>>>" + myTableSQL);
		MyTableSQLConfigerHolder.myTableSQLs.add(myTableSQL);
	}
}
