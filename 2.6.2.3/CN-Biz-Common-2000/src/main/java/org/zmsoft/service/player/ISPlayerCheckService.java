package org.zmsoft.service.player;

import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.weixin.bean.WxCallBackParamBean;

/**
 * 检查用户是否存在
 *
 */
public interface ISPlayerCheckService extends ICFrameworkConstants {

	//基于微信openid
	public UserBean checkUser(WxCallBackParamBean wxUserBean) throws Exception;

	//基于用户信息（手机，账户，ID）
	public UserBean checkUser(LoginerBean loginer) throws Exception;
}
