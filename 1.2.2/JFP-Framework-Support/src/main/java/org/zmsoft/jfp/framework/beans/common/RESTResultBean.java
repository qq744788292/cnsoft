package org.zmsoft.jfp.framework.beans.common;

import org.zmsoft.jfp.framework.beans.ObjectBean;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

/**
 * 接口数据返回主体
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */

public class RESTResultBean<T> extends ObjectBean implements IFrameworkConstants {

	protected String token = null;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	protected String jobId = null;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * 处理状态(0成功1失败)
	 */
	protected String code = ZERO;// 对接返回代码 -1:无数据 0:正确 其他：对应对接方错误码

	/**
	 * 提示信息
	 */
	protected String msg = MESSAGE_OK;// 对接返回信息 空:正确 其他：对应对接方错误描述

	/**
	 * 返回结果
	 */
	protected T data = null;

	public String getCode() {
		return code;
	}

	public void setCode(String resultCode) {
		this.code = resultCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String resultMsg) {
		this.msg = resultMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setResult(String resultCode, String resultMsg) {
		this.code = resultCode;
		this.msg = resultMsg;
	}
	
	public String toXmlString(){
		StringBuffer sb = new StringBuffer();
		sb.append("<RESTResultBean>");
		
		sb.append("<code>").append(code).append("</code>");
		sb.append("<msg>").append(msg).append("</msg>");
		sb.append("<data>").append(data).append("</data>");
		sb.append("<token>").append(token).append("</token>");
		
		sb.append("</RESTResultBean>");
		return sb.toString();
	}
}
