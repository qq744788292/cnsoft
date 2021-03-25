package org.zmsoft.pay.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.support.MyControllerSupport;
import org.zmsoft.order.beans.UserOrderRechargeBean;
import org.zmsoft.pay.service.order.UserShopOrderService;
import org.zmsoft.persistent.pay.UserOrder.UserOrderDBO;
import org.zmsoft.persistent.pay.UserOrderGoods.UserOrderGoodsDBO;

/**
 * 用户下单（总入口）
 * 
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/user/1.0/order", method = { RequestMethod.POST })
public class UserShopOrderController extends MyControllerSupport {// MyTokenCommonSupport
	@Resource
	private UserShopOrderService serviceUserShopOrder;

	// 用户下单(极简模式)
	@RequestMapping(value = "/creat", method = RequestMethod.POST)
	public RESTResultBean<UserOrderRechargeBean> doCreatOrder(HttpServletRequest request, HttpServletResponse response, UserOrderRechargeBean param) throws Exception {
		return serviceUserShopOrder.creatShopOrder(param);
	}
	
	// 用户下单
	@RequestMapping(value = "/creat2", method = RequestMethod.POST)
	public RESTResultBean<UserOrderRechargeBean> doCreatOrder(HttpServletRequest request, HttpServletResponse response, UserOrderRechargeBean param, UserOrderDBO order) throws Exception {
		return serviceUserShopOrder.creatShopOrder(param, order, order.getGoods());
	}
	
	// 用户下单
	@RequestMapping(value = "/creat3", method = RequestMethod.POST)
	public RESTResultBean<UserOrderRechargeBean> doCreatOrder(HttpServletRequest request, HttpServletResponse response, UserOrderRechargeBean param, UserOrderDBO order, @RequestParam(value = "goods") List<UserOrderGoodsDBO> goods) throws Exception {
		return serviceUserShopOrder.creatShopOrder(param, order, goods);
	}

	// 订单查询
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public RESTResultBean<UserOrderRechargeBean> doQueryOrder(HttpServletRequest request, HttpServletResponse response, UserOrderRechargeBean param) throws Exception {
		return serviceUserShopOrder.queryShopOrder(param);
	}

}
