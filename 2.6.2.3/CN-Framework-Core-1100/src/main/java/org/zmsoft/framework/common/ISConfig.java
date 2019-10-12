package org.zmsoft.framework.common;

/**
 * 参数配置
 * 
 * @see <AConfigSupport>
 */
public interface ISConfig {

	/**
	 * 自动重载数据库配置
	 * 
	 * @param reload
	 */
	public void setReload(boolean reload) throws Exception;

	/**
	 * 初始化
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception;

	/**
	 * 其他内容更新
	 * @throws Exception
	 */
	public void otherWise() throws Exception;
}
