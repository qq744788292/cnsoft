package org.cnsoft.framework.common;

import org.cnsoft.framework.core.ISSystem;

/**
 * 参数配置
 * 
 * @see <AConfigSupport>
 */
public interface ISInit extends ISSystem {
	
	/**
	 * 初始化
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception;
	
}
