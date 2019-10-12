package org.zmsoft.controller.error;

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
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * 默认异常处理
 * 
 * @author ZMSoft
 * @version 2.0.1 2018/7/8 新建
 * @since 2.0.1 2018/7/8
 */
//@ControllerAdvice
public class PageExceptionController extends MyTokenCommonSupport {
	@Autowired
	private ErrorAttributes errorAttributes;

	@ExceptionHandler(value = RuntimeException.class)
	public ModelAndView doError(HttpServletRequest request) throws Exception {

		Map<String, Object> body = this.errorAttributes.getErrorAttributes(new ServletWebRequest(request), false);

		String path = request.getRequestURI();
		String message = (String) body.get("message");

		RESTResultBean<String> result = new RESTResultBean<String>(ECCodeMessageConstants.SYSTEM_ERROR);
		ModelAndView model = new ModelAndView();

		if (path.startsWith("/page/")) {
			model.setViewName("/common/errorpage");
			if (EmptyHelper.isNotEmpty(message))
				result.setMsg(message.split("\r\n")[1]);
		} else {
			model.setViewName("/common/errorapi");
			if (EmptyHelper.isNotEmpty(message))
				result.setData(message.split("\r\n")[1]);
		}
		model.addObject("message", result);
		// 输出结果日志
		return model;
	}
}
