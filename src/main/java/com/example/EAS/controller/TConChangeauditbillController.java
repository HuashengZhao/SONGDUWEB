package com.example.EAS.controller;


import com.example.EAS.service.impl.TConChangeauditbillServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.ChangeAuditVO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author watson
 * @since 2020-09-22
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TConChangeauditbillController {
    @Autowired
    private TConChangeauditbillServiceImpl service;

    /**
     * 获取变更审批单列表
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getChangeAuditList", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getChangeAuditList(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ChangeAuditVO vo = BodyDecodeUtil.decodeBody(body, ChangeAuditVO.class);
        PageBean<ChangeAuditVO> pageBean = service.getChangeAuditList(vo);
        result.put("data", pageBean);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 查看变更审批单
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/viewChangeAudit", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R viewChangeAudit(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ChangeAuditVO vo = BodyDecodeUtil.decodeBody(body, ChangeAuditVO.class);
        ChangeAuditVO changeAuditVO = service.viewChangeAudit(vo);
        result.put("data", changeAuditVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

}
