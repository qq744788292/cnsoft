package org.cnsoft.framework.advice;

import javax.servlet.http.HttpServletRequest;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.common.ISApiSecurity;
import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 请求参数 解密操作
 */
@ControllerAdvice
public class EncryResponseBodyAdvice implements ResponseBodyAdvice<Object> , ICFrameworkConstants{

	public ISApiSecurity loadApiSecurity() {
		return MyBeanFactoryHelper.getBean(ISApiSecurity.class);
	}

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class);
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        //通过 ServerHttpRequest的实现类ServletServerHttpRequest 获得HttpServletRequest
        ServletServerHttpRequest sshr = (ServletServerHttpRequest) serverHttpRequest;
        //此处获取到request 是为了取到在拦截器里面设置的一个对象 是我项目需要,可以忽略
        HttpServletRequest request = sshr.getServletRequest();
        //数据通信安全配置
        SecurityParameter securityType = methodParameter.getMethodAnnotation(SecurityParameter.class);
        //出参是否加密，默认加密
        boolean flag = securityType.outEncode();
        Object returnStr = obj;
        if(flag) {
	        try {
	            //加密
	            HttpHeaders headers = serverHttpResponse.getHeaders();
	            //添加encry header，告诉前端数据已加密
	            headers.add("encry", "true");
	        	String jobId = headers.getFirst(CONSTANT_USER_JOBID);
	            returnStr = loadApiSecurity().encrypt(jobId, obj);
	            System.out.println(String.format("接口=%s,原始数据=%s,加密后数据=%s", request.getRequestURI(), JSONObject.toJsonString(obj), JSONObject.toJsonString(returnStr)));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }
        return returnStr;
    }
}