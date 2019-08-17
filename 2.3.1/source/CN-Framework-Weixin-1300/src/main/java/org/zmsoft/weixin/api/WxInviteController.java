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
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.token.TokenBusinessSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.weixin.bean.WxCallBackParamBean;
import org.zmsoft.framework.weixin.service.ISWxInviteProcessSupport;
import org.zmsoft.weixin.MyWeixinCommonSupport;

import com.alibaba.fastjson.JSONObject;

/**
 * 菜单入口（用户授权）邀请
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
@Controller
public class WxInviteController extends MyWeixinCommonSupport {

	/**
	 * 邀请地址
	 * 
	 * @param [playerId]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WXInvite/{playerId}", method = RequestMethod.GET)
	public ModelAndView doWXInvite(@PathVariable String playerId) throws Exception {
		logger.debug("doWXHomeIndex==>>>" + playerId);
		ModelAndView model = new ModelAndView("/weixin/WXIndex");
		// 回调地址
		model.addObject("redirect", URLEncoder.encode(getSystemConfigService().getApiServerUrl() + "/WXInvite/Back/" + playerId, SYSTEM_CHARSET));
		// 目标地址
		model.addObject("login_href", "/WXIndex/Login/1");
		model.addObject("appid", getWxConfigService().getWxAppID());
		model.addObject("state", 2);
		model.addObject("playerId", playerId);
		logger.debug("doWXHomeIndex==>>>" + model);
		return model;
	}

	/**
	 * 活动（商品文章）分享邀请地址
	 * 
	 * @param [playerId]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WXInvite/{playerId}/{classify}/{itemId}/{orderId}", method = RequestMethod.GET)
	public ModelAndView doWXInviteClassify(@PathVariable String playerId, @PathVariable String classify, @PathVariable String itemId, @PathVariable String orderId) throws Exception {
		logger.debug("doWXHomeIndex==>>>" + playerId);
		ModelAndView model = new ModelAndView("/weixin/WXIndex");
		// 回调地址
		model.addObject("redirect", URLEncoder.encode(getSystemConfigService().getApiServerUrl() + "/WXInvite/Back/" + playerId + URL_SLASH + itemId + URL_SLASH + orderId, SYSTEM_CHARSET));
		// 目标地址
		model.addObject("login_href", "/WXIndex/Login/1");
		model.addObject("appid", getWxConfigService().getWxAppID());
		model.addObject("state", 2);
		model.addObject("playerId", playerId);
		logger.debug("doWXHomeIndex==>>>" + model);
		return model;
	}

	/**
	 * 微信授权回调地址-微信菜单地址
	 * 
	 * @param [request,
	 *            response, playerId, wxUserBean]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WXInvite/Back/{playerId}", method = RequestMethod.GET)
	public ModelAndView doWXBack(HttpServletRequest request, HttpServletResponse response, 
									@PathVariable String playerId, 
									WxCallBackParamBean wxCallBackParamBean) throws Exception {
		ModelAndView model = new ModelAndView("/weixin/WXerror");
		logger.debug("doWXBack==>>>" + playerId);
		logger.debug("====doWXBack=====doWXBack====code>>>>>>>>>>>" + wxCallBackParamBean.getCode());
		if (EmptyHelper.isEmpty(wxCallBackParamBean.getCode()))
			model.addObject("msg", "授权失败！");
		else {
			// 第二步：通过code换取网页授权access_token
			wxCallBackParamBean.setAppid(getWxConfigService().getWxAppID());
			wxCallBackParamBean.setSecret(getWxConfigService().getWxAppSecret());
			JSONObject wxmsg = myWeixinCommonSupport.loadAccessToken(wxCallBackParamBean);
			String openId = wxmsg.getString("openid");
			if (EmptyHelper.isNotEmpty(openId)) {
				logger.info("====doWXBack====doWXBack=====openId>>>>>>>>>>>" + openId);
				wxCallBackParamBean.setOpenid(openId);
				wxCallBackParamBean.setAccess_token(wxmsg.getString("access_token"));

				wxCallBackParamBean.setChannel(playerId);// 渠道
				wxCallBackParamBean.setClassify(ZERO);

				// 开始自动登录
				return doWXLogin(request, response, playerId, wxCallBackParamBean);
			} else {
				model.addObject("msg", "授权失败！");
			}
		}
		return model;
	}
	/**
	 * 微信授权回调地址-微信菜单地址
	 * 
	 * @param [request,
	 *            response, playerId, wxUserBean]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WXInvite/Back/{playerId}/{classify}/{itemId}/{orderId}", method = RequestMethod.GET)
	public ModelAndView doWXBack(HttpServletRequest request, HttpServletResponse response, 
									@PathVariable String playerId, @PathVariable String classify, @PathVariable String itemId, @PathVariable String orderId, 
									WxCallBackParamBean wxCallBackParamBean) throws Exception {		
		ModelAndView model = new ModelAndView("/weixin/WXerror");
		logger.debug("doWXBack==>>>" + playerId);
		logger.debug("====doWXBack=====doWXBack====code>>>>>>>>>>>" + wxCallBackParamBean.getCode());
		if (EmptyHelper.isEmpty(wxCallBackParamBean.getCode()))
			model.addObject("msg", "授权失败！");
		else {
			// 第二步：通过code换取网页授权access_token
			wxCallBackParamBean.setAppid(getWxConfigService().getWxAppID());
			wxCallBackParamBean.setSecret(getWxConfigService().getWxAppSecret());
			JSONObject wxmsg = myWeixinCommonSupport.loadAccessToken(wxCallBackParamBean);
			String openId = wxmsg.getString("openid");
			if (EmptyHelper.isNotEmpty(openId)) {
				logger.info("====doWXBack====doWXBack=====openId>>>>>>>>>>>" + openId);
				wxCallBackParamBean.setOpenid(openId);
				wxCallBackParamBean.setAccess_token(wxmsg.getString("access_token"));

				////////////////////////////////////////////////////
				wxCallBackParamBean.setChannel(SYSTEM);// 渠道ID
				wxCallBackParamBean.setPlayerId(playerId);// 渠道推广员ID
				wxCallBackParamBean.setClassify(classify);
				wxCallBackParamBean.setItemId(itemId);
				wxCallBackParamBean.setOrderId(orderId);
				////////////////////////////////////////////////////
				
				// 开始自动登录
				return doWXLogin(request, response, playerId, wxCallBackParamBean);
			} else {
				model.addObject("msg", "授权失败！");
			}
		}
		return model;
	}

	private ModelAndView doWXLogin(HttpServletRequest request, HttpServletResponse response, String playerId, WxCallBackParamBean wxCallBackParamBean) throws Exception {
		logger.debug("doWXMenuMain==>>>" + playerId);
		logger.debug("doWXMenuMain==>>>" + wxCallBackParamBean);
		// 开始自动登录
		RESTResultBean<TokenBusinessSupport> result = getPlayerLoginService().doWeixinLogin(request, response, wxCallBackParamBean);

		ModelAndView model = new ModelAndView("/weixin/WXHome");

		model.addObject("token", result.getToken());
		
		model.addObject("user", result.getData());
		model.addObject("data", wxCallBackParamBean);
		
		model.addObject("menuId", wxCallBackParamBean.getClassify());

		model.addObject("apiServerUrl", getSystemConfigService().getApiServerUrl());
		model.addObject("pcServerUrl", getSystemConfigService().getPcServerUrl());
		model.addObject("wxServerUrl", getSystemConfigService().getWxServerUrl());

		//激活拦截器
		ISWxInviteProcessSupport myWxInviteProcessSupport = MyBeanFactoryHelper.getBean(ISWxInviteProcessSupport.class.getSimpleName());
		if(EmptyHelper.isNotEmpty(myWxInviteProcessSupport)){
			myWxInviteProcessSupport.doProcess(wxCallBackParamBean);
		}
		
		return model;
	}

}
