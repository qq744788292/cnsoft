package org.cnsoft.crawler.common;

import org.cnsoft.crawler.ACrawlerServiceSupport;
import org.cnsoft.crawler.ECrawlerState;

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
	public static final String METHOD_NAME_PAGE_LIST = "doHistoryListProcess";
	/**
	 * 历史详情
	 */
	public static final String METHOD_NAME_PAGE_INFO = "doHistoryInfoProcess";

	/**
	 * 最新数据
	 * 
	 * @param bizName
	 * @param loginParam
	 * @return
	 * @throws Exception
	 */
	public ECrawlerState doNewProcess() throws Exception {
		return ECrawlerState.STOP;
	}

	/**
	 * 类别单页数据
	 * 
	 * @param bizName
	 * @param loginParam
	 * @return
	 * @throws Exception
	 */
	public ECrawlerState doTypePageListProcess() throws Exception {
		return ECrawlerState.STOP;
	}

	/**
	 * 具体内容数据
	 * 
	 * @param bizName
	 * @param loginParam
	 * @return
	 * @throws Exception
	 */
	public ECrawlerState doTypePageInfoProcess() throws Exception {
		return ECrawlerState.STOP;
	}
}
