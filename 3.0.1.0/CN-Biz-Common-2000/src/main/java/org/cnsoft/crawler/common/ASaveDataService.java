package org.cnsoft.crawler.common;

import org.cnsoft.crawler.ACrawlerServiceSupport;
import org.cnsoft.crawler.ECrawlerState;

/**
 * 数据持久化
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class ASaveDataService<T, E> extends ACrawlerServiceSupport<T, E> {

	public abstract ECrawlerState doPageDataSaveProcess() throws Exception;

}
