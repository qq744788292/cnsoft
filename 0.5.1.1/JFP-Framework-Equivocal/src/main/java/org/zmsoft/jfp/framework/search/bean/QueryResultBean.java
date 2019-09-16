package org.zmsoft.jfp.framework.search.bean;

import java.util.List;

/**
 * 查询结果
 * 
 * @author 001745
 *
 */
public class QueryResultBean {
	protected String errorMessage;
	protected Long total = 0l;
	protected List<? extends Object> hits;
	protected boolean isSucceeded;

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<? extends Object> getHits() {
		return hits;
	}

	public void setHits(List<? extends Object> hits) {
		this.hits = hits;
	}

}
