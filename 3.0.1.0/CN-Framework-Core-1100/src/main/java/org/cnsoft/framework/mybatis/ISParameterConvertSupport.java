package org.cnsoft.framework.mybatis;

/**
 * 参数内容统一拦截
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <MyFrameworkQueryPlugin><MyFrameworkUpdatePlugin>
 */
public interface ISParameterConvertSupport {

	// TODO
	public void convert(Object object) throws Exception;
}
