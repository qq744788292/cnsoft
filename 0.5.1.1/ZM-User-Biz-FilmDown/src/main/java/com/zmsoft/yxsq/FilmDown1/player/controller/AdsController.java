package com.zmsoft.yxsq.FilmDown1.player.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.support.MyControllerSupport;

/**
 * 广告接口
 * 
 * @author 杨康
 * @version 1.0.0 2014/11/19
 * @since 1.0.0 2014/11/19
 */
@Controller
public class AdsController extends MyControllerSupport {

	/**
	 * 广告列表
	 */
	@RequestMapping(value = "/10103010", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean do10202010(String jobId, String adsAreaId) throws Exception {
		
		return null;
	}

}
