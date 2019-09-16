package org.ishome.jfp.framework.beands;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 数据返回主体
 * 
 * @author Spook
 * @version 2.0.1 2015/7/7
 * @version 0.1.0
 * @since 0.1.0
 */
@Named
public class RESTResultBean extends ObjectBean implements ISFrameworkConstants {

	/**
	 * 服务器认证授权码（登记授权）
	 */
	private String token = EMPTY;

	/**
	 * 返回结果(0成功1失败)
	 */
	private String code = ZERO;// 对接返回代码 -1:无数据 0:正确 其他：对应对接方错误码

	/**
	 * 提示信息
	 */
	private String message = MESSAGE_WAITING;// 对接返回信息 空:正确 其他：对应对接方错误描述

	/**
	 * 返回结果
	 */
	private Object result = EMPTY;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String resultCode) {
		this.code = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String resultMsg) {
		this.message = resultMsg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// RESTResultBean深层对象解析示例
		// 定义个深层对象
		RESTResultBean rs = new RESTResultBean();
		List<RESTResultBean> ls = new ArrayList<RESTResultBean>();
		ls.add(new RESTResultBean());
		rs.setResult(ls);
		// rs.setResult(new RESTResultBean());
		// 转换成文本
		String json = JSONObject.toJSONString(rs);
		// 开始解析
		JSONObject jsons = JSONObject.parseObject(json);
		{
			// 直接获得深层对象List
			JSONArray ja = (JSONArray) jsons.get("result");
			System.out.println(JSONArray.parseArray(JSONArray.toJSONString(rs.getResult()), RESTResultBean.class));
			// 直接解析数据项目得到一个数据
			System.out.println(JSONObject.toJavaObject((JSONObject) ja.get(0), RESTResultBean.class));
		}
	}
}
