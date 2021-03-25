package org.zmsoft.config.order;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;
import org.zmsoft.framework.common.ISConfig;

/**
 * 订单系统配置
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <MyBizLogOperatorSupport>
 */
@Service("OrderConfigService")
public class OrderConfigService extends AConfigSupport implements ISConfig {

	private final static String TYPE = "order.config";

	@Override
	public String loadType() {
		return TYPE;
	}

	/**
	 * 根据业务需求，决定是否从数据库加载
	 */
	private String loadDB;
	
	/**
	 * 订单缓存时间
	 */
	private String orderTimeMinute;

	public String getLoadDB() {
		return loadDB;
	}

	public void setLoadDB(String loaddb) {
		this.loadDB = loaddb;
	}

	public String getOrderTimeMinute() {
		return orderTimeMinute;
	}

	public void setOrderTimeMinute(String ordertimeminute) {
		this.orderTimeMinute = ordertimeminute;
	}
}