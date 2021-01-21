package com.example.EAS.controller;


import com.example.EAS.service.impl.TConProgrammingcontractServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.ProgramConVO;
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
public class TConProgrammingcontractController {
    @Autowired
    private TConProgrammingcontractServiceImpl service;

    /**
     * 合约规划
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getProgramCon", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getProgramCon(@RequestBody String  body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ProgramConVO vo = BodyDecodeUtil.decodeBody(body, ProgramConVO.class);
        PageBean<ProgramConVO> pageBean= service.getProgramCon(vo);
        result.put("data", pageBean);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
}
