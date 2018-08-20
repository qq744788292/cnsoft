package org.zmsoft.jfp.framework.crawler.common;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.crawler.ECrawlerState;
import org.zmsoft.jfp.framework.crawler.support.ACrawlerServiceSupport;

/**
 * 数据持久化
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class ASaveDataService<T, E> extends ACrawlerServiceSupport<T, E> {

	public abstract ECrawlerState doProcess(String bizName, RESTResultBean<T> dataResult) throws Exception;

}
