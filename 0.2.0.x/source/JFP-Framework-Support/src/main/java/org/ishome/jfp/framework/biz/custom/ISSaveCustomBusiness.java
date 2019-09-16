package org.ishome.jfp.framework.biz.custom;

/**
 * 数据保存处理
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISSaveCustomBusiness {
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
