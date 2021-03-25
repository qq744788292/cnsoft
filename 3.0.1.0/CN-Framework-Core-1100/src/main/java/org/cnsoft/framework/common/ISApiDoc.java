package org.cnsoft.framework.common;

import org.cnsoft.framework.core.ISSystem;

public interface ISApiDoc extends ISSystem {
	/**
	 * 获得API地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loadApiDocName() throws Exception;
}
