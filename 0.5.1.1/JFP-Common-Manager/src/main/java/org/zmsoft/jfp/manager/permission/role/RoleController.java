package org.zmsoft.jfp.manager.permission.role;

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
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.persistent.role.S902010Role.S902010RoleDBO;
import org.zmsoft.jfp.persistent.role.S902010Role.S902010RoleService;
import org.zmsoft.jfp.persistent.role.S902020RolePermission.S902020RolePermissionDBO;
import org.zmsoft.jfp.persistent.role.S902020RolePermission.S902020RolePermissionService;

/**
 * 角色管理
 * 
 * @author 王杰
 */
@Controller
public class RoleController extends MyControllerSupport implements IFrameworkConstants {

	@Resource
	S902010RoleService S902010RoleService_;
	@Resource
	S902020RolePermissionService S902020RolePermissionService_;

	/**
	 * 角色列表 列表/搜索
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90201010", method = RequestMethod.POST)
	public ModelAndView doSelectPage90201010(S902010RoleDBO s902010RoleDBO, PageModel<S902010RoleDBO> pageModel, RESTResultBean<String> message) throws Exception {
		ModelAndView modelAndView = getModelAndView("/permission/role/role-list");
		s902010RoleDBO.setDelFlag(ZERO);// 设置查询条件:删除标记为0的记录
		pageModel.setFormParamBean(s902010RoleDBO);
		pageModel.setResultCountFlag(true);// 开启总条数查询,总页数查询
		S902010RoleService_.doSelectPage(pageModel);

		List<S902010RoleDBO> roles = pageModel.getPageListData();
		for (S902010RoleDBO role1 : roles) {
			List<S902020RolePermissionDBO> rolePermission = S902020RolePermissionService_.doReadPart(role1.getPuk());
			for (S902020RolePermissionDBO role2 : rolePermission) {
				if (role2.getAuthId().equals("1")) {
					role1.setFb1("1");
				}
				if (role2.getAuthId().equals("2")) {
					role1.setFb2("2");
				}
				if (role2.getAuthId().equals("3")) {
					role1.setFb3("3");
				}
				if (role2.getAuthId().equals("4")) {
					role1.setFb4("4");
				}
				if (role2.getAuthId().equals("5")) {
					role1.setFb5("5");
				}
				if (role2.getAuthId().equals("6")) {
					role1.setEb1("6");
				}
			}
		}
		modelAndView.addObject("page", pageModel);
		modelAndView.addObject("searchCondition", s902010RoleDBO);
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	/**
	 * 修改--保存
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90201030", method = RequestMethod.POST)
	public ModelAndView doUpdate90201030(S902010RoleDBO s902010RoleDBO, RESTResultBean<String> message) throws Exception {
		S902010RoleService_.doUpdate(s902010RoleDBO);
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_UPDATE);
		return doSelectPage90201010(new S902010RoleDBO(), new PageModel<S902010RoleDBO>(), message);
	}

	/**
	 * 修改--跳转
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90201031", method = RequestMethod.POST)
	public ModelAndView toPage90201031(S902010RoleDBO s902010RoleDBO) throws Exception {
		ModelAndView modelAndView = getModelAndView("/permission/role/role-modify");

		List<S902010RoleDBO> list = S902010RoleService_.doSelectData(s902010RoleDBO);

		s902010RoleDBO = list.get(0);
		modelAndView.addObject("data", s902010RoleDBO);
		return modelAndView;
	}

	/**
	 * 删除
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90201040", method = RequestMethod.POST)
	public ModelAndView doDelete90201040(S902010RoleDBO s902010RoleDBO, RESTResultBean<String> message) throws Exception {
		S902010RoleService_.toDelete(s902010RoleDBO);
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_DELETE);
		return doSelectPage90201010(new S902010RoleDBO(), new PageModel<S902010RoleDBO>(), message);
	}

	/**
	 * 新增-插入
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90201020", method = RequestMethod.POST)
	public ModelAndView doInsert90201020(S902010RoleDBO s902010RoleDBO, String[] authId, String[] authName, S902020RolePermissionDBO S902020RolePermissionDBO_, RESTResultBean<String> message) throws Exception {
		if (EmptyHelper.isNotEmpty(s902010RoleDBO.getPuk())) {
			int num = (int) (Math.random() * 254998752);
			s902010RoleDBO.setPuk(String.valueOf(num));
		}
		S902010RoleService_.doInsert(s902010RoleDBO);
		for (int i = 0; i < authId.length; i++) {
			if (EmptyHelper.isNotEmpty(authId[i]) && EmptyHelper.isNotEmpty(authName[i])) {
				S902020RolePermissionDBO_.setAuthId(authId[i]);
				S902020RolePermissionDBO_.setAuthName(authName[i]);
				S902020RolePermissionDBO_.setRoleId(s902010RoleDBO.getPuk());
				S902020RolePermissionDBO_.setRoleName(s902010RoleDBO.getRoleName());
				S902020RolePermissionService_.doInsert(S902020RolePermissionDBO_);
			}
		}
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_INSERT);
		return doSelectPage90201010(new S902010RoleDBO(), new PageModel<S902010RoleDBO>(), message);
	}

	/**
	 * 角色名验证--AJAX
	 * 
	 * @author
	 */
	@ResponseBody
	@RequestMapping(value = "/90201050", method = RequestMethod.POST)
	public Boolean getRoleName90201050(S902010RoleDBO s902010RoleDBO, String num) throws Exception {
		List<S902010RoleDBO> list = S902010RoleService_.doSelectData(s902010RoleDBO);
		if (list.size() == 0) {
			return true;
		} else if (list.size() > 1) {
			return false;
		} else {
			s902010RoleDBO = list.get(0);
			if (s902010RoleDBO.getPuk().equals(num)) {
				return true;
			} else {
				return false;
			}
		}
	}
}
