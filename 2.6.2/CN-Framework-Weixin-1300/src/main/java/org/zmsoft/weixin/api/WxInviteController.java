package org.zmsoft.weixin.api;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.framework.beans.UserBean;
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
	 * @param [wxShareToken]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WXInvite/{wxShareToken}", method = RequestMethod.GET)
	public ModelAndView doWXInvite(@PathVariable String wxShareToken) throws Exception {
		logger.debug("doWXHomeIndex==>>>" + wxShareToken);
		ModelAndView model = new ModelAndView("/weixin/WXIndex");
		// 回调地址
		model.addObject("redirect", URLEncoder.encode(getSystemConfigService().getWxServerUrl() + "/WXInvite/Back/" + wxShareToken, SYSTEM_CHARSET));
		// 目标地址
		model.addObject("login_href", "/WXIndex/Login/1");
		model.addObject("appid", getWxPayConfigService().getWxAppID());
		model.addObject("state", 2);
		model.addObject("wxShareToken", wxShareToken);
		logger.debug("doWXHomeIndex==>>>" + model);
		return model;
	}

	/**
	 * 微信授权回调地址-微信菜单地址
	 * 
	 * @param [request,
	 *            response, wxShareToken, wxUserBean]
	 * @return ModelAndView
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/WXInvite/Back/{wxShareToken}", method = RequestMethod.GET)
	public ModelAndView doWXBack(HttpServletRequest request, HttpServletResponse response, 
									@PathVariable String wxShareToken, 
									WxCallBackParamBean wxCallBackParamBean) throws Exception {
		ModelAndView model = new ModelAndView("/weixin/WXerror");
		logger.debug("doWXBack==wxShareToken>>>" + wxShareToken);
		logger.debug("====doWXBack=====doWXBack====code>>>>>>>>>>>" + wxCallBackParamBean.getCode());
		if (EmptyHelper.isEmpty(wxCallBackParamBean.getCode()))
			model.addObject("msg", "授权失败！");
		else {
			// 第二步：通过code换取网页授权access_token
			wxCallBackParamBean.setAppid(getWxPayConfigService().getWxAppID());
			wxCallBackParamBean.setSecret(getWxPayConfigService().getWxAppSecret());
			JSONObject wxmsg = myWeixinCommonSupport.loadAccessToken(wxCallBackParamBean);
			String openId = wxmsg.getString("openid");
			if (EmptyHelper.isNotEmpty(openId)) {
				logger.info("====doWXBack====doWXBack=====openId>>>>>>>>>>>" + openId);
				wxCallBackParamBean.setOpenid(openId);
				wxCallBackParamBean.setAccess_token(wxmsg.getString("access_token"));

				wxCallBackParamBean.setWxShareToken(wxShareToken);//核心参数

				// 开始自动登录
				return doWXLogin(request, response, wxCallBackParamBean);
			} else {
				model.addObject("msg", "授权失败！");
			}
		}
		return model;
	}

	private ModelAndView doWXLogin(HttpServletRequest request, HttpServletResponse response, WxCallBackParamBean wxCallBackParamBean) throws Exception {
		logger.debug("doWXLogin==>>>" + wxCallBackParamBean);
		// 开始自动登录
		ModelAndView model = new ModelAndView("/weixin/WXInvite");
		if(EmptyHelper.isNotEmpty(getPlayerLoginService())){
			RESTResultBean<UserBean> result = getPlayerLoginService().doWeixinLogin(request, response, wxCallBackParamBean);
			model.addObject("token", result.getToken());
			model.addObject("user", result.getData());
		}else{
			//游客Token
			TokenBusinessSupport _TokenBusinessSupport_ = TokenBusinessSupport.defaultToken(request);
			_TokenBusinessSupport_.setMyCacheService(myCacheService);
			_TokenBusinessSupport_.saveToken();
			model.addObject("token", _TokenBusinessSupport_.getToken());
		}

		model.addObject("data", wxCallBackParamBean);
		model.addObject("apiServerUrl", getSystemConfigService().getApiServerUrl());
		model.addObject("pcServerUrl", getSystemConfigService().getPcServerUrl());
		model.addObject("wxServerUrl", getSystemConfigService().getWxServerUrl());

		//激活拦截器
		ISWxInviteProcessSupport myWxInviteProcessSupport = MyBeanFactoryHelper.getBean(ISWxInviteProcessSupport.class);
		if(EmptyHelper.isNotEmpty(myWxInviteProcessSupport)){
			myWxInviteProcessSupport.doProcess(wxCallBackParamBean);
		}
		
		return model;
	}

}
