package org.zmsoft.jfp.framework.crawler.biz;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.crawler.ACrawlerServiceSupport;
import org.zmsoft.jfp.framework.crawler.CrawlerState;

/**
 * 获得数据
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class LoadPageService<T, E> extends ACrawlerServiceSupport<T, E> {

	public abstract CrawlerState doProcess(String bizName, RESTResultBean<T> loginParam) throws Exception;

}
