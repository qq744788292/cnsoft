package org.zmsoft.framework.config.holder;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前项目下面所有dat初始化数据集合
 * 
 * @author spookfcy
 * @see <MyDBTableDatSupport>
 */
public class MyDatConfigerHolder {

	private static List<String> myTableDats = new ArrayList<String>();

	public static List<String> getMyTableDats() {
		return myTableDats;
	}

	public static void setMyTableDats(List<String> myTableDats) {
		MyDatConfigerHolder.myTableDats = myTableDats;
	}

	public static void addMyTableDats(String myTableDat) {
		MyDatConfigerHolder.myTableDats.add(myTableDat);
	}
}
