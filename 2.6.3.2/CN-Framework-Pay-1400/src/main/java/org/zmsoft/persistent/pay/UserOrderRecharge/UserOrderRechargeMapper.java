package org.zmsoft.persistent.pay.UserOrderRecharge;

import org.apache.ibatis.annotations.Mapper;
import org.zmsoft.framework.db.ISDatabaseSupport;

/**
 * 玩家订单支付信息数据库操作接口（Xml映射）
 */
@Mapper
public interface UserOrderRechargeMapper extends ISDatabaseSupport<UserOrderRechargeDBO> {

}
