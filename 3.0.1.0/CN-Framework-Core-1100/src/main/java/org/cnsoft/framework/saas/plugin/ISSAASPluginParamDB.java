package org.cnsoft.framework.saas.plugin;

import org.cnsoft.framework.db.support.ADataBaseDefaultSupportBean;

/**
 * SAAS开发模式下数据操作拦截器
 */
public interface ISSAASPluginParamDB {

	boolean doCompletion(ADataBaseDefaultSupportBean formParamBean);

}
