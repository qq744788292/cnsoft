package org.zmsoft.jfp.manager.material.material;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.persistent.material.R702020Material.R702020MaterialDBO;
import org.zmsoft.jfp.persistent.material.R702020Material.R702020MaterialDBO;
import org.zmsoft.jfp.persistent.material.R702020Material.R702020MaterialService;

import javax.annotation.Resource;

/**
 * 素材管理
 * 
 * @version 0.0.1 2018/05/08
 * @since 0.0.1 2018/05/08
 * @author 周海俊
 */
@Controller
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MaterialController extends MyControllerSupport {

	@Resource
    R702020MaterialService R702020MaterialService_;

	/**
	 * 一览
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70202010", method = RequestMethod.POST)
	public ModelAndView list70202010(R702020MaterialDBO param, PageModel<R702020MaterialDBO> pageModel, RESTResultBean<String> message) throws Exception {
		ModelAndView model = super.getModelAndView("/material/material/material-list");
		param.setDelFlag(ZERO);
		pageModel.setResultCountFlag(true);
		pageModel.setFormParamBean(param);
		pageModel.setOrderby("create_time DESC");

		pageModel = R702020MaterialService_.doSelectPage(pageModel);

		model.addObject("page", pageModel);
		model.addObject("param", param);
		model.addObject("message", message);

		return model;
	}

	/**
	 * 新建-跳转页面
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70202020", method = RequestMethod.POST)
	public ModelAndView add70202020(R702020MaterialDBO param) throws Exception {
		ModelAndView model = getModelAndView("/material/material/material-modify");
		model.addObject("mode", ONE);// 新加
		model.addObject("data", param);
		return model;
	}

	/**
	 * 编辑-跳转页面
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70202030", method = RequestMethod.POST)
	public ModelAndView edit70202030(R702020MaterialDBO param) throws Exception {
		ModelAndView model = getModelAndView("/material/material/material-modify");
		model.addObject("mode", TWO);// 编辑
		model.addObject("data", R702020MaterialService_.doRead(param));
		return model;
	}

	/**
	 * 删除
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70202040", method = RequestMethod.POST)
	public ModelAndView delete70202040(R702020MaterialDBO param, PageModel<R702020MaterialDBO> pageModel,RESTResultBean<String> message) throws Exception {
		R702020MaterialService_.doDelete(param);
        message.setCode(ONE);
        message.setMsg(MESSAGE_DB_DELETE);
        return list70202010(new R702020MaterialDBO(), pageModel, message);
	}

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70202050", method = RequestMethod.POST)
	public ModelAndView save70202050(R702020MaterialDBO param, String mode, PageModel<R702020MaterialDBO> pageModel,RESTResultBean<String> message) throws Exception {
		if (ONE.equals(mode)) {
			R702020MaterialService_.doInsert(param);
            message.setCode(ONE);
            message.setMsg(MESSAGE_DB_INSERT);
		} else {
			R702020MaterialService_.doUpdate(param);
            message.setCode(FOUR);
            message.setMsg(MESSAGE_DB_UPDATE);
        }

        return list70202010(new R702020MaterialDBO(), pageModel, message);
	}


}
