package org.zmsoft.framework.config.holder;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 当前项目下面所有dat初始化数据集合
 * 
 * @author spookfcy
 * @see <MyDBTableDatSupport>
 */
public class MyDatConfigerHolder {

	private static Set<String> myTableDats = new LinkedHashSet<String>(50);

	public static Set<String> getMyTableDats() {
		return myTableDats;
	}

	public static void setMyTableDats(Set<String> myTableDats) {
		MyDatConfigerHolder.myTableDats = myTableDats;
	}

	public static void addMyTableDats(String myTableDat) {
		MyDatConfigerHolder.myTableDats.add(myTableDat);
	}
}
