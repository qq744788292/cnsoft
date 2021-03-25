package org.zmsoft.config.wx;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;
import org.zmsoft.framework.common.ISConfig;

/**
 * 微信菜单配置
 */
@Service("WxMenuConfigService")
public class WxMenuConfigService extends AConfigSupport implements ISConfig {

	private final static String TYPE = "wx.menu";

	@Override
	public String loadType() {
		return TYPE;
	}

	private Map<String, String> menus;
	
	public String getMenu(String menuid) {
		return menus.get(menuid);
	}

	/**
	 * 
	 * @param reload
	 * @throws Exception
	 */

	// 参数赋值
	public void prepareFieldValue(Map<String, String> configs, AConfigSupport clazz) throws Exception {
		this.menus = configs;
	}

}
