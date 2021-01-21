//package com.example.EAS.interceptor;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URL;
//
//@Component
//@WebFilter(urlPatterns="/*",filterName="httpFilter")
///**
// *  过滤器，将http 请求转发到https请求上来
// *  重定向类型：307
// * @author FrankYuan
// *
// */
//public class httpFilter implements Filter{
//    private Logger logger = LoggerFactory.getLogger(httpFilter.class);
//    private static final String HTTPS ="http";
//    private static final int HTTPS_PORT = 18080;
//    @Override
//    public void destroy() {
//        logger.info("------------destroy HttpsFilter --------------");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        URL newUrl = null;
//        if(request.getScheme().equals(HTTPS)) {
//            chain.doFilter(request, response);
//        }else {
//            HttpServletRequest httpRequest = (HttpServletRequest)request;
//            HttpServletResponse httpResponse = (HttpServletResponse)response;
//            String queryString = httpRequest.getQueryString()==null ? "":"?"+httpRequest.getQueryString();
//            httpResponse.setStatus(307);
//            String requestUrl = httpRequest.getRequestURL().toString();
//            URL reqUrl = new URL(requestUrl+queryString);
//            logger.info("【original request-】 "+reqUrl.toString());
//            newUrl = new URL(HTTPS,reqUrl.getHost(),HTTPS_PORT,reqUrl.getFile());
//            //进行重定向
//            logger.info("【new request-】 "+newUrl.toString());
//            httpResponse.setHeader("Location", newUrl.toString());
//            httpResponse.setHeader("Connection", "close");
//            //允许所有跨域请求
//            httpResponse.addHeader("Access-Control-Allow-Origin", "*");
//        }
//
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//        logger.info("------------init HttpsFilter --------------");
//
//    }
//
//}