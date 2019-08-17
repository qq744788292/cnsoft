package org.zmsoft.weixin.api;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyWeixinCommonSupport;
import org.zmsoft.framework.token.TokenBusinessSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.weixin.bean.WxCallBackParamBean;

import com.alibaba.fastjson.JSONObject;

/**
 * 文章入口（用户授权）<br>
 * 素材查看（地址管理）
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
@Controller
public class WxShareViewController extends MyWeixinCommonSupport {

	/**
	 * 内容详细
	 * 
	 * @param [type]
	 *            //1楼盘详情分享//2活动分享//3文章分享
	 * @param [shareId]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WxShareView/{type}/{shareId}", method = RequestMethod.GET)
	public ModelAndView doWxShareView(@PathVariable String type, @PathVariable String shareId) throws Exception {
		logger.debug("微信分享访问.doWxShareView.shareId==>>>" + shareId);
		ModelAndView model = new ModelAndView("/weixin/WXIndex");
		// 回调地址
		model.addObject("redirect", URLEncoder.encode(h5ConfigSupport.getApiServerUrl() + "/WxShareView/Back/" + type + "/" + shareId, SYSTEM_CHARSET));
		// 目标地址
		model.addObject("href", "/WxShareView/Login/" + shareId);
		model.addObject("appid", h5ConfigSupport.getWxAppID());
		model.addObject("state", 2);
		model.addObject("menuId", shareId);
		logger.debug("微信分享访问.doWxShareView.model==>>>" + model);
		return model;
	}

	/**
	 * 微信回调地址-微信菜单地址
	 * 
	 * @param [request,
	 *            response, shareId, wxUserBean]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WxShareView/Back/{type}/{shareId}", method = RequestMethod.GET)
	public ModelAndView doWxShareViewBack(HttpServletRequest request, HttpServletResponse response, @PathVariable String type, @PathVariable String shareId, WxCallBackParamBean wxCallBackParamBean) throws Exception {
		ModelAndView model = new ModelAndView("/weixin/WXerror");
		logger.debug("微信分享访问回调.doWxShareViewBack.shareId==>>>" + shareId);
		logger.debug("微信分享访问回调.doWxShareViewBack.code==>>>" + wxCallBackParamBean.getCode());
		if (EmptyHelper.isEmpty(wxCallBackParamBean.getCode()))
			model.addObject("msg", "授权失败！");
		else {
			// 第二步：通过code换取网页授权access_token
			wxCallBackParamBean.setAppid(h5ConfigSupport.getWxAppID());
			wxCallBackParamBean.setSecret(h5ConfigSupport.getWxAppSecret());
			JSONObject wxmsg = myWeixinCommonSupport.loadAccessToken(wxCallBackParamBean);
			logger.info("微信分享访问回调.====doWxShareViewBack=========wxmsg>>>>>>>>>>>" + wxmsg);
			String openId = wxmsg.getString("openid");
			if (EmptyHelper.isNotEmpty(openId)) {
				logger.info("微信分享访问回调.====doWxShareViewBack=========openId>>>>>>>>>>>" + openId);
				wxCallBackParamBean.setOpenid(openId);
				wxCallBackParamBean.setAccess_token(wxmsg.getString("access_token"));

				// 开始自动登录
				return doWxShareViewLogin(request, response, type, shareId, wxCallBackParamBean);
			} else {
				model.addObject("msg", "授权失败！");
			}
		}
		return model;
	}

	// u.wx.zjmade.cn/WxShareView/Login/6?openid=123456789
	/**
	 * 微信分享回调地址-微信菜单地址
	 * 
	 * @param [request,
	 *            response, shareId, wxUserBean]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WxShareView/Login/{type}/{shareId}", method = RequestMethod.GET)
	public ModelAndView doWxShareViewLogin(HttpServletRequest request, HttpServletResponse response, @PathVariable String type, @PathVariable String shareId, WxCallBackParamBean wxCallBackParamBean) throws Exception {
		logger.debug("微信分享访问登录.doWxShareViewLogin.shareId==>>>" + shareId);
		logger.debug("微信分享访问登录.doWxShareViewLogin.wxUserBean==>>>" + wxCallBackParamBean);
		// 开始自动登录
		RESTResultBean<TokenBusinessSupport> result = getPlayerLoginService().doWeixinLogin(request, response, wxCallBackParamBean);

		ModelAndView model = new ModelAndView("/weixin/WXShareUrl");
		// model.addObject("token", result.getToken());
		// model.addObject("user", result.getData());
		// model.addObject("openId", wxUserBean.getOpenid());
		// model.addObject("material",
		// MaterialCacheBiz_.getMaterialUrlByPuk(shareId, true));

		model.addObject("flag", "0");// 0老用户1新用户
		model.addObject("token", result.getToken());
		model.addObject("user", result.getData());
		model.addObject("openId", wxCallBackParamBean.getOpenid());
		model.addObject("menuId", shareId);
		model.addObject("type", type);

		logger.debug("微信分享访问页面.doWxShareViewLogin.model==>>>" + model);

		return model;
	}
}
