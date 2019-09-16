package org.zmsoft.jfp.manager.permission.rolePermission;

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
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.manager.permission.role.RoleController;
import org.zmsoft.jfp.persistent.role.S902010Role.S902010RoleDBO;
import org.zmsoft.jfp.persistent.role.S902010Role.S902010RoleService;
import org.zmsoft.jfp.persistent.role.S902020RolePermission.S902020RolePermissionDBO;
import org.zmsoft.jfp.persistent.role.S902020RolePermission.S902020RolePermissionService;

/**
 * 角色权限管理
 * 
 * @author 王杰
 */
@Controller
public class RolePermissionController extends MyControllerSupport implements IFrameworkConstants {

	@Resource
	S902010RoleService S902010RoleService_;
	@Resource
	S902020RolePermissionService S902020RolePermissionService_;
	@Resource
	RoleController roleController;

	/**
	 * 角色与权限关联
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90202010", method = RequestMethod.POST)
	public ModelAndView toPage90202010(S902010RoleDBO s902010RoleDBO) {
		ModelAndView modelAndView = getModelAndView("/permission/rolePermission/role-permission");
		List<S902020RolePermissionDBO> list = S902020RolePermissionService_.doReadPart(s902010RoleDBO.getPuk());

		modelAndView.addObject("data", list);
		modelAndView.addObject("role", S902010RoleService_.doRead(s902010RoleDBO));
		return modelAndView;
	}

	/**
	 * 修改--保存
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90202020", method = RequestMethod.POST)
	public ModelAndView doUpdate90202020(S902020RolePermissionDBO s902020RolePermissionDBO, String[] authId, String[] authName, RESTResultBean<String> message) throws Exception {
		System.out.println(s902020RolePermissionDBO.getRoleId());
		S902020RolePermissionService_.doDeleteAll(s902020RolePermissionDBO);
		for (int i = 0; i < authId.length; i++) {
			if (EmptyHelper.isNotEmpty(authId[i]) && EmptyHelper.isNotEmpty(authName[i])) {
				s902020RolePermissionDBO.setAuthId(authId[i]);
				s902020RolePermissionDBO.setAuthName(authName[i]);
				S902020RolePermissionService_.doInsert(s902020RolePermissionDBO);
			}
		}
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_UPDATE);
		return roleController.doSelectPage90201010(new S902010RoleDBO(), new PageModel<S902010RoleDBO>(), message);
	}
}
