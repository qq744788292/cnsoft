package org.zmsoft.order.cache;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.cache.ADataCacheSupport;
import org.zmsoft.framework.constants.ICPayDataConstants;
import org.zmsoft.framework.token.OrderTokenHelper;
import org.zmsoft.order.beans.UserOrderRechargeBean;
import org.zmsoft.order.pay.ISRechargeOrderPersistentProcessSupport;

/**
 * 用户支付订单缓存
 * 
 * @see ISRechargeOrderPersistentProcessSupport
 */
@Service("MyUserOrderRechargeCacheSupport")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyUserOrderRechargeCacheSupport extends ADataCacheSupport<UserOrderRechargeBean> implements ICPayDataConstants {

	//一次只能有一个订单？
	public String loadCacheKey(UserOrderRechargeBean order) throws Exception {
		return CACHE_Pay + OrderTokenHelper.loadSafeOrderCode(order.getOrderId());
	}

	// 缓存订单 15分钟有效
	public UserOrderRechargeBean loadOrder(UserOrderRechargeBean order) throws Exception {
		order = loadCacheClass(loadCacheKey(order), UserOrderRechargeBean.class);
		return order;
	}

	// 缓存订单 15分钟有效
	public void saveOrder(UserOrderRechargeBean order) throws Exception {
		myCacheService.putObject(loadCacheKey(order), order.toString(), waitTimeSecond, false);
	}

	// 缓存订单 15分钟有效
	public void updateOrder(UserOrderRechargeBean order) throws Exception {
		myCacheService.putObject(loadCacheKey(order), order.toString(), waitTimeSecond, false);
	}

	/**
	 * @deprecated
	 */
	public void loadCacheData() {
	}

	/**
	 * @throws Exception 
	 */
	public void removeCacheData(UserOrderRechargeBean param) throws Exception {
		myCacheService.removeKey(loadCacheKey(param));
	}

}
