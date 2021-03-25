package org.zmsoft.order.beans.goods;

import java.math.BigDecimal;

import org.zmsoft.framework.beans.common.FrameworkDataBean;

/**
 * 订单商品详情表持久层对象
 */
public class UserOrderGoodsBean extends FrameworkDataBean {
	/**
	 * 产品ID
	 */
	private String goodsId = null;

	/**
	 * 商品发布ID
	 */
	private String goodsPublishId = null;

	/**
	 * 商品发布规格ID
	 */
	private String goodsPublishSpecsId = null;

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
	 * 数值型变量初始化<br>
	 * 仅在插入场合默认调用
	 * 
	 * @see #loadDefauft()
	 */
	public void prepareNumeric() {
		// 初始化市场价格(标价)
		if (this.marketPrice == null)
			this.marketPrice = BigDecimal.ZERO;
		// 初始化商品价格(卖价)
		if (this.goodsPrice == null)
			this.goodsPrice = BigDecimal.ZERO;
		// 初始化购买数量
		if (this.buyNum == null)
			this.buyNum = 0L;
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
	 * 获取商品发布ID
	 *
	 * @return Goods_publish_id 商品发布ID
	 */
	public String getGoodsPublishId() {
		return this.goodsPublishId;
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
	 * 设置产品ID
	 *
	 * @param Goods_id
	 *            产品ID
	 */
	public void setGoodsId(String goodsid) {
		this.goodsId = goodsid;
	}

	/**
	 * 设置商品发布ID
	 *
	 * @param Goods_publish_id
	 *            商品发布ID
	 */
	public void setGoodsPublishId(String goodspublishid) {
		this.goodsPublishId = goodspublishid;
	}

	/**
	 * 设置商品发布规格ID
	 *
	 * @param Goods_publish_specs_id
	 *            商品发布规格ID
	 */
	public void setGoodsPublishSpecsId(String goodspublishspecsid) {
		this.goodsPublishSpecsId = goodspublishspecsid;
	}

	/**
	 * 设置市场价格(标价)
	 *
	 * @param Market_price
	 *            市场价格(标价)
	 */
	public void setMarketPrice(BigDecimal marketprice) {
		this.marketPrice = marketprice;
	}

	/**
	 * 设置商品价格(卖价)
	 *
	 * @param Goods_price
	 *            商品价格(卖价)
	 */
	public void setGoodsPrice(BigDecimal goodsprice) {
		this.goodsPrice = goodsprice;
	}

	/**
	 * 设置购买数量
	 *
	 * @param Buy_num
	 *            购买数量
	 */
	public void setBuyNum(Long buynum) {
		this.buyNum = buynum;
	}
}
