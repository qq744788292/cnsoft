package org.cnsoft.framework.advice;

import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

public class MyHttpInputMessage implements HttpInputMessage {
	
    private HttpHeaders headers;
    private InputStream body;

    public MyHttpInputMessage(HttpHeaders headers,InputStream body) throws Exception {
        //请求头
        this.headers = headers;
    }

    @Override
    public InputStream getBody() {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}