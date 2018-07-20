package org.zmsoft.jfp.framework.job.bean;

import org.zmsoft.jfp.framework.beans.ObjectBean;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

/** 任务定义表*/
public class ScheduleJobBean extends ObjectBean implements IFrameworkConstants {
	/**
	 * 任务id
	 */
	private String jobId;
	/** 
     * 任务编号
     */
    private String jobNo = null;
 
    /** 
     * 任务名称
     */
    private String jobName = null;
 
    /** 
     * 分组名称
     */
    private String jobGroup = null;
 
    /** 
     * 状态
     */
    private String jobStatus = null;
 
    /** 
     * 任务类别
     */
    private String jobType = null;
 
    /** 
     * cron表达式
     */
    private String cronExpression = null;
 
    /** 
     * 描述
     */
    private String description = null;
 
    /** 
     * 任务是否有状态
     */
    private String isConcurrent = null;
 
    /** 
     * spring bean
     */
    private String springBeanId = null;
 
    /** 
     * 任务调用的方法名
     */
    private String beanMethodName = null;
 
    /** 
     * 接口调用模式
     */
    private String apiMode = null;
 
    /** 
     * 接口名称
     */
    private String apiName = null;
 
    /** 
     * 接口URL
     */
    private String apiUrl = null;
 
    /** 
     * 接口参数
     */
    private String apiParam = null;
  
    /** 
     * 获取任务ID
     *
     * @return Job_id 任务ID
     */
    public String getJobId() {
        return this.jobId;
    }
 
    /** 
     * 获取任务编号
     *
     * @return Job_no 任务编号
     */
    public String getJobNo() {
        return this.jobNo;
    }
 
    /** 
     * 获取任务名称
     *
     * @return Job_name 任务名称
     */
    public String getJobName() {
        return this.jobName;
    }
 
    /** 
     * 获取分组名称
     *
     * @return Job_group 分组名称
     */
    public String getJobGroup() {
        return this.jobGroup;
    }
 
    /** 
     * 获取状态
     *
     * @return Job_status 状态
     */
    public String getJobStatus() {
        return this.jobStatus;
    }
 
    /** 
     * 获取任务类别
     *
     * @return Job_type 任务类别
     */
    public String getJobType() {
        return this.jobType;
    }
 
    /** 
     * 获取cron表达式
     *
     * @return Cron_expression cron表达式
     */
    public String getCronExpression() {
        return this.cronExpression;
    }
 
    /** 
     * 获取描述
     *
     * @return Description 描述
     */
    public String getDescription() {
        return this.description;
    }
 
    /** 
     * 获取任务是否有状态
     *
     * @return Is_concurrent 任务是否有状态
     */
    public String getIsConcurrent() {
        return this.isConcurrent;
    }
 
    /** 
     * 获取spring bean
     *
     * @return Spring_bean_id spring bean
     */
    public String getSpringBeanId() {
        return this.springBeanId;
    }
 
    /** 
     * 获取任务调用的方法名
     *
     * @return Bean_method_name 任务调用的方法名
     */
    public String getBeanMethodName() {
        return this.beanMethodName;
    }
 
    /** 
     * 获取接口调用模式
     *
     * @return Api_mode 接口调用模式
     */
    public String getApiMode() {
        return this.apiMode;
    }
 
    /** 
     * 获取接口名称
     *
     * @return Api_name 接口名称
     */
    public String getApiName() {
        return this.apiName;
    }
 
    /** 
     * 获取接口URL
     *
     * @return Api_url 接口URL
     */
    public String getApiUrl() {
        return this.apiUrl;
    }
 
    /** 
     * 获取接口参数
     *
     * @return Api_param 接口参数
     */
    public String getApiParam() {
        return this.apiParam;
    }
 
    /** 
     * 设置任务ID
     *
     * @param Job_id 任务ID
     */
    public void setJobId(String jobid) {
        this.jobId = jobid;
    }
 
    /** 
     * 设置任务编号
     *
     * @param Job_no 任务编号
     */
    public void setJobNo(String jobno) {
        this.jobNo = jobno;
    }
 
    /** 
     * 设置任务名称
     *
     * @param Job_name 任务名称
     */
    public void setJobName(String jobname) {
        this.jobName = jobname;
    }
 
    /** 
     * 设置分组名称
     *
     * @param Job_group 分组名称
     */
    public void setJobGroup(String jobgroup) {
        this.jobGroup = jobgroup;
    }
 
    /** 
     * 设置状态
     *
     * @param Job_status 状态
     */
    public void setJobStatus(String jobstatus) {
        this.jobStatus = jobstatus;
    }
 
    /** 
     * 设置任务类别
     *
     * @param Job_type 任务类别
     */
    public void setJobType(String jobtype) {
        this.jobType = jobtype;
    }
 
    /** 
     * 设置cron表达式
     *
     * @param Cron_expression cron表达式
     */
    public void setCronExpression(String cronexpression) {
        this.cronExpression = cronexpression;
    }
 
    /** 
     * 设置描述
     *
     * @param Description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
 
    /** 
     * 设置任务是否有状态
     *
     * @param Is_concurrent 任务是否有状态
     */
    public void setIsConcurrent(String isconcurrent) {
        this.isConcurrent = isconcurrent;
    }
 
    /** 
     * 设置spring bean
     *
     * @param Spring_bean_id spring bean
     */
    public void setSpringBeanId(String springbeanid) {
        this.springBeanId = springbeanid;
    }
 
    /** 
     * 设置任务调用的方法名
     *
     * @param Bean_method_name 任务调用的方法名
     */
    public void setBeanMethodName(String beanmethodname) {
        this.beanMethodName = beanmethodname;
    }
 
    /** 
     * 设置接口调用模式
     *
     * @param Api_mode 接口调用模式
     */
    public void setApiMode(String apimode) {
        this.apiMode = apimode;
    }
 
    /** 
     * 设置接口名称
     *
     * @param Api_name 接口名称
     */
    public void setApiName(String apiname) {
        this.apiName = apiname;
    }
 
    /** 
     * 设置接口URL
     *
     * @param Api_url 接口URL
     */
    public void setApiUrl(String apiurl) {
        this.apiUrl = apiurl;
    }
 
    /** 
     * 设置接口参数
     *
     * @param Api_param 接口参数
     */
    public void setApiParam(String apiparam) {
        this.apiParam = apiparam;
    }
 


}
