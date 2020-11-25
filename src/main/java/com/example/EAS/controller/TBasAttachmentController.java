package com.example.EAS.controller;


import com.example.EAS.service.impl.TBasAttachmentServiceImpl;
import com.example.EAS.util.R;
import com.example.EAS.util.ServiceException;
import com.example.EAS.util.Util;
import com.example.EAS.util.UtilMessage;
import com.example.EAS.vo.AttachmentsVO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author watson
 * @since 2020-09-24
 */
@RestController
@RequestMapping("/EAS/baseData")
public class TBasAttachmentController {
    @Autowired
    private TBasAttachmentServiceImpl service;

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
    @RequestMapping(value = "/downLoadAttachment", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, AttachmentsVO vo) throws Exception {
        if (Util.isEmpty(vo)) {
            throw new ServiceException(UtilMessage.UNSUPPORTED_DOWNLOAD_FILE);
        }
        service.downLoadFile(request, response, vo);
    }
}
