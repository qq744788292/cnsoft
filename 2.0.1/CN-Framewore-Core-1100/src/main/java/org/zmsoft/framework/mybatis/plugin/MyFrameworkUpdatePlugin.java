package org.zmsoft.framework.mybatis.plugin;

import java.util.Properties;

import javax.annotation.Resource;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import org.zmsoft.framework.beans.common.FrameworkDataBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.mybatis.convert.DefaultConvert;

/**
 * 编辑操作插件（insert/update/delete）
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component
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
	@Resource
	private DefaultConvert defaultConvert;

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
		if (defaultConvert != null) {
			if ((queryArgs[PARAMETER_INDEX] instanceof FrameworkDataBean) == true) {
				defaultConvert.convert((FrameworkDataBean) queryArgs[PARAMETER_INDEX]);
			} else if ((queryArgs[PARAMETER_INDEX] instanceof PageModel) == true) {
				defaultConvert.convert(((PageModel<?>) queryArgs[PARAMETER_INDEX]).currentFormParamBean());
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