package com.example.EAS.controller;


import com.example.EAS.service.impl.TBasMultiapproveServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.MultiApproveVO;
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
 * @since 2020-09-02
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TBasMultiapproveController {

    @Autowired
    private TBasMultiapproveServiceImpl service;

    /**
     * 看流程审批结果
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMultiApprove", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public R getMultiApprove(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        MultiApproveVO vo = BodyDecodeUtil.decodeBody(body, MultiApproveVO.class);
        PageBean<MultiApproveVO> pageBean = service.getMultiApprove(vo);
        result.put("data", pageBean);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
}
