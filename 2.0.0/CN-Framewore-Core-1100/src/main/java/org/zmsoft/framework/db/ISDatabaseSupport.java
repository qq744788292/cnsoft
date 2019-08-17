package org.zmsoft.framework.db;

import java.util.List;

import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.IFrameworkConstants;

/**
 * 数据库操作基本接口CURD
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <ISFrameworkConstants>
 */
public interface ISDatabaseSupport<T> extends IFrameworkConstants {
	/**
	 * 分页查询
	 * @param formParam
	 * @return
	 */
	int doUpdateAll(MyDataBaseObjectSupport paramBean);
	/////////////////// 条件查询操作///////////////////
	/**
	 * 分页查询
	 * @param formParam
	 * @return
	 */
	List<T> doSelectPage(PageModel<T> formParam);
	/**
	 * 全体查询
	 * @param formParam
	 * @return
	 */
	List<T> doSelectPage(MyDataBaseObjectSupport paramBean);
	/////////////////// 增删改查（CRUD）///////////////////

	/**
	 * 查询一条记录(主键)
	 * 
	 * @param paramBean
	 */
	T doRead(MyDataBaseObjectSupport paramBean);

	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 */
	int doInsert(MyDataBaseObjectSupport paramBean);

	/**
	 * 更新一条记录(主键)
	 * 
	 * @param paramBean
	 */
	int doUpdate(MyDataBaseObjectSupport paramBean);

	/**
	 * 删除一条记录(主键，物理)
	 * 
	 * @param paramBean
	 */
	int doDelete(MyDataBaseObjectSupport paramBean);

	/**
	 * 删除一条记录(主键,逻辑)
	 * 
	 * @param paramBean
	 */
	int toDelete(MyDataBaseObjectSupport paramBean);
}
