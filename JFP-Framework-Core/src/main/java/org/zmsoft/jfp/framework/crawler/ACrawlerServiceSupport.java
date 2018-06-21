package org.zmsoft.jfp.framework.crawler;

import java.util.HashMap;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.support.MyJobSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 爬虫基类
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public abstract class ACrawlerServiceSupport<T, E> extends MyJobSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected RESTResultBean<E> result;

	/**
	 * 设定返回值
	 * 
	 * @return
	 */
	public RESTResultBean<E> getResult() {
		return result;
	}

	/**
	 * 请求参数
	 */
	protected HashMap<String, String> currentPageParam;

	public HashMap<String, String> currentPageParam() {
		return currentPageParam;
	}

	public void setCurrentPageParam(HashMap<String, String> currentPageParam) {
		this.currentPageParam = currentPageParam;
	}

}
