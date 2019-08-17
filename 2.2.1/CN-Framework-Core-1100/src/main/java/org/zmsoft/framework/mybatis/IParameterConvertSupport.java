package org.zmsoft.framework.mybatis;

/**
 * 参数内容统一拦截
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <MyFrameworkQueryPlugin><MyFrameworkUpdatePlugin>
 */
public interface IParameterConvertSupport {

	//TODO
	public void convert(Object object) throws Exception;
}
