package com.example.EAS.controller;


import com.example.EAS.service.impl.TFdcCurprojectServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.ProjectVO;
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
 * @since 2020-08-30
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TFdcCurprojectController {
    @Autowired
    private TFdcCurprojectServiceImpl curprojectService;

    /**
     * get projects
     * 获取工程项目
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/getProjects", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getProjects(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        ProjectVO vo = BodyDecodeUtil.decodeBody(body, ProjectVO.class);
        ProjectVO projects = curprojectService.getProjects(vo);
        result.put("data", projects);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);

    }

}
