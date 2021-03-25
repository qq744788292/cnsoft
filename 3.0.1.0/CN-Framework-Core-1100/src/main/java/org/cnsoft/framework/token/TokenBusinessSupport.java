package org.cnsoft.framework.token;

import javax.servlet.http.HttpServletRequest;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.beans.user.UserBean;
import org.cnsoft.framework.common.buzzinezz.ISTokenSupport;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.security.MD5SecurityHelper;
import org.cnsoft.framework.support.MyFrameWorkSupport;
import org.cnsoft.framework.utils.DateHelper;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.framework.utils.PKHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务请求Token数据算法
 * 
 * @author CNSoft
 * @version 2.1.1 2019/4/8
 * @since 2.1.1 2019/4/8
 */
public class TokenBusinessSupport extends MyFrameWorkSupport implements ISTokenSupport, ICFrameworkConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	///////////////////////////////////////////////////////////////////////////////////////////////////
	public String prepareSessionId(HttpServletRequest request) {
		return request.getSession().getId().toUpperCase().replaceAll("-", "");
	}
	
	public String prepareClientId(HttpServletRequest request) {
		return PKHelper.creatUUKey();
	}

	public String prepareClientTimestamp() {
		return DateHelper.currentTimeMillis0();
	}

	public String getToken() {
		return currentUser().getToken();
	}

	public void setToken(String token) {
		currentUser().setToken(token);
	}

	public String getJobId() {
		return currentUser().getJobId();
	}

	public void setJobId(String jobId) {
		currentUser().setJobId(jobId);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 当前登录用户
	 */
	private UserBean curUser = new UserBean();

	public void setCurUser(UserBean curUser) {
		this.curUser = curUser;
	}

	public UserBean currentUser() {
		return curUser;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * @return
	 * @throws Exception
	 */
	public String prepareTokenBiz() throws Exception {
		// 默认组合顺序
		String token = TokenBusinessHelper.getBizTokenData(currentUser().getSessionId(), // 32
				currentUser().getSid(), // 20+12
				currentUser().getUserId(), // 20+12
				currentUser().getClientId(), // 17+15
				currentUser().getClientTimestamp()// 17+15
		);
		this.setToken(MD5SecurityHelper.encrypt(token));
		return this.getToken();
	}

	public void buildTokenBiz(String bizToken) throws Exception {
		String[] ds = buildTokenBizData(bizToken);
		UserBean curUser = currentUser();
		curUser.setSessionId(ds[0]);
		curUser.setSid(ds[1]);
		curUser.setUserId(ds[2]);
		curUser.setClientId(ds[3]);
		curUser.setClientTimestamp(ds[4]);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 根据企业ID获得用户ID
	 * 
	 * @param userToken
	 * @return
	 * @throws Exception
	 */
	public static String[] buildTokenBizData(String bizToken) throws Exception {
		if (EmptyHelper.isEmpty(bizToken))
			throw new Exception("不能存在空值");

		return TokenBusinessHelper.getBizTokenData(bizToken, DATA_NUM);
	}

	public final static int DATA_NUM = 5;

	public final static String TOKEN = "TOKEN:";

	@Override
	public boolean checkToken(HttpServletRequest request) throws Exception {

		try {
			ISTokenSupport token = MyBeanFactoryHelper.getBean(ISTokenSupport.My_CustomTokenCheck_Service);
			if (EmptyHelper.isEmpty(token)) {
				logger.warn("当前模式下自定义头处理没有实现......[" + ISTokenSupport.My_CustomTokenCheck_Service + "]");
			} else {
				token.checkToken(request);
			}
		} catch (Exception e) {
			logger.error("Token校验失败", e);
		}

		return false;
	}
}