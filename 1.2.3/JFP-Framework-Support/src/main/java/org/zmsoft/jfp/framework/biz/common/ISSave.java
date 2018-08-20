package org.zmsoft.jfp.framework.biz.common;

/**
 * 保存数据到数据中心
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public interface ISSave {

	/**
	 * 数据保存
	 */
	boolean doSave() throws Exception;
}
