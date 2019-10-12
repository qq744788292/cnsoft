package org.zmsoft.pay.support;

import javax.annotation.Resource;

import org.zmsoft.framework.constants.ICPayDataConstants;
import org.zmsoft.framework.pay.ISPayNotice;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 支付框架基类
 */
public class MyOrderSupport extends MyServiceSupport implements ICPayDataConstants {
	//到账通知（业务实现）
	private ISPayNotice myPayNoticeSupport;
	public ISPayNotice getMyPayNoticeSupport() {
		if (EmptyHelper.isEmpty(myPayNoticeSupport)) {
			myPayNoticeSupport = MyBeanFactoryHelper.getBean(ISPayNotice.class);
		}
		return myPayNoticeSupport;
	}
	
	// 支付订单缓存(框架实现)
	@Resource
	protected MyPayingOrderCacheSupport myPayingOrderCacheSupport;

}