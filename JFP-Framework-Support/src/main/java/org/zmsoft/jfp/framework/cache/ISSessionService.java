package org.zmsoft.jfp.framework.cache;

/**
 * 缓存队列
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISSessionService {
	//////////数据缓存////////////////
	/**
	 * 保存数据到缓存中心
	 * <br>（基于保存Key）
	 * @return
	 */
	public boolean putObject(String key,Object value);
	/**
	 * 从缓存中心获取数据
	 * <br>（基于保存Key）
	 * @return
	 */
	public Object getObject(String key);
	/**
	 * 删除缓存中心里面的一个数据
	 * <br>（基于保存Key）
	 * @return
	 */
	public Object deleteObject(String key);

}
