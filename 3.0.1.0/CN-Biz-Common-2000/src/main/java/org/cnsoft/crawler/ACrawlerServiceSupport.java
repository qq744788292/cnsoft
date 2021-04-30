package org.cnsoft.crawler;

import java.util.HashMap;

import org.cnsoft.framework.beans.common.RESTResultBean;
import org.cnsoft.framework.support.MyFrameWorkSupport;

/**
 * 爬虫基类
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * @see
 */
public abstract class ACrawlerServiceSupport<T, E> extends MyFrameWorkSupport {

	public static final String CrawlerService_KEY = "Crawler:";

	/*
		+    	URL 中+号表示空格                                 %2B   
		空格 	URL中的空格可以用+号或者编码              %20 
		/    	分隔目录和子目录                                   %2F     
		?    	分隔实际的URL和参数                              %3F     
		%    	指定特殊字符                                          %25     
		#    	表示书签                                                %23     
		&    	URL 中指定的参数间的分隔符                  %26     
		=    	URL 中指定参数的值                                %3D
	*/
	
	/**
	 * 设定返回值
	 */
	protected RESTResultBean<E> result;

	public void setResult(RESTResultBean<E> result) {
		this.result = result;
	}

	public RESTResultBean<E> currentResult() {
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
