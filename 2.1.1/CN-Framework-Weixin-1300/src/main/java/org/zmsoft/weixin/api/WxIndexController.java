package org.zmsoft.weixin.api;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.weixin.bean.WxUserBean;
import org.zmsoft.framework.weixin.utils.WxOauth2TokenSupport;
import org.zmsoft.weixin.service.user.UserLoginServiceImpl;

import com.alibaba.fastjson.JSONObject;

import org.zmsoft.config.biz.H5ConfigSupport;

/**
 * 菜单入口（用户授权） 菜单分类
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 * @see <ApiUserInfoController>,<LCJUserLoginController>,<WxIndexController>
 */
@Controller
public class WxIndexController extends MyTokenCommonSupport {

	private WxOauth2TokenSupport WxOauth2TokenSupport_ = new WxOauth2TokenSupport();
	
	@Resource
	private H5ConfigSupport H5ConfigSupport_;
	
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
		model.addObject("redirect", URLEncoder.encode(H5ConfigSupport_.getApiServerUrl() + "/WXIndex/Back/" + menuId, SYSTEM_CHARSET));
		// 目标地址
		model.addObject("login_href", "/WXIndex/Login/" + menuId);
		model.addObject("appid", H5ConfigSupport_.getWxAppID());
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
	public ModelAndView doWXBack(HttpServletRequest request, HttpServletResponse response, @PathVariable String menuId, WxUserBean wxUserBean) throws Exception {
		ModelAndView model = new ModelAndView("/weixin/WXerror");
		logger.debug("doWXBack==>>>" + menuId);
		logger.debug("====doWXBack=====doWXBack====code>>>>>>>>>>>" + wxUserBean.getCode());
		if (EmptyHelper.isEmpty(wxUserBean.getCode()))
			model.addObject("msg", "授权失败！");
		else {
			// 第二步：通过code换取网页授权access_token
			wxUserBean.setAppid(H5ConfigSupport_.getWxAppID());
			wxUserBean.setSecret(H5ConfigSupport_.getWxAppSecret());
			JSONObject wxmsg = WxOauth2TokenSupport_.loadAccessToken(wxUserBean);
			String openId = wxmsg.getString("openid");
			if (EmptyHelper.isNotEmpty(openId)) {
				logger.info("====doWXBack====doWXBack=====openId>>>>>>>>>>>" + openId);
				wxUserBean.setOpenid(openId);
				wxUserBean.setAccess_token(wxmsg.getString("access_token"));

				// 开始自动登录
				return doWXLogin(request, response, menuId, wxUserBean);
			} else {
				model.addObject("msg", "授权失败！");
			}
		}
		return model;
	}

	@Resource
	private UserLoginServiceImpl UserLoginServiceImpl_;
	
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
	public ModelAndView doWXLogin(HttpServletRequest request, HttpServletResponse response, @PathVariable String menuId, WxUserBean wxUserBean) throws Exception {
		logger.debug("doWXMenuMain==>>>" + menuId);
		logger.debug("doWXMenuMain==>>>" + wxUserBean);
		// 开始自动登录
		RESTResultBean<UserBean> result = UserLoginServiceImpl_.doWeixinLogin(request, wxUserBean);

		ModelAndView model = new ModelAndView("/weixin/WXHome");

		model.addObject("flag", "0");//0老用户1新用户
		model.addObject("token", result.getToken());
		model.addObject("user", result.getData());
		model.addObject("openId", wxUserBean.getOpenid());
		model.addObject("menuId", menuId);
		
		model.addObject("apiServerUrl", H5ConfigSupport_.getApiServerUrl());
		model.addObject("webServerUrl", H5ConfigSupport_.getWebServerUrl());

		return model;
	}
	
	///////////////////////////////////////游客登陆//////////////////////////////////////////////////////
	@RequestMapping(value = "/WXIndex/guest/login", method = RequestMethod.GET)
	public ModelAndView doWXMenuGuest(HttpServletRequest request, HttpServletResponse response, String menuId, WxUserBean wxUserBean) throws Exception {
		return doWXMenuGuestByMenu( request,  response,  menuId,  wxUserBean);
	}
	@RequestMapping(value = "/WXIndex/guest/login/{menuId}", method = RequestMethod.GET)
	public ModelAndView doWXMenuGuestByMenu(HttpServletRequest request, HttpServletResponse response, @PathVariable String menuId, WxUserBean wxUserBean) throws Exception {
		logger.debug("doWXMenuMain==>>>" + menuId);
		logger.debug("doWXMenuMain==>>>" + wxUserBean);
		// 开始自动登录
		RESTResultBean<UserBean> result = UserLoginServiceImpl_.doWeixinLogin(request, wxUserBean);

		ModelAndView model = new ModelAndView("/weixin/WXHome");

		model.addObject("token", result.getToken());//登陆表示
		model.addObject("flag", "0");//0老用户1新用户
		model.addObject("user", result.getData());//用户数据
		model.addObject("openId", wxUserBean.getOpenid());//openid
		model.addObject("menuId", menuId);//前台请求参数

		logger.debug("model==>>>" + model);
		return model;
	}
}
