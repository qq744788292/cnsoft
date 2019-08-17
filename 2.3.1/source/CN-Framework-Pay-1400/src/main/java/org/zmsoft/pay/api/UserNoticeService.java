package org.zmsoft.pay.api;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.pay.cache.PayOrderCacheService;
import org.zmsoft.pay.service.OrderFormService;
import org.zmsoft.pay.service.PayNoticeService;

/**
 * 活动赛选页面接口
 * 
 * @version 0.1.1
 * @since 0.1.1
 */
@Service("UserNoticeService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserNoticeService extends MyServiceSupport { // MyTokenCommonSupport
	@Resource
	private PayOrderCacheService myPayOrderCacheService;
	@Resource
	private OrderFormService serviceOrderForm;
	@Resource
	private PayNoticeService servicePayNotice;

	/**
	 * 到账通知处理
	 * 
	 * @param payType
	 * @param orderCode
	 * @throws Exception
	 */
	@Async
	public void channelNoticeProcess(String payType, String ordertoken, String orderCode) throws Exception {
		if (myPayOrderCacheService.checkOrder(ordertoken, orderCode) == false) {
			logger.warn("channelNoticeProcess=====合法性验证失败>>>>>" + orderCode);
			return;
		} else {
			// 开始处理支付成功
			PayOrderBean order = new PayOrderBean();
			order.setPayType(payType);
			order.setPayTradeNo(orderCode);
			// 支付成功处理
			RESTResultBean<PayOrderBean> result = serviceOrderForm.payAfterProcess(order);
			if (ZERO.equals(result.getCode())) {
				// 修正缓存
				myPayOrderCacheService.updateOrder(order, true);
				// 到账处理(需要做订单支付成功查询)
				result = servicePayNotice.channelNoticeProcess(order);
				if (ZERO.equals(result.getCode())) {
					// 到账成功处理
					result = servicePayNotice.noticeAfterProcess(order);
					if (ZERO.equals(result.getCode())) {
						// 修正缓存
						myPayOrderCacheService.updateOrder(order, true);
					}
				}
			}
		}
	}
}
