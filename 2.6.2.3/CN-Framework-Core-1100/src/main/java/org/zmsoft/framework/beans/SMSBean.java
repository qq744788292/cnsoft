package org.zmsoft.framework.beans;

import org.zmsoft.framework.beans.common.FrameworkDataBean;

/**
 * 短信接口Bean
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public class SMSBean extends FrameworkDataBean {

	/**
	 * 来源
	 */
	protected String source;
	/**
	 * 手机号码
	 */
	protected String mobile;
	/**
	 * 信息内容
	 */
	protected String msg;

	/**
	 * 用户账号
	 */
	protected String name = "GTG-LCHAPP";

	/**
	 * 用户密码
	 */
	protected String pswd = "GTG-LCHAPP";

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}


}
