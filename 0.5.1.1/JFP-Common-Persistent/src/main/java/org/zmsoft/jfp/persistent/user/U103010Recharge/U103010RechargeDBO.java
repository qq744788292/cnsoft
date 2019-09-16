package org.zmsoft.jfp.persistent.user.U103010Recharge;

import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;

import java.math.BigDecimal;
import java.sql.Date;
 
/** 订单管理*/
public class U103010RechargeDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 订单号
     */
    private String orderNumber = null;
 
    /** 
     * 素材ID
     */
    private String materialId = null;
 
    /** 
     * 素材分类
     */
    private String materialClassify = null;
 
    /** 
     * 素材类型
     */
    private String materialTpe = null;
 
    /** 
     * 玩家流水ID
     */
    private String userId = null;
 
    /** 
     * 用户当前IP
     */
    private String userIp = null;
 
    /** 
     * OpenID
     */
    private String openid = null;
 
    /** 
     * 手机号码
     */
    private String userPhone = null;
 
    /** 
     * 支付账户类别
     */
    private String payType = null;
 
    /** 
     * 充值金额
     */
    private BigDecimal rechargeMoney = null;
 
    /** 
     * 充值状态
     */
    private String rechargeState = null;
 
    /** 
     * 订单状态
     */
    private String orderState = null;
 
    /** 
     * 补单状态
     */
    private String supplementState = null;
 
    /** 
     * 业务实现类名
     */
    private String processName = null;
 
    /** 
     * 订单校验码
     */
    private String nonceStr = null;
 
    /** 
     * 订单描述
     */
    private String body = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
     //初始化充值金额
       if(this.rechargeMoney == null)
            this.rechargeMoney = BigDecimal.ZERO;
    }
 
    /** 
     * 获取订单号
     *
     * @return Order_number 订单号
     */
    public String getOrderNumber() {
        return this.orderNumber;
    }
 
    /** 
     * 获取素材ID
     *
     * @return Material_id 素材ID
     */
    public String getMaterialId() {
        return this.materialId;
    }
 
    /** 
     * 获取素材分类
     *
     * @return Material_classify 素材分类
     */
    public String getMaterialClassify() {
        return this.materialClassify;
    }
 
    /** 
     * 获取素材类型
     *
     * @return Material_tpe 素材类型
     */
    public String getMaterialTpe() {
        return this.materialTpe;
    }
 
    /** 
     * 获取玩家流水ID
     *
     * @return User_id 玩家流水ID
     */
    public String getUserId() {
        return this.userId;
    }
 
    /** 
     * 获取用户当前IP
     *
     * @return User_ip 用户当前IP
     */
    public String getUserIp() {
        return this.userIp;
    }
 
    /** 
     * 获取OpenID
     *
     * @return OpenID OpenID
     */
    public String getOpenid() {
        return this.openid;
    }
 
    /** 
     * 获取手机号码
     *
     * @return User_phone 手机号码
     */
    public String getUserPhone() {
        return this.userPhone;
    }
 
    /** 
     * 获取支付账户类别
     *
     * @return Pay_type 支付账户类别
     */
    public String getPayType() {
        return this.payType;
    }
 
    /** 
     * 获取充值金额
     *
     * @return Recharge_money 充值金额
     */
    public BigDecimal getRechargeMoney() {
        return this.rechargeMoney;
    }
 
    /** 
     * 获取充值状态
     *
     * @return Recharge_state 充值状态
     */
    public String getRechargeState() {
        return this.rechargeState;
    }
 
    /** 
     * 获取订单状态
     *
     * @return Order_state 订单状态
     */
    public String getOrderState() {
        return this.orderState;
    }
 
    /** 
     * 获取补单状态
     *
     * @return Supplement_state 补单状态
     */
    public String getSupplementState() {
        return this.supplementState;
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
     * 设置订单号
     *
     * @param Order_number 订单号
     */
    public void setOrderNumber(String ordernumber) {
        this.orderNumber = ordernumber;
    }
 
    /** 
     * 设置素材ID
     *
     * @param Material_id 素材ID
     */
    public void setMaterialId(String materialid) {
        this.materialId = materialid;
    }
 
    /** 
     * 设置素材分类
     *
     * @param Material_classify 素材分类
     */
    public void setMaterialClassify(String materialclassify) {
        this.materialClassify = materialclassify;
    }
 
    /** 
     * 设置素材类型
     *
     * @param Material_tpe 素材类型
     */
    public void setMaterialTpe(String materialtpe) {
        this.materialTpe = materialtpe;
    }
 
    /** 
     * 设置玩家流水ID
     *
     * @param User_id 玩家流水ID
     */
    public void setUserId(String userid) {
        this.userId = userid;
    }
 
    /** 
     * 设置用户当前IP
     *
     * @param User_ip 用户当前IP
     */
    public void setUserIp(String userip) {
        this.userIp = userip;
    }
 
    /** 
     * 设置OpenID
     *
     * @param OpenID OpenID
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }
 
    /** 
     * 设置手机号码
     *
     * @param User_phone 手机号码
     */
    public void setUserPhone(String userphone) {
        this.userPhone = userphone;
    }
 
    /** 
     * 设置支付账户类别
     *
     * @param Pay_type 支付账户类别
     */
    public void setPayType(String paytype) {
        this.payType = paytype;
    }
 
    /** 
     * 设置充值金额
     *
     * @param Recharge_money 充值金额
     */
    public void setRechargeMoney(BigDecimal rechargemoney) {
        this.rechargeMoney = rechargemoney;
    }
 
    /** 
     * 设置充值状态
     *
     * @param Recharge_state 充值状态
     */
    public void setRechargeState(String rechargestate) {
        this.rechargeState = rechargestate;
    }
 
    /** 
     * 设置订单状态
     *
     * @param Order_state 订单状态
     */
    public void setOrderState(String orderstate) {
        this.orderState = orderstate;
    }
 
    /** 
     * 设置补单状态
     *
     * @param Supplement_state 补单状态
     */
    public void setSupplementState(String supplementstate) {
        this.supplementState = supplementstate;
    }
 
    /** 
     * 设置业务实现类名
     *
     * @param Process_name 业务实现类名
     */
    public void setProcessName(String processname) {
        this.processName = processname;
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
 
}
