package org.zmsoft.jfp.manager.material.materialClassifyTag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.persistent.material.R702030MaterialClassifyTag.R702030MaterialClassifyTagDBO;
import org.zmsoft.jfp.persistent.material.R702030MaterialClassifyTag.R702030MaterialClassifyTagService;


import javax.annotation.Resource;

/**
 * 素材标签发布
 * 
 * @version 0.0.1 2018/05/08
 * @since 0.0.1 2018/05/08
 * @author 周海俊
 */
@Controller
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MaterialClassifyTagController extends MyControllerSupport {

	@Resource
    R702030MaterialClassifyTagService R702030MaterialClassifyTagService_;

	/**
	 * 一览
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70203010", method = RequestMethod.POST)
	public ModelAndView list70203010(R702030MaterialClassifyTagDBO param, PageModel<R702030MaterialClassifyTagDBO> pageModel, RESTResultBean<String> message) throws Exception {
		ModelAndView model = super.getModelAndView("/material/materialClassifyTag/materialClassifyTag-list");
		param.setDelFlag(ZERO);
		pageModel.setResultCountFlag(true);
		pageModel.setFormParamBean(param);
		pageModel.setOrderby("create_time DESC");

		pageModel = R702030MaterialClassifyTagService_.doSelectPage(pageModel);

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
	@RequestMapping(value = "/70203020", method = RequestMethod.POST)
	public ModelAndView add70203020(R702030MaterialClassifyTagDBO param) throws Exception {
		ModelAndView model = getModelAndView("/material/materialClassifyTag/materialClassifyTag-modify");
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
	@RequestMapping(value = "/70203030", method = RequestMethod.POST)
	public ModelAndView edit70203030(R702030MaterialClassifyTagDBO param) throws Exception {
		ModelAndView model = getModelAndView("/material/materialClassifyTag/materialClassifyTag-modify");
		model.addObject("mode", TWO);// 编辑
		model.addObject("data", R702030MaterialClassifyTagService_.doRead(param));
		return model;
	}

	/**
	 * 删除
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70203040", method = RequestMethod.POST)
	public ModelAndView delete70203040(R702030MaterialClassifyTagDBO param, PageModel<R702030MaterialClassifyTagDBO> pageModel,RESTResultBean<String> message) throws Exception {
		R702030MaterialClassifyTagService_.doDelete(param);
        message.setCode(ONE);
        message.setMsg(MESSAGE_DB_DELETE);
        return list70203010(new R702030MaterialClassifyTagDBO(), pageModel, message);
	}

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70203050", method = RequestMethod.POST)
	public ModelAndView save70203050(R702030MaterialClassifyTagDBO param, String mode, PageModel<R702030MaterialClassifyTagDBO> pageModel,RESTResultBean<String> message) throws Exception {
		if (ONE.equals(mode)) {
			R702030MaterialClassifyTagService_.doInsert(param);
            message.setCode(ONE);
            message.setMsg(MESSAGE_DB_INSERT);
		} else {
			R702030MaterialClassifyTagService_.doUpdate(param);
            message.setCode(FOUR);
            message.setMsg(MESSAGE_DB_UPDATE);
        }

        return list70203010(new R702030MaterialClassifyTagDBO(), pageModel, message);
	}


}
