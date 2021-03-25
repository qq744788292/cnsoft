package org.zmsoft.pay.service.order;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.beans.UserBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.cache.session.SessionHelper;
import org.zmsoft.framework.utils.DateHelper;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.PKHelper;
import org.zmsoft.order.beans.UserOrderRechargeBean;
import org.zmsoft.order.cache.MyUserOrderRechargeCacheSupport;
import org.zmsoft.pay.service.MyOrderServiceSupport;
import org.zmsoft.pay.service.persistent.UserShopOrderGoodsPersistentService;
import org.zmsoft.pay.service.persistent.UserShopOrderPersistentService;
import org.zmsoft.persistent.pay.UserOrder.UserOrderDBO;
import org.zmsoft.persistent.pay.UserOrderGoods.UserOrderGoodsDBO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 用户商品订单处理
 * 
 * @version 1.2.1 2017/06/13
 * @since 1.2.1 2017/06/13
 */
@Service("UserShopOrderService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserShopOrderService extends MyOrderServiceSupport {
	// 商品订单缓存
	@Resource
	private UserShopOrderPersistentService myShopOrderPersistentService;
	// 商品详情
	@Resource
	private UserShopOrderGoodsPersistentService myShopOrderGoodsPersistentService;
	//订单系统缓存
	@Resource
	private MyUserOrderRechargeCacheSupport cacheUserOrder;

	/**
	 * 用户下单（服务入口）
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public RESTResultBean<UserOrderRechargeBean> creatShopOrder(UserOrderRechargeBean param) throws Exception {
		// 创建订单
		UserOrderDBO order;
		List<UserOrderGoodsDBO> goods;

		// 订单详情
		if (EmptyHelper.isNotEmpty(param.getOrderJsonObject()))
			order = JSONObject.parseObject(param.getOrderJsonObject(), UserOrderDBO.class);
		else {
			order = new UserOrderDBO();
			// 订单描述
			order.setBody(param.getBody());
			// 支付金额
			order.setPayAmount(param.getPayAmount());
		}

		// 商品详情
		if (EmptyHelper.isNotEmpty(param.getGoodsJsonArray())) {
			goods = JSONArray.parseArray(param.getGoodsJsonArray(), UserOrderGoodsDBO.class);
		} else {
			goods = new ArrayList<UserOrderGoodsDBO>();
		}

		return creatShopOrder(param, order, goods);
	}

	// 创建订单
	public RESTResultBean<UserOrderRechargeBean> creatShopOrder(UserOrderRechargeBean param, UserOrderDBO order, List<UserOrderGoodsDBO> goods) throws Exception {
		RESTResultBean<UserOrderRechargeBean> result = new RESTResultBean<UserOrderRechargeBean>();
		{
			// 创建主键 // 商户订单号
			order.preparePrimarykey();
			if (EmptyHelper.isEmpty(order.getUserId())) {// 是否为代付订单
				UserBean user = SessionHelper.currentUser();
				order.setUserId(user.getUserId());
				order.setUserNickname(user.getUserNameCN());
				order.setUserPhone(user.getUserPhone());
			}
			// 订单日期
			order.setOrderDate(DateHelper.currentDate4());
			order.setOrderTime(DateHelper.currentTime2());
			if (EmptyHelper.isEmpty(order.getPayAmount())) {
				result.setCode(1);
				result.setMsg("下单失败，请输入订单金额！");
				return result;
			}
			if (EmptyHelper.isEmpty(order.getGoodsCost())) {// 商品总额
				order.setGoodsCost(order.getPayAmount());
			}
			if (EmptyHelper.isEmpty(order.getOrderTotalCost())) {// 商品总额
				order.setOrderTotalCost(order.getPayAmount());
			}
			// 支付状态
			order.setRechargeStateCode(TWO);
			// 到账状态
			order.setRechargeOrderStateCode(TWO);
			
			//随机字符串
			order.setNonceStr(PKHelper.creatUUKey());
			//商品描述
			order.setBody(param.getBody());
			
			// 持久化订单
			myShopOrderPersistentService.creatShopOrder(order);
			if (EmptyHelper.isNotEmpty(goods)){
				myShopOrderGoodsPersistentService.creatShopOrderGoods(order, goods);
				//商品数据整理
				param.setGoodsJsonArray(JSON.toJSONString(goods));
				order.setGoods(null);
			}

			//订单数据整理
			param.setOrderId(order.getId());
			param.setOrderJsonObject(JSON.toJSONString(order));

			// 订单缓存
			cacheUserOrder.setWaitTimeSecond(this.configOrderTimeMinute());// 持久化时间
			cacheUserOrder.saveOrder(param);
			result.setData(param);
			result.setMsg("下单成功！");
		}
		return result;
	}

	public RESTResultBean<UserOrderRechargeBean> queryShopOrder(UserOrderRechargeBean order) throws Exception {
		RESTResultBean<UserOrderRechargeBean> result = new RESTResultBean<UserOrderRechargeBean>();
		logger.debug("=====订单查询结果>>>>>" + order);
		order = cacheUserOrder.loadOrder(order);
		if (EmptyHelper.isEmpty(order)) {
			result.setMsg("订单不存在，请联系客服！");
			result.setCode(2);
		} else {
			if (ONE.equals(order.getPayStateCode())) {
				result.setMsg("订单支付成功，业务处理中！");
				result.setMsg(ZERO);
			} else if (ONE.equals(order.getPayStateCode())) {
				result.setMsg("支付已经到账，请等待发货！");
				result.setMsg(ZERO);
			} else if (TEN.equals(order.getOrderStatusCode())) {
				// 单状态编码(1待付款, 2已取消, 3已付款待处理, 4申请退款, 5退款确认,6已发货, 7物流配送中, 8申请售后,
				// 9售后退款, 10已完成)
				result.setMsg("订单已经处理完成！");
				result.setMsg(ZERO);
			}
		}
		return result;
	}

}