package org.zmsoft.jfp.framework.crawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.zmsoft.jfp.framework.support.MyWorkSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;

/**
 * 爬虫线程启动入口
 * @author fcy
 *
 */
public class CrawlerThread extends MyWorkSupport implements Runnable {
	private String bizName = null;

	public CrawlerThread(String bizName) {
		this.bizName = bizName;
	}

	@Override
	public void run() {
		try {
			CrawlerDataBussiness _CrawlerDataBussiness_ = BeanFactoryHelper.getBean("CrawlerDataBussiness");
			_CrawlerDataBussiness_.doCrawlerProcessByGranularity(bizName);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CrawlerThread CrawlerThread_ = new CrawlerThread("");
		// 线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			threadPool.execute(CrawlerThread_);
		}
	}

}
