package org.zmsoft.jfp.framework.biz.common;
/**
 * 保存数据到数据中心
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public interface ISToken {
	/**
	 * 更新Token令牌内容
	 */
	boolean chageToken() throws Exception;
	/**
	 * 检查Token令牌内容
	 */
	boolean checkToken() throws Exception;
	/**
	 *  返回加密后Token令牌内容
	 */
	String getToken() throws Exception;
	/**
	 * 缓存Token令牌内容
	 */
	boolean saveToken() throws Exception;
	/**
	 * 清除Token令牌内容
	 */
	boolean removeToken() throws Exception;
}
