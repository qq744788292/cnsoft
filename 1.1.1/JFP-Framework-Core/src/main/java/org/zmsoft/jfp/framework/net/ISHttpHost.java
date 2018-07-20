package org.zmsoft.jfp.framework.net;

import org.apache.http.HttpHost;
/**
 * 
 * 代理IP地址
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * @see <MyHttpServiceSupport>
 */
public interface ISHttpHost {
	
	HttpHost loadHttpProxy();
}
