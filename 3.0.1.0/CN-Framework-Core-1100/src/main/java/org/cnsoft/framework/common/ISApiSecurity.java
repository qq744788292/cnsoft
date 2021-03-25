package org.cnsoft.framework.common;

import org.cnsoft.framework.core.ISSystem;

public interface ISApiSecurity extends ISSystem {

	/**
	 * 数据加密
	 * @return
	 * @throws Exception
	 */
	public String loadPassword(String jobId) throws Exception;
	
	/**
	 * 数据解密
	 * 
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String jobId, String data) throws Exception;
	
	/**
	 * 数据加密
	 * @return
	 * @throws Exception
	 */
	public Object encrypt(String jobId, Object data) throws Exception;
	
}
