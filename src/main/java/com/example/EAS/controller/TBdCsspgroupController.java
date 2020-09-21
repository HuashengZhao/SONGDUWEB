package com.example.EAS.controller;


import com.example.EAS.service.impl.TBdCsspgroupServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.SupplierTypeVO;
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
public class TBdCsspgroupController {
    @Autowired
    private TBdCsspgroupServiceImpl tBdCsspgroupService;
    /**
     * 供应商类型接口
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getSupplierTypes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getSupplierType(@RequestBody String  body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        SupplierTypeVO vo = BodyDecodeUtil.decodeBody(body, SupplierTypeVO.class);
        SupplierTypeVO supplierTypeVO= tBdCsspgroupService.getSupplierType(vo);
        result.put("data", supplierTypeVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 供应商类型接口
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getSupplierStandard", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getSupplierStandard(@RequestBody String  body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        SupplierTypeVO vo = BodyDecodeUtil.decodeBody(body, SupplierTypeVO.class);
        SupplierTypeVO supplierTypeVO= tBdCsspgroupService.getSupplierStandard(vo);
        result.put("data", supplierTypeVO);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

}
