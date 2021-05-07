package com.example.EAS.util;

import com.alibaba.fastjson.JSONObject;
import com.example.EAS.constant.CacheKeyConstant;
import com.example.EAS.dto.WSContext;
import com.example.EAS.mapper.TConSupplierapplyMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Component
public class WSLoginUtil {
    @Autowired
    private TConSupplierapplyMapper mapper;
    @Autowired
    private RedisUtil redisUtil;

    org.apache.axis.client.Service service = new org.apache.axis.client.Service();
    String sessionId = null;
    String url = null;
    org.apache.axis.client.Call call = null;

    public JSONObject login() {
        long expire = redisUtil.getExpire(redisUtil.generateKey(CacheKeyConstant.EAS_LOGIN_WAITING));
        if (Util.isNotEmpty(expire) && expire > 0) {
            throw new com.example.EAS.util.ServiceException("金蝶审批流程未结束，请稍后再试！");
        }
        JSONObject jsonObject = new JSONObject();
        try {
            call = (Call) service.createCall();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        url = mapper.selectEASLogin();

//        再登录
        call.setOperationName("login");
        call.setTargetEndpointAddress(url);
        call.setReturnType(new QName("urn:client", "WSContext"));
        call.setReturnClass(WSContext.class);
        call.setReturnQName(new QName("", "loginReturn"));
        //超时
        call.setTimeout(Integer.valueOf(1000 * 600000 * 60));
        call.setMaintainSession(true);

        try {
            WSContext rs = (WSContext) call.invoke(new Object[]{"webservice", "webservice", "eas", "easdb", "L2", Integer.valueOf(2)}); //测试地址
//            WSContext rs = (WSContext) call.invoke(new Object[]{"servicekd", "servicekd", "eas", "easdb", "L2", Integer.valueOf(2)});  ////正式地址
            sessionId = rs.getSessionId();
            redisUtil.set(redisUtil.generateKey(CacheKeyConstant.EAS_LOGIN_WAITING), sessionId, 1000 * 3600 * 24 * 10);
            log.info("登录成功：" + sessionId);
        } catch (Exception e) {
            throw new com.example.EAS.util.ServiceException("登录金蝶失败，请联系管理：" + e.getMessage());
        }
        jsonObject.put("call", call);
        jsonObject.put("sessionId", sessionId);
        return jsonObject;
    }


    public void logout(Call call) {
        redisUtil.del(redisUtil.generateKey(CacheKeyConstant.EAS_LOGIN_WAITING));
        call.clearOperation();
        call.setOperationName("logout");
        String url = mapper.selectEASLogin();
        call.setTargetEndpointAddress(url);
        try {
            call.invoke(new Object[]{"webservice", "eas", "easdb", "L2"});//测试地址
//            call.invoke(new Object[]{"servicekd", "eas", "easdb", "L2"});//正式地址
            log.info("登出成功");
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new com.example.EAS.util.ServiceException("登出金蝶失败，请联系管理：" + e.getMessage());
        }
    }
}
