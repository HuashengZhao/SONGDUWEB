package com.example.EAS.util;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 31507499811275907L;

	/**
     * 异常信息
     */
    private String errorMsg;
    
    private BusErrorCodeEnum busErrorCode;
	
    public BusinessException(String errorMsg) {
        super();
        this.errorMsg = errorMsg;
        this.busErrorCode=BusErrorCodeEnum.DEFAULT_CODE;
    }
    
	public BusinessException(BusErrorCodeEnum busErrorCode,String errorMsg) {
		super();
		this.errorMsg = errorMsg;
		this.busErrorCode=busErrorCode;
	}
	public BusinessException(BusErrorCodeEnum busErrorCode) {
		super();
		this.busErrorCode=busErrorCode;
	}

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public BusErrorCodeEnum getBusErrorCode() {
        return busErrorCode;
    }

    public void setBusErrorCode(BusErrorCodeEnum busErrorCode) {
        this.busErrorCode = busErrorCode;
    }
	
}
