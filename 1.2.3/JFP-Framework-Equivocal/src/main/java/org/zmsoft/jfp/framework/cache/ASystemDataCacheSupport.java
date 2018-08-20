package org.zmsoft.jfp.framework.cache;

import org.zmsoft.jfp.framework.support.MyDataCacheSupport;

/**
 * 数据缓存接口实现超类<br>
 * 默认启动后通过线程加载
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class ASystemDataCacheSupport<T> extends MyDataCacheSupport<T> implements ISSystemDataCache<T> {
}
