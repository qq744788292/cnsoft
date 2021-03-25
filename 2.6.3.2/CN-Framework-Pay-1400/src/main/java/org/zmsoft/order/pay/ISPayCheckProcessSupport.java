package org.zmsoft.order.pay;

import org.zmsoft.framework.beans.OrderBean;
import org.zmsoft.framework.common.ISOrder;

/**
 * 第三方支付结果检查
 */
public interface ISPayCheckProcessSupport extends ISOrder {

	/**
	 * 订单支付结果检查
	 * 
	 * @param acallBackBean
	 * @return
	 */
	public boolean doUserOrderCheck(OrderBean acallBackBean);
}
