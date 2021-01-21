package com.example.EAS.controller;


import com.example.EAS.service.impl.TFdcContracttypeServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.ContractTypeVO;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2020-08-29
 */
@Slf4j
@RestController
@RequestMapping("/EAS/baseData")
public class TFdcContracttypeController {

    @Autowired
    private TFdcContracttypeServiceImpl contracttypeService;

    /**
     * 获取合同类型
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/getContractType", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getContractType(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ContractTypeVO vo = BodyDecodeUtil.decodeBody(body, ContractTypeVO.class);
        ContractTypeVO contractTypeVOS = contracttypeService.getContractType(vo);
        result.put("data", contractTypeVOS);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
    /**
     * 新增合同时返回生成的编码
     * 生成合同编码规则额："web"+组织编码+合同类型编码+四位数流水号
     */
    @RequestMapping(value = "/getNewContractNumber", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getNewContractNumber(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ContractTypeVO vo = BodyDecodeUtil.decodeBody(body, ContractTypeVO.class);
        String newContractNumber = contracttypeService.getNewContractNumber(vo);
        result.put("data", newContractNumber);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

}
