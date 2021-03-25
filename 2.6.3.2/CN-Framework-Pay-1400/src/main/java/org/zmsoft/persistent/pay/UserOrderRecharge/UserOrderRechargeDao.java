package org.zmsoft.persistent.pay.UserOrderRecharge;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/**
 * 玩家订单支付信息数据库操作实体
 */
@Repository("U122030OrderRechargeDao")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserOrderRechargeDao extends MyDataBaseOperateSupport2<UserOrderRechargeDBO> {
	@Resource
	private UserOrderRechargeMapper mapperU122030OrderRecharge;

	public UserOrderRechargeMapper getMapper() {
		return mapperU122030OrderRecharge;
	}

}
