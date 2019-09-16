package org.zmsoft.jfp.common.job.task;

import javax.annotation.Resource;

import org.zmsoft.jfp.framework.job.QuartzTaskManagement;
import org.zmsoft.jfp.framework.job.bean.ScheduleJobBean;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.persistent.job.C804010JobInfo.C804010JobInfoDBO;
import org.zmsoft.jfp.persistent.job.C804010JobInfo.C804010JobInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 任务配置
 * 
 * @version 0.0.1 2018/05/08
 * @since 0.0.1 2018/05/08
 */
@Controller
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobTaskController extends MyControllerSupport {
	@Resource
	C804010JobInfoService C804010JobInfoService_;

	/**
	 * 一览
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/80401010", method = RequestMethod.POST)
	public ModelAndView list80401010(C804010JobInfoDBO param,String message) throws Exception {
		ModelAndView model = getModelAndView("/job/task/job-task-list");
		param.setDelFlag(ZERO);
		model.addObject("list", C804010JobInfoService_.doSelectData(param));
		model.addObject("param", param);
		model.addObject("message", message);
		return model;
	}

	/**
	 * 新建
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/80401020", method = RequestMethod.POST)
	public ModelAndView add80401020(C804010JobInfoDBO param) throws Exception {
		ModelAndView model = getModelAndView("/job/task/job-task-modify");
		model.addObject("mode", ONE);// 新加
		model.addObject("data", param);
		return model;
	}

	/**
	 * 编辑
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/80401030", method = RequestMethod.POST)
	public ModelAndView edit80401030(C804010JobInfoDBO param) throws Exception {
		ModelAndView model = getModelAndView("/job/task/job-task-modify");
		model.addObject("mode", TWO);// 新加
		model.addObject("data", C804010JobInfoService_.doRead(param));
		return model;
	}

	/**
	 * 删除
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/80401040", method = RequestMethod.POST)
	public ModelAndView delete80401040(C804010JobInfoDBO param) throws Exception {
		C804010JobInfoService_.doDelete(param);
		return list80401010(new C804010JobInfoDBO(),MESSAGE_DB_DELETE);
	}

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/80401050", method = RequestMethod.POST)
	public ModelAndView save80401050(C804010JobInfoDBO param, String mode) throws Exception {
		if (ONE.equals(mode)) {
			C804010JobInfoService_.doInsert(param);
		} else {
			C804010JobInfoService_.doUpdate(param);
		}
		return list80401010(new C804010JobInfoDBO(), MESSAGE_DB_SAVE);
	}

	/**
	 * 任务操作
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/80401060", method = RequestMethod.POST)
	public ModelAndView save80401060(C804010JobInfoDBO param) throws Exception {
		// 任务操作
		QuartzTaskManagement _QuartzTaskManagement_ = BeanFactoryHelper.getBean("QuartzTaskManagement");
		ScheduleJobBean scheduleJobBean = new ScheduleJobBean();
		BeanUtils.copyProperties(C804010JobInfoService_.doRead(param), scheduleJobBean);
		
		try{
			if (ONE.equals(param.getJobStatus())) {// 运行
				_QuartzTaskManagement_.startJob(scheduleJobBean);
			} else {// 停止
				_QuartzTaskManagement_.stopJob(scheduleJobBean);
			}
			C804010JobInfoService_.doUpdate(param);
		}catch(Exception e){
			return list80401010(new C804010JobInfoDBO(), "操作失败，请检查");
		}
		return list80401010(new C804010JobInfoDBO(), "操作成功");
	}
}
