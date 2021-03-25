package org.zmsoft.persistent.pay.UserOrderGoods;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/**
 * 订单商品详情表数据库操作实体
 */
@Repository("U122020OrderGoodsDao")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserOrderGoodsDao extends MyDataBaseOperateSupport2<UserOrderGoodsDBO> {
	@Resource
	private UserOrderGoodsMapper mapperU122020OrderGoods;

	public UserOrderGoodsMapper getMapper() {
		return mapperU122020OrderGoods;
	}

}
