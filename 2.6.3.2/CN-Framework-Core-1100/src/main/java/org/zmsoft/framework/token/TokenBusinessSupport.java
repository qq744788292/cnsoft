package org.zmsoft.framework.token;

import javax.servlet.http.HttpServletRequest;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.cache.ISCacheService;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.PKHelper;
import org.zmsoft.framework.utils.RandomHelper;

import com.alibaba.fastjson.JSON;

/**
 * 业务请求Token数据算法
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class TokenBusinessSupport extends TokenServiceImpl {

	public static void main(String[] args) throws Exception {
		TokenBusinessSupport tokenBiz = new TokenBusinessSupport();
		tokenBiz.setSessionId("1234567890ABCDEFGHIJKL");
		tokenBiz.setUuid(SYSTEM_OpenId + SYSTEM_OpenId);
		tokenBiz.setClientTimestamp("20171121010101");
		tokenBiz.setUserId("1234567890ABCDEFGHIJKL");
		tokenBiz.setHdp("gov");
		System.out.println("tokenBiz====>>>>>" + tokenBiz);// 102102103203204204
		String token = tokenBiz.prepareTokenBiz();
		System.out.println((token));// 102102103203204204
		System.out.println(build("1234567890", token, true));// {"clientTimestamp":"000000","ipAdress":"111222","sessionId":"223344","token":"102102103203204204"}
	}

	///////////////////////////////////////////////////////////////////////
	/**
	 * Token还原
	 * 
	 * @return tonkenString((时间+IP地址+HDP)+SessionID)
	 * @throws Exception
	 */
	public static TokenBusinessSupport build(String jobId, String bizToken, boolean autoSplit) throws Exception {
		TokenBusinessSupport tokenBean = new TokenBusinessSupport();
		if (autoSplit) {
			tokenBean.buildTokenBiz(bizToken);
		} else {
			tokenBean.setToken(bizToken);
		}
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
	public static TokenBusinessSupport build(HttpServletRequest request, UserBean userBean) throws Exception {
		return build(request, null, userBean);
	}

	public static TokenBusinessSupport build(HttpServletRequest request, ISCacheService myCacheService, UserBean userBean) throws Exception {
		TokenBusinessSupport tokenBean = new TokenBusinessSupport();
		if (EmptyHelper.isEmpty(userBean)) {
			tokenBean.setToken(PKHelper.creatUUKey());
			return tokenBean;
		}
		/////////////////////////////////////////////////////////////////////////////////////////////
		// SessionID
		String sessionId;
		if (EmptyHelper.isEmpty(request)) {
			sessionId = PKHelper.creatUUKey();
		} else {
			sessionId = tokenBean.prepareSessionId(request);
		}
		// 用户唯一识别ID(10位)
		String uuid = userBean.getUuid();
		if (EmptyHelper.isEmpty(uuid))
			uuid = RandomHelper.getRandomString(16).toUpperCase();
		String userId = userBean.getUserId();
		if (EmptyHelper.isEmpty(userId))
			userId = PKHelper.creatTimeKey().toUpperCase();
		String hdp = userBean.getHdp();
		if (EmptyHelper.isEmpty(hdp))
			hdp = SYSTEM;
		// 设定参数
		tokenBean.setSessionId(sessionId);
		tokenBean.setUserId(userId);
		tokenBean.setUuid(uuid);
		tokenBean.setHdp(hdp);
		tokenBean.prepareClientTimestamp();
		/////////////////////////////////////////////////////////////////////////////////////////////
		// Token制作
		tokenBean.prepareTokenBiz();
		tokenBean.setCurUser(userBean);
		tokenBean.setMyCacheService(myCacheService);
		tokenBean.saveToken(request);

		return tokenBean;
	}

	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 系统默认用户(游客)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static TokenBusinessSupport defaultToken() throws Exception {
		return defaultToken(null, null, null);
	}

	public static TokenBusinessSupport defaultToken(HttpServletRequest request) throws Exception {
		return defaultToken(request, null, defaultUser(SYSTEM_OpenId));
	}

	/**
	 * 系统默认用户(游客)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static TokenBusinessSupport defaultToken(HttpServletRequest request, ISCacheService myCacheService) throws Exception {
		return defaultToken(request, myCacheService, defaultUser(SYSTEM_OpenId));
	}

	/**
	 * 系统默认用户(游客)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static TokenBusinessSupport defaultToken(HttpServletRequest request, String userId) throws Exception {
		return defaultToken(request, null, defaultUser(userId));
	}

	/**
	 * 系统默认用户(游客)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static TokenBusinessSupport defaultToken(UserBean userBean) throws Exception {
		return defaultToken(null, null, userBean);
	}

	public static TokenBusinessSupport defaultToken(HttpServletRequest request, ISCacheService myCacheService, UserBean userBean) throws Exception {
		TokenBusinessSupport tokenBean = build(request, userBean);
		tokenBean.setMyCacheService(myCacheService);
		tokenBean.setCurUser(userBean);
		tokenBean.saveToken(request);
		return tokenBean;
	}

	public static UserBean defaultUser() throws Exception {
		return defaultUser(SYSTEM_OpenId);
	}

	public static UserBean defaultUser(String userId) throws Exception {
		UserBean curUser = new UserBean(userId);
		curUser.setUserNameCN(GUEST);
		curUser.setCompanyId(SYSTEM);
		curUser.setCompanyNameCN(GUEST);
		curUser.setRoolType("30");
		curUser.setHdp(GUEST);
		return curUser;
	}

	////////////////////////////////////////////////////////////////////////////////////////

	private UserBean curUser;

	public void setCurUser(UserBean curUser) {
		this.curUser = curUser;
	}

	public UserBean currentUser() {
		return curUser;
	}

	///////////////////////////////////////////////////////////////////
	// 自动接口拦截器(0关闭1开启)
	public static String jobIdRole = ZERO;

	public boolean checkToken(HttpServletRequest request) throws Exception {
		try {
			String curToken = getToken();
			String curJobId = super.getJobId();
			if (EmptyHelper.isEmpty(curToken))
				return false;

			if (EmptyHelper.isEmpty(curJobId) && ONE.equals(jobIdRole))
				return super.checkToken(request);
			// 基于缓存中心进行校验
			if (myCacheService != null) {
				// 获得历史Token
				String tokenKey = TOKEN + getToken();
				String tokenCatch = (String) myCacheService.getObject(tokenKey, false);
				if (logger.isInfoEnabled())
					logger.info("checkToken=====tokenCatch>>>>>" + tokenCatch);
				// 是否需要支持可变Token
				if (EmptyHelper.isNotEmpty(tokenCatch)) {
					// 解析Token对应用户信息内容
					curUser = JSON.parseObject(tokenCatch, UserBean.class);
					// 设置用户类型（（1注册用户0游客））
					if (GUEST.equals(curUser.getHdp())) {
						curUser.setRoolType(ZERO);
					} else {
						curUser.setRoolType(ONE);
					}

					// 缓存当前线程Loginer
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
			return myCacheService.putObject(TOKEN + getToken(), JSON.toJSONString(currentUser()), waitTimeSecond, false);
		return false;
	}
	
	/**
	 * Token保存
	 */
	public boolean saveToken() throws Exception {
		return saveToken(null);
	}

	/**
	 * 更新Token令牌内容，立刻生效
	 */
	public boolean chageToken(HttpServletRequest request) throws Exception {
		String oldToken = getToken();
		// 删除原有数据
		myCacheService.deleteObject(TOKEN + oldToken);
		super.setToken(EMPTY);// 废弃原有

		// 更新时间
		this.prepareClientTimestamp();

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

	/**
	 * 进程阻塞时间（秒）
	 */
	protected int waitTimeSecond = 3600 * 12;

	public void setWaitTimeSecond(int waitTimeSecond) {
		this.waitTimeSecond = waitTimeSecond;
	}

	///////////////////////////////////////////////////////////////////
	/**
	 * 返回Token令牌内容
	 */
	public String getToken() {
		if (EmptyHelper.isEmpty(super.getToken()))
			try {
				super.setToken(prepareTokenBiz());
			} catch (Exception e) {
				e.printStackTrace();
			}
		return super.getToken();
	}

	/**
	 * 缓存队列
	 */
	protected ISCacheService myCacheService;

	public void setMyCacheService(ISCacheService myCacheService) {
		this.myCacheService = myCacheService;
	}
}