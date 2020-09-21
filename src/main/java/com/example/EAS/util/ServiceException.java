package com.example.EAS.util;

/**
 * @description :自定义异常
 */
public class ServiceException extends RuntimeException {

    /**
     */
    private static final long serialVersionUID = -2608373317729107675L;

    private int code=500;
    
    private String logType;
    
    public ServiceException() {
        super();
    }

    public ServiceException(int code,String message) {
        super(message);
        this.code=code;
    }
    
    public ServiceException(int code,String logType,String message) {
        super(message);
        this.code=code;
        this.logType=logType;
    }
    
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }
    
}
