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

    @RequestMapping(value = "/getNoTextBills", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getNoTextBills(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        NoTextContractVO vo = BodyDecodeUtil.decodeBody(body, NoTextContractVO.class);
        PageBean<NoTextContractVO> pageBean = service.getNoTextBills(vo);
        result.put("data", pageBean);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 无文本详情信息
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/viewNoTextBill", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R viewNoTextBill(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        NoTextContractVO vo = BodyDecodeUtil.decodeBody(body, NoTextContractVO.class);
        NoTextContractVO notext = service.viewNoTextBill(vo);
        result.put("data", notext);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 保存无文本合同
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveNoTextBill", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R saveNoTextBill(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        NoTextContractVO vo = BodyDecodeUtil.decodeBody(body, NoTextContractVO.class);
        NoTextContractVO notext = service.saveNoTextBill(vo);
        result.put("data", notext);
        result.put("msg", UtilMessage.SAVE_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 提交无文本合同
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/submitNoTextBill", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R submitNoTextBill(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        NoTextContractVO vo = BodyDecodeUtil.decodeBody(body, NoTextContractVO.class);
        NoTextContractVO notext = service.submitNoTextBill(vo);
        result.put("data", notext);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 新增时获取无文本合同编码
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getNoTextNum", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R getNoTextNum(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        NoTextContractVO vo = BodyDecodeUtil.decodeBody(body, NoTextContractVO.class);
        String newNumber = service.getNoTextNum(vo);
        result.put("data", newNumber);
        result.put("msg", UtilMessage.SAVE_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }


    /**
     * 批量刪除无文本合同编码
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteNoTextBills", method =  RequestMethod. POST, produces = "application/json;charset=UTF-8")
    public R deleteNoTextNum(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        NoTextContractVO vo = BodyDecodeUtil.decodeBody(body, NoTextContractVO.class);
        service.deleteNoTextNum(vo);
        result.put("msg", UtilMessage.DELETE_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
}
