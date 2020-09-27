package com.example.EAS.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.EAS.service.impl.BaseDataServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2020-08-17
 */
@Slf4j
@RestController
@RequestMapping("/EAS/baseData")
public class BaseDataController {
    @Autowired
    private BaseDataServiceImpl service;

    /**
     * 流程回掉接口
     */
    @RequestMapping(value = "/acceptHandle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject acceptHandle(@RequestBody JSONObject body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        JSONObject obj = service.acceptHandle(body);
        return obj;
    }

}
