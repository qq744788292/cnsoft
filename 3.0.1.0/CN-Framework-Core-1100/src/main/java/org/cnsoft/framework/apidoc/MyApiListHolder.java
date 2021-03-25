package org.cnsoft.framework.apidoc;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.common.ISApiDoc;
import org.cnsoft.framework.config.MyConfigerHolderSupport;
import org.cnsoft.framework.utils.EmptyHelper;

/**
 * 当前项目下面所有ApiDoc类名称集合
 * 
 * @see #MyApiDocSupport
 */
public class MyApiListHolder extends MyConfigerHolderSupport {
	///////////////////////////////////////////////////////////////////////////////////
	private static List<String> apiClassNames = new ArrayList<String>(50);

	public static List<String> getApiClassNames() {
		return apiClassNames;
	}

	public static void addApiClassName(String apiClassName) throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("====api>>>>>" + apiClassName);
		MyApiListHolder.apiClassNames.add(apiClassName);
	}

	/**
	 * 返回接口文档列表，支持排序
	 * 
	 * @return
	 * @throws Exception
	 */
	public static TreeSet<ApiDocBean> loadApiDocs() throws Exception {
		// 由于加载先后顺序和性能优化，这里仅仅持有服务别名
		TreeSet<ApiDocBean> apiDocs = new TreeSet<ApiDocBean>();
		for (String apiClassName : MyApiListHolder.apiClassNames) {
			ISApiDoc api = (ISApiDoc) MyBeanFactoryHelper.getBean(apiClassName);
			if (logger.isDebugEnabled())
				logger.debug("====api>>>>>" + apiClassName);
			// 自定义的logic为空
			if (EmptyHelper.isNotEmpty(api)) {
				ApiDocBean item = new ApiDocBean();
				item.setApiName(apiClassName);
				String[] val = api.loadApiDocName().split(SEMICOLON);
				item.setDocName(val[0]);
				item.setUrl(val[1]);
				apiDocs.add(item);
			}
		}
		logger.debug("====apiDocs>>>>>" + apiDocs);
		return apiDocs;
	}
}
