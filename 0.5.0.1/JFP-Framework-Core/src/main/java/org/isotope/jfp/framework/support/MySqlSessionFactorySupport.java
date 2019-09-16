package org.isotope.jfp.framework.support;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;

/**
 * Mybatits SQLSession连接
 * 
 * @author Spook
 * 
 * @since 0.1.0 2014-11-25
 * @version 0.2.1
 */
public class MySqlSessionFactorySupport extends SqlSessionFactoryBean {

	/**
	 * Set locations of MyBatis mapper files that are going to be merged into
	 * the {@code SqlSessionFactory} configuration at runtime.
	 * 
	 * This is an alternative to specifying "&lt;sqlmapper&gt;" entries in an
	 * MyBatis config file. This property being based on Spring's resource
	 * abstraction also allows for specifying resource patterns here: e.g.
	 * "classpath*:sqlmap/*-mapper.xml".
	 */
	public void setMapperLocations(Resource[] mapperLocations) {
		for (Resource r : mapperLocations) {
			getPath(r);
		}
		super.setMapperLocations(mapperLocations);
	}
	
	/**
	 * 允许手动指定DBO来源
	 * @param dboLocations
	 * @throws Exception 
	 */
	public void setDBOLocations(List<String> dboLocations) throws Exception {
		for (String dbo : dboLocations) {
			typeAliasesList.add(Class.forName(dbo));
		}
	}
	private ArrayList<Class<?>> typeAliasesList = new ArrayList<Class<?>> ();
	
	/**
	 * 创建SQLSession
	 */
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		Class<?>[] typeAliases = new Class<?>[typeAliasesList.size()];
		for (int i=0;i< typeAliasesList.size();i++) {
			typeAliases[i] = typeAliasesList.get(i);
		}
		super.setTypeAliases(typeAliases);
		return super.buildSqlSessionFactory();
	}

	/**
	 * 获得文件
	 * 
	 * @param r
	 * @return
	 */
	private void getPath(Resource r) {
		try {
			String uri = r.getURI().toString();
			if(uri.indexOf(".jar!/")>0)
				uri = uri.substring(uri.indexOf(".jar!/") + 6);
			else if(uri.indexOf("classes/")>0)
				uri = uri.substring(uri.indexOf("classes/") + 8);
			uri = uri.replace(".xml", "DBO").replaceAll("/", ".");
			if(uri.indexOf("DBO")>0)
				typeAliasesList.add(Class.forName(uri));			
		} catch (Exception e) {
			//System.out.println("Can't load class ==>>>"+e.getMessage());
		}
	}
}
