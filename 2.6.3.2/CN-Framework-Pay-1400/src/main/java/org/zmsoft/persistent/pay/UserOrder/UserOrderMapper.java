package org.zmsoft.persistent.pay.UserOrder;

import org.apache.ibatis.annotations.Mapper;
import org.zmsoft.framework.db.ISDatabaseSupport;

/**
 * 订单信息表数据库操作接口（Xml映射）
 */
@Mapper
public interface UserOrderMapper extends ISDatabaseSupport<UserOrderDBO> {

}
