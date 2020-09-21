package com.example.EAS.controller;


import com.example.EAS.service.impl.TConBanknumServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.BankNumberVO;
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
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TConBanknumController {

    @Autowired
    private TConBanknumServiceImpl bankNumService;

    /**
     * 获取立项费用归属（成本科目）
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/getBankNum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getBankNum(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        BankNumberVO vo = BodyDecodeUtil.decodeBody(body, BankNumberVO.class);
        PageBean<BankNumberVO> bankNum = bankNumService.getBankNum(vo);
        result.put("data", bankNum);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
}
