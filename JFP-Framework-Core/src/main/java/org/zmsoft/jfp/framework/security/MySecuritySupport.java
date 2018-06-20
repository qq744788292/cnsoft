package org.zmsoft.jfp.framework.security;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.security.value.PBESecurityHelper;
import org.zmsoft.jfp.framework.support.ISSecuritySupport;
import org.zmsoft.jfp.framework.utils.TokenBusinessHelper;

/**
 * 数据加密与解密处理
 * 
 * @author zmsoft
 * 
 */
public class MySecuritySupport implements ISSecuritySupport, IFrameworkConstants {

	/**
	 * 获得企业加密Key
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String loadSecurityKey(String CompanyId, String version, String ipAdress) throws Exception {
		return TokenBusinessHelper.getBizTokenData(CompanyId, version, ipAdress.replace(".", "")).substring(0, 8);
	}

	@Override
	public String decryption(String token, String data) {
		try {
			return PBESecurityHelper.decrypt(token, data);
		} catch (Exception e) {
			return data;
		}
	}

	@Override
	public String encryption(String token, String data) {
		try {
			return PBESecurityHelper.encrypt(token, data);
		} catch (Exception e) {
			return data;
		}
	}

}
