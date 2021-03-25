package org.cnsoft.framework.db.page;

import java.util.List;

import org.cnsoft.framework.beans.FrameworkDataBean;
import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.constants.ICDBConstants;
import org.cnsoft.framework.mybatis.dialect.DefaultDialect;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 分页数据实体超类
 * <p>
 * 需要定义@CacheKeyMethod标签来为实体指定唯一Key值，完成缓存
 * 
 * @author CNSoft
 * @since 2.0.0 2018/10/10
 * @version 2.0.0 2018/10/10
 */
@Component("PageModel")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PageModel<T> extends PageData implements ICDBConstants {

	/**
	 * 开启全部返回
	 */
	private boolean resultCountFlag = false;

	/**
	 * 最大返回条数
	 */
	private int maxResultNum = 120;
	private String pageLimitSQL = "LIMIT " + maxResultNum;

	/**
	 * 默认统计查询语句
	 */
	private String countSQL = "count(1)";

	/**
	 * 页面一览数据
	 */
	private List<T> pageListData;

	/**
	 * 页面检索参数
	 */
	private FrameworkDataBean formParamBean;

	/**
	 * 自定义排序
	 */
	private String orderby = EMPTY;

	public PageModel() {
		build();
	}

	public void build() {
		pageCurrent = 0;
		pageCount = 0;
		pageLimit = 12;
		resultCount = 0;
		pageListData = null;
		formParamBean = new FrameworkDataBean();
		orderby = "";
	}

	public String currentPageLimitSQL() {
		return pageLimitSQL;
	}

	public int currentMaxResultNum() {
		return maxResultNum;
	}

	public void setMaxResultNum(int maxResultNum) {
		this.maxResultNum = maxResultNum;
		this.pageLimitSQL = "LIMIT " + maxResultNum;
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

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "PageModel [pageCurrent=" + pageCurrent + 
				", pageLimit=" + pageLimit + 
				", pageCount=" + pageCount + 
				", resultCount=" + resultCount + 
				", pageListData=" + pageListData + "]";
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

	public void setOrderbyIdASC() {
		this.orderby = Orderby_Id_ASC;
	}

	public void setOrderbyWeightASC() {
		this.orderby = Orderby_Weight_ASC;
	}

	public void setOrderbyIdDESC() {
		this.orderby = Orderby_Id_DESC;
	}

	public void setOrderbyCreateTimeDESC() {
		this.orderby = Orderby_CreateTime_DESC;
	}

	public void setOrderbyWeightDESC() {
		this.orderby = Orderby_Weight_DESC;
	}

	public void setOrderbyRand() {
		this.orderby = Orderby_Rand;
	}

	/**
	 * 当前使用分页功能
	 */
	public DefaultDialect currentDefaultDialect() {
		return MyBeanFactoryHelper.getBean(DefaultDialect.class);
	}	

}
