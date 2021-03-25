package org.cnsoft.common.cache;

import org.cnsoft.framework.cache.ADataCacheSupport;
import org.cnsoft.framework.cache.ISSystemDataCache;
import org.cnsoft.framework.cache.ISSystemDatasCache;

/**
 * 数据缓存接口实现超类<br>
 * 默认启动后通过线程加载
 * 
 * @author CNSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class ASystemDataCacheSupport<T> extends ADataCacheSupport<T> implements ICommonDataConstants, ISSystemDataCache<T>, ISSystemDatasCache<T> {
}
