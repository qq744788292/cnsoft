package org.zmsoft.jfp.framework.crawler.biz;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.crawler.ACrawlerServiceSupport;
import org.zmsoft.jfp.framework.crawler.CrawlerState;

/**
 * 获得数据
 * 
 * @author fcy
 *
 */
public abstract class LoadPageService<T, E> extends ACrawlerServiceSupport<T, E> {

	public abstract CrawlerState doProcess(String bizName, RESTResultBean<T> loginParam) throws Exception;

}
