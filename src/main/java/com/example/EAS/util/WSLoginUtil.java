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

    //    登录websevice
    public JSONObject login() {
        long expire = redisUtil.getExpire(redisUtil.generateKey(CacheKeyConstant.EAS_LOGIN_WAITING));
        if (Util.isNotEmpty(expire)&&expire>0) {
            throw new com.example.EAS.util.ServiceException("当前操作正在执行，请等待三秒后重新操作！");
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
        String s = call.toString();
        //登陆接口参数
        try {
//            WSContext rs = (WSContext) call.invoke(new Object[]{"webservice", "webservice", "eas", "easdb", "L2", Integer.valueOf(2)}); //测试地址
            WSContext rs = (WSContext) call.invoke(new Object[]{"servicekd", "servicekd", "eas", "easdb", "L2", Integer.valueOf(2)});  ////正式地址
            sessionId = rs.getSessionId();
            redisUtil.set(redisUtil.generateKey(CacheKeyConstant.EAS_LOGIN_WAITING), "point", 5);
            log.info("登录成功：" + sessionId);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new com.example.EAS.util.ServiceException(e.getMessage());
        }
        jsonObject.put("call", call);
        jsonObject.put("sessionId", sessionId);
        return jsonObject;
    }

    //    登出websevice
    public void logout(Call call) {
        call.clearOperation();
        call.setOperationName("logout");
        String url = mapper.selectEASLogin();
        call.setTargetEndpointAddress(url);
        try {
//            call.invoke(new Object[]{"webservice", "eas", "easdb", "L2"});//测试地址
            call.invoke(new Object[]{"servicekd", "eas", "easdb", "L2"});//正式地址
            log.info("登出成功");
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new com.example.EAS.util.ServiceException(e.getMessage());
        }
    }
}
