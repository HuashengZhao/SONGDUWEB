package com.example.EAS.controller;


import com.example.EAS.service.impl.TConPayrequestbillServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.PayRequestBillVO;
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
 *  前端控制器
 * </p>
 *
 * @author watson
 * @since 2020-10-09
 */
@Slf4j
@RestController
@RequestMapping("/EAS/baseData")
public class TConPayrequestbillController {
    @Autowired
    private TConPayrequestbillServiceImpl service;

    /**
     * 合同付款申请单
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getPayRequestBill", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getPayRequestBill(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        PayRequestBillVO vo = BodyDecodeUtil.decodeBody(body, PayRequestBillVO.class);
        PageBean<PayRequestBillVO> pageBean = service.getPayRequestBillVO(vo);
        result.put("data", pageBean);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 查看付款申请单
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/viewPayRequestBill", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R viewPayRequestBill(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        PayRequestBillVO vo = BodyDecodeUtil.decodeBody(body, PayRequestBillVO.class);
        PayRequestBillVO billVO = service.viewPayRequestBill(vo);
        result.put("data", billVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
}
