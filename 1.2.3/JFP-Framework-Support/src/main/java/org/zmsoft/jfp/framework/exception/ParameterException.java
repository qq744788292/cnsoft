package org.zmsoft.jfp.framework.exception;

/**
 * 参数异常处理
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class ParameterException extends RuntimeException {    
    
    /** serialVersionUID */    
    private static final long serialVersionUID = 6417641452178955756L;    
    
    public ParameterException() {    
        super();    
    }    
    
    public ParameterException(String message) {    
        super(message);    
    }    
    
    public ParameterException(Throwable cause) {    
        super(cause);    
    }    
    
    public ParameterException(String message, Throwable cause) {    
        super(message, cause);    
    }    
}  