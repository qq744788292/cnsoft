package org.zmsoft.framework.mybatis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.zmsoft.common.dba.bean.DriverInfoBean;
import org.zmsoft.framework.config.holder.MyBatisConfigerHolder;
import org.zmsoft.framework.constants.ICFrameworkConstants;

public class MyDynamicSqlSessionFactoryHelper implements ICFrameworkConstants {
//	SqlSession sqlsession = sqlSessionFactory.openSession();
//	// 调用数据库操作方法
//	sqlsession.close()
	public static SqlSessionFactory build(DriverInfoBean server) throws Exception{
		PooledDataSource dataSource = new PooledDataSource();
		dataSource.setDriver(server.getDriverClass());
		dataSource.setUrl(server.getUrl());
		dataSource.setUsername(server.getUsername());
		dataSource.setPassword(server.getPassword());
		
		//DBO文件
		Set<Class<?>> typeAliases = new LinkedHashSet<Class<?>>(50);
		for (String typeAliasesPackage : MyBatisConfigerHolder.getTypeAliasesPackages()) {
			typeAliases.addAll(MyTypeAliasesHelper.setTypeAliasesPackage(typeAliasesPackage));
		}
		
		//XML文件
		Set<Resource> mappers = new LinkedHashSet<Resource>(50);
		PathMatchingResourcePatternResolver pathMatch = new PathMatchingResourcePatternResolver();
		for (String mapperLocation : MyBatisConfigerHolder.getMapperLocations()) {
			for (Resource mapperResource : pathMatch.getResources(mapperLocation)) {
				mappers.add(mapperResource);
			}
		}
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setTypeAliases(typeAliases.toArray(new Class[0]));
		sessionFactory.setMapperLocations(mappers.toArray(new Resource[0]));
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	}
	
	public static Connection getConnection(SqlSessionFactory sqlSessionFactory) throws SQLException {
		return SqlSessionUtils.getSqlSession(sqlSessionFactory).getConnection();
	}
}
