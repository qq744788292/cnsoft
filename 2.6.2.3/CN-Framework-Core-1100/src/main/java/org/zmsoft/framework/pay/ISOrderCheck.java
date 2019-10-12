package org.zmsoft.framework.pay;

import org.zmsoft.framework.ObjectBean;

/**
 * 第三方支付结果检查
 */
public interface ISOrderCheck {
	
	/**
	 * 订单支付结果检查
	 * @param acallBackBean
	 * @return
	 */
	public boolean doUserOrderCheck(ObjectBean acallBackBean);
}
