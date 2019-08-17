package org.zmsoft.framework.beans.page;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.zmsoft.framework.beans.common.FrameworkDataBean;
import org.zmsoft.framework.mybatis.dialect.DefaultDialect;

import com.alibaba.fastjson.JSON;

/**
 * 分页数据实体超类
 * <p>
 * 需要定义@CacheKeyMethod标签来为实体指定唯一Key值，完成缓存
 * 
 * @author ZmSoft
 * @since 2.0.0 2018/10/10
 * @version 2.0.0 2018/10/10
 */
@Component("PageModel")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PageModel<T> extends PageData {
	
	protected boolean resultCountFlag = true;
	protected String countSQL = "count(1)";

	/**
	 * 页面一览数据
	 */
	protected List<T> pageListData;

	/**
	 * 页面检索参数
	 */
	protected FrameworkDataBean formParamBean;

	/**
	 * 自定义排序
	 */
	protected String orderby = EMPTY;

	public PageModel() {
		build();
	}

	public void build() {
		pageCurrent = 0;
		pageCount = 0;
		pageLimit = 10;
		resultCount = 0;
		pageListData = null;
		formParamBean = new FrameworkDataBean();
		orderby = "";
	}

	
	public boolean currentResultCountFlag() {
		return resultCountFlag;
	}

	public void setResultCountFlag(boolean resultCountFlag) {
		this.resultCountFlag = resultCountFlag;
	}

	public String currentCountSQL() {
		return countSQL;
	}

	public void setCountSQL(String countSQL) {
		this.countSQL = countSQL;
	}

	public List<T> getPageListData() {
		return pageListData;
	}

	public void setPageListData(List<T> objectData) {
		this.pageListData = objectData;
	}

	public int getPageCount() {
		if (pageLimit == 0)
			return 0;
		if (resultCount % pageLimit == 0)
			pageCount = resultCount / pageLimit;
		else
			// if (ResultCount % PageLimit != 0)
			pageCount = resultCount / pageLimit + 1;

		return pageCount;
	}

	/**
	 * 分页设置
	 * 
	 * @param pagecurrent
	 * @param pagelimit
	 */
	public void startPage(int pagecurrent, int pagelimit) {
		this.pageCurrent = pagecurrent;
		this.pageLimit = pagelimit;
	}

	public FrameworkDataBean currentFormParamBean() {
		return formParamBean;
	}

	public void setFormParamBean(FrameworkDataBean formParamBean) {
		this.formParamBean = formParamBean;
	}

	
	
	public boolean isResultCountFlag() {
		return resultCountFlag;
	}

	public String getCountSQL() {
		return countSQL;
	}

	public FrameworkDataBean getFormParamBean() {
		return formParamBean;
	}

	public String getOrderby() {
		return orderby;
	}

	public DefaultDialect getDefaultDialect() {
		return defaultDialect;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}

	/**
	 * 自定义排序
	 * 
	 * @return
	 */
	public String currentOrderby() {
		return orderby;
	}

	/**
	 * 自定义排序
	 * 
	 * @return
	 */
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	/**
	 * 当前使用分页功能
	 */
	private DefaultDialect defaultDialect;

	public DefaultDialect currentDefaultDialect() {
		return defaultDialect;
	}

	public void setDefaultDialect(DefaultDialect defaultDialect) {
		this.defaultDialect = defaultDialect;
	}

	
}
