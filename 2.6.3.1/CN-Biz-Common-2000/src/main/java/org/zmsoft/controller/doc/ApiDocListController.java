package org.zmsoft.controller.doc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.ApiDocBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.config.holder.MyApiDocConfigerHolder;
import org.zmsoft.framework.support.MyTokenCommonSupport;

/**
 * 默认用户登录管理
 * 
 * @author ZMSoft
 * @version 2.0.1 2018/7/8 新建
 * @since 2.0.1 2018/7/8
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/doc/1.0/base", method = { RequestMethod.GET })
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
	public RESTResultBean<List<ApiDocBean>> doDocList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 定义返回结果集合
		RESTResultBean<List<ApiDocBean>> result = new RESTResultBean<List<ApiDocBean>>();

		result.setData(MyApiDocConfigerHolder.loadApiDocs());

		return result;
	}
}
