package org.zmsoft.framework.support;

import javax.annotation.Resource;

import org.zmsoft.config.biz.H5ConfigSupport;
import org.zmsoft.framework.constants.IBussinessConstants;
import org.zmsoft.framework.constants.IWeixinConstants;
import org.zmsoft.framework.login.ISPlayerLoginService;
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
public class MyWeixinCommonSupport extends MyTokenCommonSupport implements IBussinessConstants, IWeixinConstants {

	// 微信授权
	@Resource
	protected WxJSAccessKeyCacheService myWeixinCommonSupport;

	// 公众号配置
	@Resource
	protected H5ConfigSupport h5ConfigSupport;

	// 用户登录
	private ISPlayerLoginService playerLoginService;

	public ISPlayerLoginService getPlayerLoginService() {
		if (EmptyHelper.isEmpty(playerLoginService))
			playerLoginService = MyBeanFactoryHelper.getBean(ISPlayerLoginService.class.getSimpleName());
		return playerLoginService;
	}

	// @Resource
	// WxMaterialCacheBiz MaterialCacheBiz_;// 微信文章地址缓存

}
