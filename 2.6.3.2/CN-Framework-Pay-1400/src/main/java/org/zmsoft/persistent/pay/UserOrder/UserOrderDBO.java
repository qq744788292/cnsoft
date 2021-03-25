package org.zmsoft.persistent.pay.UserOrder;

import java.math.BigDecimal;
import java.util.List;

import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
import org.zmsoft.persistent.pay.UserOrderGoods.UserOrderGoodsDBO;

/**
 * 订单信息表持久层对象
 */
public class UserOrderDBO extends MyDataBaseObjectSupport3 {
	
	//商品明细
	List<UserOrderGoodsDBO> goods;
	
    public List<UserOrderGoodsDBO> getGoods() {
		return goods;
	}

	public void setGoods(List<UserOrderGoodsDBO> goods) {
		this.goods = goods;
	}

	/////////////////////////////////////////////////////////////////////
	/** 
     * 第三方订单唯一编号
     */
    private String orderNo = null;
 
    /** 
     * 玩家渠道ID
     */
    private String channelId = null;
 
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
     * 设备ID
     */
    private String machineId = null;
 
    /** 
     * 设备类型编码
     */
    private String machineTypeCode = null;
 
    /** 
     * 设备类型说明
     */
    private String machineTypeName = null;
 
    /** 
     * 订单类型编码
     */
    private String orderTypeCode = null;
 
    /** 
     * 订单类型说明
     */
    private String orderTypeeName = null;
 
    /** 
     * 订单日期
     */
    private String orderDate = null;
 
    /** 
     * 订单时间
     */
    private String orderTime = null;
 
    /** 
     * 商品总额
     */
    private BigDecimal goodsCost = null;
 
    /** 
     * 运费
     */
    private BigDecimal transportCost = null;
 
    /** 
     * 订单总额
     */
    private BigDecimal orderTotalCost = null;
 
    /** 
     * 是否有折扣
     */
    private String isDiscount = null;
 
    /** 
     * 返现
     */
    private BigDecimal discountsCost = null;
 
    /** 
     * 优惠券
     */
    private BigDecimal realityCost = null;
 
    /** 
     * 待支付金额
     */
    private BigDecimal payAmount = null;
 
    /** 
     * 是否需要支付(2需要,1不需要)
     */
    private String isRecharge = null;
 
    /** 
     * 支付方式编码(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    private String rechargeTypeCode = null;
 
    /** 
     * 订单支付流水ID
     */
    private String orderRechargeId = null;
 
    /** 
     * 实际支付金额
     */
    private BigDecimal rechargeMoney = null;
 
    /** 
     * 支付状态（1已支付,2未支付）
     */
    private String rechargeStateCode = null;
 
    /** 
     * 到账状态编码（1已到账,2未到账）
     */
    private String rechargeOrderStateCode = null;
 
    /** 
     * 是否需要售后(2需要,1不需要)
     */
    private String isAfterSale = null;
 
    /** 
     * 售后流水ID
     */
    private String orderAftersaleId = null;
 
    /** 
     * 售后状态编码(1正常, 6待出库, 9已完成)
     */
    private String afterSaleStateCode = null;
 
    /** 
     * 是否需要补单(2需要,1不需要)
     */
    private String isSupplement = null;
 
    /** 
     * 补单发货时间
     */
    private String orderSupplementTime = null;
 
    /** 
     * 是否需要快递配送(1:不需要；2：需要)
     */
    private String isExpress = null;
 
    /** 
     * 快递配送流水ID
     */
    private String orderExpressId = null;
 
    /** 
     * 快递单号
     */
    private String expressNo = null;
 
    /** 
     * 快递公司
     */
    private String expressCompany = null;
 
    /** 
     * 订单状态编码(1待付款, 2已取消, 3已付款待处理, 4申请退款, 5退款确认,6已发货, 7物流配送中, 8申请售后, 9售后退款, 10已完成)
     */
    private String orderStatusCode = null;
 
    /** 
     * 订单状态说明
     */
    private String orderStatusName = null;
 
    /** 
     * 业务实现类名
     */
    private String processName = null;
 
