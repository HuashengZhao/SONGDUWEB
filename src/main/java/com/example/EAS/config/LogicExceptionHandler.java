package com.example.EAS.config;

import com.example.EAS.util.R;
import com.example.EAS.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class LogicExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(LogicExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R logicExceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        
        //如果是业务逻辑异常，返回具体的错误码与提示信息
        if (e instanceof ServiceException) {
            ServiceException busError=(ServiceException) e;
            logger.warn(busError.getMessage());
//            MDC.put("busErrorType", busError.getBusErrorCode().busErrorType());
            return R.error(busError.getCode()
                    , busError.getMessage());
        }  else {
            logger.error(e.getMessage(),e);
            return R.error(500, "服务器异常");
        }
    }
}
