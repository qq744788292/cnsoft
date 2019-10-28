package org.zmsoft.pay.service;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.pay.support.MyOrderSupport;
import org.zmsoft.pay.support.MyPayingOrderCacheSupport;

/**
 * 到账结果通知
 */
@Service("PayNoticeService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PayNoticeService extends MyOrderSupport {
	@Resource
	private MyPayingOrderCacheSupport myPayOrderCacheService;

	/**
	 * 到账通知处理
	 * 
	 * @param payType
	 * @param orderCode
	 * @throws Exception
	 */
	@Async
	public void channelNoticeProcess(String payType, String ordertoken, String orderCode) throws Exception {
		logger.debug("ordertoken=====>>>>>" + ordertoken);
		if (EmptyHelper.isEmpty(orderCode)) {
			logger.debug("订单编号为空！");
			return;
		}
		logger.debug("channelNoticeProcess:" + orderCode);
		// 开始处理支付成功
		PayOrderBean order = new PayOrderBean();
		order.setPayType(payType);
		order.setPayTradeNo(orderCode);
		// //////////////// 获取实际订单 //////////////////////////////
		order = myPayingOrderCacheSupport.loadOrder(order, true);
		if (EmptyHelper.isEmpty(order)) {
			logger.debug("订单不存在，系统异常！");
			return;
		}
		// 判断是否到账成功
		if (ONE.equals(order.getPayState())) {
			logger.debug("订单未支付！");// 可能需要补单
			return;
		} else if (TWO.equals(order.getOrderState())) {
			logger.debug("订单已处理完结！");
			return;
		}
		// 修正缓存
		myPayOrderCacheService.updateOrder(order, true);
		
		// 处理到账通知
		if(EmptyHelper.isNotEmpty(getMyPayNoticeSupport())){//检查是否存在后续处理实现
			if(getMyPayNoticeSupport().channelNoticeProcess(order).getCode() == 0){//渠道通知
				myPayOrderCacheService.updateOrder(order, true);// 修正缓存
				getMyPayNoticeSupport().noticeAfterProcess(order);
			}
		}
	}

}
