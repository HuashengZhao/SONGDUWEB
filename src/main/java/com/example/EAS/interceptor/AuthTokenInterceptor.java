package com.example.EAS.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.EAS.constant.CacheKeyConstant;
import com.example.EAS.util.*;
import com.example.EAS.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PackageName:pamier.manage.AuthoritySystem.interceptor
 * @ClassName:AuthTokenInterceptor
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/5/12 17:06
 */

@Slf4j
@Component
public class AuthTokenInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenInterceptor.class);

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String token = request.getHeader("token");

        if (Util.isEmpty(token)) {
            logger.warn("未携带token,非法请求");
            R result = R.error(10001, "未携带token,非法请求");
            write2Response(response, result);
            return false;
        }
//        解析token并根据token携带的信息获取缓存中对应的token;
        String keyStr = null;
        try {
            keyStr = RSAUtil.dencrypt(token, "pri.key");
        } catch (Exception e) {
            e.printStackTrace();
            throw  new ServiceException(UtilMessage.DES_LOGININFO_ERROR);
        }
        String[] split = keyStr.split("&&");
        String org = split[0];
        String person = split[1];
        if (Util.isEmpty(org) || Util.isEmpty(person)) {
            R result = R.error(10002, "登录信息错误，请确认信息后重新登录");
            write2Response(response, result);
            return false;
        }
        String s = redisUtil.getString(redisUtil.generateKey(CacheKeyConstant.WEB_LOGIN_TOKEN,person));
        if (Util.isEmpty(s)) {
            R result = R.error(10004, "登录信息从错误，请核验后重新登录");
            write2Response(response, result);
            return false;
        }else if (!s.equals(token)){
            R result = R.error(10005, "该账号已被异地登陆！");
            write2Response(response, result);
            return false;
        }
        long expire = redisUtil.getExpire(redisUtil.generateKey(CacheKeyConstant.WEB_LOGIN_TOKEN,person));
        if (expire<=0){
            R result = R.error(10003, "登录过期，请重新登录");
            write2Response(response, result);
            return false;
        }

        LoginVO loginVO = new LoginVO();
//        token = URLDecoder.decode(token,"utf-8");
        loginVO.setToken(token);
        RequestHolder.add(loginVO);
        return true;
    }

    private void write2Response(HttpServletResponse httpResponse, R result) throws IOException {
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.getWriter().write(JSONObject.toJSONString(result));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {
    }
}
