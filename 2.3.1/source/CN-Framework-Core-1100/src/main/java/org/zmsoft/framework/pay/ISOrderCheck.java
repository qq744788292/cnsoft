package org.zmsoft.framework.pay;

import org.zmsoft.framework.ObjectBean;

public interface ISOrderCheck {
	
	/**
	 * 订单检查
	 * @param acallBackBean
	 * @return
	 */
	public boolean doUserOrderCheck(ObjectBean acallBackBean);
}
