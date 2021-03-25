package org.zmsoft.common.dba;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.common.dba.bean.DBUpdateInfoBean;
import org.zmsoft.common.dba.service.DBBuildService;
import org.zmsoft.common.dba.service.DBTransferService;
import org.zmsoft.common.dba.service.DBUpdateService;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyControllerSupport;

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
public class PageDBManagerServiceController extends MyControllerSupport { // MyTokenCommonSupport
	
	@Value("${spring.redis.password}")
	private String password;

	@RequestMapping(value = "/buildok", method = RequestMethod.POST)
	public ModelAndView doMain(DBUpdateInfoBean dbu, String type, String pass, String drop) throws Exception {
		// 设定展示界面
		ModelAndView modelAndView = getModelAndView("/common/dba/build");
		modelAndView.addObject("tserver", dbu.gettServer());
		// 输出参数日志
		logger.debug("param===password==>>>>" + pass);
		logger.debug("param=====>>>>" + dbu.gettServer());

		// 数据库权限
		if (password.equals(pass)) {
			modelAndView.setViewName("/common/dba/buildok");
			DBBuildService service = MyBeanFactoryHelper.getBean(DBBuildService.class);
			service.setServer(dbu.gettServer());
			service.doProcess(type, drop);
		}else{
			modelAndView.addObject("msg", "密码权限错误");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/updateok", method = RequestMethod.POST)
	public ModelAndView doUpdate(DBUpdateInfoBean dbu, String pass) throws Exception {
		// 设定展示界面
		ModelAndView modelAndView = getModelAndView("/common/dba/update");
		modelAndView.addObject("tserver", dbu.gettServer());
		// 输出参数日志
		logger.debug("param===password==>>>>" + pass);
		logger.debug("param=====>>>>" + dbu.gettServer());
		
		// 数据库权限
		if (password.equals(pass)) {
			modelAndView.setViewName("/common/dba/updateok");
			DBUpdateService service = MyBeanFactoryHelper.getBean(DBUpdateService.class);
			service.setServer(dbu.gettServer());
			service.doProcess();
		}else{
			modelAndView.addObject("msg", "密码权限错误");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/transferok", method = RequestMethod.POST)
	public ModelAndView doTransfer(DBUpdateInfoBean dbu, String pass) throws Exception {
		// 设定展示界面
		ModelAndView modelAndView = getModelAndView("/common/dba/transfer");
		// 输出参数日志
		logger.debug("param===password==>>>>" + dbu.getPass());
		logger.debug("param=====>>>>" + dbu.getsServer());
		logger.debug("param=====>>>>" + dbu.gettServer());
		modelAndView.addObject("sServer", dbu.getsServer());
		modelAndView.addObject("tServer", dbu.gettServer());

		// 数据库权限
		if (password.equals(pass)) {
			modelAndView.setViewName("/common/dba/transferok");
			DBTransferService service = MyBeanFactoryHelper.getBean(DBTransferService.class);
			service.setUpdateInfo(dbu);
			service.doProcess();
		}else{
			modelAndView.addObject("msg", "密码权限错误");
		}
		return modelAndView;
	}
	
}
