package com.example.EAS.controller;


import com.example.EAS.service.impl.TConContractwithouttextServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.NoTextContractVO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 * 无文本合同
 * </p>
 *
 * @author watson
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TConContractwithouttextController {

    @Autowired
    private TConContractwithouttextServiceImpl service;

    /**
     * 无文本合同列表
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getNoTextBills", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getNoTextBills(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        NoTextContractVO vo = BodyDecodeUtil.decodeBody(body, NoTextContractVO.class);
        PageBean<NoTextContractVO> pageBean = service.getNoTextBills(vo);
        result.put("data", pageBean);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }


}
