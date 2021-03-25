package org.cnsoft.framework.apidoc;

import org.cnsoft.framework.config.MyConfigerHolderSupport;

/**
 * 当前项目下面所有ApiDoc类名称集合
 * 
 * @see <MyApiDocSupport><ApiDocListController>
 */
public class MyApiDocConfiger extends MyConfigerHolderSupport {
	public static boolean openDoc = true;

	public static void addApiClassName(String apiClassName) throws Exception {
		if (openDoc)
			MyApiListHolder.addApiClassName(apiClassName);
	}

	public static void addTableSQLs(String myTableSQL) throws Exception {
		if (openDoc)
			MyTableSQLConfigerHolder.addTableSQLs(myTableSQL);
	}

	public static void addTableDats(String tableDat) throws Exception {
		if (openDoc)
			MyDatConfigerHolder.addTableDats(tableDat);
	}

}
