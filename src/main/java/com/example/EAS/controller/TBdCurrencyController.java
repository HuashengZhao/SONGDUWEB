package com.example.EAS.controller;


import com.example.EAS.service.impl.TBdCurrencyServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.CurrencyVO;
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
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TBdCurrencyController {

    @Autowired
    private TBdCurrencyServiceImpl service;

    /**
     * 获取币别接口
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCurrency", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getCurrency(@RequestBody String  body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        CurrencyVO vo = BodyDecodeUtil.decodeBody(body, CurrencyVO.class);
        CurrencyVO currencyVO= service.getCurrency(vo);
        result.put("data", currencyVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
}
