package org.zmsoft.persistent.pay.UserOrderGoods;

import java.math.BigDecimal;

import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;

/**
 * 订单商品详情表持久层对象
 */
public class UserOrderGoodsDBO extends MyDataBaseObjectSupport3 {

    /** 
     * 玩家订单ID
     */
    private String orderId = null;
 
    /** 
     * 商户ID
     */
    private String merchantId = null;
 
    /** 
     * 商户名称
     */
    private String merchantName = null;
 
    /** 
     * 产品ID
     */
    private String goodsId = null;
 
    /** 
     * 产品名称
     */
    private String goodsName = null;
 
    /** 
     * 商品发布ID
     */
    private String goodsPublishId = null;
 
    /** 
     * 商品发布名称
     */
    private String goodsPublishName = null;
 
    /** 
     * 商品发布规格ID
     */
    private String goodsPublishSpecsId = null;
 
    /** 
     * 商品发布规格名称
     */
    private String goodsPublishSpecsName = null;
 
    /** 
     * 市场价格(标价)
     */
    private BigDecimal marketPrice = null;
 
    /** 
     * 商品价格(卖价)
     */
    private BigDecimal goodsPrice = null;
 
    /** 
     * 购买数量
     */
    private Long buyNum = null;
 
    /** 
     * 商品状态编码(1待发货2已发货3缺货取消9退货)
     */
    private String goodsStatusCode = null;
 
    /** 
     * 商品状态说明
     */
    private String goodsStatusName = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
     //初始化市场价格(标价)
       if(this.marketPrice == null)
            this.marketPrice = BigDecimal.ZERO;
     //初始化商品价格(卖价)
       if(this.goodsPrice == null)
            this.goodsPrice = BigDecimal.ZERO;
       //初始化购买数量
       if(this.buyNum == null)
            this.buyNum = 0L;
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
     * 获取商户ID
     *
     * @return Merchant_id 商户ID
     */
    public String getMerchantId() {
        return this.merchantId;
    }
 
    /** 
     * 获取商户名称
     *
     * @return Merchant_name 商户名称
     */
    public String getMerchantName() {
        return this.merchantName;
    }
 
    /** 
     * 获取产品ID
     *
     * @return Goods_id 产品ID
     */
    public String getGoodsId() {
        return this.goodsId;
    }
 
    /** 
     * 获取产品名称
     *
     * @return Goods_name 产品名称
     */
    public String getGoodsName() {
        return this.goodsName;
    }
 
    /** 
     * 获取商品发布ID
     *
     * @return Goods_publish_id 商品发布ID
     */
    public String getGoodsPublishId() {
        return this.goodsPublishId;
    }
 
    /** 
     * 获取商品发布名称
     *
     * @return Goods_publish_name 商品发布名称
     */
    public String getGoodsPublishName() {
        return this.goodsPublishName;
    }
 
    /** 
     * 获取商品发布规格ID
     *
     * @return Goods_publish_specs_id 商品发布规格ID
     */
    public String getGoodsPublishSpecsId() {
        return this.goodsPublishSpecsId;
    }
 
    /** 
     * 获取商品发布规格名称
     *
     * @return Goods_publish_specs_name 商品发布规格名称
     */
    public String getGoodsPublishSpecsName() {
        return this.goodsPublishSpecsName;
    }
 
    /** 
     * 获取市场价格(标价)
     *
     * @return Market_price 市场价格(标价)
     */
    public BigDecimal getMarketPrice() {
        return this.marketPrice;
    }
 
    /** 
     * 获取商品价格(卖价)
     *
     * @return Goods_price 商品价格(卖价)
     */
    public BigDecimal getGoodsPrice() {
        return this.goodsPrice;
    }
 
    /** 
     * 获取购买数量
     *
     * @return Buy_num 购买数量
     */
    public Long getBuyNum() {
        return this.buyNum;
    }
 
    /** 
     * 获取商品状态编码(1待发货2已发货3缺货取消9退货)
     *
     * @return Goods_status_code 商品状态编码(1待发货2已发货3缺货取消9退货)
     */
    public String getGoodsStatusCode() {
        return this.goodsStatusCode;
    }
 
    /** 
     * 获取商品状态说明
     *
     * @return Goods_status_name 商品状态说明
     */
    public String getGoodsStatusName() {
        return this.goodsStatusName;
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
     * 设置商户ID
     *
     * @param Merchant_id 商户ID
     */
    public void setMerchantId(String merchantid) {
        this.merchantId = merchantid;
    }
 
    /** 
     * 设置商户名称
     *
     * @param Merchant_name 商户名称
     */
    public void setMerchantName(String merchantname) {
        this.merchantName = merchantname;
    }
 
    /** 
     * 设置产品ID
     *
     * @param Goods_id 产品ID
     */
    public void setGoodsId(String goodsid) {
        this.goodsId = goodsid;
    }
 
    /** 
     * 设置产品名称
     *
     * @param Goods_name 产品名称
     */
    public void setGoodsName(String goodsname) {
        this.goodsName = goodsname;
    }
 
    /** 
     * 设置商品发布ID
     *
     * @param Goods_publish_id 商品发布ID
     */
    public void setGoodsPublishId(String goodspublishid) {
        this.goodsPublishId = goodspublishid;
    }
 
    /** 
     * 设置商品发布名称
     *
     * @param Goods_publish_name 商品发布名称
     */
    public void setGoodsPublishName(String goodspublishname) {
        this.goodsPublishName = goodspublishname;
    }
 
    /** 
     * 设置商品发布规格ID
     *
     * @param Goods_publish_specs_id 商品发布规格ID
     */
    public void setGoodsPublishSpecsId(String goodspublishspecsid) {
        this.goodsPublishSpecsId = goodspublishspecsid;
    }
 
    /** 
     * 设置商品发布规格名称
     *
     * @param Goods_publish_specs_name 商品发布规格名称
     */
    public void setGoodsPublishSpecsName(String goodspublishspecsname) {
        this.goodsPublishSpecsName = goodspublishspecsname;
    }
 
    /** 
     * 设置市场价格(标价)
     *
     * @param Market_price 市场价格(标价)
     */
    public void setMarketPrice(BigDecimal marketprice) {
        this.marketPrice = marketprice;
    }
 
    /** 
     * 设置商品价格(卖价)
     *
     * @param Goods_price 商品价格(卖价)
     */
    public void setGoodsPrice(BigDecimal goodsprice) {
        this.goodsPrice = goodsprice;
    }
 
    /** 
     * 设置购买数量
     *
     * @param Buy_num 购买数量
     */
    public void setBuyNum(Long buynum) {
        this.buyNum = buynum;
    }
 
    /** 
     * 设置商品状态编码(1待发货2已发货3缺货取消9退货)
     *
     * @param Goods_status_code 商品状态编码(1待发货2已发货3缺货取消9退货)
     */
    public void setGoodsStatusCode(String goodsstatuscode) {
        this.goodsStatusCode = goodsstatuscode;
    }
 
    /** 
     * 设置商品状态说明
     *
     * @param Goods_status_name 商品状态说明
     */
    public void setGoodsStatusName(String goodsstatusname) {
        this.goodsStatusName = goodsstatusname;
    }
 
}
