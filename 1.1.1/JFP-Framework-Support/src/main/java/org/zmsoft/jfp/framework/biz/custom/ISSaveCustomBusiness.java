package org.zmsoft.jfp.framework.biz.custom;

import org.zmsoft.jfp.framework.biz.common.ISSave;

/**
 * 数据保存处理
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * 
 */
public interface ISSaveCustomBusiness extends ISSave {
	/**
	 * 数据保存前
	 */
	boolean beforeSave() throws Exception;
	/**
	 * 数据保存中
	 */
	boolean doSave() throws Exception;
	/**
	 * 数据保存后
	 */
	boolean afterSave() throws Exception;
}
