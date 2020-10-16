package com.example.EAS.service.impl;

import com.example.EAS.model.TBasAttachment;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.service.ITBasAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-24
 */
@Service
public class TBasAttachmentServiceImpl extends ServiceImpl<TBasAttachmentMapper, TBasAttachment> implements ITBasAttachmentService {

    @Autowired
    private TBasAttachmentMapper mapper;
    @Autowired
    private FtpUtil ftpUtil;

    @Override
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response, String webUrl) {
        String fileName = webUrl.split("/")[webUrl.split("/").length - 1];
        webUrl = webUrl.replace(fileName, "");
        String replace = webUrl.replace("/data", "");
        ftpUtil.downLoadEASAttachments(request, response, replace, fileName);
    }

}
