package org.zmsoft.pay.service;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.pay.support.MyOrderSupport;

/**
 * 用户订单状态查询
 */
@Service("QueryOrderService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QueryOrderService extends MyOrderSupport {
	/**
	 * 订单结果查询
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public RESTResultBean<PayOrderBean> queryOrder(PayOrderBean order) throws Exception {
		RESTResultBean<PayOrderBean> result = new RESTResultBean<PayOrderBean>();
		logger.debug("=====订单查询结果>>>>>" + order);
		order = myPayingOrderCacheSupport.loadOrder(order, true);
		if (EmptyHelper.isEmpty(order)) {
			result.setMsg("订单不存在，请联系客服！");
			result.setCode(2);
		} else {
			if (TWO.equals(order.getPayState())) {
				result.setMsg("充值已经到账，请查阅！");
				result.setMsg(ZERO);
			} else if (ONE.equals(order.getOrderState())) {
				result.setMsg("订单支付成功，业务处理中！");
				result.setMsg(ZERO);
			} else if (TWO.equals(order.getOrderState())) {
				result.setMsg("订单已经处理成功！");
				result.setMsg(ZERO);
			}
		}
		return result;
	}

}