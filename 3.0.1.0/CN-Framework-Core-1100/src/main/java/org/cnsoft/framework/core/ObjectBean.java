package org.cnsoft.framework.core;

import org.cnsoft.framework.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 对象超类
 * 
 * @since 1.0.0 2018/02/02
 * @version 3.0.0 202118/02/22 升级到jackson
 * @version 2.0.0 2018/08/08
 * @version 1.0.0 2018/02/02
 * @author CNSoft
 */
@JsonInclude(Include.NON_NULL) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectBean {
	
	public String toString() {
		return toJsonString();
	}
	
	public String toJsonString() {
		return JSONObject.toJsonString(this);
	}
}
