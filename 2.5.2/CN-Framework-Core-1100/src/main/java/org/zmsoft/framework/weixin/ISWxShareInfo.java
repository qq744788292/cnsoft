package org.zmsoft.framework.weixin;

import com.alibaba.fastjson.JSONObject;

/**
 * 获得文章分享数据信息
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
public interface ISWxShareInfo {

	/**
	 * 获得文章分享数据信息
	 * title 分享标题<br>
	 * desc 分享描述<br>
	 * link 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致<br>
	 * imgUrl 分享图标<br>
	 * @param [id] 文章ID
	 * @return com.alibaba.fastjson.JSONObject
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	*/
	public JSONObject loadShareInfo(String type, String id);
}
