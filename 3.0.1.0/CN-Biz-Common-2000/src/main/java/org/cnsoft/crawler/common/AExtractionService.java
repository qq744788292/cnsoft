package org.cnsoft.crawler.common;

import org.cnsoft.crawler.ACrawlerServiceSupport;
import org.cnsoft.crawler.ECrawlerState;

/**
 * 数据抽取
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class AExtractionService<T, E> extends ACrawlerServiceSupport<T, E> {

	public abstract ECrawlerState doPageExtractionProcess() throws Exception;

}
