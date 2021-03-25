package org.zmsoft.common.dba;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ICDBConstants;
import org.zmsoft.framework.support.MyControllerSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 数据库迁移工具
 * 
 * @author ZmSoft·
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
@Controller
@RequestMapping(value = "/dba/1.0/tool", method = { RequestMethod.GET, RequestMethod.POST })
public class PageDBManagerHomeController extends MyControllerSupport implements ICDBConstants { // MyTokenCommonSupport

	/**
	 * 数据库重构
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doMain() throws Exception {
		// 输出参数日志
		// 设定展示界面
		ModelAndView modelAndView = getModelAndView("/common/dba/init");
		return modelAndView;
	}
	
	/**
	 * 数据库重构
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/build", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doBuild() throws Exception {
		// 输出参数日志
		// 设定展示界面
		ModelAndView modelAndView = getModelAndView("/common/dba/build");
		return modelAndView;
	}

	/**
	 * 数据库升级
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView doUpdate() throws Exception {
		// 设定展示界面
		ModelAndView modelAndView = getModelAndView("/common/dba/update");
		return modelAndView;
	}

	/**
	 * 数据库迁移
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ModelAndView doTransfer() throws Exception {
		// 设定展示界面
		ModelAndView modelAndView = getModelAndView("/common/dba/transfer");
		return modelAndView;
	}

	/**
	 * 数据消息
	 * 
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/msg", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public RESTResultBean<String> doMsg() throws ServletException, IOException {
		RESTResultBean<String> result = new RESTResultBean<String>();
		result.setCode(0);
		String msg = (String) myCacheService.pollFirstObjectInList(MSG_MQ_LIST);
		if(EmptyHelper.isEmpty(msg)){
			result.setCode(1);
		}else{
			result.setMsg(msg);
		}
		return result;
	}
}
