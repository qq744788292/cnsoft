package org.zmsoft.persistent.pay.UserOrderRecharge;

import java.math.BigDecimal;

import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;

/**
 * 玩家订单支付信息持久层对象
 */
public class UserOrderRechargeDBO extends MyDataBaseObjectSupport3 {

    /** 
     * 玩家订单ID
     */
    private String orderId = null;
 
    /** 
     * 玩家ID
     */
    private String userId = null;
 
    /** 
     * 玩家昵称
     */
    private String userNickname = null;
 
    /** 
     * 玩家手机号
     */
    private String userPhone = null;
 
    /** 
     * 订单支付类别编码(1充值2购买)
     */
    private String payReasonCode = null;
 
    /** 
     * 订单支付类别说明
     */
    private String payReasonName = null;
 
    /** 
     * 第三方唯一标识
     */
    private String openId = null;
 
    /** 
     * 订单校验码
     */
    private String nonceStr = null;
 
    /** 
     * 订单描述
     */
    private String body = null;
 
    /** 
     * 商户支付订单ID（UUID）
     */
    private String payTradeNo = null;
 
    /** 
     * 支付方式编码(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    private String payTypeCode = null;
 
    /** 
     * 支付方式说明(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    private String payTypeName = null;
 
    /** 
     * 支付账户ID
     */
    private String payAccount = null;
 
    /** 
     * 支付账号人姓名
     */
    private String payAccountName = null;
 
    /** 
     * 支付账号手机号
     */
    private String payAccountPhone = null;
 
    /** 
     * 支付账号绑定验证码
     */
    private String paySecCode = null;
 
    /** 
     * 支付金额
     */
    private BigDecimal payAmount = null;
 
    /** 
     * 支付状态编码（1已支付,2未支付）
     */
    private String payStateCode = null;
 
    /** 
     * 支付状态说明
     */
    private String payStateName = null;
 
    /** 
     * 第三方支付流水号
     */
    private String payNumber = null;
 
    /** 
     * 支付日期
     */
    private String payDate = null;
 
    /** 
     * 支付时间
     */
    private String payTime = null;
 
    /** 
     * 到账状态编码（1已到账,2未到账）
     */
    private String orderStateCode = null;
 
    /** 
     * 到账状态说明
     */
    private String orderStateName = null;
 
    /** 
     * 到账时间
     */
    private String orderTime = null;
 
    /** 
     * 是否需要补单(2需要,1不需要)
     */
    private String isSupplement = null;
 
    /** 
     * 补单发货时间
     */
    private String orderSupplementTime = null;
 
    /** 
     * 到账后业务实现类名
     */
    private String processName = null;
 
