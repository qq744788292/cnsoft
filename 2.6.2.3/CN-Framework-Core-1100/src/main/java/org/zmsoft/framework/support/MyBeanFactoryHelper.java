package org.zmsoft.framework.support;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 注入ApplicationContext对象的三种方式
 * 
 * @author ZMSoft
 *
 */
@Component
public class MyBeanFactoryHelper implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		MyBeanFactoryHelper.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		assertApplicationContext();
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		try {
			assertApplicationContext();
			if (EmptyHelper.isEmpty(beanName))
				return null;
			if (null != applicationContext) {
				return (T) applicationContext.getBean(beanName);
			}
		} catch (Exception e) {

		}
		return null;
	}

	public static <T> T getBean(Class<T> requiredType) {
		assertApplicationContext();
		return applicationContext.getBean(requiredType);
	}

	private static void assertApplicationContext() {
		if (MyBeanFactoryHelper.applicationContext == null) {
			throw new RuntimeException("applicaitonContext属性为null,请检查是否注入了SpringContextHolder!");
		}
	}

	public static <T> Map<String, T> getBeans(Class<T> requiredType) {
		// 根据接口获取配置的所有实现类
		return applicationContext.getBeansOfType(requiredType);
	}

}