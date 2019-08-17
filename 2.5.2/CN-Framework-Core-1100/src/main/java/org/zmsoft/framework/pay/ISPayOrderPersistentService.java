package org.zmsoft.framework.pay;

import org.zmsoft.framework.beans.PayOrderBean;

/**
 * 支付订单持久化
 */
public interface ISPayOrderPersistentService {

	/**
	 * 创建订单
	 * 
	 * @param order
	 * @return
	 */
	public PayOrderBean creatOrder(PayOrderBean order) throws Exception;

	/**
	 * 更新订单
	 * 
	 * @param order
	 * @throws Exception
	 */
	public void modifyOrder(PayOrderBean order) throws Exception;

	/**
	 * 查询订单
	 * 
	 * @param order
	 * @return
	 */
	public PayOrderBean queryOrder(PayOrderBean order) throws Exception;

}
