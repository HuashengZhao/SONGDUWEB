package com.example.EAS.controller;


import com.example.EAS.service.impl.TBasAttachmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
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
     * download附件
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/downLoadEASAttachment",method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, String webUrl) throws Exception{
        service.downLoadFile(request,response,webUrl);
    }
}
