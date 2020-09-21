package com.example.EAS.controller;


import com.example.EAS.service.impl.TFdcCostaccountServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.CostAccountVO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

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
public class TFdcCostaccountController {

    @Autowired
    private TFdcCostaccountServiceImpl costAccountService;

    /**
     * 获取立项费用归属（成本科目）
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/getCostAccount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getCostAccount(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        CostAccountVO vo = BodyDecodeUtil.decodeBody(body, CostAccountVO.class);
        CostAccountVO costAccountVO = costAccountService.getCostAccount(vo);
        result.put("data", costAccountVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 新增合同时 获取未曾关联的费用归属
     * 根据 合同单据表中  fmarketprojectid  跟 fmpcostaccountid  查看 是否存在关联
     */
    @RequestMapping(value = "/unUseCostAccount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R unUseCostAccount(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        CostAccountVO vo = BodyDecodeUtil.decodeBody(body, CostAccountVO.class);
        List<CostAccountVO> costAccountVOS = costAccountService.unUseCostAccount(vo);
        result.put("data", costAccountVOS);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

}
