package org.zmsoft.jfp.framework.support;

/**
 * 加密解密接口
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISSecuritySupport {
	/**
	 * 加密
	 */
	public static final String ENCRYPTION = "1";
	
	/**
	 * 未加密
	 */
	public static final String DECRYPTION= "0";
	
	/**
	 * 数据解密
	 * @param companyKey         企业密钥
	 * @param data               数据
	 * @return
	 */
	public String decryption(String companyKey,String data);
	
	/**
	 * 数据加密
	 * @param companyKey         企业密钥
	 * @param data               数据
	 * @return
	 */
	public String encryption(String companyKey,String data);
}
