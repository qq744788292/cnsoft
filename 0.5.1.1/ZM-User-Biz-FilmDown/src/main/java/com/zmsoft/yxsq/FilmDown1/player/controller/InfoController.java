package com.zmsoft.yxsq.FilmDown1.player.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

import com.zmsoft.yxsq.FilmDown1.player.cache.MaterialInfoCacheBiz;

/**
 * 详细页面接口
 * 
 * @version 1.0.0 2014/11/19
 * @since 1.0.0 2014/11/19
 */
@Controller
public class InfoController extends MyControllerSupport  {

	@Resource
	MaterialInfoCacheBiz MaterialInfoCacheBiz_;
	
	/**
	 * 单集素材基本信息
	 */
	@RequestMapping(value = "/1010201010", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean do1010201010(String jobId,String materialId) throws Exception {
		RESTResultBean result = new RESTResultBean();
		if(EmptyHelper.isEmpty(materialId)){
			result.setCode(ONE);
			result.setMsg("materialId不能为空...");
		}else{
			result.setData(MaterialInfoCacheBiz_.getBaseMaterialInfo(materialId, true));
		}
		return result;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 合集素材基本信息
	 */
	@RequestMapping(value = "/1010202010", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean do1010202010(String jobId,String materialId) throws Exception {
		RESTResultBean result = new RESTResultBean();
		if(EmptyHelper.isEmpty(materialId)){
			result.setCode(ONE);
			result.setMsg("materialId不能为空...");
		}else{
			result.setData(MaterialInfoCacheBiz_.getCollectionMaterialInfo(materialId, true));
		}
		return result;
	}
	/**
	 * 合集下面单集列表信息
	 */
	@RequestMapping(value = "/1010202020", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean do1010202020(String jobId,String materialId) throws Exception {
		RESTResultBean result = new RESTResultBean();
		if(EmptyHelper.isEmpty(materialId)){
			result.setCode(ONE);
			result.setMsg("materialId不能为空...");
		}else{
			result.setData(MaterialInfoCacheBiz_.getCollectionMaterialList(materialId, true));
		}
		return result;
	}
}
