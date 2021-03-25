package org.cnsoft.framework.support;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnsoft.framework.beans.user.UserBean;
import org.cnsoft.framework.cache.session.SessionHelper;
import org.cnsoft.framework.common.ISLoginer;
import org.cnsoft.framework.common.buzzinezz.ISESDateSupport;
import org.cnsoft.framework.utils.DateHelper;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.framework.utils.RandomHelper;

/**
 * 数据业务操作接口定义超类<br>
 * 事务性质业务逻辑<br>
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class MyServiceSupport extends MyFrameWorkSupport implements ISLoginer {
	
	/**
	 * 当前登录用户
	 */
	private UserBean curUser;

	public void setCurUser(UserBean curUser) {
		this.curUser = curUser;
	}

	public UserBean currentUser() {
		if (curUser == null) {
			curUser = SessionHelper.currentUser();
			if (curUser == null) {
				throw new RuntimeException("当前用户没有登录");
			}
		}
		return curUser;
	}
	////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 获得当前线程的安全码
	 * 
	 * @param token
	 * @return
	 */
	public String loadSecurityCode(String token) {
		String code = RandomHelper.getRandomNumerical(4);
		logger.debug("loadSecurityCode=====code>>>>>" + code + "=====token>>>>>" + token);
		myCacheService.putObject(CONSTANT_SECURITY_CODE + token, code, 60);
		return code;
	}
	
	/**
	 * 获得当前线程的安全码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String loadSecurityCode(HttpServletRequest request, HttpServletResponse response) {
		return loadSecurityCode(currentUser().getToken());
	}

	/**
	 * 安全码验证(成功后删除)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean checkSecurityCode(HttpServletRequest request, HttpServletResponse response) {
		return checkSecurityCode(request, response, false);
	}

	/**
	 * 安全码验证
	 * 
	 * @param request
	 * @param response
	 * @param autodelete
	 *            false:成功后删除;true:自动删除
	 * @return
	 */
	public boolean checkSecurityCode(HttpServletRequest request, HttpServletResponse response, boolean autodelete) {
		String curCode = request.getParameter("securityCode");
		return checkSecurityCode(currentUser().getToken(), curCode, autodelete);
	}

	public boolean checkSecurityCode(HttpServletRequest request, String curCode, boolean autodelete) {
		return checkSecurityCode(currentUser().getToken(), curCode, autodelete);
	}

	public boolean checkSecurityCode(String token, String curCode, boolean autodelete) {
		try {
			if (EmptyHelper.isEmpty(curCode))
				return false;
			String secCode = myCacheService.getObject(CONSTANT_SECURITY_CODE + token);
			boolean check = secCode.equals(curCode);
			if (autodelete || check)
				myCacheService.removeKey(CONSTANT_SECURITY_CODE + token);
			logger.debug("CheckCodeImageApi=====curCode>>>>>" + curCode + "=====token>>>>>" + token + "=====SecurityCode>>>>>" + secCode);
			return check;
		} catch (Exception e) {
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 开始时间和结束时间的处理
	 *
	 * @param page
	 */
	public void dateConvert(ISESDateSupport page, String type) {
		if (EmptyHelper.isEmpty(type)) {
			dateJudge(page);
			return;
		}
		// 前7天
		if (ZERO.equals(type)) {
			dateJudge0(page);
			return;
		}
		// 今天
		if (ONE.equals(type)) {
			dateJudge1(page);
			return;
		}
		// 昨天
		if (TWO.equals(type)) {
			dateJudge2(page);
			return;
		}
		// 前天
		if (THREE.equals(type)) {
			dateJudge3(page);
			return;
		}
		// 最近30天
		if (FOUR.equals(type)) {
			dateJudge4(page);
			return;
		}
		// 本周
		if (FIVE.equals(type)) {
			dateJudge5(page);
			return;
		}
		// 上周
		if (SIX.equals(type)) {
			dateJudge6(page);
			return;
		}
		// 本月
		if (SEVEN.equals(type)) {
			dateJudge7(page);
			return;
		}
		// 上月
		if (EIGHT.equals(type)) {
			dateJudge8(page);
			return;
		}
	}

	// 前7天
	public void dateJudge(ISESDateSupport page) {
		// 计算当前时间前7天的日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -7);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		// 如果第一个时间为空 赋值为当前时间的前7天日期 第二个为空赋值为当前日期
		if (EmptyHelper.isEmpty(page.getStartDate()) && EmptyHelper.isEmpty(page.getEndDate())) {
			page.setStartDate(preMonday + " 00:00:00");
			page.setEndDate(DateHelper.currentTimeMillisCN3() + " 23:59:59");
			return;
		}
		if (EmptyHelper.isEmpty(page.getStartDate())) {
			page.setStartDate(preMonday + " 00:00:00");
			return;
		}
		if (EmptyHelper.isEmpty(page.getEndDate())) {
			page.setEndDate(DateHelper.currentTimeMillisCN3() + " 23:59:59");
			return;
		}

	}

	// 前7天
	public void dateJudge0(ISESDateSupport page) {
		// 计算当前时间前7天的日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -7);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		page.setStartDate(preMonday + " 00:00:00");
		page.setEndDate(DateHelper.currentTimeMillisCN3() + " 23:59:59");

	}

	// 今天
	public void dateJudge1(ISESDateSupport page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		page.setStartDate(DateHelper.currentTimeMillisCN3() + " 00:00:00");
		page.setEndDate(sdf.format(new Date()));
	}

	// 昨天
	public void dateJudge2(ISESDateSupport page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		page.setStartDate(preMonday + " 00:00:00");
		page.setEndDate(preMonday + " 23:59:59");
	}

	// 前天
	public void dateJudge3(ISESDateSupport page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -2);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		page.setStartDate(preMonday + " 00:00:00");
		page.setEndDate(preMonday + " 23:59:59");
	}

	// 最近30天
	public void dateJudge4(ISESDateSupport page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -30);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		page.setStartDate(preMonday + " 00:00:00");
		page.setEndDate(DateHelper.currentTimeMillisCN3() + " 23:59:59");
	}

	// 本周
	public void dateJudge5(ISESDateSupport page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		page.setStartDate(preMonday + " 00:00:00");
		page.setEndDate(DateHelper.currentTimeMillisCN3() + " 23:59:59");
	}

	// 上周
	public void dateJudge6(ISESDateSupport page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 上周周日时间
		Date sunday = c.getTime();
		String preMonday = sdf.format(sunday);
		page.setEndDate(preMonday + " 23:59:59");

		c.add(Calendar.DATE, -6);// 减6天是上周周一时间
		Date monday = c.getTime();
		String preMonday2 = sdf.format(monday);
		page.setStartDate(preMonday2 + " 00:00:00");
	}

	// 本月
	public void dateJudge7(ISESDateSupport page) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date start = c.getTime();
		String preMonday = sdf.format(start);
		page.setStartDate(preMonday + " 00:00:00");
		page.setEndDate(DateHelper.currentTimeMillisCN3() + " 23:59:59");
	}

	// 上月
	public void dateJudge8(ISESDateSupport page) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date sunday = c.getTime();
		String preMonday = sdf.format(sunday);// 上个月第一天
		page.setStartDate(preMonday + " 00:00:00");

		c.set(Calendar.MONTH, c.get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		String preMonday2 = sdf.format(c.getTime());
		page.setEndDate(preMonday2 + " 23:59:59");
	}

}
