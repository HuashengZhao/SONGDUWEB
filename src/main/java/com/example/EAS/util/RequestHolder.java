package com.example.EAS.util;


import com.example.EAS.vo.LoginVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Watson
 * @date 2019/11/20
 */
public class RequestHolder {
    private static final ThreadLocal<LoginVO> userHolder = new ThreadLocal<LoginVO>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void add(LoginVO sysUser) {
        userHolder.set(sysUser);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static LoginVO getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
}