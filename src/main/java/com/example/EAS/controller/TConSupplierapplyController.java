package com.example.EAS.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.EAS.service.impl.TConSupplierapplyServiceImpl;
import com.example.EAS.util.*;
import com.example.EAS.vo.AttachmentsVO;
import com.example.EAS.vo.SupplierApplyVO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TConSupplierapplyController {

    @Autowired
    private TConSupplierapplyServiceImpl service;
    @Autowired
    private FtpUtil ftpUtil;

    /**
     * 获取/查看供应商申请
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/getSupplierApply", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getSupplierApply(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        SupplierApplyVO vo = BodyDecodeUtil.decodeBody(body, SupplierApplyVO.class);
        PageBean<SupplierApplyVO> pageBean = service.getSupplierApply(vo);
        result.put("data", pageBean);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }


    /**
     * 新增供应商申请
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/addSupplierApply", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R addSupplierApply(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        SupplierApplyVO vo = BodyDecodeUtil.decodeBody(body, SupplierApplyVO.class);
         JSONObject obj = service.addSupplierApply(vo);
        result.put("msg",obj.get("message") );
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * delete
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/deleteSupplierApply", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R deleteSupplierApply(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        SupplierApplyVO vo = BodyDecodeUtil.decodeBody(body, SupplierApplyVO.class);
        service.deleteSupplierApply(vo);
        result.put("msg", UtilMessage.DELETE_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 获取新供应商申请单编码
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getSupplierNewNum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R getSupplierNewNum(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        SupplierApplyVO vo = BodyDecodeUtil.decodeBody(body, SupplierApplyVO.class);
        String number = service.getNewNumber(vo);
        result.put("data", number);
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }
//    /**
//     * 判断是否重复
//     */
//    @RequestMapping(value = "/whetherRepeat", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public R wetherRepet(@RequestBody String body) throws Exception {
//        HashMap<String, Object> result = new HashMap<>(10);
//        SupplierApplyVO vo = BodyDecodeUtil.decodeBody(body, SupplierApplyVO.class);
//         Integer status = service.whetherRepeat(vo);
//        result.put("data", status);
//        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
//        result.put("code", HttpStatus.SC_OK);
//        return R.ok(result);
//    }

    /**
     * submit supplier Apply
     */
    @RequestMapping(value = "/supplierSubmit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R supplierSubmit(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        SupplierApplyVO vo = BodyDecodeUtil.decodeBody(body, SupplierApplyVO.class);
        SupplierApplyVO supplierApplyVO = service.supplierSubmit(vo);
        String message = supplierApplyVO.getMessage();
        if (message!=null){
            result.put("msg", message);
            result.put("code", HttpStatus.SC_NO_CONTENT);
            return R.ok(result);
        }
        result.put("msg", UtilMessage.GET_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }


    /**
     * update supplier Apply
     */
    @RequestMapping(value = "/updateSupplierApply", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R supllierUpdate(@RequestBody String body) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
        SupplierApplyVO vo = BodyDecodeUtil.decodeBody(body, SupplierApplyVO.class);
        JSONObject object = service.suplierUpdate(vo);
        result.put("msg", UtilMessage.SAVE_MSG_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * 上传附件
     *
     * @param
     * @return
     */

    @RequestMapping(value = "/uploadAttachment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R uploadAttachment(AttachmentsVO vo) throws Exception {
        HashMap<String, Object> result = new HashMap<>(10);
//        AttachmentsVO vo = BodyDecodeUtil.decodeBody(body, AttachmentsVO.class);
        List<AttachmentsVO> attachmentsVOS = service.uploadAttachment(vo);
        result.put("data", attachmentsVOS);
        result.put("msg", UtilMessage.UPLOAD_SUCCESS);
        result.put("code", HttpStatus.SC_OK);
        return R.ok(result);
    }

    /**
     * download附件
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/downLoadAttachment",method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response,String webUrl,String fileUUID) throws Exception{
        if (Util.isEmpty(fileUUID)||Util.isEmpty(webUrl)){
            throw  new ServiceException(UtilMessage.UNSUPPORTED_DOWNLOAD_FILE);
        }
        service.downLoadFile(request,response,webUrl,fileUUID);
    }
}
