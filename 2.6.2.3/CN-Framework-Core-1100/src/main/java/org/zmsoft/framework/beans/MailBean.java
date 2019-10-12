package org.zmsoft.framework.beans;

import org.springframework.mail.SimpleMailMessage;
import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 邮件对象内容
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class MailBean extends SimpleMailMessage implements ICFrameworkConstants {

	private static final long serialVersionUID = 58164148372216867L;

	public MailBean() {
		super();
	}
}
