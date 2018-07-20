package org.zmsoft.jfp.framework.beans.pub;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.springframework.mail.SimpleMailMessage;

/**
 * 邮件对象内容
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class MailBean extends SimpleMailMessage implements IFrameworkConstants {

	private static final long serialVersionUID = -8536273277566490823L;

	public MailBean() {
		super();
	}

	public MailBean(MailBean original) {
		super(original);
	}

}
