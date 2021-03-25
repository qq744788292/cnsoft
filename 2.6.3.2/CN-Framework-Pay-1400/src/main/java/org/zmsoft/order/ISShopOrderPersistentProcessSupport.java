package org.zmsoft.order;

import org.zmsoft.framework.common.ISOrder;
import org.zmsoft.persistent.pay.UserOrder.UserOrderDBO;

/**
 * 用户商品订单持久化
 */
public interface ISShopOrderPersistentProcessSupport extends ISOrder {

	/**
	 * 创建订单
	 * 
	 * @param order
	 * @return
	 */
	public UserOrderDBO creatShopOrder(UserOrderDBO order) throws Exception;

	/**
	 * 更新订单
	 * 
	 * @param order
	 * @throws Exception
	 */
	public void modifyShopOrder(UserOrderDBO order) throws Exception;

	/**
	 * 查询订单
	 * 
	 * @param order
	 * @return
	 */
	public UserOrderDBO queryShopOrder(UserOrderDBO order) throws Exception;

}
