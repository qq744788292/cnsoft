package org.zmsoft.pay;

import org.zmsoft.framework.beans.PayOrderBean;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.pay.ISPayNotice;

/**
 * 业务自定义支付到账通知处理业务
 */
//@Service("MyPayNoticeService")
public class APayNoticeService implements ISPayNotice {

	@Override
	public RESTResultBean<PayOrderBean> channelNoticeProcess(PayOrderBean order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RESTResultBean<PayOrderBean> noticeAfterProcess(PayOrderBean order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
