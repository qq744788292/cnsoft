package org.zmsoft.jfp.framework.crawler;

import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.crawler.biz.ExtractionService;
import org.zmsoft.jfp.framework.crawler.biz.LoadPageService;
import org.zmsoft.jfp.framework.crawler.biz.LoginService;
import org.zmsoft.jfp.framework.crawler.biz.SaveDataService;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 爬虫业务处理逻辑驱动器
 * 
 * @author fcy
 *
 */
@Service("CrawlerDataBussiness")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CrawlerDataBussiness extends ACrawlerServiceSupport<Object, Object> {

	/**
	 * 爬虫处理逻辑-基于例子性操作，统一控制完成
	 * 
	 * @param bizName
	 * @throws Exception
	 */
	public void doCrawlerProcessByGranularity(String bizName) throws Exception {
		// 初始化
		ISCacheService myCacheService = BeanFactoryHelper.getBean("myCache");
		this.setMyCacheService(myCacheService);

		try {
			// 基于缓存线程锁
			if (super.checkLock() == true) {
				// 系统运行中
				logger.warn("CrawlerDataBussiness.doCrawlerProcessByGranularity .......... RUNNING ......" + this.getJobKey());
				return;
			}
			// 初始化
			super.startLock();// 线程锁

			// 模拟登录--------------------------------------------------------------------------------------------------------------------
			LoginService<Object, Object> _LoginService_ = BeanFactoryHelper.getBean(bizName + "LoginService");
			_LoginService_.setJobKey(jobKey);
			_LoginService_.setMyCacheService(myCacheService);
			{
				CrawlerState loginCrawlerState = CrawlerState.RUNNING;
				while (CrawlerState.SUCCESS.equals(loginCrawlerState) == false) {
					// 运行结果状态
					loginCrawlerState = _LoginService_.doProcess(bizName, this.getResult());
					if (CrawlerState.STOP.equals(loginCrawlerState)) {
						logger.warn("LoginService.doProcess .......... STOP");
						break;
					} else if (CrawlerState.FAIL.equals(loginCrawlerState)) {
						logger.warn("LoginService.doProcess .......... FAIL");
						break;
					} else if (CrawlerState.REPEAT.equals(loginCrawlerState)) {
						logger.debug("LoginService.doProcess .......... REPEAT");
					}
				}
				if (CrawlerState.SUCCESS.equals(loginCrawlerState))
					logger.debug("LoginService.doProcess .......... SUCCESS");
			}
			// 模拟打开页面--------------------------------------------------------------------------------------------------------------------
			LoadPageService<Object, Object> _LoadPageService_ = BeanFactoryHelper.getBean(bizName + "LoadPageService");
			ExtractionService<Object, Object> _ExtractionService_ = BeanFactoryHelper.getBean(bizName + "ExtractionService");
			SaveDataService<Object, Object> _SaveDataService_ = BeanFactoryHelper.getBean(bizName + "SaveDataService");

			_LoadPageService_.setJobKey(jobKey);
			_ExtractionService_.setJobKey(jobKey);
			_SaveDataService_.setJobKey(jobKey);
			_LoadPageService_.setMyCacheService(myCacheService);
			_ExtractionService_.setMyCacheService(myCacheService);
			_SaveDataService_.setMyCacheService(myCacheService);

			{
				CrawlerState loadPageCrawlerState = CrawlerState.RUNNING;
				CrawlerState extractionCrawlerState = CrawlerState.RUNNING;
				CrawlerState saveCrawlerState = CrawlerState.RUNNING;
				while (CrawlerState.SUCCESS.equals(loadPageCrawlerState) == false) {
					// 初始化
					super.startLock();// 线程锁
					loadPageCrawlerState = CrawlerState.RUNNING;
					extractionCrawlerState = CrawlerState.RUNNING;
					saveCrawlerState = CrawlerState.RUNNING;
					// 运行结果状态
					_LoadPageService_.setHttpService(_LoginService_.currentHttpServiceSupport());
					loadPageCrawlerState = _LoadPageService_.doProcess(bizName, _LoginService_.getResult());

					if (CrawlerState.STOP.equals(loadPageCrawlerState)) {
						logger.warn("LoadPageService.doProcess .......... STOP");
						break;
					} else if (CrawlerState.FAIL.equals(loadPageCrawlerState)) {
						logger.warn("LoadPageService.doProcess .......... FAIL");
						break;
					} else if (CrawlerState.REPEAT.equals(loadPageCrawlerState)) {
						logger.debug("LoadPageService.doProcess .......... REPEAT");
					}
					// 数据抽取--------------------------------------------------------------------------------------------------------------------
					{
						while (CrawlerState.SUCCESS.equals(extractionCrawlerState) == false) {
							// 运行结果状态
							_ExtractionService_.setHttpService(_LoadPageService_.currentHttpServiceSupport());
							extractionCrawlerState = _ExtractionService_.doProcess(bizName, _LoadPageService_.getResult(), _LoadPageService_.currentPageParam());
							if (CrawlerState.STOP.equals(extractionCrawlerState)) {
								logger.warn("ExtractionService.doProcess .......... STOP");
								break;
							} else if (CrawlerState.FAIL.equals(extractionCrawlerState)) {
								logger.warn("ExtractionService.doProcess .......... FAIL");
								break;
							} else if (CrawlerState.REPEAT.equals(extractionCrawlerState)) {
								logger.debug("ExtractionService.doProcess .......... REPEAT");
							}
							// 数据保存--------------------------------------------------------------------------------------------------------------------
							{
								while (CrawlerState.SUCCESS.equals(saveCrawlerState) == false) {
									// 运行结果状态
									_SaveDataService_.setHttpService(_ExtractionService_.currentHttpServiceSupport());
									saveCrawlerState = _SaveDataService_.doProcess(bizName, _ExtractionService_.getResult());
									if (CrawlerState.STOP.equals(saveCrawlerState)) {
										logger.warn("SaveDataService.doProcess .......... STOP");
										break;
									} else if (CrawlerState.FAIL.equals(saveCrawlerState)) {
										logger.warn("SaveDataService.doProcess .......... FAIL");
										break;
									} else if (CrawlerState.REPEAT.equals(saveCrawlerState)) {
										logger.debug("SaveDataService.doProcess .......... REPEAT");
									}
								}
								if (CrawlerState.SUCCESS.equals(saveCrawlerState))
									logger.debug("SaveDataService.doProcess .......... SUCCESS");
							}
						}
						if (CrawlerState.SUCCESS.equals(extractionCrawlerState))
							logger.debug("ExtractionService.doProcess .......... SUCCESS");
					}
				}
				if (CrawlerState.SUCCESS.equals(loadPageCrawlerState))
					logger.debug("LoadPageService.doProcess .......... SUCCESS");
			}
		} finally {
			// 结束任务
			super.endLock();
		}
	}

	/**
	 * 爬虫处理逻辑-基于线性操作，自由控制完成
	 * 
	 * @param bizName
	 * @throws Exception
	 */
	public void doCrawlerProcessByLinear(String bizName) throws Exception {
		// 模拟登录--------------------------------------------------------------------------------------------------------------------
		LoginService<Object, Object> _LoginService_ = BeanFactoryHelper.getBean(bizName + "LoginService");
		{
			CrawlerState loginCrawlerState = CrawlerState.RUNNING;
			while (CrawlerState.SUCCESS.equals(loginCrawlerState) == false) {
				// 运行结果状态
				loginCrawlerState = _LoginService_.doProcess(bizName, this.getResult());
				if (CrawlerState.STOP.equals(loginCrawlerState)) {
					logger.warn("LoginService.doProcess .......... STOP");
					break;
				} else if (CrawlerState.FAIL.equals(loginCrawlerState)) {
					logger.warn("LoginService.doProcess .......... FAIL");
					break;
				} else if (CrawlerState.REPEAT.equals(loginCrawlerState)) {
					logger.debug("LoginService.doProcess .......... REPEAT");
				}
			}
			if (CrawlerState.SUCCESS.equals(loginCrawlerState))
				logger.debug("LoginService.doProcess .......... SUCCESS");
		}
		// 模拟打开页面--------------------------------------------------------------------------------------------------------------------
		LoadPageService<Object, Object> _LoadPageService_ = BeanFactoryHelper.getBean(bizName + "LoadPageService");
		{
			CrawlerState loadPageCrawlerState = CrawlerState.RUNNING;
			while (CrawlerState.SUCCESS.equals(loadPageCrawlerState) == false) {
				loadPageCrawlerState = CrawlerState.RUNNING;
				// 运行结果状态
				_LoadPageService_.setHttpService(_LoginService_.currentHttpServiceSupport());
				loadPageCrawlerState = _LoadPageService_.doProcess(bizName, _LoginService_.getResult());
				if (CrawlerState.STOP.equals(loadPageCrawlerState)) {
					logger.warn("LoadPageService.doProcess .......... STOP");
					break;
				} else if (CrawlerState.FAIL.equals(loadPageCrawlerState)) {
					logger.warn("LoadPageService.doProcess .......... FAIL");
					break;
				} else if (CrawlerState.REPEAT.equals(loadPageCrawlerState)) {
					logger.debug("LoadPageService.doProcess .......... REPEAT");
				}
			}
			if (CrawlerState.SUCCESS.equals(loadPageCrawlerState))
				logger.debug("LoadPageService.doProcess .......... SUCCESS");
		}

	}

}
