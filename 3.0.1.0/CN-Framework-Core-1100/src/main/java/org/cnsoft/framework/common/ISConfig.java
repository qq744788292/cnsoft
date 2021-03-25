package org.cnsoft.framework.common;

/**
 * 参数配置
 * 
 * @see <AConfigSupport>
 */
public interface ISConfig extends ISInit {

	/**
	 * 自动重载数据库配置
	 * 
	 * @param reload
	 */
	public void setReload(boolean reload) throws Exception;

	/**
	 * 其他内容更新
	 * 
	 * @throws Exception
	 */
	public void otherWise() throws Exception;
}
