package org.zmsoft.framework.support;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.common.ILoginer;
import org.zmsoft.framework.common.ISearchDatePrepare;
import org.zmsoft.framework.utils.DateHelper;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 数据业务操作接口定义超类<br>
 * 事务性质业务逻辑<br>
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class MyServiceSupport extends MyTokenCommonSupport implements ILoginer {

	/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 当前登录用户
	 */
	public UserBean currentUser() {
		UserBean loginer = SessionHelper.currentUser();
		if (loginer == null) {
			throw new RuntimeException("当前用户没有登录");
		}
		return loginer;
	}

	////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 开始时间和结束时间的处理
	 *
	 * @param page
	 */
	public void dateConvert(ISearchDatePrepare page, String type) {
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
	public void dateJudge(ISearchDatePrepare page) {
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
	public void dateJudge0(ISearchDatePrepare page) {
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
	public void dateJudge1(ISearchDatePrepare page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		page.setStartDate(DateHelper.currentTimeMillisCN3() + " 00:00:00");
		page.setEndDate(sdf.format(new Date()));
	}

	// 昨天
	public void dateJudge2(ISearchDatePrepare page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		page.setStartDate(preMonday + " 00:00:00");
		page.setEndDate(preMonday + " 23:59:59");
	}

	// 前天
	public void dateJudge3(ISearchDatePrepare page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -2);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		page.setStartDate(preMonday + " 00:00:00");
		page.setEndDate(preMonday + " 23:59:59");
	}

	// 最近30天
	public void dateJudge4(ISearchDatePrepare page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -30);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		page.setStartDate(preMonday + " 00:00:00");
		page.setEndDate(DateHelper.currentTimeMillisCN3() + " 23:59:59");
	}

	// 本周
	public void dateJudge5(ISearchDatePrepare page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		page.setStartDate(preMonday + " 00:00:00");
		page.setEndDate(DateHelper.currentTimeMillisCN3() + " 23:59:59");
	}

	// 上周
	public void dateJudge6(ISearchDatePrepare page) {

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
	public void dateJudge7(ISearchDatePrepare page) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date start = c.getTime();
		String preMonday = sdf.format(start);
		page.setStartDate(preMonday + " 00:00:00");
		page.setEndDate(DateHelper.currentTimeMillisCN3() + " 23:59:59");
	}

	// 上月
	public void dateJudge8(ISearchDatePrepare page) {
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
