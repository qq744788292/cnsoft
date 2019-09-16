package org.zmsoft.jfp.manager.permission.groupPermission;

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
import org.zmsoft.jfp.manager.permission.group.GroupController;
import org.zmsoft.jfp.persistent.role.S902030Group.S902030GroupDBO;
import org.zmsoft.jfp.persistent.role.S902030Group.S902030GroupService;
import org.zmsoft.jfp.persistent.role.S902040GroupPermission.S902040GroupPermissionDBO;
import org.zmsoft.jfp.persistent.role.S902040GroupPermission.S902040GroupPermissionService;
import org.zmsoft.jfp.persistent.system.S901030Menu.S901030MenuDBO;
import org.zmsoft.jfp.persistent.system.S901030Menu.S901030MenuService;

/**
 * 用户组权限管理
 * 
 * @author 王杰
 */
@Controller
public class GroupPermissionController extends MyControllerSupport implements IFrameworkConstants {

	@Resource
	S902030GroupService S902030GroupService_;
	@Resource
	S902040GroupPermissionService S902040GroupPermissionService_;
	@Resource
	S901030MenuService S901030MenuService_;
	@Resource
	GroupController groupController;

	/**
	 * 用户组与权限关联 列表
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90204010", method = RequestMethod.POST)
	public ModelAndView toPage90204010(S902030GroupDBO s902030GroupDBO, S902040GroupPermissionDBO s902040GroupPermissionDBO) {
		ModelAndView modelAndView = getModelAndView("/permission/groupPermission/group-permission");
		// 获取当前用户组
		List<S902030GroupDBO> list1 = S902030GroupService_.doSelectData(s902030GroupDBO);
		s902030GroupDBO = list1.get(0);
		// 获取所有菜单
		s902040GroupPermissionDBO.setGroupId(s902030GroupDBO.getPuk());
		s902040GroupPermissionDBO.setDelFlag(ZERO);// 有效标识
		List<S902040GroupPermissionDBO> list2 = S902040GroupPermissionService_.doSelectPagePVO(s902040GroupPermissionDBO);
		modelAndView.addObject("groupPermission", list2);
		modelAndView.addObject("data", s902030GroupDBO);
		return modelAndView;
	}

	/**
	 * 修改--保存
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90204020", method = RequestMethod.POST)
	public ModelAndView doUpdate90204020(S902040GroupPermissionDBO s902040GroupPermissionDBO, S901030MenuDBO s901030MenuDBO, String[] menuId, String[] menuName, String[] menuLevel, RESTResultBean<String> message) throws Exception {
		S902040GroupPermissionService_.doDeleteAll(s902040GroupPermissionDBO);
		for (int i = 0; i < menuId.length; i++) {
			if (EmptyHelper.isNotEmpty(menuId[i]) && EmptyHelper.isNotEmpty(menuName[i]) && EmptyHelper.isNotEmpty(menuLevel[i])) {
				s901030MenuDBO.setPuk(menuId[i]);
				s901030MenuDBO = S901030MenuService_.doRead(s901030MenuDBO);
				s902040GroupPermissionDBO.setMenuId(menuId[i]);
				s902040GroupPermissionDBO.setMenuName(menuName[i]);
				s902040GroupPermissionDBO.setMenuLevel(menuLevel[i]);
				s902040GroupPermissionDBO.setFatherMenuId(s901030MenuDBO.getFatherMenuId());
				S902040GroupPermissionService_.doInsert(s902040GroupPermissionDBO);
			}
		}
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_UPDATE);
		return groupController.doSelectPage90203010(new S902030GroupDBO(), new PageModel<S902030GroupDBO>(), message);
	}
}
