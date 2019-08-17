package org.zmsoft.framework.beans;
import java.math.BigDecimal;
import java.sql.Date;

import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
 
/** 会员充值记录*/
public class PayOrderBean extends MyDataBaseObjectSupport3
{
    /** 
     * 商户订单号
     */
    private String payTradeNo = null;
 
    /** 
     * 渠道ID
     */
    private String channelId = null;
 
    /** 
     * 会员ID
     */
    private String userId = null;
 
    /** 
     * 会员昵称
     */
    private String userNickname = null;
 
    /** 
     * 会员手机号
     */
    private String userPhone = null;
 
    /** 
     * 支付方式(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    private String payType = null;
 
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
     * 支付状态
     */
    private String payState = null;
 
    /** 
     * 支付时间
     */
    private Date payTime = null;
 
    /** 
     * 到账状态
     */
    private String orderState = null;
 
    /** 
     * 到账时间
     */
    private Date orderTime = null;
 
    /** 
     * 到账类别
     */
    private String orderType = null;
 
    /** 
     * 业务实现类名
     */
    private String processName = null;
 
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
     * 获取商户订单号
     *
     * @return Pay_trade_no 商户订单号
     */
    public String getPayTradeNo() {
        return this.payTradeNo;
    }
 
    /** 
     * 获取渠道ID
     *
     * @return Channel_id 渠道ID
     */
    public String getChannelId() {
        return this.channelId;
    }
 
    /** 
     * 获取会员ID
     *
     * @return User_id 会员ID
     */
    public String getUserId() {
        return this.userId;
    }
 
    /** 
     * 获取会员昵称
     *
     * @return User_nickname 会员昵称
     */
    public String getUserNickname() {
        return this.userNickname;
    }
 
    /** 
     * 获取会员手机号
     *
     * @return User_phone 会员手机号
     */
    public String getUserPhone() {
        return this.userPhone;
    }
 
    /** 
     * 获取支付方式(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     *
     * @return Pay_type 支付方式(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    public String getPayType() {
        return this.payType;
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
     * 获取支付状态
     *
     * @return Pay_state 支付状态
     */
    public String getPayState() {
        return this.payState;
    }
 
    /** 
     * 获取支付时间
     *
     * @return Pay_time 支付时间
     */
    public Date getPayTime() {
        return this.payTime;
    }
 
    /** 
     * 获取到账状态
     *
     * @return Order_state 到账状态
     */
    public String getOrderState() {
        return this.orderState;
    }
 
    /** 
     * 获取到账时间
     *
     * @return Order_time 到账时间
     */
    public Date getOrderTime() {
        return this.orderTime;
    }
 
    /** 
     * 获取到账类别
     *
     * @return Order_type 到账类别
     */
    public String getOrderType() {
        return this.orderType;
    }
 
    /** 
     * 获取业务实现类名
     *
     * @return Process_name 业务实现类名
     */
    public String getProcessName() {
        return this.processName;
    }
 
    /** 
     * 设置商户订单号
     *
     * @param Pay_trade_no 商户订单号
     */
    public void setPayTradeNo(String paytradeno) {
        this.payTradeNo = paytradeno;
    }
 
    /** 
     * 设置渠道ID
     *
     * @param Channel_id 渠道ID
     */
    public void setChannelId(String channelid) {
        this.channelId = channelid;
    }
 
    /** 
     * 设置会员ID
     *
     * @param User_id 会员ID
     */
    public void setUserId(String userid) {
        this.userId = userid;
    }
 
    /** 
     * 设置会员昵称
     *
     * @param User_nickname 会员昵称
     */
    public void setUserNickname(String usernickname) {
        this.userNickname = usernickname;
    }
 
    /** 
     * 设置会员手机号
     *
     * @param User_phone 会员手机号
     */
    public void setUserPhone(String userphone) {
        this.userPhone = userphone;
    }
 
    /** 
     * 设置支付方式(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     *
     * @param Pay_type 支付方式(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    public void setPayType(String paytype) {
        this.payType = paytype;
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
     * 设置支付状态
     *
     * @param Pay_state 支付状态
     */
    public void setPayState(String paystate) {
        this.payState = paystate;
    }
 
    /** 
     * 设置支付时间
     *
     * @param Pay_time 支付时间
     */
    public void setPayTime(Date paytime) {
        this.payTime = paytime;
    }
 
    /** 
     * 设置到账状态
     *
     * @param Order_state 到账状态
     */
    public void setOrderState(String orderstate) {
        this.orderState = orderstate;
    }
 
    /** 
     * 设置到账时间
     *
     * @param Order_time 到账时间
     */
    public void setOrderTime(Date ordertime) {
        this.orderTime = ordertime;
    }
 
    /** 
     * 设置到账类别
     *
     * @param Order_type 到账类别
     */
    public void setOrderType(String ordertype) {
        this.orderType = ordertype;
    }
 
    /** 
     * 设置业务实现类名
     *
     * @param Process_name 业务实现类名
     */
    public void setProcessName(String processname) {
        this.processName = processname;
    }
 
}
