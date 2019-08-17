package com.zmsoft.service.player;

import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.constants.IFrameworkConstants;
import org.zmsoft.framework.weixin.bean.WxCallBackParamBean;

/**
 * 用户首页
 * 
 * @author spookfcy
 *
 */
public interface ISPlayerCheckService extends IFrameworkConstants {

	public UserBean checkUser(WxCallBackParamBean wxUserBean) throws Exception;

	// 查看基本用户数据
	public UserBean checkUser(LoginerBean loginer) throws Exception;
}
