package org.cnsoft.crawler.common;

import org.cnsoft.crawler.ACrawlerServiceSupport;
import org.cnsoft.crawler.ECrawlerState;

/**
 * 模拟登录
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class ALoginService<T,E> extends ACrawlerServiceSupport<T,E> {

	public abstract ECrawlerState doLoginProcess()  throws Exception;
	
}
