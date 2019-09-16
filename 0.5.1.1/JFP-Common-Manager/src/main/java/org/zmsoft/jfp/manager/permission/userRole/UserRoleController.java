package org.zmsoft.jfp.manager.permission.userRole;

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
import org.zmsoft.jfp.persistent.role.S903020UserRole.S903020UserRoleDBO;
import org.zmsoft.jfp.persistent.role.S903020UserRole.S903020UserRolePVO;
import org.zmsoft.jfp.persistent.role.S903020UserRole.S903020UserRoleService;

/**
 * 用户关联角色管理
 * 
 * @author 王杰
 */
@Controller
public class UserRoleController extends MyControllerSupport implements IFrameworkConstants {

	@Resource
	S902010RoleService S902010RoleService_;
	@Resource
	S903020UserRoleService S903020UserRoleService_;
	@Resource
	RoleController roleController;

	/**
	 * 角色跳转用户角色关联 列表
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90302010", method = RequestMethod.POST)
	public ModelAndView toPage90302010(S902010RoleDBO s902010RoleDBO, S903020UserRoleDBO s903020UserRoleDBO) {
		ModelAndView modelAndView = getModelAndView("/permission/userRole/user-role");
		// 获得角色
		List<S902010RoleDBO> list = S902010RoleService_.doSelectData(s902010RoleDBO);
		s902010RoleDBO = list.get(0);
		// 获取用户关联角色
		s903020UserRoleDBO.setGroupId(s902010RoleDBO.getPuk());
		s903020UserRoleDBO.setDelFlag(ZERO);// 有效标识
		List<S903020UserRolePVO> S903020UserRoleDBOList = S903020UserRoleService_.doSelectPagePVO(s903020UserRoleDBO);
		modelAndView.addObject("userRole", S903020UserRoleDBOList);
		modelAndView.addObject("data", s902010RoleDBO);

		return modelAndView;
	}

	/**
	 * 用户角色关联添加
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90302020", method = RequestMethod.POST)
	public ModelAndView doInsert90302020(S903020UserRoleDBO s903020UserRoleDBO, String[] userId, String[] userName, RESTResultBean<String> message) throws Exception {
		S903020UserRoleService_.doDeleteAll(s903020UserRoleDBO);
		for (int i = 0; i < userName.length; i++) {
			if (EmptyHelper.isNotEmpty(userId[i]) && EmptyHelper.isNotEmpty(userName[i])) {
				s903020UserRoleDBO.setUserName(userName[i]);
				s903020UserRoleDBO.setUserId(userId[i]);
				S903020UserRoleService_.doInsert(s903020UserRoleDBO);
			}
		}
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_UPDATE);
		return roleController.doSelectPage90201010(new S902010RoleDBO(), new PageModel<S902010RoleDBO>(), message);
	}
}
