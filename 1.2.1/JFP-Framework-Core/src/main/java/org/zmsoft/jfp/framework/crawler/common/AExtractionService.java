package org.zmsoft.jfp.framework.crawler.common;

import java.util.Map;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.crawler.ECrawlerState;
import org.zmsoft.jfp.framework.crawler.support.ACrawlerServiceSupport;

/**
 * 数据抽取
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class AExtractionService<T, E> extends ACrawlerServiceSupport<T, E> {

	public abstract ECrawlerState doProcess(String bizName, RESTResultBean<T> htmlParam, Map<String, String> currentPageParam) throws Exception;

}
