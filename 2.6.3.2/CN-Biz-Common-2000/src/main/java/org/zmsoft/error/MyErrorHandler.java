package org.zmsoft.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.constants.ECCodeMessageConstants;
import org.zmsoft.framework.exception.BusinessException;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

/**
 * 默认异常处理
 * 
 * @author ZMSoft
 * @version 2.0.1 2018/7/8 新建
 * @since 2.0.1 2018/7/8
 */
@ControllerAdvice
public class MyErrorHandler extends MyTokenCommonSupport {
	@Autowired
	private ErrorAttributes errorAttributes;

	@ExceptionHandler(value = RuntimeException.class)
	public ModelAndView myRuntimeExceptionHandler(HttpServletRequest request) throws Exception {
		return myExceptionHandler(request);
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView myExceptionHandler(HttpServletRequest request) throws Exception {
		Map<String, Object> body = this.errorAttributes.getErrorAttributes(new ServletWebRequest(request), false);

		ModelAndView model = new ModelAndView();
		// 判断返回方式
		String path = request.getRequestURI();
		if (path.startsWith("/page/")) {
			model.setViewName("/common/errorpage");
		} else {
			model.setViewName("/common/errorapi");
		}
		// 处理提示信息
		String message = (String) body.get("message");
		RESTResultBean<String> result = new RESTResultBean<String>(ECCodeMessageConstants.SYSTEM_ERROR);
		if (EmptyHelper.isNotEmpty(message)) {
			if (message.indexOf("\r\n") > 0) {
				result.setMsg(message.split("\r\n")[1]);
			} else {
				BusinessException myBusinessException = JSON.parseObject(message, BusinessException.class);
				result.setResult(1, myBusinessException.getMessage());// myBusinessException.getCode()
			}
		}
		model.addObject("result", result);
		// 输出结果日志
		return model;
	}
}
