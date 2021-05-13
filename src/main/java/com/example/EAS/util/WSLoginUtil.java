package com.example.EAS.util;

import com.alibaba.fastjson.JSONObject;
import com.example.EAS.dto.WSContext;
import com.example.EAS.mapper.TConSupplierapplyMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

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

        String key = redisUtil.getString("easLogin");
        if (Util.isNotEmpty(key)){
            throw new com.example.EAS.util.ServiceException("請等待片刻，等上一個流程走完");
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
//            WSContext rs = (WSContext) call.invoke(new Object[]{"webservice", "webservice", "eas", "easdb", "L2", Integer.valueOf(2)}); //测试地址
            WSContext rs = (WSContext) call.invoke(new Object[]{"servicekd", "servicekd", "eas", "easdb", "L2", Integer.valueOf(2)});  ////正式地址
            sessionId = rs.getSessionId();
            redisUtil.set("easLogin", 1, 1000 * 20);
            log.info("登录成功：" + sessionId);
        } catch (Exception e) {
            mapper.insertAcceptInfo(e.getMessage(), LocalDateTime.now().toString(), "金蝶登录", "登录", 1);
            throw new com.example.EAS.util.ServiceException("登录金蝶失败，请联系管理：" + e.getMessage());
        }
        jsonObject.put("call", call);
        jsonObject.put("sessionId", sessionId);
        return jsonObject;
    }


    public void logout(Call call) {
        redisUtil.del("easLogin");
        call.clearOperation();
        call.setOperationName("logout");
        String url = mapper.selectEASLogin();
        call.setTargetEndpointAddress(url);
        try {
//            call.invoke(new Object[]{"webservice", "eas", "easdb", "L2"});//测试地址
            call.invoke(new Object[]{"servicekd", "eas", "easdb", "L2"});//正式地址
            log.info("登出成功");
        } catch (RemoteException e) {
            mapper.insertAcceptInfo(e.getMessage(), LocalDateTime.now().toString(), "金蝶登出", "登出", 1);
            throw new com.example.EAS.util.ServiceException("登出金蝶失败，请联系管理：" + e.getMessage());
        }
    }
}
