package com.example.EAS.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class BodyDecodeUtil {
    
    private static DesEncrypt desEncrypt = new DesEncrypt();
    
    public static <T>T decodeBody(String body,Class<T> clazz) {

        if (body!=null&&body!="") {
            JSONObject jsonObject = JSON.parseObject(body.replace("#", ""));
            T r = JSON.parseObject(desEncrypt.decrypt(jsonObject.getString("body")),clazz);
            return r;
        } else {
            return null;
        }
    }
    
    public static String encrypt(Object o) {
        if(o == null) {
            return "";
        }
        return desEncrypt.encrypt(JSON.toJSONString(o));
    }
    
    public static void main(String[] args) {
        String a="";
        
        /*
         * ObjectMapper mapper = new ObjectMapper(); try { ProjectStageVO
         * vo=mapper.readValue(a, ProjectStageVO.class); System.out.println(vo.getId());
         * } catch (JsonMappingException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); } catch (JsonProcessingException e) { // TODO
         * Auto-generated catch block e.printStackTrace(); }
         */
//        ProjectStageVO projectStageVO = JSON.parseObject(a,ProjectStageVO.class);
    }
}
