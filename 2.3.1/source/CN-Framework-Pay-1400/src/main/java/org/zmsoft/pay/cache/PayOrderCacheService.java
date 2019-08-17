package org.zmsoft.pay.cache;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.cache.ADataCacheSupport;
import org.zmsoft.framework.constants.ICPayDataConstants;
import org.zmsoft.framework.pay.ISPayOrderPersistentService;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.token.OrderTokenHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.pay.ICSystemPayConstants;

/**
 * 支付订单缓存
 * 
 * @see ISPayOrderPersistentService
 */
@Service("PayOrderCacheService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PayOrderCacheService extends ADataCacheSupport<PayOrderBean> implements ICPayDataConstants, ICSystemPayConstants {

	ISPayOrderPersistentService myPayOrderPersistentService;

	public ISPayOrderPersistentService getMyPayOrderPersistentService() {
		if (EmptyHelper.isEmpty(myPayOrderPersistentService)) {
			myPayOrderPersistentService = MyBeanFactoryHelper.getBean(ISPayOrderPersistentService.class.getSimpleName());
		}
		return myPayOrderPersistentService;
	}

	public String loadCacheKey(PayOrderBean order) throws Exception {
		return CACHE_Pay + OrderTokenHelper.loadSafeOrderCode(order.getPayTradeNo());
	}

	public boolean checkOrder(String ordertoken, String orderCode) throws Exception {
		String[] ds = OrderTokenHelper.loadOrderData(ordertoken);
		if (ds[0].equals(orderCode)==false) {//1、合法订单基本签名验证
			return false;
		} else {
			// 获得订单
			PayOrderBean order = new PayOrderBean();
			order.setPayTradeNo(orderCode);
			// TODO 根据业务需求，决定是否从数据库加载
			order = loadOrder(order, false);
			//订单合法性检查
			if(EmptyHelper.isEmpty(order)){//2、存在性检查
				return false;
			}
			else if(order.getUserId().equals(ds[1])==false){//3、用户一致性检查
				return false;
			}
		}

		return true;
	}

	// 缓存订单 15分钟有效
	public PayOrderBean loadOrder(PayOrderBean order, boolean load) throws Exception {
		order = loadCacheClass(loadCacheKey(order), PayOrderBean.class);
		if (load) {
			// 数据操作-下单数据保存
			order = loadDataPersistentOrder(order);// 插入表
			if (EmptyHelper.isNotEmpty(order)) {
				saveOrder(order, true);
			}
		}
		return order;
	}

	// 缓存订单 15分钟有效
	public void saveOrder(PayOrderBean order, boolean save) throws Exception {
		myCacheService.putObject(loadCacheKey(order), order.toString(), waitTimeSecond, false);
		if (save) {
			// 数据操作-下单数据保存
			getMyPayOrderPersistentService().creatOrder(order);// 插入表
		}
	}

	// 缓存订单 15分钟有效
	public void updateOrder(PayOrderBean order, boolean save) throws Exception {
		myCacheService.putObject(loadCacheKey(order), order.toString(), waitTimeSecond, false);
		if (save) {
			// 数据操作-下单数据保存
			getMyPayOrderPersistentService().modifyOrder(order);// 更新表
		}
	}

	/**
	 * 获得订单
	 * 
	 * @throws Exception
	 */
	public PayOrderBean loadDataPersistentOrder(PayOrderBean order) throws Exception {
		return getMyPayOrderPersistentService().queryOrder(order);// 读取表
	}

	/**
	 * @deprecated
	 */
	public void loadCacheData() {
	}

	/**
	 * @deprecated
	 */
	public void removeCacheData(PayOrderBean param) {
	}

}
