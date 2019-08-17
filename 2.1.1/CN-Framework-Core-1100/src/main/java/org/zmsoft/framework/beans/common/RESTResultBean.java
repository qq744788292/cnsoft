package org.zmsoft.framework.beans.common;

import org.zmsoft.framework.beans.ObjectBean;
import org.zmsoft.framework.beans.page.PageData;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.ECodeMessageConstants;
import org.zmsoft.framework.constants.IFrameworkConstants;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 接口数据返回主体
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */

public class RESTResultBean<T> extends ObjectBean implements IFrameworkConstants {

	public RESTResultBean(int code, String msg) {
		this.code = code;
		this.msg = msg;

	}

	public RESTResultBean(int code) {
		this.code = code;

	}

	public RESTResultBean() {

	}

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
	protected int code = 0;// 对接返回代码 -1:无数据 0:正确 其他：对应对接方错误码

	/**
	 * 提示信息
	 */
	protected String msg = MESSAGE_OK;// 对接返回信息 空:正确 其他：对应对接方错误描述

	/**
	 * 返回结果
	 */
	protected Object data = null;

	public int getCode() {
		return code;
	}

	public void setCode(int resultCode) {
		this.code = resultCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String resultMsg) {
		this.msg = resultMsg;
	}

	public Object getData() {
		if(EmptyHelper.isEmpty(data))
			return "{}";
		return data;
	}

	@SuppressWarnings("unchecked")
	public void setData(Object data) {
		if (data instanceof PageModel) {
			setPageModel((PageModel<T>) data);
			return;
		}
		this.data = data;
	}

	public void setResult(int resultCode, String resultMsg) {
		this.code = resultCode;
		this.msg = resultMsg;
	}

	public void setResult(ECodeMessageConstants codeMessage) {
		setResult(codeMessage.getCode(), codeMessage.getMsg());
	}

	protected PageData page;

	public PageData getPage() {
		return page;
	}

	public void setPage(PageData page) {
		this.page = page;
	}

	public void setPageModel(PageModel<T> pagemodel) {
		if (EmptyHelper.isEmpty(pagemodel))
			return;
		page = new PageData();
		this.setData(pagemodel.getPageListData());
		this.page.setPageCount(pagemodel.getPageCount());
		this.page.setPageCurrent(pagemodel.getPageCurrent());
		this.page.setPageLimit(pagemodel.getPageLimit());
		this.page.setResultCount(pagemodel.getResultCount());
	}

	public String toXmlString() {
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
