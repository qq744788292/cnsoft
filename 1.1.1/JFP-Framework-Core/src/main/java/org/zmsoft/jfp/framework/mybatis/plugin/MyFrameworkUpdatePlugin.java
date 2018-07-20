package org.zmsoft.jfp.framework.mybatis.plugin;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.zmsoft.jfp.framework.beans.common.FrameworkDataBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.mybatis.IParameterConvertSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;

/**
 * 编辑操作插件（insert/update/delete）
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MyFrameworkUpdatePlugin implements Interceptor {
	// protected Logger logger = LoggerFactory.getLogger(this.getClass());
	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;

	/**
	 * 参数转换器
	 */
	private IParameterConvertSupport myConvert;
	public IParameterConvertSupport getMyConvert() {
		if(myConvert==null)
			myConvert = BeanFactoryHelper.getBean("IParameterConvert");
		return myConvert;
	}

	public Object intercept(Invocation invocation) throws Throwable {
		processIntercept(invocation.getArgs());
		return invocation.proceed();
	}

	/**
	 * 
	 * @param queryArgs
	 */
	private void processIntercept(final Object[] queryArgs) throws Throwable {
		// 参数统一拦截
		if (getMyConvert() != null) {
			if ((queryArgs[PARAMETER_INDEX] instanceof FrameworkDataBean) == true) {
				myConvert.convert((FrameworkDataBean) queryArgs[PARAMETER_INDEX]);
			} else if ((queryArgs[PARAMETER_INDEX] instanceof PageModel) == true) {
				myConvert.convert(((PageModel<?>) queryArgs[PARAMETER_INDEX]).currentFormParamBean());
			}
		}

		// MappedStatement ms = (MappedStatement)
		// queryArgs[MAPPED_STATEMENT_INDEX];
		// 动态设定数据源
		// MyDataSourceManager.setDynamicDataSource(ms,queryArgs[PARAMETER_INDEX]);
		// BoundSql boundSql = ms.getBoundSql(queryArgs[PARAMETER_INDEX]);
		// String sql = boundSql.getSql().trim();
		// MappedStatement newMs =
		// MyFrameworkSqlSource.copyFromMappedStatement(ms, new
		// MyFrameworkSqlSource(ms.getConfiguration(), sql,
		// boundSql.getParameterMappings()));
		// // 设定操作参数MAPPED
		// queryArgs[MAPPED_STATEMENT_INDEX] = newMs;

	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}

}