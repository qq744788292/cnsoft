package org.zmsoft.jfp.manager.permission.group;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.persistent.role.S902030Group.S902030GroupDBO;
import org.zmsoft.jfp.persistent.role.S902030Group.S902030GroupService;

/**
 * 用户组管理
 * 
 * @author 王杰
 */
@Controller
public class GroupController extends MyControllerSupport implements IFrameworkConstants {

	@Resource
	S902030GroupService S902030GroupService_;

	/**
	 * 用户组列表 列表/搜索
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90203010", method = RequestMethod.POST)
	public ModelAndView doSelectPage90203010(S902030GroupDBO s902030GroupDBO, PageModel<S902030GroupDBO> pageModel, RESTResultBean<String> message) throws Exception {
		ModelAndView modelAndView = getModelAndView("/permission/group/group-list");
		s902030GroupDBO.setDelFlag(ZERO);// 设置查询条件:删除标记为0的记录
		pageModel.setFormParamBean(s902030GroupDBO);
		pageModel.setResultCountFlag(true);// 开启总条数查询,总页数查询
		S902030GroupService_.doSelectPage(pageModel);
		modelAndView.addObject("page", pageModel);
		modelAndView.addObject("searchCondition", s902030GroupDBO);
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	/**
	 * 修改--保存
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90203030", method = RequestMethod.POST)
	public ModelAndView doUpdate90203030(S902030GroupDBO s902030GroupDBO, RESTResultBean<String> message) throws Exception {
		S902030GroupService_.doUpdate(s902030GroupDBO);
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_UPDATE);
		return doSelectPage90203010(new S902030GroupDBO(), new PageModel<S902030GroupDBO>(), message);
	}

	/**
	 * 修改--跳转
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90203031", method = RequestMethod.POST)
	public ModelAndView toPage90203031(S902030GroupDBO s902030GroupDBO) throws Exception {
		ModelAndView modelAndView = getModelAndView("/permission/group/group-modify");
		modelAndView.addObject("data", doReadOne(s902030GroupDBO));
		return modelAndView;
	}

	/**
	 * 删除
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90203040", method = RequestMethod.POST)
	public ModelAndView doDelete90203040(S902030GroupDBO s902030GroupDBO, RESTResultBean<String> message) throws Exception {
		S902030GroupService_.toDelete(s902030GroupDBO);
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_DELETE);
		return doSelectPage90203010(new S902030GroupDBO(), new PageModel<S902030GroupDBO>(), message);
	}

	/**
	 * 新增-插入
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90203020", method = RequestMethod.POST)
	public ModelAndView doInsert90203020(S902030GroupDBO s902030GroupDBO, RESTResultBean<String> message) throws Exception {
		s902030GroupDBO.setUseStatus("0");
		S902030GroupService_.doInsert(s902030GroupDBO);
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_INSERT);
		return doSelectPage90203010(new S902030GroupDBO(), new PageModel<S902030GroupDBO>(), message);
	}

	/**
	 * 查看一条记录
	 * 
	 * @author 王杰
	 */
	public static S902030GroupDBO doReadOne(S902030GroupDBO s902030GroupDBO) throws Exception {
		S902030GroupService S902030GroupService_ = BeanFactoryHelper.getBean("S902030GroupService");
		return S902030GroupService_.doSelectData(s902030GroupDBO).get(0);
	}

	/**
	 * 用户组名验证--AJAX
	 * 
	 * @author
	 */
	@ResponseBody
	@RequestMapping(value = "/90203050", method = RequestMethod.POST)
	public Boolean getGroupName90203050(S902030GroupDBO s902030GroupDBO, String num) throws Exception {
		List<S902030GroupDBO> list = S902030GroupService_.doSelectData(s902030GroupDBO);
		if (list.size() == 0) {
			return true;
		} else if (list.size() > 1) {
			return false;
		} else {
			s902030GroupDBO = list.get(0);
			if (s902030GroupDBO.getPuk().equals(num)) {
				return true;
			} else {
				return false;
			}
		}
	}
}
