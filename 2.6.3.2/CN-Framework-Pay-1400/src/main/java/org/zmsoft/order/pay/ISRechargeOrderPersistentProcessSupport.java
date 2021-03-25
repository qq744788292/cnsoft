package org.zmsoft.order.pay;

import org.zmsoft.framework.common.ISOrder;
import org.zmsoft.persistent.pay.UserOrderRecharge.UserOrderRechargeDBO;

/**
 * 用户支付订单持久化
 */
public interface ISRechargeOrderPersistentProcessSupport extends ISOrder {

	/**
	 * 创建第三方支付订单
	 * 
	 * @param recharge
	 * @return
	 */
	public UserOrderRechargeDBO creatRechargeOrder(UserOrderRechargeDBO recharge) throws Exception;

	/**
	 * 更新第三方支付订单
	 * 
	 * @param recharge
	 * @throws Exception
	 */
	public void modifyRechargeOrder(UserOrderRechargeDBO recharge) throws Exception;

	/**
	 * 查询第三方支付订单
	 * 
	 * @param recharge
	 * @return
	 */
	public UserOrderRechargeDBO queryRechargeOrder(UserOrderRechargeDBO recharge) throws Exception;

}
