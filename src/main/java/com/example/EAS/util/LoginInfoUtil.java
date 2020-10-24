package com.example.EAS.util;

import com.alibaba.fastjson.JSONObject;
import com.example.EAS.mapper.TBdPersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @PackageName:com.example.EAS.util
 * @ClassName:LoginInfoUtil
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/10/24 15:09
 */
@Component
public class LoginInfoUtil {

    @Autowired
    private   TBdPersonMapper personMapper;

    //    获取系统登录信息
    public  JSONObject getToken() {
        JSONObject object = new JSONObject();

        //       获取当前用户组织
        String token = RequestHolder.getCurrentUser().getToken();
        String dencrypt = null;
        try {
            dencrypt = RSAUtil.dencrypt(token, "pri.key");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] split = dencrypt.split("&&");
        String org = split[0];
        String person = split[1];
        object.put("org", org);
        object.put("person", person);
        if (Util.isNotEmpty(person)) {
            String name = this.personMapper.selectNameByNum(person);
            if (Util.isNotEmpty(name)) {
                object.put("personName", name);
            }
        }else{
            throw new ServiceException(UtilMessage.PERSON_MISSING);
        }
        return object;
    }

    /**
     * 判断是否在it部门
     * d90AAAFIJknM567U，正式库信息部的ID,d90AAAAANNPM567U。正式库董事长ID,d90AAAAANNTM567U，正式库总裁室ID
     * d90AAAAASfLM567U，测试库信息部ID,d90AAAAANNPM567U，测试库董事长ID,d90AAAAANNTM567U，测试库总裁室ID
     */

    public  Boolean ifInItDept() {
        Boolean b = false;
        JSONObject token = getToken();
        String personNumber = token.getString("person");
        List<String> numbers = personMapper.selectITPersonNumbers();//it部门人员编码集合
        if (numbers!=null && numbers.size()>0){
            if (numbers.contains(personNumber)){
                b=true;
            }
        }
        return b;
    }
}
