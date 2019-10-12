package org.zmsoft.common.cache;

import org.zmsoft.framework.cache.ADataCacheSupport;
import org.zmsoft.framework.cache.ISSystemDataCache;
import org.zmsoft.framework.cache.ISSystemDatasCache;

/**
 * 数据缓存接口实现超类<br>
 * 默认启动后通过线程加载
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class ASystemDataCacheSupport<T> extends ADataCacheSupport<T> implements ICommonDataConstants, ISSystemDataCache<T>, ISSystemDatasCache<T> {
}
