package org.zmsoft.pay;

import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.token.OrderTokenHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.order.beans.UserOrderRechargeBean;
import org.zmsoft.order.pay.ISPayNoticeProcessSupport;

/**
 * 业务自定义支付到账通知处理业务
 */
//@Service("MyPayNoticeService")
public class APayNoticeService implements ISPayNoticeProcessSupport {

	@Override
	public RESTResultBean<UserOrderRechargeBean> channelNoticeProcess(UserOrderRechargeBean order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RESTResultBean<UserOrderRechargeBean> noticeAfterProcess(UserOrderRechargeBean order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 业务时效性检查
	 * 
	 * @param ordertoken
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public boolean checkShopOrder(String ordertoken, String orderid) throws Exception {
		String[] ds = OrderTokenHelper.loadOrderData(ordertoken);
		if (ds[0].equals(orderid) == false) {// 1、合法订单基本签名验证
			return false;
		} else {
			// 获得订单
			UserOrderRechargeBean order = new UserOrderRechargeBean();
			order.setOrderId(orderid);
			// 根据业务需求，决定是否从数据库加载
		//	order = cacheUserOrder.loadOrder(order);
			// 订单合法性检查
			if (EmptyHelper.isEmpty(order)) {// 2、存在性检查
				return false;
			} else if (order.getUserId().equals(ds[1]) == false) {// 3、用户一致性检查
				return false;
			}
		}

		return true;
	}
}
