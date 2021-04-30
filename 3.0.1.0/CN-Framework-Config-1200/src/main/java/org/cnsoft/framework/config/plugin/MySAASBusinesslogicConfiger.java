package org.cnsoft.framework.config.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.cnsoft.framework.cache.session.SessionHelper;
import org.cnsoft.framework.db.support.ADataBaseDefaultSupportBean;
import org.cnsoft.framework.saas.plugin.ISSAASPluginParamDB;
import org.cnsoft.framework.saas.plugin.ISSAASPluginParamRequest;
import org.cnsoft.framework.utils.EmptyHelper;

/**
 * SAAS服务模式拦截器
 */
public class MySAASBusinesslogicConfiger implements ISSAASPluginParamRequest, ISSAASPluginParamDB {

	@Override
	public boolean doCompletion(ADataBaseDefaultSupportBean formParamBean) {
		//gid
		try {
			//游戏ID
			BeanUtils.setProperty(formParamBean, "gameId", SessionHelper.currentUser().getPassWord());
		} catch (Exception e) {
		}
		try {
			//用户识别ID
			BeanUtils.setProperty(formParamBean, "sid", SessionHelper.currentUserSid());
		} catch (Exception e) {
		}
		return true;
	}

	@Override
	public boolean doCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean check = true;
		try {
			String gameId_flg = request.getParameter("gameId_flg");
			String gameId = request.getParameter("gameId");
			
			//System.out.println("SessionHelper.currentUser()======>>>>>>>>>>"+SessionHelper.currentUser());
			//游戏ID
			if(EmptyHelper.isEmpty(gameId_flg)) {
				if(EmptyHelper.isEmpty(gameId)) {
					gameId = SessionHelper.currentUser().getPassWord();
					if(EmptyHelper.isEmpty(gameId)) {
						check = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
}
