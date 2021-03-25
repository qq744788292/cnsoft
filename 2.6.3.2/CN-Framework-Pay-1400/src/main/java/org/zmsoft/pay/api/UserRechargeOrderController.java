package org.zmsoft.pay.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyControllerSupport;
import org.zmsoft.order.beans.UserOrderRechargeBean;
import org.zmsoft.pay.service.order.UserRechargeOrderService;
import org.zmsoft.persistent.pay.UserOrderRecharge.UserOrderRechargeDBO;

/**
 * 用户下单（总入口）
 * 
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/user/1.0/recharge", method = { RequestMethod.POST })
public class UserRechargeOrderController extends MyControllerSupport {// MyTokenCommonSupport
	@Resource
	private UserRechargeOrderService serviceUserRechargeOrder;

	// WxPayConfigService

	/**
	 * 根据商品订单下单支付
	 * @param request
	 * @param response
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/creat", method = RequestMethod.POST)
	public RESTResultBean<UserOrderRechargeBean> doCreatOrder(HttpServletRequest request, HttpServletResponse response, UserOrderRechargeBean param) throws Exception {
		return serviceUserRechargeOrder.creatRechargeOrder(param);
	}

	/**
	 * 自由下单支付
	 * @param request
	 * @param response
	 * @param param
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/creat2", method = RequestMethod.POST)
	public RESTResultBean<UserOrderRechargeBean> doCreatOrder(HttpServletRequest request, HttpServletResponse response, UserOrderRechargeBean param, UserOrderRechargeDBO order) throws Exception {
		return serviceUserRechargeOrder.creatRechargeOrder(param, order);
	}

	// 订单查询
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public RESTResultBean<UserOrderRechargeBean> doQueryOrder(HttpServletRequest request, HttpServletResponse response, UserOrderRechargeBean param) throws Exception {
		return serviceUserRechargeOrder.queryRechargeOrder(param);
	}

}
