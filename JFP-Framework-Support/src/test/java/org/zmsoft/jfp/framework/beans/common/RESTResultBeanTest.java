package org.zmsoft.jfp.framework.beans.common;

import java.util.ArrayList;
import java.util.List;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.PKHelper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 接口数据返回主体
 * 
 * @author zmsoft
 * @version 0.1
 * @since 0.2.0.0
 */

public class RESTResultBeanTest implements IFrameworkConstants {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		{
			RESTResultBean result = new RESTResultBean();
			result.setJobId(PKHelper.creatPUKey());
			result.setCode(SYSTEM_TOKEN_ERROR_CODE);
			result.setMsg(MESSAGE_TOKEN_ERROR);// 登录失效，请重新登录
			// 转换成文本
			String json = JSONObject.toJSONString(result);
			System.out.println(json);
		}
		{
			// RESTResultBean深层对象解析示例
			// 定义个深层对象
			RESTResultBean rs = new RESTResultBean();
			List<RESTResultBean> ls = new ArrayList<RESTResultBean>();
			ls.add(new RESTResultBean());
			rs.setData(ls);
			// rs.setResult(new RESTResultBean());
			// 转换成文本
			String json = JSONObject.toJSONString(rs);
			System.out.println(json);
			// 开始解析
			JSONObject jsons = JSONObject.parseObject(json);
			{
				// 直接获得深层对象List
				JSONArray ja = (JSONArray) jsons.get("result");
				//
				System.out.println(JSONArray.parseArray(JSONArray.toJSONString(rs.getData()), RESTResultBean.class));
				// 直接解析数据项目得到一个数据
				System.out.println(JSONObject.toJavaObject((JSONObject) ja.get(0), RESTResultBean.class));
			}
		}
	}
}
