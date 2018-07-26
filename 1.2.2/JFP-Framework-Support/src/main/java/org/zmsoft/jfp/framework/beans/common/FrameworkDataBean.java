package org.zmsoft.jfp.framework.beans.common;

import org.zmsoft.jfp.framework.beans.ObjectBean;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

/**
 * 基底共通
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class FrameworkDataBean extends ObjectBean implements IFrameworkConstants {

	/**
	 * 系统数据唯一识别ID（固定主键）
	 */
	private String puk = null;

	/**
	 * 获取系统数据唯一识别ID（固定主键）
	 *
	 * @return PUK 系统数据唯一识别ID（固定主键）
	 */
	public String getPuk() {
		return this.puk;
	}

	/**
	 * 设置系统数据唯一识别ID（固定主键）
	 *
	 * @param PUK
	 *            系统数据唯一识别ID（固定主键）
	 */
	public void setPuk(String PUK) {
		this.puk = PUK;
	}

}
