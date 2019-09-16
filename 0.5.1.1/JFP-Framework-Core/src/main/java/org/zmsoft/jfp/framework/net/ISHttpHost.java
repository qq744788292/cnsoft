package org.zmsoft.jfp.framework.net;

import org.apache.http.HttpHost;
/**
 * 
 * 代理IP地址
 * 
 * @author zmsoft
 * @since 2.4.1.2017/06/20
 * @version 2.4.1.2017/06/20
 * @see <MyHttpServiceSupport>
 *
 */
public interface ISHttpHost {
	
	HttpHost loadHttpProxy();
}
