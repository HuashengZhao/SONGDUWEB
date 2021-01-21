package com.example.EAS.controller;


import com.example.EAS.service.impl.TFdcLanddeveloperServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.PartAVO;
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
 * @since 2020-09-01
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TFdcLanddeveloperController {

    @Autowired
    private TFdcLanddeveloperServiceImpl service;

    /**
     * 甲方接口
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getPartA", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getPartA(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        PartAVO vo = BodyDecodeUtil.decodeBody(body, PartAVO.class);
        PartAVO partAVO = service.getPartA(vo);
        result.put("data", partAVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

}
