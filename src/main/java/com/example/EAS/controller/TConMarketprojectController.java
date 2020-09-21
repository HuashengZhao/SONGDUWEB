package com.example.EAS.controller;


import com.example.EAS.service.impl.TConMarketprojectServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.MarketProjectVO;
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
public class TConMarketprojectController {

    @Autowired
    private TConMarketprojectServiceImpl marketprojectService;

    /**
     * 获取立项接口
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMarketProjects", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getMarketProjects(@RequestBody String  body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        MarketProjectVO vo = BodyDecodeUtil.decodeBody(body, MarketProjectVO.class);
        MarketProjectVO marketProject= marketprojectService.getMarketProjects(vo);
        result.put("data", marketProject);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
}
