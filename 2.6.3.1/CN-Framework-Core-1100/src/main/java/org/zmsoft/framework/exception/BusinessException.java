package org.zmsoft.framework.exception;

import org.zmsoft.framework.ObjectBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 系统业务异常
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class BusinessException extends ObjectBean implements ICFrameworkConstants {
	private String type;
	private int code;
	private String message;

	public BusinessException() {
		this(ONE, SYSTEM_ERROR_CODE, MESSAGE_ERROR_SYSTEM);
	}

	public BusinessException(String message) {
		this(ONE, SYSTEM_ERROR_CODE, message);
	}

	public BusinessException(int code, String message) {
		this(ONE, code, message);
	}

	public BusinessException(String type, int code, String message) {
		this.type = type;
		this.code = code;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}