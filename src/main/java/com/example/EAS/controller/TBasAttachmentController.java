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
     * 宋都eas web端上传
     * 地址172.17.4.60 ，端口：21 ，登录名：adminftp ，密码：sdjt2020@# ，文件夹 /ftp ；
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
     * 附件下载需要区分三种来源
     * 1.来自web上传的存在宋都ftp服务器上的附件，地址172.17.4.60 ，端口：21 ，登录名：adminftp ，密码：sdjt2020@# ，文件夹 /ftp ；
     * 2.来自于金蝶eas存储在linux文档中的附件 测试库地址：172.17.4.69 ，端口：22 ，登录名：root ，密码：XuNaiRui7788@# ，
     * 正式库地址：172.17.4.63 ，端口：22 ，登录名：root ，密码：XuNaiRui7788@# ；
     * 3.天联云的附件，在eas T_BAS_ATTACHMENT表中存有文件路径 根据路径去目标ftp上取附件 地址：172.17.16.31  ，端口: 21,登录名：admin ，密码：sdjt1234@# ；
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/downLoadAttachment", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, String webUrl, String fileUUID) throws Exception {
        if (Util.isEmpty(fileUUID) || Util.isEmpty(webUrl)) {
            throw new ServiceException(UtilMessage.UNSUPPORTED_DOWNLOAD_FILE);
        }
        service.downLoadFile(request, response, webUrl, fileUUID);
    }
}
