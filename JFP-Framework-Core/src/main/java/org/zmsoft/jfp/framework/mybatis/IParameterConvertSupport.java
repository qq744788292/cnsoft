package org.zmsoft.jfp.framework.mybatis;

/**
 * 参数内容统一拦截
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * @see <MyFrameworkQueryPlugin><MyFrameworkUpdatePlugin>
 */
public interface IParameterConvertSupport {

	//TODO
	public void convert(Object object) throws Exception;
}
