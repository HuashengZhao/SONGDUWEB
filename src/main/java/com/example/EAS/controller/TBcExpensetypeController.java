package com.example.EAS.controller;


import com.example.EAS.service.impl.TBcExpensetypeServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.ExpenseTypeVO;
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
public class TBcExpensetypeController {
    @Autowired
    private TBcExpensetypeServiceImpl service;


    /**
     * 费用类别接口
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getExpenseType", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getExpenseType(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ExpenseTypeVO vo = BodyDecodeUtil.decodeBody(body, ExpenseTypeVO.class);
        ExpenseTypeVO vo1 = service.getExpenseType(vo);
        result.put("data", vo1);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

}
