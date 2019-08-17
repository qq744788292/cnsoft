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
 * 菜单入口（用户授权） 菜单分类
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
@Controller
public class WxIndexController extends MyWeixinCommonSupport {

	/**
	 * 打开菜单
	 * 
	 * @param [menuId]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WXIndex/{menuId}", method = RequestMethod.GET)
	public ModelAndView doWXHomeIndex(@PathVariable String menuId) throws Exception {
		logger.debug("doWXHomeIndex==>>>" + menuId);
		ModelAndView model = new ModelAndView("/weixin/WXIndex");
		// 回调地址
		model.addObject("redirect", URLEncoder.encode(h5ConfigSupport.getApiServerUrl() + "/WXIndex/Back/" + menuId, SYSTEM_CHARSET));
		// 目标地址
		model.addObject("login_href", "/WXIndex/Login/" + menuId);
		model.addObject("appid", h5ConfigSupport.getWxAppID());
		model.addObject("state", 2);
		model.addObject("menuId", menuId);
		logger.debug("doWXHomeIndex==>>>" + model);
		return model;
	}

	/**
	 * 微信授权回调地址-微信菜单地址
	 * 
	 * @param [request,
	 *            response, menuId, wxUserBean]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WXIndex/Back/{menuId}", method = RequestMethod.GET)
	public ModelAndView doWXBack(HttpServletRequest request, HttpServletResponse response, @PathVariable String menuId, WxCallBackParamBean wxCallBackParamBean) throws Exception {
		ModelAndView model = new ModelAndView("/weixin/WXerror");
		logger.debug("doWXBack==>>>" + menuId);
		logger.debug("====doWXBack=====doWXBack====code>>>>>>>>>>>" + wxCallBackParamBean.getCode());
		if (EmptyHelper.isEmpty(wxCallBackParamBean.getCode()))
			model.addObject("msg", "授权失败！");
		else {
			// 第二步：通过code换取网页授权access_token
			wxCallBackParamBean.setAppid(h5ConfigSupport.getWxAppID());
			wxCallBackParamBean.setSecret(h5ConfigSupport.getWxAppSecret());
			JSONObject wxmsg = myWeixinCommonSupport.loadAccessToken(wxCallBackParamBean);
			String openId = wxmsg.getString("openid");
			if (EmptyHelper.isNotEmpty(openId)) {
				logger.info("====doWXBack====doWXBack=====openId>>>>>>>>>>>" + openId);
				wxCallBackParamBean.setOpenid(openId);
				wxCallBackParamBean.setAccess_token(wxmsg.getString("access_token"));

				// 开始自动登录
				return doWXLogin(request, response, menuId, wxCallBackParamBean);
			} else {
				model.addObject("msg", "授权失败！");
			}
		}
		return model;
	}

	/**
	 * 微信用户登陆
	 * 
	 * @param [request,
	 *            response, menuId, wxUserBean]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WXIndex/login/{menuId}", method = RequestMethod.GET)
	public ModelAndView doWXLogin(HttpServletRequest request, HttpServletResponse response, @PathVariable String menuId, WxCallBackParamBean wxCallBackParamBean) throws Exception {
		logger.debug("doWXMenuMain==>>>" + menuId);
		logger.debug("doWXMenuMain==>>>" + wxCallBackParamBean);
		// 开始自动登录
		RESTResultBean<TokenBusinessSupport> result = getPlayerLoginService().doWeixinLogin(request, response, wxCallBackParamBean);

		ModelAndView model = new ModelAndView("/weixin/WXHome");

		model.addObject("flag", result.getCode());// 0老用户1新用户
		model.addObject("token", result.getToken());
		
		model.addObject("user", result.getData());
		model.addObject("data", wxCallBackParamBean);
		
		model.addObject("menuId", menuId);

		model.addObject("apiServerUrl", h5ConfigSupport.getApiServerUrl());
		model.addObject("webServerUrl", h5ConfigSupport.getWebServerUrl());

		return model;
	}

}
