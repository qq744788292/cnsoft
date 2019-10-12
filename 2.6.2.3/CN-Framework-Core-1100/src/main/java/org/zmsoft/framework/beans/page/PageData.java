package org.zmsoft.framework.beans.page;

import org.zmsoft.framework.ObjectBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;

public class PageData extends ObjectBean implements ICFrameworkConstants {
	/**
	 * 当前页码
	 */
	protected int pageCurrent;
	/**
	 * 每页显示条数
	 */
	protected int pageLimit;
	////////////////////////////////
	/**
	 * 总页数(计算获得)
	 */
	protected int pageCount;
	/**
	 * 总条数(计算获得)
	 */
	protected int resultCount;

	public int getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

}
