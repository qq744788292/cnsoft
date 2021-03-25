package org.zmsoft.pay.service.persistent;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.order.ISShopOrderPersistentProcessSupport;
import org.zmsoft.pay.service.ISOrderService;
import org.zmsoft.persistent.pay.UserOrder.UserOrderDBO;
import org.zmsoft.persistent.pay.UserOrder.UserOrderDao;

/**
 * 用户商品订单详情持久化
 */

@Service("UserShopOrderPersistentService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserShopOrderPersistentService extends MyServiceSupport implements ISShopOrderPersistentProcessSupport, ISOrderService {

	@Resource
	private UserOrderDao daoUserOrder;

	@Override
	public UserOrderDBO creatShopOrder(UserOrderDBO order) throws Exception {
		daoUserOrder.doInsertSelective(order);
		return order;
	}

	@Override
	public void modifyShopOrder(UserOrderDBO order) throws Exception {
		daoUserOrder.doUpdate(order);
	}

	@Override
	public UserOrderDBO queryShopOrder(UserOrderDBO order) throws Exception {
		return daoUserOrder.doRead(order);
	}

}