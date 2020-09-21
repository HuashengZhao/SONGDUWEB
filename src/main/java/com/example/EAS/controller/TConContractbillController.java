package com.example.EAS.controller;


import com.example.EAS.service.impl.TConContractbillServiceImpl;
import com.example.EAS.util.*;
import com.example.EAS.vo.ContractDetailVO;
import com.example.EAS.vo.ContractVO;
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
 *  前端控制器
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TConContractbillController {
    @Autowired
    private TConContractbillServiceImpl service;

    /**
     * 获取合同
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/getContractList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getContractList(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ContractVO vo = BodyDecodeUtil.decodeBody(body, ContractVO.class);
        PageBean<ContractVO> pageBean = service.getContractList(vo);
        result.put("data", pageBean);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
    /**
     * 保存、修改合同单据
     */
    @RequestMapping(value = "/saveContractBill", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R saveContractBill(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ContractVO vo = BodyDecodeUtil.decodeBody(body, ContractVO.class);
        ContractVO contractVO = service.saveContractBill(vo);
        if (Util.isNotEmpty(contractVO.getMessage())){
            result.put("msg", contractVO.getMessage());
            result.put("code", HttpStatus.SC_METHOD_FAILURE);
        }
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 根据合同类型获取对应的合同详情信息集合
     */
    @RequestMapping(value = "/getContractDetails", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getContractDetails(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ContractDetailVO vo = BodyDecodeUtil.decodeBody(body, ContractDetailVO.class);
        List<ContractDetailVO> detailVOS = service.getContractDetails(vo);
        result.put("data", detailVOS);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 合同查看
     */
    @RequestMapping(value = "viewContractBill", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R viewContractBill(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ContractVO vo = BodyDecodeUtil.decodeBody(body, ContractVO.class);
        ContractVO contractVO = service.viewContractBill(vo);
        result.put("data", contractVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
    /**
     * 合同提交oa
     */
    @RequestMapping(value = "submitToOa", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R submitToOa(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ContractVO vo = BodyDecodeUtil.decodeBody(body, ContractVO.class);
        service.submitToOa(vo);
        result.put("msg", UtilMessage.SUBMIT_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
    /**
     * 批量删除合同
     */
    @RequestMapping(value = "deleteContractBills", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R deleteContractBills(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ContractVO vo = BodyDecodeUtil.decodeBody(body, ContractVO.class);
        service.deleteContractBills(vo);
        result.put("msg", UtilMessage.SUBMIT_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }


}
