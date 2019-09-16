 package org.zmsoft.jfp.manager.system.config;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.persistent.system.S901020Config.S901020ConfigDBO;
import org.zmsoft.jfp.persistent.system.S901020Config.S901020ConfigService;

@Controller
public class ParametersDefinedController extends MyControllerSupport implements IFrameworkConstants{
	
	@Resource
	S901020ConfigService s901020ConfigService;
	
	/**
	 * 系统配置列表
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90103010", method = RequestMethod.POST)
	public ModelAndView doSelectAll90301010(S901020ConfigDBO S901020ConfigDBO,PageModel<S901020ConfigDBO> pageModel,RESTResultBean<String> message)throws Exception{
		ModelAndView model = getModelAndView("/system/systemConfig/system-Config-url-list");
		S901020ConfigDBO.setDelFlag(ZERO);
		pageModel.setFormParamBean(S901020ConfigDBO);
		pageModel.setResultCountFlag(true);
		
		s901020ConfigService.doSelectPage(pageModel);//调用业务逻辑查询
		
		model.addObject("data", pageModel);//把查询出来的数据添加到返回对象里
		model.addObject("searchCondition",S901020ConfigDBO);
		model.addObject("message",message);
		return model;
	}
	
	/**
	 * 添加--跳转
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90101020",method = RequestMethod.POST)
	public ModelAndView editorSystemConfig90301020(S901020ConfigDBO s901020ConfigDBO,String model,PageModel<S901020ConfigDBO> pageModel)throws Exception{
		/*ModelAndView model = new ModelAndView();
		model.addObject("mode", ONE);
		model.addObject("data", s901020ConfigDBO);*/
		model=ONE;
		return saveSystemConfig90301050(s901020ConfigDBO, model, pageModel);
	}
	
	/**
	 * 修改跳转页面
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90101030", method = RequestMethod.POST)
	public ModelAndView editorSystemConfig90301030(S901020ConfigDBO S901020ConfigDBO){
		ModelAndView model=getModelAndView("/system/systemConfig/system-Config-url-modify");
		List<S901020ConfigDBO> result=s901020ConfigService.doSelectData(S901020ConfigDBO);
		S901020ConfigDBO = result.get(0);
		model.addObject("mode", TWO);
		model.addObject("result", S901020ConfigDBO);
		return model;
	}
	
	/**
	 * 删除
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90101040", method = RequestMethod.POST)
	public ModelAndView deleteSystemConfig90301040(S901020ConfigDBO S901020ConfigDBO)throws Exception{
		s901020ConfigService.doDelete(S901020ConfigDBO);
		RESTResultBean<String> message=new RESTResultBean<String>();
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_DELETE);
		return doSelectAll90301010(new S901020ConfigDBO(), new PageModel<S901020ConfigDBO>(), message);
	}
	
	/**
	 * 保存
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90101050", method = RequestMethod.POST)
	public ModelAndView saveSystemConfig90301050(S901020ConfigDBO s901020ConfigDBO,String mode,PageModel<S901020ConfigDBO> pageModel)throws Exception{
		RESTResultBean<String> message=new RESTResultBean<String>();
		if(ONE.equals(mode)){
			message.setCode(ONE);
			message.setMsg(MESSAGE_DB_INSERT);
			s901020ConfigService.doInsert(s901020ConfigDBO);
		}else{
			message.setCode(ONE);
			message.setMsg(MESSAGE_DB_UPDATE);
			s901020ConfigService.doUpdate(s901020ConfigDBO);
		}
		return doSelectAll90301010(new S901020ConfigDBO(),pageModel,message);
	}
	
	/**
	 * 分类下拉框
	 * @author 李小锋
	 */
//	@RequestMapping(value = "/90103011", method = RequestMethod.POST)
//	@ResponseBody
//	public List<S901020ConfigDBO> doList90103011(S901020ConfigDBO s901020ConfigDBO){
//		List<S901020ConfigDBO> result = s901020ConfigService.doSelectByDistinct();
//		RESTResultBean<String> message=new RESTResultBean<String>();
//		message.setCode(ZERO);
//		return result;
//	}
/*	*//**
	 * 添加--保存
	 * @author 李小锋
	 *//*
	@RequestMapping(value = "/90101060",method = RequestMethod.POST)
	public ModelAndView saveSystemConfig90301050(S901020ConfigDBO S901020ConfigDBO)throws Exception{
		S901020ConfigService s901020ConfigService = BeanFactoryHelper.getBean("S901020ConfigService");
		s901020ConfigService.doInsert(S901020ConfigDBO);
		RESTResultBean resultMessage = new RESTResultBean();
		resultMessage.setCode(ONE);
		resultMessage.setMsg("添加成功");
		return doSelectAll90301010(new S901020ConfigDBO(),new PageModel<S901020ConfigDBO>(),resultMessage);
	}*/
}
