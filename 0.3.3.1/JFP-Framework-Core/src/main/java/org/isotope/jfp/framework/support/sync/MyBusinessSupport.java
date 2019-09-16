package org.isotope.jfp.framework.support.sync;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISModelConstants;
import org.isotope.jfp.framework.support.MyWorkSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据业务操作接口定义超类<br>
 * 事务性质业务逻辑<br>
 * 
 * @author Spook
 * @since 0.1.0 2013-8-21
 * @version 0.1.0
 */
public class MyBusinessSupport extends MyWorkSupport implements ISFrameworkConstants, ISModelConstants { //ISToken
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 返回结果(0：成功、其他：失败（业务系统提示码）)
	 */
	protected String returnCode = ZERO;
	/**
	 * 提示信息
	 */
	protected String returnMessage = MESSAGE_ERROR_SYNC;
	/**
	 * 接口返回的数据
	 */
	protected Object returnObject = EMPTY;

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		if (EmptyHelper.isEmpty(returnObject)) {
			this.returnObject = EMPTY;
		} else {
			this.returnObject = returnObject;
		}
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	
	////////////////////////////////////
	/**
	 * 业务请求版本号
	 */
	protected String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * 用户请求参数
	 */
	protected String paramValue;

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}
