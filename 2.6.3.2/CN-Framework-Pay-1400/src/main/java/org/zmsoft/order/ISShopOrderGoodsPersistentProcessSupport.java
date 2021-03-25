package org.zmsoft.order;

import java.util.List;

import org.zmsoft.framework.common.ISOrder;
import org.zmsoft.persistent.pay.UserOrder.UserOrderDBO;
import org.zmsoft.persistent.pay.UserOrderGoods.UserOrderGoodsDBO;

/**
 * 用户订单商品详情持久化
 */
public interface ISShopOrderGoodsPersistentProcessSupport extends ISOrder {

	/**
	 * 创建订单商品详情
	 * 
	 * @param order
	 * @return
	 */
	public List<UserOrderGoodsDBO> creatShopOrderGoods(UserOrderDBO order, List<UserOrderGoodsDBO> goods) throws Exception;

	/**
	 * 更新订单商品详情(退货)
	 * 
	 * @param order
	 * @throws Exception
	 */
	public void modifyShopOrderGoods(UserOrderGoodsDBO goods) throws Exception;

	/**
	 * 查询订单商品详情
	 * 
	 * @param order
	 * @return
	 */
	public List<UserOrderGoodsDBO> queryShopOrderGoods(UserOrderGoodsDBO goods) throws Exception;

}
