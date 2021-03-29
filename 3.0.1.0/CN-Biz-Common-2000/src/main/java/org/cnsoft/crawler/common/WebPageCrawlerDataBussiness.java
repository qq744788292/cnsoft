package org.cnsoft.crawler.common;

import org.cnsoft.crawler.ACrawlerServiceSupport;
import org.cnsoft.crawler.ECrawlerState;
import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 爬虫业务处理逻辑驱动器
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * @see #CrawlerThread
 */
@Service("WebPageCrawlerDataBussiness")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WebPageCrawlerDataBussiness extends ACrawlerServiceSupport<Object, Object> {

	private String bizName;
	private String jobKey;

	public void init(String bizname, String jobkey) {
		this.bizName = bizname;
		this.jobKey = jobkey;
	}
//
//	/**
//	 * 爬虫处理逻辑-基于粒子性操作，统一控制完成
//	 * @see #ALoginService
//	 * @see #ALoadPageService
//	 * @see #AExtractionService
//	 * @see #ASaveDataService
//	 * @param bizName
//	 * @throws Exception
//	 */
//	public void doCrawlerProcessByGranularity() throws Exception {
//		logger.debug("开始启动【{}】，任务ID={}", bizName, jobKey);
//		// 初始化
//		// 模拟登录--------------------------------------------------------------------------------------------------------------------
//		ALoginService<Object, Object> _LoginService_ = MyBeanFactoryHelper.getBean(bizName + "LoginService");
//		{
//			ECrawlerState loginCrawlerState = ECrawlerState.RUNNING;
//			while (ECrawlerState.SUCCESS.equals(loginCrawlerState) == false) {
//				// 运行结果状态
//				loginCrawlerState = _LoginService_.doProcess(bizName, this.currentResult());
//				if (ECrawlerState.STOP.equals(loginCrawlerState)) {
//					logger.warn("LoginService.doProcess .......... STOP");
//					break;
//				} else if (ECrawlerState.FAIL.equals(loginCrawlerState)) {
//					logger.warn("LoginService.doProcess .......... FAIL");
//					break;
//				} else if (ECrawlerState.REPEAT.equals(loginCrawlerState)) {
//					logger.debug("LoginService.doProcess .......... REPEAT");
//				}
//			}
//			if (ECrawlerState.SUCCESS.equals(loginCrawlerState))
//				logger.debug("LoginService.doProcess .......... SUCCESS");
//		}
//		// 模拟打开页面--------------------------------------------------------------------------------------------------------------------
//		ALoadPageService<Object, Object> _LoadPageService_ = MyBeanFactoryHelper.getBean(bizName + "LoadPageService");
//		AExtractionService<Object, Object> _ExtractionService_ = MyBeanFactoryHelper.getBean(bizName + "ExtractionService");
//		ASaveDataService<Object, Object> _SaveDataService_ = MyBeanFactoryHelper.getBean(bizName + "SaveDataService");
//		{
//			ECrawlerState loadPageCrawlerState = ECrawlerState.RUNNING;
//			ECrawlerState extractionCrawlerState = ECrawlerState.RUNNING;
//			ECrawlerState saveCrawlerState = ECrawlerState.RUNNING;
//			while (ECrawlerState.SUCCESS.equals(loadPageCrawlerState) == false) {
//				// 初始化
//				loadPageCrawlerState = ECrawlerState.RUNNING;
//				extractionCrawlerState = ECrawlerState.RUNNING;
//				saveCrawlerState = ECrawlerState.RUNNING;
//				// 运行结果状态
//				loadPageCrawlerState = _LoadPageService_.doNewProcess();
//
//				if (ECrawlerState.STOP.equals(loadPageCrawlerState)) {
//					logger.warn("LoadPageService.doProcess .......... STOP");
//					break;
//				} else if (ECrawlerState.FAIL.equals(loadPageCrawlerState)) {
//					logger.warn("LoadPageService.doProcess .......... FAIL");
//					break;
//				} else if (ECrawlerState.REPEAT.equals(loadPageCrawlerState)) {
//					logger.debug("LoadPageService.doProcess .......... REPEAT");
//				}
//				// 数据抽取--------------------------------------------------------------------------------------------------------------------
//				{
//					while (ECrawlerState.SUCCESS.equals(extractionCrawlerState) == false) {
//						// 运行结果状态
//						extractionCrawlerState = _ExtractionService_.doProcess(bizName, _LoadPageService_.currentResult(), _LoadPageService_.currentPageParam());
//						if (ECrawlerState.STOP.equals(extractionCrawlerState)) {
//							logger.warn("ExtractionService.doProcess .......... STOP");
//							break;
//						} else if (ECrawlerState.FAIL.equals(extractionCrawlerState)) {
//							logger.warn("ExtractionService.doProcess .......... FAIL");
//							break;
//						} else if (ECrawlerState.REPEAT.equals(extractionCrawlerState)) {
//							logger.debug("ExtractionService.doProcess .......... REPEAT");
//						}
//						// 数据保存--------------------------------------------------------------------------------------------------------------------
//						{
//							while (ECrawlerState.SUCCESS.equals(saveCrawlerState) == false) {
//								// 运行结果状态
//								saveCrawlerState = _SaveDataService_.doProcess(bizName, _ExtractionService_.currentResult());
//								if (ECrawlerState.STOP.equals(saveCrawlerState)) {
//									logger.warn("SaveDataService.doProcess .......... STOP");
//									break;
//								} else if (ECrawlerState.FAIL.equals(saveCrawlerState)) {
//									logger.warn("SaveDataService.doProcess .......... FAIL");
//									break;
//								} else if (ECrawlerState.REPEAT.equals(saveCrawlerState)) {
//									logger.debug("SaveDataService.doProcess .......... REPEAT");
//								}
//							}
//							if (ECrawlerState.SUCCESS.equals(saveCrawlerState))
//								logger.debug("SaveDataService.doProcess .......... SUCCESS");
//						}
//					}
//					if (ECrawlerState.SUCCESS.equals(extractionCrawlerState))
//						logger.debug("ExtractionService.doProcess .......... SUCCESS");
//				}
//			}
//			if (ECrawlerState.SUCCESS.equals(loadPageCrawlerState))
//				logger.debug("LoadPageService.doProcess .......... SUCCESS");
//		}
//		logger.debug("正常结束【{}】，任务ID={}", bizName, jobKey);
//	}
//
//	/**
//	 * 爬虫处理逻辑-基于线性操作，自由控制完成（不保存）
//	 * @see #ALoginService
//	 * @see #ALoadPageService
//	 * @param bizName
//	 * @throws Exception
//	 */
//	public void doCrawlerProcessByLinear() throws Exception {
//		logger.debug("开始启动【{}】，任务ID={}", bizName, jobKey);
//		// 模拟登录--------------------------------------------------------------------------------------------------------------------
//		ALoginService<Object, Object> _LoginService_ = MyBeanFactoryHelper.getBean(bizName + "LoginService");
//		{
//			ECrawlerState loginCrawlerState = ECrawlerState.RUNNING;
//			while (ECrawlerState.SUCCESS.equals(loginCrawlerState) == false) {
//				// 运行结果状态
//				loginCrawlerState = _LoginService_.doProcess(bizName, this.currentResult());
//				if (ECrawlerState.STOP.equals(loginCrawlerState)) {
//					logger.warn("LoginService.doProcess .......... STOP");
//					break;
//				} else if (ECrawlerState.FAIL.equals(loginCrawlerState)) {
//					logger.warn("LoginService.doProcess .......... FAIL");
//					break;
//				} else if (ECrawlerState.REPEAT.equals(loginCrawlerState)) {
//					logger.debug("LoginService.doProcess .......... REPEAT");
//				}
//			}
//			if (ECrawlerState.SUCCESS.equals(loginCrawlerState))
//				logger.debug("LoginService.doProcess .......... SUCCESS");
//		}
//		// 模拟打开页面--------------------------------------------------------------------------------------------------------------------
//		ALoadPageService<Object, Object> _LoadPageService_ = MyBeanFactoryHelper.getBean(bizName + "LoadPageService");
//		{
//			ECrawlerState loadPageCrawlerState = ECrawlerState.RUNNING;
//			while (ECrawlerState.SUCCESS.equals(loadPageCrawlerState) == false) {
//				loadPageCrawlerState = ECrawlerState.RUNNING;
//				// 运行结果状态
//				loadPageCrawlerState = _LoadPageService_.doNewProcess();
//				if (ECrawlerState.STOP.equals(loadPageCrawlerState)) {
//					logger.warn("LoadPageService.doProcess .......... STOP");
//					break;
//				} else if (ECrawlerState.FAIL.equals(loadPageCrawlerState)) {
//					logger.warn("LoadPageService.doProcess .......... FAIL");
//					break;
//				} else if (ECrawlerState.REPEAT.equals(loadPageCrawlerState)) {
//					logger.debug("LoadPageService.doProcess .......... REPEAT");
//				}
//			}
//			if (ECrawlerState.SUCCESS.equals(loadPageCrawlerState))
//				logger.debug("LoadPageService.doProcess .......... SUCCESS");
//		}
//		logger.debug("正常结束【{}】，任务ID={}", bizName, jobKey);
//	}

	/**
	 * 爬虫处理逻辑-基于简单操作，自由控制完成
	 * 
	 * @param bizName
	 * @throws Exception
	 */
	public void doCrawlerProcessByDefault() throws Exception {
		logger.debug("开始启动【{}】，任务ID={}", bizName, jobKey);
		// 模拟打开页面--------------------------------------------------------------------------------------------------------------------
		ALoadPageService<Object, Object> _LoadPageService_ = MyBeanFactoryHelper.getBean(bizName + "LoadPageService");
		{
			ECrawlerState loadPageCrawlerState = ECrawlerState.RUNNING;
			while (ECrawlerState.SUCCESS.equals(loadPageCrawlerState) == false) {
				loadPageCrawlerState = ECrawlerState.RUNNING;
				// 运行结果状态
				loadPageCrawlerState = _LoadPageService_.doNewProcess();
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
		logger.debug("正常结束【{}】，任务ID={}", bizName, jobKey);
	}
}
