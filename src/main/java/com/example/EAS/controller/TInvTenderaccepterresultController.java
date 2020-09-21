package com.example.EAS.controller;


import com.example.EAS.service.impl.TInvTenderaccepterresultServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.TenderAccepterVO;
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
public class TInvTenderaccepterresultController {

    @Autowired
    private TInvTenderaccepterresultServiceImpl service;

    /**
     * 获取中标审批接口
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getTenderAccepter", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getTenderAcc(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        TenderAccepterVO vo = BodyDecodeUtil.decodeBody(body, TenderAccepterVO.class);
        TenderAccepterVO accepterVO = service.getTenderAcc(vo);
        result.put("data", accepterVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
}
