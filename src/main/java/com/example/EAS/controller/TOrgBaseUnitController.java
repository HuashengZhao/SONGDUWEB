package com.example.EAS.controller;


import com.example.EAS.service.impl.TOrgBaseunitServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.OrgVO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TOrgBaseUnitController {

    @Autowired
    private TOrgBaseunitServiceImpl orgBaseunitService;

    /**
     * 获取组织集合
     * 可以根据company判断公司、部门，
     * 可以根据描述内容判断物业预算控制部门
     * 可以根据公司id查询
     * getOrgs
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getOrgs", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getOrgs(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        OrgVO vo = BodyDecodeUtil.decodeBody(body, OrgVO.class);
        OrgVO orgVOS = orgBaseunitService.getData(vo);
        result.put("data", orgVOS);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 获取实体成本组织--用于无文本的预算承担部门
     * getOrgs
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getCostEntities", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getCostEntitys(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        OrgVO vo = BodyDecodeUtil.decodeBody(body, OrgVO.class);
        List<OrgVO> orgVOS = orgBaseunitService.getCostEntitys(vo);
        result.put("data", orgVOS);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 实体财务组织
     * 只展示实体财务组织，无文本录入时获取预算承担公司调用
     */
    @RequestMapping(value = "/getEntityFinalOrg", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getEntityFinalOrg(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        OrgVO vo = BodyDecodeUtil.decodeBody(body, OrgVO.class);
        OrgVO orgVO = orgBaseunitService.getEntityFinalOrg(vo);
        result.put("data", orgVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
}
