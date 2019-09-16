package org.zmsoft.jfp.common.job.log;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.persistent.job.L804011JobLog.L804011JobLogDBO;
import org.zmsoft.jfp.persistent.job.L804011JobLog.L804011JobLogService;

/**
 * 日志查看
 * 
 * @version 0.0.1 2018/05/08
 * @since 0.0.1 2018/05/08
 */
@Controller
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobLogController extends MyControllerSupport {

	@Resource
	L804011JobLogService L804011JobLogService_;

	/**
	 * 统计
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/80402010", method = RequestMethod.POST)
	public ModelAndView total80402010(L804011JobLogDBO param) throws Exception {
		ModelAndView model = getModelAndView("/job/log/job-log-total");
		model.addObject("list", L804011JobLogService_.doSelectTotal(param));
		model.addObject("param", param);
		return model;
	}

	/**
	 * 详细列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/80402020", method = RequestMethod.POST)
	public ModelAndView list80402020(L804011JobLogDBO param,PageModel<L804011JobLogDBO> pageModel) throws Exception {
		ModelAndView model = getModelAndView("/job/log/job-log-list");
		pageModel.setFormParamBean(param);
		pageModel.setResultCountFlag(true);
		model.addObject("page", L804011JobLogService_.doSelectPage(pageModel));
		model.addObject("param", param);
		return model;
	}
}
