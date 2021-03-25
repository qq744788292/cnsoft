package org.zmsoft.pay.service.persistent;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.order.ISShopOrderGoodsPersistentProcessSupport;
import org.zmsoft.pay.service.ISOrderService;
import org.zmsoft.persistent.pay.UserOrder.UserOrderDBO;
import org.zmsoft.persistent.pay.UserOrderGoods.UserOrderGoodsDBO;
import org.zmsoft.persistent.pay.UserOrderGoods.UserOrderGoodsDao;

/**
 * 用户商品订单持久化
 */

@Service("ISShopOrderGoodsPersistentProcessSupport")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserShopOrderGoodsPersistentService extends MyServiceSupport implements ISShopOrderGoodsPersistentProcessSupport, ISOrderService {

	@Resource
	private UserOrderGoodsDao daoUserOrderGoods;

	@Override
	public List<UserOrderGoodsDBO> creatShopOrderGoods(UserOrderDBO order, List<UserOrderGoodsDBO> goods) throws Exception {
		for (UserOrderGoodsDBO item : goods){
			item.setOrderId(order.getId());
			daoUserOrderGoods.doInsertSelective(item);
		}
		return goods;
	}

	@Override
	public void modifyShopOrderGoods(UserOrderGoodsDBO goods) throws Exception {
		daoUserOrderGoods.doUpdate(goods);
	}

	@Override
	public List<UserOrderGoodsDBO> queryShopOrderGoods(UserOrderGoodsDBO goods) throws Exception {
		return daoUserOrderGoods.doSelectData(goods);
	}

}