package org.zmsoft.manager.common;

import java.util.List;

import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.support.MyBusinessLogicSupport;
import org.zmsoft.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;
import org.zmsoft.manager.common.constants.IBussinessConstants;

/**
 * 获得用户数据权限
 * 
 * @author spookfcy
 *
 */
@Component
public class ManagerHdpService extends MyBusinessLogicSupport implements IBussinessConstants {
	/**
	 * 获取用户权限
	 * @param userBean
	 */
	public void prepareDataHdp(PageModel<? extends MyDataBaseObjectSupport3> page) {
		prepareDataHdp((MyDataBaseObjectSupport3)page.getFormParamBean());
	}
	
	public void prepareDataHdp(MyDataBaseObjectSupport3 bean) {
		UserBean currentUser = SessionHelper.currentUser();
		if (EmptyHelper.isEmpty(currentUser) || ONE.equals(currentUser.getIsAdmin()) || EmptyHelper.isEmpty(currentUser.getId()))
			return;
		
		//////////////////////////////////////权限处理///////////////////////////////////////
		boolean hdp = false;
		//判断登录用户企业组织
		String companyId= currentUser.getCompanyId();
		//判断登录用户角色=====================================================================
		List<String> roleList = JSON.parseArray(currentUser.getRoleIds(), String.class);
		if(EmptyHelper.isNotEmpty(roleList)){
			if(roleList.contains("2000")){//集团运营
				bean.setHdp(companyId);
				hdp = true;
			}
			if(hdp == false && roleList.contains("3000")){//城市公司运营
				//TODO 业务待定
			}
			if(hdp == false && roleList.contains("5000")){//项目负责人
				bean.setCreator(currentUser.getId());
				hdp = true;
			}
		}
		//判断登录用户运营团队所属楼盘=====================================================================
		List<String> estateList = JSON.parseArray(currentUser.getEstateIds(), String.class);
		if(hdp == true && EmptyHelper.isNotEmpty(estateList)){
			StringBuffer hdpParamSB = new StringBuffer();
			for (String estateId : estateList) {
				hdpParamSB.append(COMMA);
				hdpParamSB.append(QUOTES);
				hdpParamSB.append(estateId);
				hdpParamSB.append(QUOTES);
			}
			bean.setPv1(hdpParamSB.toString().substring(1));
		}
		
		//关闭人员转换拦截器---------------------------------------------------------------------
		bean.setConverNameFlag(false);	
	}
	
	/**
	 * 登录用户权限
	 * @param currentUser
	 */
	public void loadUserHdp(UserBean currentUser) {
		if (EmptyHelper.isEmpty(currentUser) || ONE.equals(currentUser.getIsAdmin()) || EmptyHelper.isEmpty(currentUser.getId()))
			return;

//		// 获得用户信息
//		SysUserDBO user = new SysUserDBO();
//		user.setId(currentUser.getId());
//		user = SysUserDao_.doRead(user);
//		if (EmptyHelper.isEmpty(user)) {
//			return;
//		}
//		// 获得用户所属城市公司-----------------------------
//		{
//			SysPositionDBO position = new SysPositionDBO();
//			position.setId(user.getMainPositionCode());
//			position = SysPositionDao_.doRead(position);
//			// 获得用户职位
//			if (EmptyHelper.isNotEmpty(position)) {
//				SysUnitDBO unit = new SysUnitDBO();
//				unit.setId(position.getVerticalUnitCode());
//				unit = SysUnitDao_.doRead(unit);
//				if (EmptyHelper.isNotEmpty(unit)) {
//					currentUser.setCompanyId(unit.getId());
//					currentUser.setCompanyNameCN(unit.getOrgUnitName());
//				}
//			}
//		}
//		// 获得用户角色-----------------------------
//		{
//			SysUserRoleDBO role = new SysUserRoleDBO();
//			role.setUserId(currentUser.getId());
//			List<SysUserRoleDBO> list = SysUserRoleDao_.doSelectData(role);
//			if (EmptyHelper.isNotEmpty(list)) {
//				List<String> roleList = new ArrayList<String>();
//				for(SysUserRoleDBO item : list){
//					if(roleList.contains(item.getRoleId())==false)
//						roleList.add(item.getRoleId());
//				}
//				currentUser.setRoleIds(JSON.toJSONString(roleList));
//			}
//		}
//		// 获得用户团队-----------------------------
//		{
//			SysUserTeamDBO team = new SysUserTeamDBO();
//			team.setUserId(currentUser.getId());
//			List<SysUserTeamDBO> list = SysUserTeamDao_.doSelectData(team);
//			if (EmptyHelper.isNotEmpty(list)) {
//				List<String> teamList = new ArrayList<String>();
//				for(SysUserTeamDBO item : list){
//					if(teamList.contains(item.getTeamId())==false){
//						teamList.add(item.getTeamId());
//					}
//				}
//				currentUser.setTeamIds(JSON.toJSONString(teamList));
//
//				//获得楼盘
//				List<String> estateList = new ArrayList<String>();
//				SysTeamDBO paramSysTeamDBO;
//				for(String teamId : teamList){
//					paramSysTeamDBO = new SysTeamDBO();
//					paramSysTeamDBO.setId(teamId);
//					paramSysTeamDBO = SysTeamDao_.doRead(paramSysTeamDBO);
//					if (EmptyHelper.isNotEmpty(paramSysTeamDBO)) {
//						estateList.add(paramSysTeamDBO.getEstateId());
//					}
//				}
//				currentUser.setEstateIds(JSON.toJSONString(estateList));
//			}
//		}
	}
//
//	@Resource
//	SysTeamDao SysTeamDao_;//团队
//	@Resource
//	SysUserTeamDao SysUserTeamDao_;//团队
//	@Resource
//	SysUserRoleDao SysUserRoleDao_;//角色
//	@Resource
//	SysUserDao SysUserDao_;//用户
//	@Resource
//	SysPositionDao SysPositionDao_;//职位
//	@Resource
//	SysUnitDao SysUnitDao_;//公司
}
