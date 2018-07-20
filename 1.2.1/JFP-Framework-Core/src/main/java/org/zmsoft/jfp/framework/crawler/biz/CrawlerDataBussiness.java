package org.zmsoft.jfp.framework.crawler.biz;

import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.crawler.ECrawlerState;
import org.zmsoft.jfp.framework.crawler.common.AExtractionService;
import org.zmsoft.jfp.framework.crawler.common.ALoadPageService;
import org.zmsoft.jfp.framework.crawler.common.ALoginService;
import org.zmsoft.jfp.framework.crawler.common.ASaveDataService;
import org.zmsoft.jfp.framework.crawler.support.ACrawlerServiceSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 爬虫业务处理逻辑驱动器
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
@Service("CrawlerDataBussiness")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CrawlerDataBussiness extends ACrawlerServiceSupport<Object, Object> {

	/**
	 * 爬虫处理逻辑-基于粒子性操作，统一控制完成
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
			ALoginService<Object, Object> _LoginService_ = BeanFactoryHelper.getBean(bizName + "LoginService");
			_LoginService_.setJobKey(jobKey);
			_LoginService_.setMyCacheService(myCacheService);
			{
				ECrawlerState loginCrawlerState = ECrawlerState.RUNNING;
				while (ECrawlerState.SUCCESS.equals(loginCrawlerState) == false) {
					// 运行结果状态
					loginCrawlerState = _LoginService_.doProcess(bizName, this.getResult());
					if (ECrawlerState.STOP.equals(loginCrawlerState)) {
						logger.warn("LoginService.doProcess .......... STOP");
						break;
					} else if (ECrawlerState.FAIL.equals(loginCrawlerState)) {
						logger.warn("LoginService.doProcess .......... FAIL");
						break;
					} else if (ECrawlerState.REPEAT.equals(loginCrawlerState)) {
						logger.debug("LoginService.doProcess .......... REPEAT");
					}
				}
				if (ECrawlerState.SUCCESS.equals(loginCrawlerState))
					logger.debug("LoginService.doProcess .......... SUCCESS");
			}
			// 模拟打开页面--------------------------------------------------------------------------------------------------------------------
			ALoadPageService<Object, Object> _LoadPageService_ = BeanFactoryHelper.getBean(bizName + "LoadPageService");
			AExtractionService<Object, Object> _ExtractionService_ = BeanFactoryHelper.getBean(bizName + "ExtractionService");
			ASaveDataService<Object, Object> _SaveDataService_ = BeanFactoryHelper.getBean(bizName + "SaveDataService");

			_LoadPageService_.setJobKey(jobKey);
			_ExtractionService_.setJobKey(jobKey);
			_SaveDataService_.setJobKey(jobKey);
			_LoadPageService_.setMyCacheService(myCacheService);
			_ExtractionService_.setMyCacheService(myCacheService);
			_SaveDataService_.setMyCacheService(myCacheService);

			{
				ECrawlerState loadPageCrawlerState = ECrawlerState.RUNNING;
				ECrawlerState extractionCrawlerState = ECrawlerState.RUNNING;
				ECrawlerState saveCrawlerState = ECrawlerState.RUNNING;
				while (ECrawlerState.SUCCESS.equals(loadPageCrawlerState) == false) {
					// 初始化
					super.startLock();// 线程锁
					loadPageCrawlerState = ECrawlerState.RUNNING;
					extractionCrawlerState = ECrawlerState.RUNNING;
					saveCrawlerState = ECrawlerState.RUNNING;
					// 运行结果状态
					_LoadPageService_.setHttpService(_LoginService_.currentHttpServiceSupport());
					loadPageCrawlerState = _LoadPageService_.doProcess(bizName, _LoginService_.getResult());

					if (ECrawlerState.STOP.equals(loadPageCrawlerState)) {
						logger.warn("LoadPageService.doProcess .......... STOP");
						break;
					} else if (ECrawlerState.FAIL.equals(loadPageCrawlerState)) {
						logger.warn("LoadPageService.doProcess .......... FAIL");
						break;
					} else if (ECrawlerState.REPEAT.equals(loadPageCrawlerState)) {
						logger.debug("LoadPageService.doProcess .......... REPEAT");
					}
					// 数据抽取--------------------------------------------------------------------------------------------------------------------
					{
						while (ECrawlerState.SUCCESS.equals(extractionCrawlerState) == false) {
							// 运行结果状态
							_ExtractionService_.setHttpService(_LoadPageService_.currentHttpServiceSupport());
							extractionCrawlerState = _ExtractionService_.doProcess(bizName, _LoadPageService_.getResult(), _LoadPageService_.currentPageParam());
							if (ECrawlerState.STOP.equals(extractionCrawlerState)) {
								logger.warn("ExtractionService.doProcess .......... STOP");
								break;
							} else if (ECrawlerState.FAIL.equals(extractionCrawlerState)) {
								logger.warn("ExtractionService.doProcess .......... FAIL");
								break;
							} else if (ECrawlerState.REPEAT.equals(extractionCrawlerState)) {
								logger.debug("ExtractionService.doProcess .......... REPEAT");
							}
							// 数据保存--------------------------------------------------------------------------------------------------------------------
							{
								while (ECrawlerState.SUCCESS.equals(saveCrawlerState) == false) {
									// 运行结果状态
									_SaveDataService_.setHttpService(_ExtractionService_.currentHttpServiceSupport());
									saveCrawlerState = _SaveDataService_.doProcess(bizName, _ExtractionService_.getResult());
									if (ECrawlerState.STOP.equals(saveCrawlerState)) {
										logger.warn("SaveDataService.doProcess .......... STOP");
										break;
									} else if (ECrawlerState.FAIL.equals(saveCrawlerState)) {
										logger.warn("SaveDataService.doProcess .......... FAIL");
										break;
									} else if (ECrawlerState.REPEAT.equals(saveCrawlerState)) {
										logger.debug("SaveDataService.doProcess .......... REPEAT");
									}
								}
								if (ECrawlerState.SUCCESS.equals(saveCrawlerState))
									logger.debug("SaveDataService.doProcess .......... SUCCESS");
							}
						}
						if (ECrawlerState.SUCCESS.equals(extractionCrawlerState))
							logger.debug("ExtractionService.doProcess .......... SUCCESS");
					}
				}
				if (ECrawlerState.SUCCESS.equals(loadPageCrawlerState))
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
		ALoginService<Object, Object> _LoginService_ = BeanFactoryHelper.getBean(bizName + "LoginService");
		{
			ECrawlerState loginCrawlerState = ECrawlerState.RUNNING;
			while (ECrawlerState.SUCCESS.equals(loginCrawlerState) == false) {
				// 运行结果状态
				loginCrawlerState = _LoginService_.doProcess(bizName, this.getResult());
				if (ECrawlerState.STOP.equals(loginCrawlerState)) {
					logger.warn("LoginService.doProcess .......... STOP");
					break;
				} else if (ECrawlerState.FAIL.equals(loginCrawlerState)) {
					logger.warn("LoginService.doProcess .......... FAIL");
					break;
				} else if (ECrawlerState.REPEAT.equals(loginCrawlerState)) {
					logger.debug("LoginService.doProcess .......... REPEAT");
				}
			}
			if (ECrawlerState.SUCCESS.equals(loginCrawlerState))
				logger.debug("LoginService.doProcess .......... SUCCESS");
		}
		// 模拟打开页面--------------------------------------------------------------------------------------------------------------------
		ALoadPageService<Object, Object> _LoadPageService_ = BeanFactoryHelper.getBean(bizName + "LoadPageService");
		{
			ECrawlerState loadPageCrawlerState = ECrawlerState.RUNNING;
			while (ECrawlerState.SUCCESS.equals(loadPageCrawlerState) == false) {
				loadPageCrawlerState = ECrawlerState.RUNNING;
				// 运行结果状态
				_LoadPageService_.setHttpService(_LoginService_.currentHttpServiceSupport());
				loadPageCrawlerState = _LoadPageService_.doProcess(bizName, _LoginService_.getResult());
				if (ECrawlerState.STOP.equals(loadPageCrawlerState)) {
					logger.warn("LoadPageService.doProcess .......... STOP");
					break;
				} else if (ECrawlerState.FAIL.equals(loadPageCrawlerState)) {
					logger.warn("LoadPageService.doProcess .......... FAIL");
					break;
				} else if (ECrawlerState.REPEAT.equals(loadPageCrawlerState)) {
					logger.debug("LoadPageService.doProcess .......... REPEAT");
				}
			}
			if (ECrawlerState.SUCCESS.equals(loadPageCrawlerState))
				logger.debug("LoadPageService.doProcess .......... SUCCESS");
		}

	}
	
	/**
	 * 爬虫处理逻辑-基于简单操作，自由控制完成
	 * 
	 * @param bizName
	 * @throws Exception
	 */
	public void doCrawlerProcessByDefault(String bizName) throws Exception {
		// 模拟打开页面--------------------------------------------------------------------------------------------------------------------
		ALoadPageService<Object, Object> _LoadPageService_ = BeanFactoryHelper.getBean(bizName + "LoadPageService");
		{
			ECrawlerState loadPageCrawlerState = ECrawlerState.RUNNING;
			while (ECrawlerState.SUCCESS.equals(loadPageCrawlerState) == false) {
				loadPageCrawlerState = ECrawlerState.RUNNING;
				// 运行结果状态
				loadPageCrawlerState = _LoadPageService_.doProcess(bizName, this.getResult());
				if (ECrawlerState.STOP.equals(loadPageCrawlerState)) {
					logger.warn("LoadPageService.doProcess .......... STOP");
					break;
				} else if (ECrawlerState.FAIL.equals(loadPageCrawlerState)) {
					logger.warn("LoadPageService.doProcess .......... FAIL");
					break;
				} else if (ECrawlerState.REPEAT.equals(loadPageCrawlerState)) {
					logger.debug("LoadPageService.doProcess .......... REPEAT");
				}
			}
			if (ECrawlerState.SUCCESS.equals(loadPageCrawlerState))
				logger.debug("LoadPageService.doProcess .......... SUCCESS");
		}

	}

}
