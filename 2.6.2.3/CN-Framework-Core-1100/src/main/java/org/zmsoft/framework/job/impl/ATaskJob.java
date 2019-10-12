package org.zmsoft.framework.job.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.job.bean.JobLogBean;
import org.zmsoft.framework.job.bean.ScheduleJobBean;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.DateHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.HttpServiceHelper;
import org.zmsoft.framework.utils.JSONHelper;

/**
 * 动态任务接口
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public abstract class ATaskJob implements Job, ICFrameworkConstants {

	public final static String LOG_TASK_KEY = "ELK_TASK_LOG";
	
	protected Logger logger = LoggerFactory.getLogger(QuartzJobImpl.class);

	// 运行业务
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobLogBean jobLogBean = new JobLogBean();
		long start = System.currentTimeMillis();
		jobLogBean.setJobStartTime(DateHelper.currentTimeMillis2());
		logger.debug("==========>>>>>>>>>>作业任务开始运行==========>>>>>>>>>>");
		// 运行参数获得
		ScheduleJobBean scheduleJobBean = (ScheduleJobBean) context.getMergedJobDataMap().get("jobConfig");
		String jobKey = (String) context.getMergedJobDataMap().get("jobKey");
		logger.debug("==========" + jobKey + ">>>>>>>>>>" + scheduleJobBean);
		try {
			// 1000本地接口2000远程接口
			if ("1000".equals(scheduleJobBean.getJobType())) {
				// 变量初始化
				Class<?> clazz = MyBeanFactoryHelper.getBean(scheduleJobBean.getSpringBeanId());
				Method method = ReflectionUtils.findMethod(clazz, scheduleJobBean.getBeanMethodName());
				// 运行方法
				ReflectionUtils.invokeMethod(method, clazz);
			} else if ("1000".equals(scheduleJobBean.getJobType())) {
				String remoteApi = scheduleJobBean.getApiUrl();
				if ("POST".equals(scheduleJobBean.getApiMode().toUpperCase())) {
					Map<String, String> param = new HashMap<String, String>();
					if (EmptyHelper.isNotEmpty(scheduleJobBean.getApiParam())) {
						param = JSONHelper.toMap(scheduleJobBean.getApiParam());
					}
					// 请求接口
					String result = HttpServiceHelper.doHttpPOST(remoteApi, param);
					logger.debug("==========" + scheduleJobBean.getApiName() + ">>>>>>>>>>" + result);
				} else {
					// 请求接口
					String result = HttpServiceHelper.doHttpGET(remoteApi);
					jobLogBean.setJobRunResult(result);
					jobLogBean.setJobRunStatus(ONE);
					logger.debug("==========" + scheduleJobBean.getApiName() + ">>>>>>>>>>" + result);
				}

			} else {
				logger.error("=====>>>>>=====>>>>>作业任务配置异常=====>>>>>=====>>>>>" + scheduleJobBean.getJobId() + "." + scheduleJobBean.getJobName());
			}

		} catch (Exception e) {
			jobLogBean.setJobRunStatus(ONE);
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		// 日志整理
		{
			jobLogBean.setTaskId(jobKey);
			jobLogBean.setJobNo(scheduleJobBean.getJobNo());
			jobLogBean.setJobId(scheduleJobBean.getJobId());
			jobLogBean.setJobName(scheduleJobBean.getJobName());
			jobLogBean.setJobUseTime((end - start) + "");
		}
		// 日志输出
		saveJobLog(jobLogBean);
		logger.debug("==========>>>>>>>>>>作业任务成功运行==========>>>>>>>>>> " + jobLogBean.getJobUseTime());
	}
	
	public void saveJobLog(JobLogBean jobLogBean){
//		//日志输出
//		ELKLogSendConnection _ELKLogSendConnection_ = BeanFactoryHelper.getBean("ELKLogSendConnection");
//		_ELKLogSendConnection_.sendLogData(LOG_TASK_KEY, jobLogBean.toString());
	}
}
