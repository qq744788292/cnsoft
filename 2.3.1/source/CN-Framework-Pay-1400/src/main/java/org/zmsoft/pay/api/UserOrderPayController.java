package org.zmsoft.pay.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyControllerSupport;
import org.zmsoft.pay.service.OrderFormService;
import org.zmsoft.pay.service.QueryOrderService;

/**
 * 活动赛选页面接口
 * 
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/pay", method = { RequestMethod.POST })
public class UserOrderPayController extends MyControllerSupport { // MyTokenCommonSupport
	@Resource
	private OrderFormService serviceOrderForm;
	@Resource
	private QueryOrderService serviceQueryOrder;

	// 用户下单
	@RequestMapping(value = "/creatOrder", method = RequestMethod.POST)
	public RESTResultBean<PayOrderBean> doCreatOrder(HttpServletRequest request, HttpServletResponse response, PayOrderBean order) throws Exception {
		return serviceOrderForm.creatOrderProcess(order);
	}
	
	// 用户发起支付
	@RequestMapping(value = "/now", method = RequestMethod.POST)
	public RESTResultBean<PayOrderBean> doOrderPay(HttpServletRequest request, HttpServletResponse response, PayOrderBean order) throws Exception {
		return serviceOrderForm.doOrderPay(order);
	}

	// 订单查询
	@RequestMapping(value = "/queryOrder", method = RequestMethod.POST)
	public RESTResultBean<PayOrderBean> doQueryOrder(HttpServletRequest request, HttpServletResponse response, PayOrderBean order) throws Exception {
		return serviceQueryOrder.queryOrder(order);
	}

}