    /** 
     * 业务实现方法名称
     */
    private String funcationName = null;
 
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
     //初始化商品总额
       if(this.goodsCost == null)
            this.goodsCost = BigDecimal.ZERO;
     //初始化运费
       if(this.transportCost == null)
            this.transportCost = BigDecimal.ZERO;
     //初始化订单总额
       if(this.orderTotalCost == null)
            this.orderTotalCost = BigDecimal.ZERO;
     //初始化返现
       if(this.discountsCost == null)
            this.discountsCost = BigDecimal.ZERO;
     //初始化优惠券
       if(this.realityCost == null)
            this.realityCost = BigDecimal.ZERO;
     //初始化待支付金额
       if(this.payAmount == null)
            this.payAmount = BigDecimal.ZERO;
     //初始化实际支付金额
       if(this.rechargeMoney == null)
            this.rechargeMoney = BigDecimal.ZERO;
    }
 
    /** 
     * 获取第三方订单唯一编号
     *
     * @return Order_no 第三方订单唯一编号
     */
    public String getOrderNo() {
        return this.orderNo;
    }
 
    /** 
     * 获取玩家渠道ID
     *
     * @return Channel_id 玩家渠道ID
     */
    public String getChannelId() {
        return this.channelId;
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
     * 获取设备ID
     *
     * @return Machine_id 设备ID
     */
    public String getMachineId() {
        return this.machineId;
    }
 
    /** 
     * 获取设备类型编码
     *
     * @return Machine_type_code 设备类型编码
     */
    public String getMachineTypeCode() {
        return this.machineTypeCode;
    }
 
    /** 
     * 获取设备类型说明
     *
     * @return Machine_type_name 设备类型说明
     */
    public String getMachineTypeName() {
        return this.machineTypeName;
    }
 
    /** 
     * 获取订单类型编码
     *
     * @return Order_type_code 订单类型编码
     */
    public String getOrderTypeCode() {
        return this.orderTypeCode;
    }
 
    /** 
     * 获取订单类型说明
     *
     * @return Order_typee_name 订单类型说明
     */
    public String getOrderTypeeName() {
        return this.orderTypeeName;
    }
 
    /** 
     * 获取订单日期
     *
     * @return Order_date 订单日期
     */
    public String getOrderDate() {
        return this.orderDate;
    }
 
    /** 
     * 获取订单时间
     *
     * @return Order_time 订单时间
     */
    public String getOrderTime() {
        return this.orderTime;
    }
 
    /** 
     * 获取商品总额
     *
     * @return Goods_cost 商品总额
     */
    public BigDecimal getGoodsCost() {
        return this.goodsCost;
    }
 
    /** 
     * 获取运费
     *
     * @return Transport_cost 运费
     */
    public BigDecimal getTransportCost() {
        return this.transportCost;
    }
 
    /** 
     * 获取订单总额
     *
     * @return Order_total_cost 订单总额
     */
    public BigDecimal getOrderTotalCost() {
        return this.orderTotalCost;
    }
 
    /** 
     * 获取是否有折扣
     *
     * @return Is_discount 是否有折扣
     */
    public String getIsDiscount() {
        return this.isDiscount;
    }
 
    /** 
     * 获取返现
     *
     * @return Discounts_cost 返现
     */
    public BigDecimal getDiscountsCost() {
        return this.discountsCost;
    }
 
    /** 
     * 获取优惠券
     *
     * @return Reality_cost 优惠券
     */
    public BigDecimal getRealityCost() {
        return this.realityCost;
    }
 
    /** 
     * 获取待支付金额
     *
     * @return Pay_amount 待支付金额
     */
    public BigDecimal getPayAmount() {
        return this.payAmount;
    }
 
    /** 
     * 获取是否需要支付(2需要,1不需要)
     *
     * @return Is_recharge 是否需要支付(2需要,1不需要)
     */
    public String getIsRecharge() {
        return this.isRecharge;
    }
 
    /** 
     * 获取支付方式编码(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     *
     * @return Recharge_type_code 支付方式编码(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    public String getRechargeTypeCode() {
        return this.rechargeTypeCode;
    }
 
    /** 
     * 获取订单支付流水ID
     *
     * @return Order_recharge_id 订单支付流水ID
     */
    public String getOrderRechargeId() {
        return this.orderRechargeId;
    }
 
    /** 
     * 获取实际支付金额
     *
     * @return Recharge_money 实际支付金额
     */
    public BigDecimal getRechargeMoney() {
        return this.rechargeMoney;
    }
 
    /** 
     * 获取支付状态（1已支付,2未支付）
     *
     * @return Recharge_state_code 支付状态（1已支付,2未支付）
     */
    public String getRechargeStateCode() {
        return this.rechargeStateCode;
    }
 
    /** 
     * 获取到账状态编码（1已到账,2未到账）
     *
     * @return Recharge_order_state_code 到账状态编码（1已到账,2未到账）
     */
    public String getRechargeOrderStateCode() {
        return this.rechargeOrderStateCode;
    }
 
    /** 
     * 获取是否需要售后(2需要,1不需要)
     *
     * @return Is_after_sale 是否需要售后(2需要,1不需要)
     */
    public String getIsAfterSale() {
        return this.isAfterSale;
    }
 
    /** 
     * 获取售后流水ID
     *
     * @return Order_aftersale_id 售后流水ID
     */
    public String getOrderAftersaleId() {
        return this.orderAftersaleId;
    }
 
    /** 
     * 获取售后状态编码(1正常, 6待出库, 9已完成)
     *
     * @return After_sale_state_code 售后状态编码(1正常, 6待出库, 9已完成)
     */
    public String getAfterSaleStateCode() {
        return this.afterSaleStateCode;
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
     * 获取是否需要快递配送(1:不需要；2：需要)
     *
     * @return Is_express 是否需要快递配送(1:不需要；2：需要)
     */
    public String getIsExpress() {
        return this.isExpress;
    }
 
    /** 
     * 获取快递配送流水ID
     *
     * @return Order_express_id 快递配送流水ID
     */
    public String getOrderExpressId() {
        return this.orderExpressId;
    }
 
    /** 
     * 获取快递单号
     *
     * @return Express_no 快递单号
     */
    public String getExpressNo() {
        return this.expressNo;
    }
 
    /** 
     * 获取快递公司
     *
     * @return Express_company 快递公司
     */
    public String getExpressCompany() {
        return this.expressCompany;
    }
 
    /** 
     * 获取订单状态编码(1待付款, 2已取消, 3已付款待处理, 4申请退款, 5退款确认,6已发货, 7物流配送中, 8申请售后, 9售后退款, 10已完成)
     *
     * @return Order_status_code 订单状态编码(1待付款, 2已取消, 3已付款待处理, 4申请退款, 5退款确认,6已发货, 7物流配送中, 8申请售后, 9售后退款, 10已完成)
     */
    public String getOrderStatusCode() {
        return this.orderStatusCode;
    }
 
    /** 
     * 获取订单状态说明
     *
     * @return Order_status_name 订单状态说明
     */
    public String getOrderStatusName() {
        return this.orderStatusName;
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
     * 获取业务实现方法名称
     *
     * @return Funcation_name 业务实现方法名称
     */
    public String getFuncationName() {
        return this.funcationName;
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
     * 设置第三方订单唯一编号
     *
     * @param Order_no 第三方订单唯一编号
     */
    public void setOrderNo(String orderno) {
        this.orderNo = orderno;
    }
 
    /** 
     * 设置玩家渠道ID
     *
     * @param Channel_id 玩家渠道ID
     */
    public void setChannelId(String channelid) {
        this.channelId = channelid;
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
     * 设置设备ID
     *
     * @param Machine_id 设备ID
     */
    public void setMachineId(String machineid) {
        this.machineId = machineid;
    }
 
    /** 
     * 设置设备类型编码
     *
     * @param Machine_type_code 设备类型编码
     */
    public void setMachineTypeCode(String machinetypecode) {
        this.machineTypeCode = machinetypecode;
    }
 
    /** 
     * 设置设备类型说明
     *
     * @param Machine_type_name 设备类型说明
     */
    public void setMachineTypeName(String machinetypename) {
        this.machineTypeName = machinetypename;
    }
 
    /** 
     * 设置订单类型编码
     *
     * @param Order_type_code 订单类型编码
     */
    public void setOrderTypeCode(String ordertypecode) {
        this.orderTypeCode = ordertypecode;
    }
 
    /** 
     * 设置订单类型说明
     *
     * @param Order_typee_name 订单类型说明
     */
    public void setOrderTypeeName(String ordertypeename) {
        this.orderTypeeName = ordertypeename;
    }
 
    /** 
     * 设置订单日期
     *
     * @param Order_date 订单日期
     */
    public void setOrderDate(String orderdate) {
        this.orderDate = orderdate;
    }
 
    /** 
     * 设置订单时间
     *
     * @param Order_time 订单时间
     */
    public void setOrderTime(String ordertime) {
        this.orderTime = ordertime;
    }
 
    /** 
     * 设置商品总额
     *
     * @param Goods_cost 商品总额
     */
    public void setGoodsCost(BigDecimal goodscost) {
        this.goodsCost = goodscost;
    }
 
    /** 
     * 设置运费
     *
     * @param Transport_cost 运费
     */
    public void setTransportCost(BigDecimal transportcost) {
        this.transportCost = transportcost;
    }
 
    /** 
     * 设置订单总额
     *
     * @param Order_total_cost 订单总额
     */
    public void setOrderTotalCost(BigDecimal ordertotalcost) {
        this.orderTotalCost = ordertotalcost;
    }
 
    /** 
     * 设置是否有折扣
     *
     * @param Is_discount 是否有折扣
     */
    public void setIsDiscount(String isdiscount) {
        this.isDiscount = isdiscount;
    }
 
    /** 
     * 设置返现
     *
     * @param Discounts_cost 返现
     */
    public void setDiscountsCost(BigDecimal discountscost) {
        this.discountsCost = discountscost;
    }
 
    /** 
     * 设置优惠券
     *
     * @param Reality_cost 优惠券
     */
    public void setRealityCost(BigDecimal realitycost) {
        this.realityCost = realitycost;
    }
 
    /** 
     * 设置待支付金额
     *
     * @param Pay_amount 待支付金额
     */
    public void setPayAmount(BigDecimal payamount) {
        this.payAmount = payamount;
    }
 
    /** 
     * 设置是否需要支付(2需要,1不需要)
     *
     * @param Is_recharge 是否需要支付(2需要,1不需要)
     */
    public void setIsRecharge(String isrecharge) {
        this.isRecharge = isrecharge;
    }
 
    /** 
     * 设置支付方式编码(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     *
     * @param Recharge_type_code 支付方式编码(1:支付宝支付2:微信支付3:支付宝h5支付4:微信扫码支付5:微信公众号支付)
     */
    public void setRechargeTypeCode(String rechargetypecode) {
        this.rechargeTypeCode = rechargetypecode;
    }
 
    /** 
     * 设置订单支付流水ID
     *
     * @param Order_recharge_id 订单支付流水ID
     */
    public void setOrderRechargeId(String orderrechargeid) {
        this.orderRechargeId = orderrechargeid;
    }
 
    /** 
     * 设置实际支付金额
     *
     * @param Recharge_money 实际支付金额
     */
    public void setRechargeMoney(BigDecimal rechargemoney) {
        this.rechargeMoney = rechargemoney;
    }
 
    /** 
     * 设置支付状态（1已支付,2未支付）
     *
     * @param Recharge_state_code 支付状态（1已支付,2未支付）
     */
    public void setRechargeStateCode(String rechargestatecode) {
        this.rechargeStateCode = rechargestatecode;
    }
 
    /** 
     * 设置到账状态编码（1已到账,2未到账）
     *
     * @param Recharge_order_state_code 到账状态编码（1已到账,2未到账）
     */
    public void setRechargeOrderStateCode(String rechargeorderstatecode) {
        this.rechargeOrderStateCode = rechargeorderstatecode;
    }
 
    /** 
     * 设置是否需要售后(2需要,1不需要)
     *
     * @param Is_after_sale 是否需要售后(2需要,1不需要)
     */
    public void setIsAfterSale(String isaftersale) {
        this.isAfterSale = isaftersale;
    }
 
    /** 
     * 设置售后流水ID
     *
     * @param Order_aftersale_id 售后流水ID
     */
    public void setOrderAftersaleId(String orderaftersaleid) {
        this.orderAftersaleId = orderaftersaleid;
    }
 
    /** 
     * 设置售后状态编码(1正常, 6待出库, 9已完成)
     *
     * @param After_sale_state_code 售后状态编码(1正常, 6待出库, 9已完成)
     */
    public void setAfterSaleStateCode(String aftersalestatecode) {
        this.afterSaleStateCode = aftersalestatecode;
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
     * 设置是否需要快递配送(1:不需要；2：需要)
     *
     * @param Is_express 是否需要快递配送(1:不需要；2：需要)
     */
    public void setIsExpress(String isexpress) {
        this.isExpress = isexpress;
    }
 
    /** 
     * 设置快递配送流水ID
     *
     * @param Order_express_id 快递配送流水ID
     */
    public void setOrderExpressId(String orderexpressid) {
        this.orderExpressId = orderexpressid;
    }
 
    /** 
     * 设置快递单号
     *
     * @param Express_no 快递单号
     */
    public void setExpressNo(String expressno) {
        this.expressNo = expressno;
    }
 
    /** 
     * 设置快递公司
     *
     * @param Express_company 快递公司
     */
    public void setExpressCompany(String expresscompany) {
        this.expressCompany = expresscompany;
    }
 
    /** 
     * 设置订单状态编码(1待付款, 2已取消, 3已付款待处理, 4申请退款, 5退款确认,6已发货, 7物流配送中, 8申请售后, 9售后退款, 10已完成)
     *
     * @param Order_status_code 订单状态编码(1待付款, 2已取消, 3已付款待处理, 4申请退款, 5退款确认,6已发货, 7物流配送中, 8申请售后, 9售后退款, 10已完成)
     */
    public void setOrderStatusCode(String orderstatuscode) {
        this.orderStatusCode = orderstatuscode;
    }
 
    /** 
     * 设置订单状态说明
     *
     * @param Order_status_name 订单状态说明
     */
    public void setOrderStatusName(String orderstatusname) {
        this.orderStatusName = orderstatusname;
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
     * 设置业务实现方法名称
     *
     * @param Funcation_name 业务实现方法名称
     */
    public void setFuncationName(String funcationname) {
        this.funcationName = funcationname;
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
