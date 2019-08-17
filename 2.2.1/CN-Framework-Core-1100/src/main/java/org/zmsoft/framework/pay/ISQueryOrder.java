package org.zmsoft.framework.pay;

import org.zmsoft.framework.beans.PayOrderBean;

/**
 * 用户订单查询
 * 
 * @author Administrator
 *
 */
public interface ISQueryOrder {

	/**
	 * 查询订单
	 * 
	 * @param order
	 * @return
	 */
	public PayOrderBean queryOrder(PayOrderBean order) throws Exception;

}
