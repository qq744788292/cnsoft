package org.cnsoft.framework.config.system;

import org.apache.commons.codec.binary.Base64;
import org.cnsoft.framework.common.ISApiSecurity;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.json.JSONObject;
import org.cnsoft.framework.security.DESSecurityHelper;
import org.springframework.stereotype.Component;

/**
 * 
 * @see #SecurityParameter
 */
@Component("ISApiSecurity")
public class MyApiSecurityConfiger implements ISApiSecurity,ICFrameworkConstants {

	@Override
	public String decrypt(String jobId, String data) throws Exception {

		System.out.println("===eeeeeeeee==="+ JSONObject.toJsonString(data));
		return data;
	}

	@Override
	public Object encrypt(String jobId, Object data) throws Exception {

		System.out.println("===ddddddddd==="+ JSONObject.toJsonString(data));
		
		return new String(Base64.encodeBase64(DESSecurityHelper.encrypt(data.toString().getBytes(SYSTEM_CHARSET), loadPassword(jobId))));
	}

	@Override
	public String loadPassword(String jobId) throws Exception {
		// TODO Auto-generated method stub
		return "1234567890abcdef";
	}
	
	

}
