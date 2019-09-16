package org.jfpc.base.helper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
/**
 * 
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class BeanFactoryHelper implements BeanFactoryAware {

	private static BeanFactory beanFactory; // BEAN工厂

	public void setBeanFactory(BeanFactory f) throws BeansException {
		this.beanFactory = f;
	}

	public static BeanFactory getBeanfactory() {
		return beanFactory;
	}

	/**
	 * 根据beanName名字取得bean
	 * 
	 * @param beanName
	 * @return
	 */
	public static <T> T getBean(String beanName) {
		if (null != beanFactory) {
			return (T) beanFactory.getBean(beanName);
		}
		return null;
	}

}