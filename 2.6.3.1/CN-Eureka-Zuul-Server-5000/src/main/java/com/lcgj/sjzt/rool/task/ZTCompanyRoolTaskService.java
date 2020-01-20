package com.lcgj.sjzt.rool.task;

import org.springframework.stereotype.Component;

/**
 * 中台账户中心服务监控
 * 
 * @author ZMSoft
 *
 */
@Component
public class ZTCompanyRoolTaskService //extends ATaskSupport implements IDataCacheConstants 
{

//	// 设置启动模式-每分钟启动
//	@Scheduled(cron = "0 0/5 * * * ?")
//	public void doQuartzService() throws Exception {
//		// 激活父类启动方法
//		doJobTaskProcess();
//	}
//
//	///////////////////////////////////////////////////////////////
//	@Resource
//	RolModuleFuncDao RolModuleFuncDao_;
//	@Resource
//	RolCompanyDao RolCompanyDao_;
//	@Resource
//	RolCompanyModuleFuncDao RolCompanyModuleFuncDao_;
//
//	/**
//	 * 业务逻辑
//	 */
//	@Override
//	public boolean doProcess() throws Exception {
//		// 加载所有功能配置
//		HashMap<String, String> funcsMap = new HashMap<String, String>();
//		{
//			RolModuleFuncDBO param = new RolModuleFuncDBO();
//			List<RolModuleFuncDBO> funcs = RolModuleFuncDao_.doSelectData(param);
//			StringBuffer funcSB = new StringBuffer(2000);
//			for (RolModuleFuncDBO func : funcs) {
//				if (EmptyHelper.isEmpty(func.getApiUrl()))
//					continue;
//				funcsMap.put(func.getId(), func.getApiUrl());
//				funcSB.append(SEMICOLON);
//				funcSB.append(func.getApiUrl());
//			}
//			// 保存所有权限
//			myCacheService.putObject(API_ROLE_KEY + ZERO, funcSB.toString(), 3600 * 12, false);
//		}
//
//		// 读取企业列表
//		RolCompanyDBO paramRolCompanyDBO = new RolCompanyDBO();
//		List<RolCompanyDBO> listRolCompanyDBO = RolCompanyDao_.doSelectData(paramRolCompanyDBO);
//		List<RolCompanyModuleFuncDBO> listRolCompanyModuleFuncDBO;
//		RolCompanyModuleFuncDBO paramRolCompanyModuleFuncDBO;
//		for (RolCompanyDBO itemRolCompanyDBO : listRolCompanyDBO) {
//			// 企業功能权限加入缓存
//			paramRolCompanyModuleFuncDBO = new RolCompanyModuleFuncDBO();
//			paramRolCompanyModuleFuncDBO.setCompanyId(itemRolCompanyDBO.getId());
//			listRolCompanyModuleFuncDBO = RolCompanyModuleFuncDao_.doSelectData(paramRolCompanyModuleFuncDBO);
//			// 用户ID//功能URL//功能ID
//			String key;
//			for (RolCompanyModuleFuncDBO itemRolCompanyModuleFuncDBO : listRolCompanyModuleFuncDBO) {
//				// 获取功能列表
//				logger.debug("itemRolCompanyModuleFuncDBO=====>>>>" + itemRolCompanyModuleFuncDBO);
//				key = itemRolCompanyDBO.getAppId() + funcsMap.get(itemRolCompanyModuleFuncDBO.getFuncId());
//				logger.debug("key=====>>>>" + API_ROLE_KEY + key);
//				logger.debug("value=====>>>>" + itemRolCompanyModuleFuncDBO.getId());
//
//				// 缓存写入
//				myCacheService.putObject(API_ROLE_KEY + key, itemRolCompanyModuleFuncDBO.getId(), 60 * 12, false);
//			}
//		}
//		return true;
//	}
//
//	/**
//	 * 启动开始动作
//	 */
//	@Override
//	public boolean beforeProcess() throws Exception {
//		logger.info("中台账户中心权限加载......start");
//		return true;
//	}
//
//	/**
//	 * 启动结束动作
//	 */
//	@Override
//	public boolean afterProcess() throws Exception {
//		logger.info("中台账户中心权限加载......end");
//		return true;
//	}

}