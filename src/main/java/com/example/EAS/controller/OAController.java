package com.example.EAS.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.EAS.constant.CacheKeyConstant;
import com.example.EAS.mapper.TConSupplierapplyMapper;
import com.example.EAS.service.impl.TConSupplierapplyServiceImpl;
import com.example.EAS.service.impl.TOrgBaseunitServiceImpl;
import com.example.EAS.util.*;
import com.example.EAS.vo.LoginVO;
import com.example.EAS.vo.OrgVO;
import com.example.EAS.vo.PersonIdentityVO;
import com.example.EAS.vo.PersonsVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName:com.example.EAS.controller
 * @ClassName:OAController
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/12 9:51
 */
@RestController
@RequestMapping("/EAS/login")
@Slf4j
public class OAController {

    @Autowired
    private TConSupplierapplyMapper mapper;
    @Autowired
    private TOrgBaseunitServiceImpl service;
    @Autowired
    private TConSupplierapplyServiceImpl supplierapplyService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 单点登录
     * oa调用rest地址 登陆后返回web地址给oa
     * OA通过用户编码、部门、物业/金蝶 进行登陆 ，返回一串web地址拼接token给oa 给oa直接跳转
     * 注意@！！oa登录的组织与eas不同 eas查看组织 用用户去查
     */
    @RequestMapping(value = "/ByNameAndDept", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R ByNameAndDept(@RequestBody LoginVO vo) throws Exception {
        JSONObject json = new JSONObject();
        try {
            String org = vo.getOrg();
            if (Util.isEmpty(org)) {
                json.put("code", 2);
                json.put("message", "登录缺少部门信息");
                return R.error(json);
            }
            String person = vo.getPerson();
            if (Util.isEmpty(person)) {
                json.put("code", 2);
                json.put("message", "登录缺少员工信息");
                return R.error(json);
            }

            StringBuffer sb = new StringBuffer();
            String s = String.valueOf(sb.append(org).append("&&").append(person));
            String token = RSAUtil.encrypt(s, "pub.key");
//          存放token
            redisUtil.set(redisUtil.generateKey(CacheKeyConstant.WEB_LOGIN_TOKEN, person), token, 1000 * 3600 * 240);
//          返回link拼接
            String type = null;
            if (Util.isNotEmpty(vo.getType())) {
                type = vo.getType();
                if (type!=null&&type.contains("payment")){
                    type="payment";
                }
            }
            StringBuffer sb1 = new StringBuffer();
            token = URLEncoder.encode(token, "utf-8");
            json.put("permission", token);
//            type
            String s1 = mapper.selectViewUrl();
            String link = String.valueOf(sb1.append(s1)
                    .append(type).append("?token=").append(token));
            json.put("link", link);
            log.info("oa登录时返回link: " + link);
            json.put("code", 1);
            json.put("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
            json.clear();
            json.put("code", 2);
            json.put("message", e.toString());
            return R.error(json.toString());
        }
        log.info(json.toString());
        return R.ok(json);
    }

    /**
     * 获取登陆信息
     */
    @RequestMapping(value = "/getLoginData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getLoginData(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        LoginVO vo = BodyDecodeUtil.decodeBody(body, LoginVO.class);
        PersonsVO personsVO = null;
//        String token = vo.getToken();
        if (Util.isNotEmpty(vo.getToken())) {
            String token = RequestHolder.getCurrentUser().getToken();
            String dencrypt = RSAUtil.dencrypt(token, "pri.key");
            String[] split = dencrypt.split("&&");
            String org = split[0];
            String person = split[1];
            personsVO = mapper.selectCreator(person);
//            根据OA用户编码获取EAS对应的公司信息
            List<String> longNumbers = mapper.selectOrgInfoByPerson(person);
//            获取所有财务实体组织
            List<OrgVO> orgVOList = new ArrayList<>();
            Map map = new HashMap<>();
            List<OrgVO> orgVOS = mapper.selectOrgList();
            if (orgVOS != null && orgVOS.size() > 0) {
                for (OrgVO orgVO : orgVOS) {
                    if (longNumbers != null && longNumbers.size() > 0) {
                        for (String longNumber : longNumbers) {
                            if (longNumber.contains(orgVO.getLongNumber())
                                    && Util.isEmpty(orgVO.getParentId())) {
                                Object o = map.get(orgVO.getNum());
                                if (Util.isEmpty(o)) {
                                    orgVOList.add(orgVO);
                                }
                                map.put(orgVO.getNum(), orgVO);
                            }
                        }
                    }
                }
            }
            if (Util.isNotEmpty(personsVO)) {
                personsVO.setOrgVOList(orgVOList);
            }
        }
        result.put("data", personsVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 登陆用户取oa身份/岗位列表
     */
    @RequestMapping(value = "/getPersonIdentity", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getPersonIdentity(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        PersonIdentityVO vo = BodyDecodeUtil.decodeBody(body, PersonIdentityVO.class);
        List<PersonIdentityVO> vos = service.getPersonIdentities(vo);
        result.put("data", vos);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 流程查看获取当前token 返回oa
     */
    @RequestMapping(value = "/getRecentToken", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getRecentToken(@RequestBody LoginVO vo) throws Exception {
        JSONObject json = new JSONObject();
        String person = vo.getPerson();
        String org = vo.getOrg();
        if (Util.isEmpty(person) || Util.isEmpty(org)) {
            json.put("code", 2);
            json.put("message", "请求参数缺失");
        }
        String token = redisUtil.getString(redisUtil.generateKey(CacheKeyConstant.WEB_LOGIN_TOKEN, person));
        if (Util.isNotEmpty(token)) {
            redisUtil.set(redisUtil.generateKey(CacheKeyConstant.WEB_LOGIN_TOKEN, person), token, 1000 * 3600 * 240);//刷新时效
        } else {
            StringBuffer sb = new StringBuffer();
            String s = String.valueOf(sb.append(org).append("&&").append(person));
            token = RSAUtil.encrypt(s, "pub.key");
//          存放token
            redisUtil.set(redisUtil.generateKey(CacheKeyConstant.WEB_LOGIN_TOKEN, person), token, 1000 * 3600 * 240);
        }
        token = URLEncoder.encode(token, "utf-8");
        json.put("permission", token);
        json.put("code", 1);
        json.put("message", "success");
        log.info("流程查看获取token" + token);
        return R.ok(json);
    }

    @RequestMapping(value = "/delKingdeeToken", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R delKingdeeToken(@RequestBody LoginVO vo) throws Exception {
        JSONObject json = new JSONObject();
        redisUtil.del(redisUtil.generateKey(CacheKeyConstant.EAS_LOGIN_WAITING));
        json.put("code", 1);
        json.put("message", "success");
        return R.ok(json);
    }


    /**
     * 通过密文转为实体
     *
     * @param body
     * @return
     */
    private LoginVO getLoginMsgBody(String body) throws Exception {
        if (body != null && body != "") {
            JSONObject jsonObject = JSON.parseObject(body.replace("#", ""));
            LoginVO userVO = JSON.parseObject(RSAUtil.dencrypt(jsonObject.getString("body"), "pri.key"), LoginVO.class);
            return userVO;
        } else {
            return null;
        }
    }
}
