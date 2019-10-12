package org.zmsoft.pay.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.token.OrderTokenHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.pay.support.MyOrderSupport;
import org.zmsoft.pay.support.MyPayingOrderCacheSupport;

/**
 * 用户订单业务时效性检查
 * @see <PayNoticeWeixinController>
 */
@Service("CheckOrderService")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CheckOrderService extends MyOrderSupport {

	@Resource
	private MyPayingOrderCacheSupport myPayOrderCacheService;
	
	/**
	 * 业务时效性检查
	 * @param ordertoken
	 * @param orderCode
	 * @return
	 * @throws Exception
	 */
	public boolean checkOrder(String ordertoken, String orderCode) throws Exception {
		String[] ds = OrderTokenHelper.loadOrderData(ordertoken);
		if (ds[0].equals(orderCode)==false) {//1、合法订单基本签名验证
			return false;
		} else {
			// 获得订单
			PayOrderBean order = new PayOrderBean();
			order.setPayTradeNo(orderCode);
			// 根据业务需求，决定是否从数据库加载
			order = myPayOrderCacheService.loadOrder(order, false);
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

}