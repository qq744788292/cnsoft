package com.hundsun.med.hdp.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hundsun.med.access.hao.hospital.SyncParamVersionHAO;
import com.hundsun.med.framework.beands.RESTResultBean;
import com.hundsun.med.hdp.utils.MyHospitalAPISupport;

/**
 * 医院退费(冲正)
 * 
 * @author fucy
 * @version 2.0.1 2015/2/3
 * @version 2.0.0 2015/1/19
 * @since 2.0.0 2015/1/19
 */
@Controller
public class HospitalRefundAPI extends MyHospitalAPISupport {
	private static final Logger logger = LoggerFactory.getLogger(HospitalRefundAPI.class);

	/**
	 * 获得医院对接系统版本信息（前置机监控程序调用）
	 * 
	 * @param request
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	@RequestMapping(value = "/Fees/HospitalRefund", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean doFeesHospitalRefund(HttpServletRequest request) {
		logger.debug(">>>>>doGetSyncParamVersion<<<<<");
		RESTResultBean rs = new RESTResultBean();
		rs.setResult(new SyncParamVersionHAO());
		rs.setMessage("处理正常");
		return rs;
	}
}
