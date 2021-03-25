package org.cnsoft.framework.beans.common;

import java.util.ArrayList;

import org.cnsoft.framework.core.ObjectBean;
import org.cnsoft.framework.json.JSONObject;

/** KV定义 */
public class KeyValueBean extends ObjectBean {

	protected String key;

	protected String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static void main(String[] args) {
		KeyValueBean kvb = new KeyValueBean();
		kvb.setKey("aaa");
		kvb.setValue("2020-02-13 13:12:12");
		
		ArrayList<KeyValueBean> list = new ArrayList<KeyValueBean>();
		list.add(kvb);
		list.add(kvb);
		list.add(kvb);
		
		{
		String s = JSONObject.toJsonString(kvb);
		System.out.println(s);
		System.out.println(JSONObject.parseObject(s, KeyValueBean.class));
		}
		{
			String s = JSONObject.toJsonString(list);
		System.out.println(s);
		
		System.out.println(JSONObject.parseObject(s, KeyValueBean.class));
		}

	}
}
