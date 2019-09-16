package org.zmsoft.jfp.manager.permission.userGroup;

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
import org.zmsoft.jfp.persistent.role.S903030UserGroup.S903030UserGroupDBO;
import org.zmsoft.jfp.persistent.role.S903030UserGroup.S903030UserGroupPVO;
import org.zmsoft.jfp.persistent.role.S903030UserGroup.S903030UserGroupService;

/**
 * 用户关联用户组管理
 * 
 * @author 王杰
 */
@Controller
public class UserGroupController extends MyControllerSupport implements IFrameworkConstants {

	@Resource
	S902030GroupService S902030GroupService_;
	@Resource
	S903030UserGroupService S903030UserGroupService_;
	@Resource
	GroupController groupController;

	/**
	 * 用户组与用户角色关联 列表
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90303010", method = RequestMethod.POST)
	public ModelAndView toPage90303010(S902030GroupDBO s02030GroupDBO, S903030UserGroupDBO s903030UserGroupDBO) {
		ModelAndView modelAndView = getModelAndView("/permission/userGroup/user-group");
		// 获得用户组
		List<S902030GroupDBO> list1 = S902030GroupService_.doSelectData(s02030GroupDBO);
		s02030GroupDBO = list1.get(0);
		// 获取用户关联用户组
		s903030UserGroupDBO.setGroupId(s02030GroupDBO.getPuk());
		s903030UserGroupDBO.setDelFlag(ZERO);// 有效标识
		List<S903030UserGroupPVO> list2 = S903030UserGroupService_.doSelectPagePVO(s903030UserGroupDBO);
		modelAndView.addObject("data", s02030GroupDBO);
		modelAndView.addObject("userGroup", list2);
		return modelAndView;
	}

	/**
	 * 修改--保存
	 * 
	 * @author 王杰
	 */
	@RequestMapping(value = "/90303020", method = RequestMethod.POST)
	public ModelAndView doInsert90302020(S903030UserGroupDBO s903030UserGroupDBO, String[] userId, String[] userName, RESTResultBean<String> message) throws Exception {
		S903030UserGroupService_.doDeleteAll(s903030UserGroupDBO);
		for (int i = 0; i < userId.length; i++) {
			if (EmptyHelper.isNotEmpty(userId[i]) && EmptyHelper.isNotEmpty(userName[i])) {
				s903030UserGroupDBO.setUserId(userId[i]);
				s903030UserGroupDBO.setUserName(userName[i]);
				S903030UserGroupService_.doInsert(s903030UserGroupDBO);
			}
		}
		message.setCode(ZERO);
		message.setMsg(MESSAGE_DB_SAVE);
		return groupController.doSelectPage90203010(new S902030GroupDBO(), new PageModel<S902030GroupDBO>(), message);
	}
}
