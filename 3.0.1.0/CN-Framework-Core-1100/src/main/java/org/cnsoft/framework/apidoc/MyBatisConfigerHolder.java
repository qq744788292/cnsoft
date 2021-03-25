package org.cnsoft.framework.apidoc;

import java.util.LinkedHashSet;
import java.util.Set;

import org.cnsoft.framework.config.MyConfigerHolderSupport;

/**
 * 当前项目下面所有dat初始化数据集合
 * 
 * @author CNSoft
 * @see <MyDBTableDatSupport>
 */
public class MyBatisConfigerHolder extends MyConfigerHolderSupport {

	private static Set<String> myMapperLocations = new LinkedHashSet<String>(50);

	public static Set<String> getMapperLocations() {
		return myMapperLocations;
	}

	public static void setMapperLocations(Set<String> myTableDats) {
		MyBatisConfigerHolder.myMapperLocations = myTableDats;
	}

	public static void addMapperLocation(String mapperLocation) {
		if (logger.isDebugEnabled())
			logger.debug("====mapperLocation>>>>>" + mapperLocation);
		MyBatisConfigerHolder.myMapperLocations.add(mapperLocation);
	}

	private static Set<String> myTypeAliasesPackages = new LinkedHashSet<String>(50);

	public static Set<String> getTypeAliasesPackages() {
		return myTypeAliasesPackages;
	}

	public static void setTypeAliasesPackages(Set<String> typeAliasesPackages) {
		MyBatisConfigerHolder.myTypeAliasesPackages = typeAliasesPackages;
	}

	public static void addTypeAliasesPackage(String typeAliasesPackage) {
		MyBatisConfigerHolder.myTypeAliasesPackages.add(typeAliasesPackage);
	}

}