package org.cnsoft.framework.core;

import org.cnsoft.framework.beans.common.RESTResultBean;
import org.cnsoft.framework.support.MyTokenCommonSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ApiErrorController extends MyTokenCommonSupport { 

	@RequestMapping(value = "/error", method = RequestMethod.POST)
	public RESTResultBean<String> error() throws Exception {
		RESTResultBean<String> result = new RESTResultBean<String> ();
		// 输出参数日志
		return result;
	}

}
