package org.zmsoft.jfp.framework.cache;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.zmsoft.jfp.framework.biz.common.ISProcess;
import org.zmsoft.jfp.framework.support.MyDataCacheSupport;
import org.zmsoft.jfp.framework.support.MyWorkSupport;

/**
 * 系统缓存异步加载线程
 * 
 * @author ZmSoft
 * @version 1.2.1 2018/07/18
 * @since 1.2.1 2018/07/18
 */
public class SystemCacheManagerService extends MyWorkSupport implements ISProcess, Runnable {

	List<MyDataCacheSupport<T>> serviceList;

	public void setServiceList(List<MyDataCacheSupport<T>> serviceList) {
		this.serviceList = serviceList;
	}

	/**
	 * 激活线程
	 */
	public boolean doProcess() throws Exception {
		(new Thread(this)).start();
		return true;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1800);
		} catch (InterruptedException e) {
		}
		for (MyDataCacheSupport<T> item : serviceList){
			logger.debug("=====>>>>>"+item);
			item.loadDefaultCacheData();
		}
	}
}
