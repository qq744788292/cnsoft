package org.zmsoft.jfp.common.pvo;

import org.zmsoft.jfp.framework.common.ISearchDatePrepare;
import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;

/** 时间默认值 */
public class ManagerDataFindPVO extends MyDataBaseObjectSupport3 implements ISearchDatePrepare {

	/**
	 * 开始时间
	 */
	private String startDate = null;

	/**
	 * 结束时间
	 */
	private String endDate = null;

	/**
	 * 需要统计的列名
	 */
	private String columnName;
 
   /**
    * 统计的y轴名称
    */
	private String yName;
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getyName() {
		return yName;
	}

	public void setyName(String yName) {
		this.yName = yName;
	}

	
}
