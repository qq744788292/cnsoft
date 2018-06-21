package org.zmsoft.jfp.framework.biz;

/**
 * 缓存数据到缓存中心
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public interface ISCache {

	/**
	 * 数据保存
	 */
	boolean doCache() throws Exception;
}
