package org.zmsoft.persistent.pay.UserOrder;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/**
 * 订单信息表数据库操作实体
 */
@Repository("U122010OrderDao")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserOrderDao extends MyDataBaseOperateSupport2<UserOrderDBO> {
	@Resource
	private UserOrderMapper mapperU122010Order;

	public UserOrderMapper getMapper() {
		return mapperU122010Order;
	}

}
