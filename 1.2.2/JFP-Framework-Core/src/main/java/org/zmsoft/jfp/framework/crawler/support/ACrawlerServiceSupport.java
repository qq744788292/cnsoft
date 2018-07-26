package org.zmsoft.jfp.framework.crawler.support;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.net.MyHttpServiceSupport;
import org.zmsoft.jfp.framework.support.MyJobSupport;

/**
 * 爬虫基类
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * @see
 */
public abstract class ACrawlerServiceSupport<T, E> extends MyJobSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 网络请求服务控制器
	 */
	protected MyHttpServiceSupport httpService;

	public MyHttpServiceSupport currentHttpService() {
		return httpService;
	}

	public void setHttpService(MyHttpServiceSupport httpService) {
		this.httpService = httpService;
	}

	/**
	 * 设定返回值
	 */
	protected RESTResultBean<E> result;

	public RESTResultBean<E> currentResult() {
		return result;
	}

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
