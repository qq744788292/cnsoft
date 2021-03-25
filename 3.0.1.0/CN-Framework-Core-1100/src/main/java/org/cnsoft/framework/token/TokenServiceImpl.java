package org.cnsoft.framework.token;

import javax.servlet.http.HttpServletRequest;

import org.cnsoft.framework.beans.user.UserBean;
import org.cnsoft.framework.cache.session.SessionHelper;
import org.cnsoft.framework.json.JSONObject;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.framework.utils.PKHelper;

/**
 * 业务请求Token数据算法
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class TokenServiceImpl extends TokenBusinessSupport {
	
	//////////////////////////////////////TOKEN制作///////////////////////////////////////////////
	/**
	 * 系统默认用户(游客)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static TokenServiceImpl defaultToken() throws Exception {
		return defaultToken(null, SYSTEM_OpenId);
	}

	public static TokenServiceImpl defaultToken(HttpServletRequest request) throws Exception {
		return defaultToken(request, defaultUser(SYSTEM_OpenId));
	}

	/**
	 * 系统默认用户(游客)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static TokenServiceImpl defaultToken(HttpServletRequest request, String userId) throws Exception {
		return defaultToken(request, defaultUser(userId));
	}

	/**
	 * 系统默认用户(游客)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static TokenServiceImpl defaultToken(UserBean userBean) throws Exception {
		return defaultToken(null, userBean);
	}

	public static TokenServiceImpl defaultToken(HttpServletRequest request, UserBean userBean) throws Exception {
		TokenServiceImpl tokenBean = build(request, userBean);
		tokenBean.setCurUser(userBean);
		return tokenBean;
	}

	/**
	 * Token还原
	 */
	public static TokenServiceImpl build(String jobId, String bizToken) throws Exception {
		TokenServiceImpl tokenBean = new TokenServiceImpl();
		tokenBean.setToken(bizToken);
		tokenBean.setJobId(jobId);
		return tokenBean;
	}

	/**
	 * 基于用户信息制作Token
	 * 
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static TokenServiceImpl build(HttpServletRequest request, UserBean curUser) throws Exception {
		TokenServiceImpl tokenBean = new TokenServiceImpl();
		if (EmptyHelper.isEmpty(curUser)) {
			curUser = new UserBean();
			curUser.setToken(PKHelper.creatUUKey());
			tokenBean.setCurUser(curUser);
			return tokenBean;
		}
		/////////////////////////////////////////////////////////////////////////////////////////////
		// SessionID
		String sessionId;
		// 客户端识别ID(前端密钥)
		String clientId;
		if (EmptyHelper.isEmpty(request)) {
			sessionId = PKHelper.creatUUKey();
			clientId = PKHelper.creatUUKey();
		} else {
			sessionId = tokenBean.prepareSessionId(request);
			clientId = tokenBean.prepareClientId(request);
		}
		// 用户唯一识别ID(10位)
		String uuid = curUser.getUuid();
		if (EmptyHelper.isEmpty(uuid))
			uuid = PKHelper.creatUUKey();
		/////////////////////////////////////////////////////////////////////////////////////////////
		String userId = curUser.getUserId();
		if (EmptyHelper.isEmpty(userId))
			userId = PKHelper.creatUUKey();
		/////////////////////////////////////////////////////////////////////////////////////////////
		String sid = curUser.getSid();
		if (EmptyHelper.isEmpty(sid))
			sid = SYSTEM;
		// 设定参数
		/////////////////////////////////////////////////////////////////////////////////////////////
		curUser.setSessionId(sessionId);
		curUser.setSid(sid);
		curUser.setUserId(userId);
		curUser.setClientId(clientId);
		curUser.setClientTimestamp(tokenBean.prepareClientTimestamp());

//		tokenBean.setSessionId("1234567890ABCDEFGHIJKL");
//		tokenBean.setSid(SYSTEM_OpenId);
//		tokenBean.setUserId("1234567890ABCDEFGHIJKL");
//		tokenBean.setClientId("gov");
//		tokenBean.setClientTimestamp("20171121010101");
		/////////////////////////////////////////////////////////////////////////////////////////////
		// Token制作
		tokenBean.setCurUser(curUser);
		tokenBean.prepareTokenBiz();

		return tokenBean;
	}

	public static UserBean defaultUser() throws Exception {
		return defaultUser(SYSTEM_OpenId);
	}

	public static UserBean defaultUser(String userId) throws Exception {
		UserBean curUser = new UserBean(userId);
		curUser.setUserNameCN(GUEST);
		curUser.setCompanyId(GUEST);
		curUser.setCompanyNameCN(GUEST);
		curUser.setRoolType("30");
		curUser.setSid(SYSTEM_OpenId);
		return curUser;
	}

	//////////////////////////////////////////TOKEN校验//////////////////////////////////////////////
	// 自动接口拦截器(0关闭1开启)
	public static String jobIdRole = ZERO;

	public boolean checkToken(HttpServletRequest request) throws Exception {
		try {
			String curToken = super.getToken();
			if (EmptyHelper.isEmpty(curToken))
				return false;

			String curJobId = super.getJobId();
			if (EmptyHelper.isEmpty(curJobId) && ONE.equals(jobIdRole))
				return super.checkToken(request);
			// 基于缓存中心进行校验
			if (myCacheService != null) {
				// 获得历史Token
				String tokenKey = TOKEN + getToken();
				String tokenCatch = myCacheService.getObject(tokenKey);
				if (logger.isInfoEnabled())
					logger.info("checkToken=====tokenCatch>>>>>" + tokenCatch);
				// 是否需要支持可变Token
				if (EmptyHelper.isNotEmpty(tokenCatch)) {
					// 解析Token对应用户信息内容
					UserBean curUser = JSONObject.parseObject(tokenCatch, UserBean.class);
					// 设置用户类型（（1注册用户0游客））
					if (GUEST.equals(curUser.getSid())) {
						curUser.setRoolType(ONE);
					} else {
						curUser.setRoolType(ZERO);
					}

					///////////////////////////////////////////////////////////////////////
					// 缓存当前线程用户信息
					super.setCurUser(curUser);
					SessionHelper.setSessionAttribute(this);
					myCacheService.expire(tokenKey, waitTimeSecond);
					return true;
				}
			}
		} catch (Exception r) {
		}
		// 清空线程缓存
		SessionHelper.setSessionAttribute(null);
		return false;
	}

	/**
	 * Token保存
	 */
	public boolean saveToken(HttpServletRequest request) throws Exception {
		// 当前线程缓存用户对象
		SessionHelper.setSessionAttribute(this);
		// 缓存更新用户对象
		if (myCacheService != null)
			return myCacheService.putObject(TOKEN + super.getToken(), currentUser().toJsonString(), waitTimeSecond);
		return false;
	}

	/**
	 * 更新Token令牌内容，立刻生效
	 */
	public boolean chageToken(HttpServletRequest request) throws Exception {
		String oldToken = super.getToken();
		// 删除原有数据
		myCacheService.deleteObject(TOKEN + oldToken);
		super.setToken(EMPTY);// 废弃原有

		// 更新时间
		super.currentUser().setClientTimestamp(this.prepareClientTimestamp());

		return saveToken(request);
	}

	/**
	 * 删除token
	 */
	public boolean removeToken() throws Exception {
		// 删除原有数据
		myCacheService.deleteObject(TOKEN + getToken());
		return true;
	}

}