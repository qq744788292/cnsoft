package org.zmsoft.framework.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 注入ApplicationContext对象的三种方式
 * @author spookfcy
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

}