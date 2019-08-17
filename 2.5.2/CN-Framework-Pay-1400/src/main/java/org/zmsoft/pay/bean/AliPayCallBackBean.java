package org.zmsoft.pay.bean;

import org.zmsoft.config.alipay.ICAliPayConstants;
import org.zmsoft.framework.ObjectBean;

/**
 * 支付宝回调参数
 * 
 * @author Administrator
 *
 */
public class AliPayCallBackBean extends ObjectBean implements ICAliPayConstants {
	// 商户订单号
	private String out_trade_no;
	// 支付宝交易号
	private String trade_no;
	// 交易状态
	private String trade_status;

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

}
