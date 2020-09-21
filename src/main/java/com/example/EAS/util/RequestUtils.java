package com.example.EAS.util;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

public class RequestUtils {

    private RequestUtils() {
    }

    private static final String UNKNOWN = "unknown";

    public static String getRequestJsonString(HttpServletRequest request) throws IOException {
        String method = request.getMethod();
        if (method.equals("POST")) {
            if (StringUtils.isEmpty(request.getQueryString())) {
                return null;
            }
            return new String(URLDecoder.decode(request.getQueryString(), "utf-8"));
        } else {
            return getRequestPostStr(request);
        }
    }

    public static String getRequestPostStr(HttpServletRequest request) throws IOException {
        byte[] buffer = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte[] buffer = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readLen = request.getInputStream().read(buffer, i, contentLength - i);
            if (readLen == -1) {
                break;
            }
            i += readLen;
        }
        return buffer;
    }

    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name))
                return cookie.getValue();
        }
        return "";
    }
}