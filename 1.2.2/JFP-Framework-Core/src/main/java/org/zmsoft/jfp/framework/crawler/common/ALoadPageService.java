package org.zmsoft.jfp.framework.crawler.common;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.crawler.ECrawlerState;
import org.zmsoft.jfp.framework.crawler.support.ACrawlerServiceSupport;

/**
 * 获得数据
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class ALoadPageService<T, E> extends ACrawlerServiceSupport<T, E> {

	/**
	 * 最新数据
	 * @param bizName
	 * @param loginParam
	 * @return
	 * @throws Exception
	 */
	public abstract ECrawlerState doProcess(String bizName, RESTResultBean<T> loginParam) throws Exception;
	
	/**
	 * 历史数据
	 * @param bizName
	 * @param loginParam
	 * @return
	 * @throws Exception
	 */
	public  ECrawlerState doProcessHistory(String bizName, RESTResultBean<T> loginParam) throws Exception{
		return ECrawlerState.STOP;
	}
}
