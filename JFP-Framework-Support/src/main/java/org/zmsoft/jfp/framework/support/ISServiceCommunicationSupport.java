package org.zmsoft.jfp.framework.support;

import java.util.List;

import org.zmsoft.jfp.framework.beans.ObjectBean;


/**
 * 数据压缩解压缩接口
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public interface ISServiceCommunicationSupport {

	/**
	 * List存储对象压缩
	 * 
	 * @param data
	 * @return
	 */
	public String compress(List<ObjectBean> data);

	/**
	 * 压缩对象转换为List存储
	 * 
	 * @param data
	 * @return
	 */
	public List<ObjectBean> unCmpress(String data);
}
