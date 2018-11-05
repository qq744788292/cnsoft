package org.zmsoft.framework.config;

/**
 * 管理员用户名称转换接口
 * 
 * @author spookfcy
 * @see <myManagerNameTransSupport>
 */
public interface ISManagerNameTransSupport {
	/**
	 * 名称转换
	 * 
	 * @param userId
	 * @return
	 */
	public String converName(String userId);
}
