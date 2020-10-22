package com.example.EAS.service;

import com.example.EAS.model.TBasAttachment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.AttachmentsVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-24
 */
public interface ITBasAttachmentService extends IService<TBasAttachment> {

//    void downLoadFile(HttpServletRequest request, HttpServletResponse response, String webUrl);

    List<AttachmentsVO> uploadAttachment(AttachmentsVO vo) throws IOException;

    void downLoadFile(HttpServletRequest request, HttpServletResponse response,AttachmentsVO vo) throws IOException;
}
