package org.ishome.jfp.hds.service.biz;

import org.ishome.jfp.hds.conf.CloundSecyrityConfig;
import org.ishome.jfp.hds.constants.IHDSConstants;

import com.hundsun.med.common.Hospital.HospitalInfoService;
import com.hundsun.med.common.Hospital.HospitalSyncConfigBean;
import com.hundsun.med.framework.beands.RESTResultBean;
import com.hundsun.med.framework.biz.ISCheck;
import com.hundsun.med.framework.biz.ISInit;
import com.hundsun.med.framework.biz.ISProcess;
import com.hundsun.med.framework.constants.ISFrameworkConstants;
import com.hundsun.med.framework.mq.IMedMqService;
import com.hundsun.med.framework.utils.DateHelper;
import com.hundsun.med.framework.utils.EmptyHelper;

/**
 * 响应医院请求
 * @author fucy
 *
 * @version 2.1.0 2015/3/30
 * @version 2.0.5 2015/3/21
 */
public abstract class ACloundBusinessSupport implements IHDSConstants, ISFrameworkConstants, ISProcess, ISCheck,ISInit {
	// 云端服务配置
	protected CloundSecyrityConfig cloundSecyrityConfig;
	//缓存队列
	protected IMedMqService mqService;
	//医院状态检查
	protected HospitalInfoService hospitalInfoService; 
	protected HospitalSyncConfigBean hospitalConfig ;
	
	protected String message = null;// 医院端请求数据
	
	/**
	 * 参数初始化
	 * 
	 * @param HttpService_
	 * @param CloundApiConfig_
	 * @param pool
	 */
	public void doInit(
			IMedMqService mq, 
			HospitalSyncConfigBean HospitalSyncConfigBean_,
			HospitalInfoService HospitalInfoService_, 
			CloundSecyrityConfig CloundSecyrityConfig_,
			String jsonData) {
		this.mqService = mq;
		this.hospitalConfig = HospitalSyncConfigBean_;
		this.hospitalInfoService = HospitalInfoService_;
		this.cloundSecyrityConfig = CloundSecyrityConfig_;
		if(hospitalConfig!=null)
			this.hosId = hospitalConfig.getHosId();
		if(EmptyHelper.isNotBlank(jsonData))
			this.message = jsonData;
	}
	/**
	 * 医院ID
	 */
	protected String hosId = null;
	
	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}
	/**
	 * 业务请求API key 名称
	 */
	protected String bizName;

	public String getBizName() {
		if (EmptyHelper.isEmpty(bizName))
			bizName = this.getClass().getSimpleName();
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	
	/**
	 * 返回结果
	 */
	protected RESTResultBean result = new RESTResultBean();

	public RESTResultBean getResult() {
		return result;
	}

	public void setResult(RESTResultBean result) {
		this.result = result;
	}

	/**
	 * 业务处理过程中，医院的提示信息，用于展示
	 * @param message
	 */
	public void addHospitalMessage(String message) {
		mqService.offerObjectInList(hosId+":message", DateHelper.currentTimeMillisCN1() + " " + message);
	}
	
}
