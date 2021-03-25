package org.zmsoft.framework;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 对象超类
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/08/08
 * @since 1.0.0 2018/02/02
 * @see <MyFrameWorkSupport>
 */
//@JsonInclude(value=Include.NON_NULL)
public class ObjectBean {

	@Override
	public String toString() {
		return JSON.toJSONString(this);
//
//		ObjectMapper mapper = new ObjectMapper();// 先创建objmapper的对象
//		String string = "";
//		try {
//			string = mapper.writeValueAsString(this);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(this.getClass().getSimpleName() +"===" +string);
//
//		return string;
	}
}
