package org.zmsoft.framework.weixin.service;

import org.zmsoft.framework.weixin.bean.WxCallBackParamBean;

/**
 * 用户分享邀请拦截器
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * 
 */
public interface ISWxInviteProcessSupport {

	/**
	 * 新用户访问回调
	 * 
	 * @param playerId
	 * @param wxCallBackParamBean
	 * @return
	 * @throws Exception
	 */
	boolean doProcess(WxCallBackParamBean wxCallBackParamBean) throws Exception;
}
