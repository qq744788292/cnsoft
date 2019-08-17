package org.zmsoft.weixin.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.config.biz.H5ConfigSupport;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.weixin.IWxShareInfo;
import org.zmsoft.framework.weixin.utils.WxJSAccessKeyCacheService;

import com.alibaba.fastjson.JSONObject;

/**
 * 获取微信分享信息
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/app/3.0/WXShare", method = { RequestMethod.POST})
public class WxShareController extends MyTokenCommonSupport{

	@Resource
	private WxJSAccessKeyCacheService WxShareAccessKeyService_;
	@Resource
	private H5ConfigSupport H5ConfigSupport_;
	/**
	 * 获得一个分享ID
	 * 
	 * @param [request,
	 *            response, token, id, url, spName]
	 * @return RESTResultBean
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	@RequestMapping(value = "/loadShareInfo", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean<JSONObject> doLoadShareTokenGET(HttpServletRequest request, HttpServletResponse response, String token, String id, String url, String spName , String type) throws Exception {
		return doLoadShareTokenPOST(request, response, token, id, url, spName,type);
	}

	// 获得文章分享数据信息
	@Autowired(required = false)
	IWxShareInfo _WxShareInfo_;

	/**
	 * 分享素材
	 * 
	 * @param [request,
	 *            response, token, id, url, spName]
	 * @return RESTResultBean
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	
	public RESTResultBean<JSONObject> doLoadShareTokenPOST(HttpServletRequest request, HttpServletResponse response, String token, String id, String url, String spName,String type) throws Exception {
		RESTResultBean<JSONObject> result = new RESTResultBean<JSONObject>();

		// 用户ID
		// String userId = PKHelper.creatPUKey();
		// if (super.doCheckToken(token))
		// userId = SessionHelper.currentUser().getUserId();

		// StringBuffer reqUrl = request.getRequestURL();
		// if (EmptyHelper.isNotEmpty(request.getQueryString()))
		// url.append("?").append(request.getQueryString());// 访问路径

		// 获得微信分享签名参数
		JSONObject signJSONObject = WxShareAccessKeyService_.loadSignature(request, H5ConfigSupport_.getWxAppID(), H5ConfigSupport_.getWxAppSecret(), url);

		// 获得分享Token值
		signJSONObject.put("appID", H5ConfigSupport_.getWxAppID());

		// 获得文章分享数据信息
		if (EmptyHelper.isEmpty(_WxShareInfo_)) {
			result.setCode(9);
			result.setMsg("当前系统没有定义分享IWxShareInfo实现！");
			return result;
		}
		JSONObject shareJSONObject = _WxShareInfo_.loadShareInfo(type,id);
		if (EmptyHelper.isEmpty(shareJSONObject)) {
			result.setCode(1);
			result.setMsg("素材信息不存在！");
			return result;
		}
		signJSONObject.putAll(shareJSONObject);

		// 设定返回
		result.setData(signJSONObject);
		return result;
	}
}
