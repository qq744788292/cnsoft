package org.zmsoft.config.system;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.zmsoft.framework.config.holder.MyBatisConfigerHolder;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.mybatis.MyTypeAliasesHelper;
import org.zmsoft.framework.mybatis.plugin.MyFrameworkQueryPlugin;
import org.zmsoft.framework.mybatis.plugin.MyFrameworkUpdatePlugin;

/**
 * MyBatis持久层配置
 */
@Configuration
@PropertySource("classpath:application.yml")
// 扫描DAO并加载到Bean容器管理
@MapperScan(basePackages = { "com.*.persistent.*.*", "org.*.persistent.*.*" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfiger implements ICFrameworkConstants {
	protected static Logger logger = LoggerFactory.getLogger(MyBatisConfiger.class);
	@Autowired
	private MyFrameworkQueryPlugin queryPluginInteceptor;
	@Autowired
	private MyFrameworkUpdatePlugin updatePluginInteceptor;
	@Autowired
	private Environment env;

	// /**
	// * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
	// * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
	// */
	// @Bean
	// @Primary
	// public DynamicDataSource dataSource(
	// @Qualifier("routeDataSource") DataSource routeDataSource,
	// @Qualifier("operateDataSource") DataSource operateDataSource) {
	// Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
	// targetDataSources.put(DatabaseType.routeDS, routeDataSource);
	// targetDataSources.put(DatabaseType.operateDS, operateDataSource);
	//
	// DynamicDataSource dataSource = new DynamicDataSource();
	// dataSource.setTargetDataSources(targetDataSources);//
	// 该方法是AbstractRoutingDataSource的方法
	// return dataSource;
	// }

	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration globalConfiguration() {
		return new org.apache.ibatis.session.Configuration();
	}
	
	@Bean
//	@ConditionalOnBean
//	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, org.apache.ibatis.session.Configuration configuration) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		{
			logger.debug("=====>>>>>=====>>>>>SqlSessionFactory=====>>>>>自定义设置=====>>>>>开始=====>>>>>");
			String typeAliasesPackages = env.getProperty("mybatis.typeAliasesPackage");
			String mapperLocations = env.getProperty("mybatis.mapperLocations");
			{
				Set<Class<?>> result = new LinkedHashSet<Class<?>>(50);
				for (String typeAliasesPackage : typeAliasesPackages.split(" ")) {
					logger.debug("====typeAliasesPackage>>>>>>>>>>>>>>>>>>>>>" + typeAliasesPackage);
					MyBatisConfigerHolder.addTypeAliasesPackage(typeAliasesPackage);
					result.addAll(MyTypeAliasesHelper.setTypeAliasesPackage(typeAliasesPackage));
				}
				sessionFactory.setTypeAliases(result.toArray(new Class[0]));
			}
			sessionFactory.setDataSource(dataSource);
			sessionFactory.setConfiguration(configuration);
			sessionFactory.setTypeAliasesPackage(EMPTY);
			{
				Set<Resource> result = new LinkedHashSet<Resource>(50);
				PathMatchingResourcePatternResolver pathMatch = new PathMatchingResourcePatternResolver();
				for (String mapperLocation : mapperLocations.split(" ")) {
					logger.debug("====mapperLocation>>>>>>>>>>>>>>>>>>>>>" + mapperLocation);
					MyBatisConfigerHolder.addMapperLocation(mapperLocation);
					for (Resource mapperResource : pathMatch.getResources(mapperLocation)) {
						result.add(mapperResource);
					}
				}
				sessionFactory.setMapperLocations(result.toArray(new Resource[0]));

			}
			sessionFactory.setPlugins(new Interceptor[] { queryPluginInteceptor, updatePluginInteceptor });
			logger.debug("<<<<<=====<<<<<=====SqlSessionFactory<<<<<=====自定义设置<<<<<=====结束<<<<<=====");
		}
		return sessionFactory.getObject();
	}
}
