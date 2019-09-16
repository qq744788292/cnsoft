package org.zmsoft.service.player;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.framework.beans.LoginerBean;
import org.zmsoft.framework.beans.common.RESTResultBean;

/**
 * 会员注册
 *
 */
public interface ISPLayerRegistService {

	RESTResultBean<String> doRegist(HttpServletRequest request, HttpServletResponse response, LoginerBean loginer) throws Exception;

}
