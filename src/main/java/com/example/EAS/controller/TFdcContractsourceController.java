package com.example.EAS.controller;


import com.example.EAS.service.impl.TFdcContractsourceServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.MadeWayVO;
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
 * @since 2020-09-01
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TFdcContractsourceController {

    @Autowired
    private TFdcContractsourceServiceImpl service;

    /**
     * 形成方式
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMadeWay", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getMadeWay(@RequestBody String  body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        MadeWayVO vo = BodyDecodeUtil.decodeBody(body, MadeWayVO.class);
        MadeWayVO madeWayVO= service.getMadeWay(vo);
        result.put("data", madeWayVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

}