package org.zmsoft.framework.beans;

import org.zmsoft.framework.ObjectBean;

/**
 * 用户登录信息
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */

public class ApiDocBean extends ObjectBean implements Comparable<ApiDocBean> {

	String apiName;
	String docName;
	String url;

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int compareTo(ApiDocBean adb) {
		String a1 = this.apiName.substring(1, 7);
		String a2 = adb.apiName.substring(1, 7);

		return a1.compareTo(a2);
	}

}
