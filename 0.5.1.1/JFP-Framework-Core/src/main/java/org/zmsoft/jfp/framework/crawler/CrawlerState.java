package org.zmsoft.jfp.framework.crawler;

/**
 * 爬虫运行状态
 * @author fcy
 *
 */
public enum CrawlerState {

	SUCCESS, //成功
	FAIL, //失败
	REPEAT, //循环（成功）
	RUNNING, //启动
	STOP	//停止
}
