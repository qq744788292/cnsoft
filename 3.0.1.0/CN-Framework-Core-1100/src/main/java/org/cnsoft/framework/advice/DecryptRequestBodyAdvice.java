package org.cnsoft.framework.advice;

import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.commons.io.IOUtils;
import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.common.ISApiSecurity;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

/**
 * 请求参数 解密操作
 */
@ControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice,ICFrameworkConstants {

	private ISApiSecurity loadApiSecurity() {
		return MyBeanFactoryHelper.getBean(ISApiSecurity.class);
	}

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class);
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> selectedConverterType) throws IOException {
		//数据通信安全配置
        SecurityParameter securityType = methodParameter.getMethodAnnotation(SecurityParameter.class);
        //入参是否解密，默认解密
        boolean flag = securityType.inDecode();

        // 解密操作
        if(flag) {
        	try {
	        	String indata = IOUtils.toString(httpInputMessage.getBody(), SYSTEM_CHARSET);
	        	HttpHeaders headers = httpInputMessage.getHeaders();
	        	String jobId = headers.getFirst(CONSTANT_USER_JOBID);
				indata = loadApiSecurity().decrypt(jobId, indata);
				return new MyHttpInputMessage(httpInputMessage.getHeaders(), IOUtils.toInputStream(indata, SYSTEM_CHARSET));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        return httpInputMessage;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}


}