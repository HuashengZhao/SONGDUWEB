package com.example.EAS.controller;


import com.example.EAS.service.impl.TBdSupplierServiceImpl;
import com.example.EAS.util.BodyDecodeUtil;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.R;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.SupplierVO;
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
public class TBdSupplierController {
@Autowired
    private TBdSupplierServiceImpl supplierService;

    /**
     * 获取供应商接口
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getSuppliers", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getSuppliers(@RequestBody String  body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        SupplierVO vo = BodyDecodeUtil.decodeBody(body, SupplierVO.class);
        PageBean<SupplierVO> pageBean= supplierService.getSuppliers(vo);
        result.put("data", pageBean);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
}
