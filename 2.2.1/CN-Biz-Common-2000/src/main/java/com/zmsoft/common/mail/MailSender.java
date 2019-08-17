package com.zmsoft.common.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.zmsoft.config.system.MyMailConfig;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.EmptyHelper;


/**
 * 实现多账号，轮询发送
 */
@Component("MailSender")
public class MailSender extends JavaMailSenderImpl implements JavaMailSender {
	private MyMailConfig myMailConfig;
	// 保存多个用户名和密码的队列
	private ArrayList<String> usernameList;
	private ArrayList<String> passwordList;
	// 轮询标识
	private int currentMailId = 0;

	//
	public MailSender() {
		if (EmptyHelper.isEmpty(myMailConfig))
			myMailConfig = MyBeanFactoryHelper.getBean(MyMailConfig.class.getSimpleName());
		String username = myMailConfig.getUsername();
		String password = myMailConfig.getPassword();
		// 初始化账号
		if (usernameList == null)
			usernameList = new ArrayList<String>();
		String[] userNames = username.split(",");
		if (userNames != null) {
			for (String user : userNames) {
				usernameList.add(user);
			}
		}

		// 初始化密码
		if (passwordList == null)
			passwordList = new ArrayList<String>();
		String[] passwords = password.split(",");
		if (passwords != null) {
			for (String pw : passwords) {
				passwordList.add(pw);
			}
		}
	}

	@Override
	protected void doSend(MimeMessage[] mimeMessage, Object[] object)
			throws MailException {
		super.setUsername(usernameList.get(currentMailId));
		super.setPassword(passwordList.get(currentMailId));
		String host = myMailConfig.getHost();

		int port = Integer.valueOf(myMailConfig.getPort());

		String defaultEncoding = myMailConfig.getDefaultEncoding();
		// 设置编码和各种参数
		super.setHost(host);
		super.setPort(port);
		super.setDefaultEncoding(defaultEncoding);
		super.setJavaMailProperties(asProperties(this.getProperties()));
		super.doSend(mimeMessage, object);
		// 轮询
		currentMailId = (currentMailId + 1) % usernameList.size();

	}

	private Properties asProperties(Map<String, String> source) {
		Properties properties = new Properties();
		properties.putAll(source);
		return properties;
	}

	public Map<String, String> getProperties() {
		//
		String auth = myMailConfig.getAuth();

		String timeout = myMailConfig.getTimeout();
		
		int port = Integer.valueOf(myMailConfig.getPort());

		Map<String, String> map = new HashMap<String, String>();
		map.put("mail.smtp.timeout", timeout);
		map.put("mail.smtp.auth", auth);
		map.put("mail.smtp.socketFactory.fallback", "false");
		map.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		map.put("mail.smtp.socketFactory.port", port + "");
		return map;

	}

	@Override
	public String getUsername() {
		return usernameList.get(currentMailId);
	}

}