package org.zmsoft.framework.config.holder;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.zmsoft.framework.beans.ApiDocBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyBusinessLogicSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 当前项目下面所有ApiDoc类名称集合
 * 
 * @see <MyApiDocSupport><ApiDocListController>
 */
public class MyApiDocConfigerHolder implements ICFrameworkConstants {

	private static List<String> apiClassNames = new ArrayList<String>();

	public static List<String> getApiClassNames() {
		return apiClassNames;
	}
	public static void addApiClassName(String apiClassName) throws Exception {
		MyApiDocConfigerHolder.apiClassNames.add(apiClassName);
	}
	
	/**
	 * 返回接口文档列表，支持排序
	 * @return
	 * @throws Exception
	 */
	public static TreeSet<ApiDocBean> loadApiDocs() throws Exception {
		//由于加载先后顺序和性能优化，这里仅仅持有服务别名
		TreeSet<ApiDocBean> apiDocs = new TreeSet<ApiDocBean>();
		for(String apiClassName : MyApiDocConfigerHolder.apiClassNames){
			MyBusinessLogicSupport api = (MyBusinessLogicSupport) MyBeanFactoryHelper.getBean(apiClassName);
			//System.out.println("====api>>>>>" + apiClassName);
			// 自定义的logic为空
			if (EmptyHelper.isNotEmpty(api)) {
				//System.out.println(apiClassName.substring(1,7));
				ApiDocBean item = new ApiDocBean();
				item.setApiName(apiClassName);
				String[] val = api.loadApiDocName().split(SEMICOLON);
				item.setDocName(val[0]);
				item.setUrl(val[1]);
				apiDocs.add(item);
			}
		}
		return apiDocs;
	}
}
