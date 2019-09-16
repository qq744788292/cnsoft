package org.zmsoft.config;

import java.lang.reflect.Field;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.framework.ObjectBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 获得配置(系统KEY具有优先赋值的权利)
 * 
 * @see #init
 * @see <ISConfig>
 */
public abstract class AConfigSupport extends ObjectBean implements ICFrameworkConstants {
	// 日志
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private MySystemConfigParameterSupport mySystemConfigParameterSupport;

	public MySystemConfigParameterSupport loadMySystemConfigParameterSupport() {
		if (EmptyHelper.isEmpty(mySystemConfigParameterSupport)) {
			mySystemConfigParameterSupport = MyBeanFactoryHelper.getBean(MySystemConfigParameterSupport.class);
		}
		return mySystemConfigParameterSupport;
	}
	
	/**
	 * 自动同步数据库内容
	 * @param reload
	 */
	public void setReload(boolean reload) {
		// 初始化
		loadMySystemConfigParameterSupport();
		mySystemConfigParameterSupport.setReload(reload);
	}
	
	public abstract String loadType();

	/**
	 * 初始化配置
	 */
	public void init() throws Exception {
		// 初始化
		loadMySystemConfigParameterSupport();
		// 加载系统配置
		prepareSystemConfigByclass(this);
		// 加载当前参数
		prepareSystemParameterByclass(loadType(), this);
	}
	
	/**
	 * 其他配置
	 */
	public void otherWise() throws Exception {
	}

	/**
	 * 获得系统配置（基于类属性字段）
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public void prepareSystemConfigByclass(AConfigSupport clazz) throws Exception {
		if (EmptyHelper.isEmpty(mySystemConfigParameterSupport)) {
			return;
		}
		// 获取所有参数
		prepareFieldValue(mySystemConfigParameterSupport.loadCacheDatas(1, EMPTY), clazz);
	}

	/**
	 * 针对某一个类别的参数进行赋值
	 */
	public void prepareSystemParameterByclass(String type, AConfigSupport clazz) throws Exception {
		if (EmptyHelper.isEmpty(mySystemConfigParameterSupport)) {
			return;
		}
		// 获取所有参数
		prepareFieldValue(mySystemConfigParameterSupport.loadCacheDatas(2, type), clazz);
	}

	// 参数赋值
	private void prepareFieldValue(Map<String, String> configs, AConfigSupport clazz) throws Exception {
		Field[] fields = clazz.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				// 获取原来的访问控制权限
				boolean accessFlag = field.isAccessible();
				if (!field.isAccessible())
					field.setAccessible(true);

				if (field.getName().equalsIgnoreCase("TYPE"))
					continue;
				// 设置内容
				if (configs.containsKey(field.getName()))
					field.set(clazz, configs.get(field.getName()));

				// 必须修改回原来的访问控制权限
				field.setAccessible(accessFlag);
			} catch (Exception e) {
			}
		}
	}
}
