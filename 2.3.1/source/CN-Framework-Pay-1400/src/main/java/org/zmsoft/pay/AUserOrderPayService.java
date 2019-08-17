package org.zmsoft.pay;

import org.zmsoft.framework.constants.ICPayDataConstants;
import org.zmsoft.framework.pay.ISOrderForm;
import org.zmsoft.framework.pay.ISPayNotice;
import org.zmsoft.framework.support.MyServiceSupport;

/**
 * 用户下单与支付和到账
 */
public abstract class AUserOrderPayService extends MyServiceSupport implements ICPayDataConstants, ISOrderForm, ISPayNotice {

}
