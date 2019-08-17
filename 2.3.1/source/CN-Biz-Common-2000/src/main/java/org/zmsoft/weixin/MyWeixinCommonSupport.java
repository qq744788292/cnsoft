package org.zmsoft.weixin;

import javax.annotation.Resource;

import org.zmsoft.common.MySystemConfigService;
import org.zmsoft.config.wx.WxConfigService;
import org.zmsoft.framework.constants.ICBussinessConstants;
import org.zmsoft.framework.constants.ICWeixinConstants;
import org.zmsoft.framework.login.ISPlayerLoginService;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.weixin.utils.WxJSAccessKeyCacheService;

/**
 * 菜单入口（用户授权） 菜单分类
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 * @see <ApiUserInfoController>,<LCJUserLoginController>,<WxIndexController>
 */
public class MyWeixinCommonSupport extends MyTokenCommonSupport implements ICBussinessConstants, ICWeixinConstants {

	// 微信授权
	@Resource
	protected WxJSAccessKeyCacheService myWeixinCommonSupport;

	// 用户登录
	private ISPlayerLoginService playerLoginService;
	public ISPlayerLoginService getPlayerLoginService() {
		if (EmptyHelper.isEmpty(playerLoginService))
			playerLoginService = MyBeanFactoryHelper.getBean(ISPlayerLoginService.class.getSimpleName());
		return playerLoginService;
	}
	
	//微信配置
	private WxConfigService wxConfigService;
	public WxConfigService getWxConfigService() {
		if (EmptyHelper.isEmpty(wxConfigService))
			wxConfigService = MyBeanFactoryHelper.getBean(WxConfigService.class.getSimpleName());
		return wxConfigService;
	}
	
	//系统参数
	MySystemConfigService systemConfigService;
	public MySystemConfigService getSystemConfigService() {
		if (EmptyHelper.isEmpty(systemConfigService))
			systemConfigService = MyBeanFactoryHelper.getBean(MySystemConfigService.class.getSimpleName());
		return systemConfigService;
	}
	
	// @Resource
	// WxMaterialCacheBiz MaterialCacheBiz_;// 微信文章地址缓存

}
