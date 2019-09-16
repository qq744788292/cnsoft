package org.zmsoft.jfp.manager.material.wxdyh;

import javax.annotation.Resource;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.crawler.CrawlerState;
import org.zmsoft.jfp.framework.crawler.biz.LoadPageService;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.persistent.material.W702120MaterialUrl.W702120MaterialUrlDBO;
import org.zmsoft.jfp.persistent.material.W702120MaterialUrl.W702120MaterialUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 日志查看
 * 
 * @version 0.0.1 2018/05/08
 * @since 0.0.1 2018/05/08
 */
@Controller
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WXMaterialUrlController extends MyControllerSupport {

	@Resource
	W702120MaterialUrlService W702120MaterialUrlService_;// 微信文章地址管理

	/**
	 * 一览
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70212010", method = RequestMethod.POST)
	public ModelAndView list70212010(W702120MaterialUrlDBO param, PageModel<W702120MaterialUrlDBO> pageModel, RESTResultBean<String> message) throws Exception {
		ModelAndView model = super.getModelAndView("/wx_material/material_url/wx-material-url-list");
		param.setDelFlag(ZERO);
		pageModel.setResultCountFlag(true);
		pageModel.setFormParamBean(param);
		pageModel.setOrderby("create_time DESC");
		// 获取菜单数据
		pageModel = W702120MaterialUrlService_.doSelectPage(pageModel);

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
	@RequestMapping(value = "/70212020", method = RequestMethod.POST)
	public ModelAndView add70212020(W702120MaterialUrlDBO param) throws Exception {
		ModelAndView model = getModelAndView("/wx_material/material_url/wx-material-url-modify");
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
	@RequestMapping(value = "/70212030", method = RequestMethod.POST)
	public ModelAndView edit70212030(W702120MaterialUrlDBO param) throws Exception {
		ModelAndView model = getModelAndView("/wx_material/material_url/wx-material-url-modify");
		model.addObject("mode", TWO);// 编辑
		model.addObject("data", W702120MaterialUrlService_.doRead(param));
		return model;
	}

	/**
	 * 删除
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70212040", method = RequestMethod.POST)
	public ModelAndView delete70212040(W702120MaterialUrlDBO param, PageModel<W702120MaterialUrlDBO> pageModel,RESTResultBean<String> message) throws Exception {
		W702120MaterialUrlService_.doDelete(param);
        message.setCode(ONE);
        message.setMsg(MESSAGE_DB_DELETE);
        return list70212010(new W702120MaterialUrlDBO(), pageModel, message);
	}

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70212050", method = RequestMethod.POST)
	public ModelAndView save70212050(W702120MaterialUrlDBO param, String mode, PageModel<W702120MaterialUrlDBO> pageModel,RESTResultBean<String> message) throws Exception {
		if (ONE.equals(mode)) {
			W702120MaterialUrlService_.doInsert(param);
            message.setCode(ONE);
            message.setMsg(MESSAGE_DB_INSERT);
		} else {
			W702120MaterialUrlService_.doUpdate(param);
            message.setCode(FOUR);
            message.setMsg(MESSAGE_DB_UPDATE);
        }

        return list70212010(new W702120MaterialUrlDBO(), pageModel, message);
	}

	// /**
	// * 操作
	// *
	// * @param param
	// * @return
	// * @throws Exception
	// */
	// @RequestMapping(value = "/70211060", method = RequestMethod.POST)
	// public ModelAndView save80401060(W702120MaterialUrlDBO param) throws
	// Exception {
	// return list80401010(new W702120MaterialUrlDBO(), "操作成功");
	// }

	/**
	 * 手动抓取公众号文章
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70212090", method = RequestMethod.POST)
	public ModelAndView do70212090(String serviceName, String remoteURL, PageModel<W702120MaterialUrlDBO> pageModel,RESTResultBean<String> message) throws Exception {

		try {
			LoadPageService<?, ?> _LoadPageService_ = BeanFactoryHelper.getBean(serviceName);
			if (CrawlerState.SUCCESS.equals(_LoadPageService_.doProcess(remoteURL, null)) == false) {
			    message.setCode(FOUR);
                message.setMsg("更新失败");
            }
		} catch (Exception e) {
            message.setCode(FOUR);
            message.setMsg("更新失败");
			e.printStackTrace();
		}
		return list70212010(new W702120MaterialUrlDBO(), pageModel, message);
	}
}
