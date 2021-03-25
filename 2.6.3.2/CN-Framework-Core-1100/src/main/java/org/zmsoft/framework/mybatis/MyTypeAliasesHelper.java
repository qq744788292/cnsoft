package org.zmsoft.framework.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.zmsoft.framework.config.holder.MyApiDocConfigerHolder;
import org.zmsoft.framework.config.holder.MyDatConfigerHolder;
import org.zmsoft.framework.config.holder.MyTableSQLConfigerHolder;
import org.zmsoft.framework.constants.ICFrameworkConstants;

public class MyTypeAliasesHelper implements ICFrameworkConstants {

	// 仅仅处理JAVA
	static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
	
	public static List<Class<?>> setTypeAliasesPackage(String typeAliasesPackage) throws Exception {
		List<Class<?>> typeAliasesList = new ArrayList<Class<?>>();

		ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
		typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/" + DEFAULT_RESOURCE_PATTERN;
		Resource[] resources = resolver.getResources(typeAliasesPackage);
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		String clazz = null;
		Resource rt;
		if (resources != null && resources.length > 0) {
			MetadataReader metadataReader = null;
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					metadataReader = metadataReaderFactory.getMetadataReader(resource);
					clazz = metadataReader.getClassMetadata().getClassName();
					// 别名映射自动扫描器(以 DBO,DTO,PVO结尾的实体)
					if ((clazz.lastIndexOf("DBO") > 0) || (clazz.lastIndexOf("PBO") > 0) || (clazz.lastIndexOf("DTO") > 0) || (clazz.lastIndexOf("PVO") > 0)) {
						typeAliasesList.add(Class.forName(clazz));
					}
					// Api文档扫描
					if ((clazz.lastIndexOf("Businesslogic") > 0)) {
						MyApiDocConfigerHolder.addApiClassName(clazz.substring(clazz.lastIndexOf(DOT) + 1));
					}

					// SQL文档扫描
					if ((clazz.lastIndexOf("SQL") > 0)) {
						MyTableSQLConfigerHolder.addTableSQLs(clazz.substring(clazz.lastIndexOf(DOT) + 1));
						clazz = resource.getURI().toString().replaceAll("SQL.class", ".dat");
						rt = resourceLoader.getResource(clazz);
						if (rt.exists()) {
							MyDatConfigerHolder.addTableDats(clazz);
						}
					}
				}
			}
		}

//		if (EmptyHelper.isEmpty(typeAliasesList)) {
//			logger.warn("mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:" + typeAliasesPackage + "未找到任何可用实体");
//		}

		return typeAliasesList;
	}

	public static void main(String[] args) throws Exception {
		MyTypeAliasesHelper.setTypeAliasesPackage("org.*.persistent.*.* com.*.persistent.*.*");
	}
}
