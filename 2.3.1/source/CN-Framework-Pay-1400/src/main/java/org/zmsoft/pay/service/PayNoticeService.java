package org.zmsoft.pay.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.pay.MyOrderSupport;

/**
 * 网吧大师登录本平台系统唯一接口地址
 * 
 * @author tasoft.com
 * @version 1.2.1 2017/06/13
 * @since 1.2.1 2017/06/13
 */
@Service("PayNoticeService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PayNoticeService extends MyOrderSupport {

	/**
	 * 到账处理
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public RESTResultBean<PayOrderBean> channelNoticeProcess(PayOrderBean order) throws Exception {
		String orderCode = order.getPayTradeNo();
		logger.debug("channelNoticeProcess:" + orderCode);
		RESTResultBean<PayOrderBean> result = new RESTResultBean<PayOrderBean>();
		if (EmptyHelper.isEmpty(orderCode)) {
			result.setMsg("订单编号为空！");
			result.setCode(1);
		} else if (EmptyHelper.isEmpty(getUserOrderPayService())) {
			result = new RESTResultBean<PayOrderBean>();
			result.setMsg("当前系统未实现支付业务！");
			result.setCode(1);
		} else {
			// //////////////// 获取实际订单 //////////////////////////////
			order = getMyPayOrderCacheService().loadOrder(order, true);
			if (EmptyHelper.isEmpty(order)) {
				result.setMsg("订单不存在，系统异常！");
				result.setCode(2);
			} else {
				// 判断是否到账成功
				if (ONE.equals(order.getPayState())) {
					result.setMsg("订单为支付！");
					result.setCode(1);// 可能需要补单
				}else if (TWO.equals(order.getOrderState())) {
					result.setMsg("订单已处理完结！");
					result.setCode(9);// 可能需要补单
				} else {
					result = getUserOrderPayService().channelNoticeProcess(order);
				}
			}
		}
		logger.debug(orderCode + "=====支付通知处理结果>>>>>" + result);
		return result;
	}

	public RESTResultBean<PayOrderBean> noticeAfterProcess(PayOrderBean order) throws Exception {
		RESTResultBean<PayOrderBean> result;
		if (EmptyHelper.isEmpty(getUserOrderPayService())) {
			result = new RESTResultBean<PayOrderBean>();
			result.setMsg("当前系统未实现支付业务！");
			result.setCode(1);
		} else {
			result = getUserOrderPayService().noticeAfterProcess(order);// 下单数据保存
		}
		return result;
	}
}
