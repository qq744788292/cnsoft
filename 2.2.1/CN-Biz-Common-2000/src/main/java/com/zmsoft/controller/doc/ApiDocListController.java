package com.zmsoft.controller.doc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.config.holder.MyApiDocConfigerHolder;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyBusinessLogicSupport;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSONObject;

/**
 * 默认用户登录管理
 * 
 * @author FCY
 * @version 2.0.1 2018/7/8 新建
 * @since 2.0.1 2018/7/8
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/doc", method = { RequestMethod.GET })
public class ApiDocListController extends MyTokenCommonSupport {
	
	/**
	 * 获得所有Api列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public RESTResultBean<List<JSONObject>> doDocList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 定义返回结果集合
		RESTResultBean<List<JSONObject>> result = new RESTResultBean<List<JSONObject>>();

		List<JSONObject> apiClass = new ArrayList<JSONObject>();
		MyBusinessLogicSupport api;
		JSONObject item;
		String[] val;
		for (String beanName : MyApiDocConfigerHolder.getApiClassNames()) {
			api = (MyBusinessLogicSupport) MyBeanFactoryHelper.getBean(beanName);
			//自定义的logic为空
			if(EmptyHelper.isNotEmpty(api)){
				item = new JSONObject();
				val = api.loadApiDocName().split(SEMICOLON);
				item.put("name", val[0]);
				item.put("url", val[1]);
				apiClass.add(item);
			}
		}

		result.setData(apiClass);

		return result;
	}
}