    /** 
     * 到账后业务实现方法名称
     */
    private String funcationName = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
     //初始化支付金额
       if(this.payAmount == null)
            this.payAmount = BigDecimal.ZERO;
    }
 
    /** 
     * 获取玩家订单ID
     *
     * @return Order_id 玩家订单ID
     */
    public String getOrderId() {
        return this.orderId;
    }
 
    /** 
     * 获取玩家ID
     *
     * @return User_id 玩家ID
     */
    public String getUserId() {
        return this.userId;
    }
 
    /** 
     * 获取玩家昵称
     *
     * @return User_nickname 玩家昵称
     */
    public String getUserNickname() {
        return this.userNickname;
    }
 
    /** 
     * 获取玩家手机号
     *
     * @return User_phone 玩家手机号
     */
    public String getUserPhone() {
        return this.userPhone;
    }
 
    /** 
     * 获取订单支付类别编码(1充值2购买)
     *
     * @return Pay_reason_code 订单支付类别编码(1充值2购买)
     */
    public String getPayReasonCode() {
        return this.payReasonCode;
    }
 
    /** 
     * 获取订单支付类别说明
     *
     * @return Pay_reason_name 订单支付类别说明
     */
    public String getPayReasonName() {
        return this.payReasonName;
    }
 
    /** 
     * 获取第三方唯一标识
     *
     * @return Open_id 第三方唯一标识
     */
    public String getOpenId() {
        return this.openId;
    }
 
    /** 
     * 获取订单校验码
     *
     * @return Nonce_str 订单校验码
     */
    public String getNonceStr() {
        return this.nonceStr;
    }
 
    /** 
     * 获取订单描述
     *
     * @return Body 订单描述
     */
    public String getBody() {
        return this.body;
    }
 
    /** 
     * 获取商户支付订单ID（UUID）
     *
     * @return Pay_trade_no 商户支付订单ID（UUID）
     */
    public String getPayTradeNo() {
        return this.payTradeNo;
    }
 
    /** 
     * 获取支付方式编码(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     *
     * @return Pay_type_code 支付方式编码(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    public String getPayTypeCode() {
        return this.payTypeCode;
    }
 
    /** 
     * 获取支付方式说明(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     *
     * @return Pay_type_name 支付方式说明(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    public String getPayTypeName() {
        return this.payTypeName;
    }
 
    /** 
     * 获取支付账户ID
     *
     * @return Pay_account 支付账户ID
     */
    public String getPayAccount() {
        return this.payAccount;
    }
 
    /** 
     * 获取支付账号人姓名
     *
     * @return Pay_account_name 支付账号人姓名
     */
    public String getPayAccountName() {
        return this.payAccountName;
    }
 
    /** 
     * 获取支付账号手机号
     *
     * @return Pay_account_phone 支付账号手机号
     */
    public String getPayAccountPhone() {
        return this.payAccountPhone;
    }
 
    /** 
     * 获取支付账号绑定验证码
     *
     * @return Pay_sec_code 支付账号绑定验证码
     */
    public String getPaySecCode() {
        return this.paySecCode;
    }
 
    /** 
     * 获取支付金额
     *
     * @return Pay_amount 支付金额
     */
    public BigDecimal getPayAmount() {
        return this.payAmount;
    }
 
    /** 
     * 获取支付状态编码（1已支付,2未支付）
     *
     * @return Pay_state_code 支付状态编码（1已支付,2未支付）
     */
    public String getPayStateCode() {
        return this.payStateCode;
    }
 
    /** 
     * 获取支付状态说明
     *
     * @return Pay_state_name 支付状态说明
     */
    public String getPayStateName() {
        return this.payStateName;
    }
 
    /** 
     * 获取第三方支付流水号
     *
     * @return Pay_number 第三方支付流水号
     */
    public String getPayNumber() {
        return this.payNumber;
    }
 
    /** 
     * 获取支付日期
     *
     * @return Pay_date 支付日期
     */
    public String getPayDate() {
        return this.payDate;
    }
 
    /** 
     * 获取支付时间
     *
     * @return Pay_time 支付时间
     */
    public String getPayTime() {
        return this.payTime;
    }
 
    /** 
     * 获取到账状态编码（1已到账,2未到账）
     *
     * @return Order_state_code 到账状态编码（1已到账,2未到账）
     */
    public String getOrderStateCode() {
        return this.orderStateCode;
    }
 
    /** 
     * 获取到账状态说明
     *
     * @return Order_state_name 到账状态说明
     */
    public String getOrderStateName() {
        return this.orderStateName;
    }
 
    /** 
     * 获取到账时间
     *
     * @return Order_time 到账时间
     */
    public String getOrderTime() {
        return this.orderTime;
    }
 
    /** 
     * 获取是否需要补单(2需要,1不需要)
     *
     * @return Is_supplement 是否需要补单(2需要,1不需要)
     */
    public String getIsSupplement() {
        return this.isSupplement;
    }
 
    /** 
     * 获取补单发货时间
     *
     * @return Order_supplement_time 补单发货时间
     */
    public String getOrderSupplementTime() {
        return this.orderSupplementTime;
    }
 
    /** 
     * 获取到账后业务实现类名
     *
     * @return Process_name 到账后业务实现类名
     */
    public String getProcessName() {
        return this.processName;
    }
 
    /** 
     * 获取到账后业务实现方法名称
     *
     * @return Funcation_name 到账后业务实现方法名称
     */
    public String getFuncationName() {
        return this.funcationName;
    }
 
    /** 
     * 设置玩家订单ID
     *
     * @param Order_id 玩家订单ID
     */
    public void setOrderId(String orderid) {
        this.orderId = orderid;
    }
 
    /** 
     * 设置玩家ID
     *
     * @param User_id 玩家ID
     */
    public void setUserId(String userid) {
        this.userId = userid;
    }
 
    /** 
     * 设置玩家昵称
     *
     * @param User_nickname 玩家昵称
     */
    public void setUserNickname(String usernickname) {
        this.userNickname = usernickname;
    }
 
    /** 
     * 设置玩家手机号
     *
     * @param User_phone 玩家手机号
     */
    public void setUserPhone(String userphone) {
        this.userPhone = userphone;
    }
 
    /** 
     * 设置订单支付类别编码(1充值2购买)
     *
     * @param Pay_reason_code 订单支付类别编码(1充值2购买)
     */
    public void setPayReasonCode(String payreasoncode) {
        this.payReasonCode = payreasoncode;
    }
 
    /** 
     * 设置订单支付类别说明
     *
     * @param Pay_reason_name 订单支付类别说明
     */
    public void setPayReasonName(String payreasonname) {
        this.payReasonName = payreasonname;
    }
 
    /** 
     * 设置第三方唯一标识
     *
     * @param Open_id 第三方唯一标识
     */
    public void setOpenId(String openid) {
        this.openId = openid;
    }
 
    /** 
     * 设置订单校验码
     *
     * @param Nonce_str 订单校验码
     */
    public void setNonceStr(String noncestr) {
        this.nonceStr = noncestr;
    }
 
    /** 
     * 设置订单描述
     *
     * @param Body 订单描述
     */
    public void setBody(String body) {
        this.body = body;
    }
 
    /** 
     * 设置商户支付订单ID（UUID）
     *
     * @param Pay_trade_no 商户支付订单ID（UUID）
     */
    public void setPayTradeNo(String paytradeno) {
        this.payTradeNo = paytradeno;
    }
 
    /** 
     * 设置支付方式编码(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     *
     * @param Pay_type_code 支付方式编码(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    public void setPayTypeCode(String paytypecode) {
        this.payTypeCode = paytypecode;
    }
 
    /** 
     * 设置支付方式说明(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     *
     * @param Pay_type_name 支付方式说明(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    public void setPayTypeName(String paytypename) {
        this.payTypeName = paytypename;
    }
 
    /** 
     * 设置支付账户ID
     *
     * @param Pay_account 支付账户ID
     */
    public void setPayAccount(String payaccount) {
        this.payAccount = payaccount;
    }
 
    /** 
     * 设置支付账号人姓名
     *
     * @param Pay_account_name 支付账号人姓名
     */
    public void setPayAccountName(String payaccountname) {
        this.payAccountName = payaccountname;
    }
 
    /** 
     * 设置支付账号手机号
     *
     * @param Pay_account_phone 支付账号手机号
     */
    public void setPayAccountPhone(String payaccountphone) {
        this.payAccountPhone = payaccountphone;
    }
 
    /** 
     * 设置支付账号绑定验证码
     *
     * @param Pay_sec_code 支付账号绑定验证码
     */
    public void setPaySecCode(String payseccode) {
        this.paySecCode = payseccode;
    }
 
    /** 
     * 设置支付金额
     *
     * @param Pay_amount 支付金额
     */
    public void setPayAmount(BigDecimal payamount) {
        this.payAmount = payamount;
    }
 
    /** 
     * 设置支付状态编码（1已支付,2未支付）
     *
     * @param Pay_state_code 支付状态编码（1已支付,2未支付）
     */
    public void setPayStateCode(String paystatecode) {
        this.payStateCode = paystatecode;
    }
 
    /** 
     * 设置支付状态说明
     *
     * @param Pay_state_name 支付状态说明
     */
    public void setPayStateName(String paystatename) {
        this.payStateName = paystatename;
    }
 
    /** 
     * 设置第三方支付流水号
     *
     * @param Pay_number 第三方支付流水号
     */
    public void setPayNumber(String paynumber) {
        this.payNumber = paynumber;
    }
 
    /** 
     * 设置支付日期
     *
     * @param Pay_date 支付日期
     */
    public void setPayDate(String paydate) {
        this.payDate = paydate;
    }
 
    /** 
     * 设置支付时间
     *
     * @param Pay_time 支付时间
     */
    public void setPayTime(String paytime) {
        this.payTime = paytime;
    }
 
    /** 
     * 设置到账状态编码（1已到账,2未到账）
     *
     * @param Order_state_code 到账状态编码（1已到账,2未到账）
     */
    public void setOrderStateCode(String orderstatecode) {
        this.orderStateCode = orderstatecode;
    }
 
    /** 
     * 设置到账状态说明
     *
     * @param Order_state_name 到账状态说明
     */
    public void setOrderStateName(String orderstatename) {
        this.orderStateName = orderstatename;
    }
 
    /** 
     * 设置到账时间
     *
     * @param Order_time 到账时间
     */
    public void setOrderTime(String ordertime) {
        this.orderTime = ordertime;
    }
 
    /** 
     * 设置是否需要补单(2需要,1不需要)
     *
     * @param Is_supplement 是否需要补单(2需要,1不需要)
     */
    public void setIsSupplement(String issupplement) {
        this.isSupplement = issupplement;
    }
 
    /** 
     * 设置补单发货时间
     *
     * @param Order_supplement_time 补单发货时间
     */
    public void setOrderSupplementTime(String ordersupplementtime) {
        this.orderSupplementTime = ordersupplementtime;
    }
 
    /** 
     * 设置到账后业务实现类名
     *
     * @param Process_name 到账后业务实现类名
     */
    public void setProcessName(String processname) {
        this.processName = processname;
    }
 
    /** 
     * 设置到账后业务实现方法名称
     *
     * @param Funcation_name 到账后业务实现方法名称
     */
    public void setFuncationName(String funcationname) {
        this.funcationName = funcationname;
    }
 

}
