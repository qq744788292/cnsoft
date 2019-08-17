package org.zmsoft.framework.config;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.zmsoft.framework.config.holder.MyApiDocConfigerHolder;
import org.zmsoft.framework.config.holder.MyTableSQLConfigerHolder;
import org.zmsoft.framework.constants.IFrameworkConstants;
import org.zmsoft.framework.mybatis.plugin.MyFrameworkQueryPlugin;
import org.zmsoft.framework.mybatis.plugin.MyFrameworkUpdatePlugin;
import org.zmsoft.framework.utils.EmptyHelper;

@Configuration
@PropertySource("classpath:application.yml")
// 扫描DAO并加载到Bean容器管理
@MapperScan(basePackages = { "com.*.*.persistent.*.*" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfiger implements IFrameworkConstants {
	protected static Logger logger = LoggerFactory.getLogger(MyBatisConfiger.class);
	@Autowired
	private MyFrameworkQueryPlugin queryPluginInteceptor;
	@Autowired
	private MyFrameworkUpdatePlugin updatePluginInteceptor;
	@Autowired
	private Environment env;

	static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

	public static Class<?>[] setTypeAliasesPackage(String typeAliasesPackage) throws Exception {
		logger.debug("typeAliasesPackage====>>>>>>>>>>>>>>>>>>>>>..." + typeAliasesPackage);

		ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
		typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/" + DEFAULT_RESOURCE_PATTERN;
		List<Class<?>> typeAliasesList = new ArrayList<Class<?>>();
		Resource[] resources = resolver.getResources(typeAliasesPackage);
		String clazz = null;
		if (resources != null && resources.length > 0) {
			MetadataReader metadataReader = null;
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					metadataReader = metadataReaderFactory.getMetadataReader(resource);
					clazz = metadataReader.getClassMetadata().getClassName();
					// logger.debug("clazz====>>>>>>>>>>>>>>>>>>>>>" + clazz);
					// 别名映射自动扫描器(以 DBO,DTO,PVO结尾的实体)
					if ((clazz.lastIndexOf("DBO") > 0) || (clazz.lastIndexOf("DTO") > 0) || (clazz.lastIndexOf("PVO") > 0))
						typeAliasesList.add(Class.forName(clazz));
					//Api文档扫描
					if ((clazz.lastIndexOf("Businesslogic") > 0))
						MyApiDocConfigerHolder.addApiClassName(clazz.substring(clazz.lastIndexOf(DOT)+1));

					//SQL文档扫描
					if ((clazz.lastIndexOf("SQL") > 0))
						MyTableSQLConfigerHolder.addMyTableSQLs(clazz.substring(clazz.lastIndexOf(DOT)+1));
				}
			}
		}
		if (EmptyHelper.isEmpty(typeAliasesList)) {
			logger.warn("mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:" + typeAliasesPackage + "未找到任何包");
		}
		Class<?>[] typeAliases = new Class<?>[typeAliasesList.size()];
		for (int i = 0; i < typeAliasesList.size(); i++) {
			logger.debug("clazz====>>>>>>>>>>>>>>>>>>>>>" + typeAliasesList.get(i));
			typeAliases[i] = typeAliasesList.get(i);
		}
		return typeAliases;
	}
	
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
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, org.apache.ibatis.session.Configuration configuration) throws Exception {
		logger.debug("=====>>>>>=====>>>>>SqlSessionFactory=====>>>>>自定义设置=====>>>>>开始=====>>>>>");
		String typeAliasesPackage = env.getProperty("mybatis.typeAliasesPackage");
		String mapperLocations = env.getProperty("mybatis.mapperLocations");
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setTypeAliases(setTypeAliasesPackage(typeAliasesPackage));
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setConfiguration(configuration);
		sessionFactory.setTypeAliasesPackage(EMPTY);
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		sessionFactory.setPlugins(new Interceptor[] { queryPluginInteceptor, updatePluginInteceptor });
		logger.debug("<<<<<=====<<<<<=====SqlSessionFactory<<<<<=====自定义设置<<<<<=====结束<<<<<=====");
		return sessionFactory.getObject();
	}

	public static void main(String[] args) throws Exception {
		setTypeAliasesPackage("com.*.*.persistent.*.*");
	}
}
