package org.cnsoft.framework.beans.common;

import org.cnsoft.framework.cache.session.SessionHelper;
import org.cnsoft.framework.constants.ECCodeMessageConstants;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.core.ObjectBean;
import org.cnsoft.framework.db.page.PageData;
import org.cnsoft.framework.db.page.PageModel;
import org.cnsoft.framework.utils.EmptyHelper;

/**
 * 接口数据返回主体
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class RESTResultBean<T> extends ObjectBean implements ICFrameworkConstants {

	public RESTResultBean() {
	}

	public RESTResultBean(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public RESTResultBean(int code) {
		this.code = code;
	}

	public RESTResultBean(ECCodeMessageConstants codeMessage) {
		setResult(codeMessage);
	}

	/**
	 * 处理状态(0成功1失败)
	 */
	private int code = 0;// 对接返回代码 1:无数据 0:正确 其他：对应对接方错误码

	/**
	 * 提示信息
	 */
	private String msg = MESSAGE_OK;// 对接返回信息 ok:正确 其他：对应对接方错误描述

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

	public void setResult(int resultCode, String resultMsg) {
		this.code = resultCode;
		this.msg = resultMsg;
	}

	public void setResult(ECCodeMessageConstants codeMessage) {
		setResult(codeMessage.getCode(), codeMessage.getMsg());
	}

	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 返回结果
	 */
	private Object data;

	public Object getData() {
//		if (EmptyHelper.isEmpty(data))
//			return "{}";
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

	///////////////////////////////////////////////////////////////////////
	private String token;

	public String getToken() {
		if (EmptyHelper.isEmpty(token))
			return SessionHelper.currentToken();
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String jobId;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * 未读消息数目
	 */
	private int wdNum = 0;

	public int getWdNum() {
		return wdNum;
	}

	public void setWdNum(int wdNum) {
		this.wdNum = wdNum;
	}

	////////////////////////////// 分页器信息/////////////////////////////////////////
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

	//////////////////////////////////////////////////////////////////////////////////
	/**
	 * XML格式输出
	 * @param outFlag 是否输出data对应内容
	 * @return
	 */
	public String toXmlString(boolean outFlag) {
		StringBuffer sb = new StringBuffer();
		sb.append("<RESTResultBean>");

		sb.append("<code>").append(code).append("</code>");
		sb.append("<msg>").append(msg).append("</msg>");
		sb.append("<wdNum>").append(wdNum).append("</wdNum>");
		if (outFlag)
			sb.append("<data>").append(data).append("</data>");
		sb.append("<token>").append(token).append("</token>");

		sb.append("</RESTResultBean>");
		return sb.toString();
	}
}
