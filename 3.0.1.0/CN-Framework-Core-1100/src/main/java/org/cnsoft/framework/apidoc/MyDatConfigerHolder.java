package org.cnsoft.framework.apidoc;

import java.util.LinkedHashSet;
import java.util.Set;

import org.cnsoft.framework.config.MyConfigerHolderSupport;

/**
 * 当前项目下面所有dat初始化数据集合
 * 
 * @author CNSoft
 * @see <MyDBTableDatSupport>
 */
public class MyDatConfigerHolder extends MyConfigerHolderSupport {

	private static Set<String> myTableDats = new LinkedHashSet<String>(50);

	public static Set<String> getTableDats() {
		return myTableDats;
	}

	public static void setTableDats(Set<String> myTableDats) {
		MyDatConfigerHolder.myTableDats = myTableDats;
	}

	public static void addTableDats(String tableDat) {
		if (logger.isDebugEnabled())
			logger.debug("====tableDat>>>>>" + tableDat);
		MyDatConfigerHolder.myTableDats.add(tableDat);
	}
}
