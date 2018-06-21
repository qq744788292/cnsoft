package org.zmsoft.jfp.framework.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * JSON工具
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class JSONHelper {

	public static Map<String, String> toMap(String data) {
		Map<String, String> map = new HashMap<String, String>();
		// 将字符串转换成JSONObject
		JSONObject jsonObject = JSONObject.parseObject(data);
		// 遍历jsonObject数据,添加到Map对象
		for (Map.Entry<String, Object> key : jsonObject.entrySet()) {
			map.put(key.getKey(), key.getValue().toString());
		}
		return map;
	}
}
