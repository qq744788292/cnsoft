package org.cnsoft.crawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.cnsoft.crawler.common.WebPageCrawlerDataBussiness;
import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.job.AJobTaskSupport;
import org.cnsoft.framework.utils.PKHelper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 爬虫线程启动入口
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * @see #WebPageCrawlerDataBussiness
 */
@Service("CrawlerThreadSupport")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CrawlerThreadSupport extends AJobTaskSupport implements Runnable {
	protected String bizName = null;
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	
	@Override
	public void run() {
		try {
			super.doJobTaskProcess();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
	
	//业务处理
	public boolean doJobBizProcess() throws Exception{
		WebPageCrawlerDataBussiness _CrawlerDataBussiness_ = MyBeanFactoryHelper.getBean("WebPageCrawlerDataBussiness");
		_CrawlerDataBussiness_.init(bizName,PKHelper.creatPUKey());
		_CrawlerDataBussiness_.doCrawlerProcessByDefault();
		return true;
	}
	
	public static void main(String[] args) {
		CrawlerThreadSupport CrawlerThread_ = new CrawlerThreadSupport();
		CrawlerThread_.setBizName("");
		// 线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			threadPool.execute(CrawlerThread_);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @deprecated
	 */
	public boolean doProcess() throws Exception {
		return false;
	}

	/**
	 * @deprecated
	 */
	public boolean beforeProcess() throws Exception {
		return false;
	}

	/**
	 * @deprecated
	 */
	public boolean afterProcess() throws Exception {
		return false;
	}

}
