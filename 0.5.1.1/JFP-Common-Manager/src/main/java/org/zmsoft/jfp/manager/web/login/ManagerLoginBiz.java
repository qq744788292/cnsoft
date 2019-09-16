package org.zmsoft.jfp.manager.web.login;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.zmsoft.jfp.common.constants.CustomConstants;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.user.UserBean;
import org.zmsoft.jfp.framework.elk.ELKLogData;
import org.zmsoft.jfp.framework.security.value.MD5SecurityHelper;
import org.zmsoft.jfp.framework.support.MyBusinessSupport;
import org.zmsoft.jfp.framework.token.TokenBusinessSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.DateHelper;
import org.zmsoft.jfp.framework.utils.PKHelper;
import org.zmsoft.jfp.persistent.common.SystemManager.SystemManagerDBO;
import org.zmsoft.jfp.persistent.common.SystemManager.SystemManagerService;

/**
 * 运维人员登录
 * 
 * @author fucy
 * @version 0.0.1 2017/07/10
 * @since 0.0.1 2017/07/10
 */
@Service("ManagerLoginBiz")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ManagerLoginBiz extends MyBusinessSupport<Object, String> implements CustomConstants {

	public RESTResultBean<String> doLogIn(TokenBusinessSupport tokenBean, UserBean loginer) throws Exception {

		logger.debug("TokenBusinessSupport==>>>" + tokenBean);
		logger.debug("UserBean==>>>" + loginer);

		result = new RESTResultBean<String>();

		SystemManagerService _SystemManagerService_ = BeanFactoryHelper.getBean("SystemManagerService");
		SystemManagerDBO _SystemManagerDBO_ = new SystemManagerDBO();
		// s9010UserDBO.setAccountType(accounttype);//1用户名2手机3邮箱
		_SystemManagerDBO_.setAccount(loginer.getAccount());

		List<SystemManagerDBO> users = _SystemManagerService_.doSelectManager(_SystemManagerDBO_);
		if (users == null) {
			result.setCode(ONE);
			result.setMsg("用户不存在");
		} else if (users.size() == 0) {
			result.setCode(ONE);
			result.setMsg("用户不存在");
		} else if (users.size() > 1) {
			result.setCode(TWO);
			result.setMsg("账户异常");
		} else {
			_SystemManagerDBO_ = users.get(0);// 获得 用户数据

			if (_SystemManagerDBO_.getPassWord().equals(MD5SecurityHelper.encrypt(loginer.getPassWord()))) {
				_SystemManagerDBO_.setPassWord(null);// 保证安全
				UserBean curUser = new UserBean(_SystemManagerDBO_.getPuk());
				BeanUtils.copyProperties(_SystemManagerDBO_, curUser);

				// 缓存中心缓存用户对象
				tokenBean.setCurUser(curUser);
				tokenBean.setWaitTimeSecond(1800);
				tokenBean.saveToken();// 处理TOKEN

				// 记录登录信息
				{
					_SystemManagerDBO_.setPuk(PKHelper.creatPUKey());
					_SystemManagerDBO_.setLoginStatus(ZERO);
					_SystemManagerDBO_.setOnlineType(TWO);
					_SystemManagerDBO_.setOnlineLastIp(tokenBean.getIpAdress());
					_SystemManagerDBO_.setOnlineLastTime(DateHelper.currentTimeMillisCN1());
					_SystemManagerService_.saveLoginLog(_SystemManagerDBO_);

					ELKLogData eld = new ELKLogData("UserLogin");
					eld.setCountType("manager");// 统计分类
					eld.setServiceType("SystemManager");// 服务分类

					eld.setIpAdress(tokenBean.getIpAdress());

					eld.setBusinessName(curUser.getAccount());// 业务名称
					eld.setComment(curUser.getUserId() + ":" + curUser.getUserNameCN());

					eld.loadELKLoggerAppender().debug();
				}

			} else {
				result.setCode(THREE);
				result.setMsg("密码错误");
			}
		}
		logger.debug("RESTResultBean==>>>" + result);
		return result;
	}
}
