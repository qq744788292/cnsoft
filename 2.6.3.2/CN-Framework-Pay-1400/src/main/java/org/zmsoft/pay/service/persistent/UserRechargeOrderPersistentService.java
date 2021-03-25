package org.zmsoft.pay.service.persistent;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.order.pay.ISRechargeOrderPersistentProcessSupport;
import org.zmsoft.pay.service.ISOrderService;
import org.zmsoft.persistent.pay.UserOrderRecharge.UserOrderRechargeDBO;
import org.zmsoft.persistent.pay.UserOrderRecharge.UserOrderRechargeDao;

/**
 * 用户支付订单持久化
 */
@Service("UserRechargeOrderPersistentService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserRechargeOrderPersistentService extends MyServiceSupport implements ISRechargeOrderPersistentProcessSupport, ISOrderService {
	@Resource
	private UserOrderRechargeDao daoUserOrderRecharge;

	@Override
	public UserOrderRechargeDBO creatRechargeOrder(UserOrderRechargeDBO recharge) throws Exception {
		daoUserOrderRecharge.doInsertSelective(recharge);
		return recharge;
	}

	@Override
	public void modifyRechargeOrder(UserOrderRechargeDBO recharge) throws Exception {
		daoUserOrderRecharge.doUpdate(recharge);
	}

	@Override
	public UserOrderRechargeDBO queryRechargeOrder(UserOrderRechargeDBO recharge) throws Exception {
		return daoUserOrderRecharge.doRead(recharge);
	}

}