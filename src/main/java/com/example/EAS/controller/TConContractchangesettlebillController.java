package com.example.EAS.controller;


import com.example.EAS.service.impl.TConChangeauditbillServiceImpl;
import com.example.EAS.service.impl.TConContractchangesettlebillServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.ChangeAuditVO;
import com.example.EAS.vo.ChangeSettleVO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author watson
 * @since 2020-09-22
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TConContractchangesettlebillController {

    @Autowired
    private TConContractchangesettlebillServiceImpl  service;

    /**
     * 获取变更确认单列表
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getChangeSettleList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getChangeSettleList(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ChangeSettleVO vo = BodyDecodeUtil.decodeBody(body, ChangeSettleVO.class);
        PageBean<ChangeSettleVO> pageBean = service.getChangeSettleList(vo);
        result.put("data", pageBean);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 查看变更确认单
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/viewChangeSettle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R viewChangeSettle(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ChangeSettleVO vo = BodyDecodeUtil.decodeBody(body, ChangeSettleVO.class);
        ChangeSettleVO settleVO = service.viewChangeSettle(vo);
        result.put("data", settleVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
}
