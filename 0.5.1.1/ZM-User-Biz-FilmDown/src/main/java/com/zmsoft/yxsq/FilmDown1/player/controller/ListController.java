package com.zmsoft.yxsq.FilmDown1.player.controller;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

import com.zmsoft.yxsq.FilmDown1.player.IFilmDownConstants;
import com.zmsoft.yxsq.FilmDown1.player.cache.ClassifyTagCacheBiz;
import com.zmsoft.yxsq.FilmDown1.player.cache.MaterialListCacheBiz;

/**
 * 列表页面接口
 * 
 * @version 1.0.0 2014/11/19
 * @since 1.0.0 2014/11/19
 */
@Controller
public class ListController extends MyControllerSupport {

	@Resource
	ClassifyTagCacheBiz ClassifyTagCacheBiz_;
	@Resource
	MaterialListCacheBiz MaterialListCacheBiz_;

	/**
	 * 分类列表
	 */
	@RequestMapping(value = "/10101010", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean do10101010(String jobId) throws Exception {
		RESTResultBean result = new RESTResultBean();
		result.setData(ClassifyTagCacheBiz_.getClassifyTypeList(IFilmDownConstants.CLASSIFY_SYS_TYPE, true));
		return result;
	}

	/**
	 * 素材列表
	 */
	@RequestMapping(value = "/10101020", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean do10101020(String jobId, String classifyId, String tagId, int pageCurrent, int pageLimit) throws Exception {
		RESTResultBean result = new RESTResultBean();

		if (EmptyHelper.isNotEmpty(classifyId)) {// 分类下面的数据列表
			result.setData(MaterialListCacheBiz_.getClassifyMaterialList(classifyId, true, pageCurrent, pageLimit));
		} else if (EmptyHelper.isNotEmpty(tagId)) {// 标签下面的数据列表
			result.setData(MaterialListCacheBiz_.getTagMaterialList(tagId, true, pageCurrent, pageLimit));
		} else {// 最新推荐
			result.setData(MaterialListCacheBiz_.getNewList(EMPTY, EMPTY, true, pageCurrent, pageLimit));
		}
		return result;
	}

	/**
	 * 热门列表
	 */
	@RequestMapping(value = "/10101030", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean do10101030(String jobId, String classifyId, String tagId, int pageCurrent, int pageLimit) throws Exception {
		RESTResultBean result = new RESTResultBean();

		result.setData(MaterialListCacheBiz_.getHotList(classifyId, tagId, true, pageCurrent, pageLimit));
		return result;
	}

	/**
	 * 排行列表
	 */
	@RequestMapping(value = "/10101040", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean do10101040(String jobId, String classifyId, String tagId, int pageCurrent, int pageLimit) throws Exception {
		RESTResultBean result = new RESTResultBean();

		result.setData(MaterialListCacheBiz_.getTopList(classifyId, tagId, true, pageCurrent, pageLimit));
		return result;
	}

	/**
	 * 检索列表
	 */
	@RequestMapping(value = "/10101050", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean do10101050(String jobId, String pv1, int pageCurrent, int pageLimit) throws Exception {
		RESTResultBean result = new RESTResultBean();

		result.setData(MaterialListCacheBiz_.getSearchList(pv1, true, pageCurrent, pageLimit));
		return result;
	}

	/**
	 * 随机列表
	 */
	@RequestMapping(value = "/10101060", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean do10101060(String jobId) throws Exception {
		RESTResultBean result = new RESTResultBean();
		
		Random rd = new Random();
		int pageCurrent = rd.nextInt(50);
		int pageLimit = 16;
		result.setData(MaterialListCacheBiz_.getRandomList(true, pageCurrent, pageLimit));
		return result;
	}
}
