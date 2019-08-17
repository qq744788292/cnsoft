package org.zmsoft.framework.config.holder;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前项目下面所有ApiDoc类名称集合
 * 
 * @author spookfcy
 * @see <MyApiDocSupport>
 */
public class MyApiDocConfigerHolder {

	private static List<String> apiClassNames = new ArrayList<String>();

	public static List<String> getApiClassNames() {
		return apiClassNames;
	}

	public static void setApiClassNames(List<String> apiClassNames) {
		MyApiDocConfigerHolder.apiClassNames = apiClassNames;
	}

	public static void addApiClassName(String apiClassName) {
		MyApiDocConfigerHolder.apiClassNames.add(apiClassName);
	}

}
