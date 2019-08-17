package org.zmsoft.framework.token;

import javax.servlet.http.HttpServletRequest;

import org.zmsoft.framework.beans.TokenBean;
import org.zmsoft.framework.constants.IFrameworkConstants;
import org.zmsoft.framework.utils.DateHelper;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 业务请求Token数据算法
 * 
 * @author ZmSoft
 * @version 2.1.1 2019/4/8
 * @since 2.1.1 2019/4/8
 */
public class TokenServiceImpl extends TokenBean implements IFrameworkConstants {

	/**
	 * 服务器认证授权码（登记授权）
	 */
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public String prepareSessionId(HttpServletRequest request) {
		return request.getSession().getId().toUpperCase().replaceAll("-", "");
	}

	/**
	 * 
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String prepareTokenBiz() throws Exception {
		// 默认组合顺序
		this.setToken(TokenBusinessHelper.getBizTokenData(
				this.getSessionId(),//32
				this.getUserId() + DOWN_LINE + this.getHdp(), //20+12
				this.getClientTimestamp() + DOWN_LINE + this.getUuid()//17+15
				));
		return this.getToken();
	}

	public void buildTokenBiz(String bizToken) throws Exception {
		String[] ds = buildTokenBizData(bizToken);
		this.setSessionId(ds[0]);
		this.setUserId(ds[1]);
		this.setUuid(ds[2]);
		{
			ds = this.getUserId().split(DOWN_LINE);
			this.setUserId(ds[0]);
			this.setHdp(ds[1]);
		}
		{
			ds = this.getUuid().split(DOWN_LINE);
			this.setClientTimestamp(ds[0]);
			this.setUuid(ds[1]);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void prepareClientTimestamp() {
		this.setClientTimestamp(DateHelper.currentTimeMillis0());
	}
	
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

	// 拆分数目
	public final static int DATA_NUM = 3;

	public final static String TOKEN = "TOKEN:";
}