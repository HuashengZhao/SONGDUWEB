package com.example.EAS.service;

import com.example.EAS.model.TBasAttachment;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-24
 */
public interface ITBasAttachmentService extends IService<TBasAttachment> {

    void downLoadFile(HttpServletRequest request, HttpServletResponse response, String webUrl);

}
