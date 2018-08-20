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
	 * 最新
	 */
	public static final String METHOD_NAME_NEW = "doNewProcess";
	/**
	 * 历史列表
	 */
	public static final String METHOD_NAME_HISTORY_LIST = "doHistoryListProcess";
	/**
	 * 历史详情
	 */
	public static final String METHOD_NAME_HISTORY_INFO = "doHistoryInfoProcess";

	/**
	 * 最新数据
	 * 
	 * @param bizName
	 * @param loginParam
	 * @return
	 * @throws Exception
	 */
	public ECrawlerState doNewProcess(String bizName, RESTResultBean<T> loginParam) throws Exception {
		return ECrawlerState.STOP;
	}

	/**
	 * 历史数据
	 * 
	 * @param bizName
	 * @param loginParam
	 * @return
	 * @throws Exception
	 */
	public ECrawlerState doHistoryListProcess(String bizName, RESTResultBean<T> loginParam) throws Exception {
		return ECrawlerState.STOP;
	}

	/**
	 * 历史数据
	 * 
	 * @param bizName
	 * @param loginParam
	 * @return
	 * @throws Exception
	 */
	public ECrawlerState doHistoryInfoProcess(String bizName, RESTResultBean<T> loginParam) throws Exception {
		return ECrawlerState.STOP;
	}
}
