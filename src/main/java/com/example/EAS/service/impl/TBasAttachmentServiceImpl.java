package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.mapper.TConSupplierapplyMapper;
import com.example.EAS.model.TBasAttachment;
import com.example.EAS.service.ITBasAttachmentService;
import com.example.EAS.util.EasFileDownLoadUtil;
import com.example.EAS.util.FtpUtil;
import com.example.EAS.vo.AttachmentsVO;
import com.example.EAS.vo.PersonsVO;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-24
 */
@Log4j2
@Service
public class TBasAttachmentServiceImpl extends ServiceImpl<TBasAttachmentMapper, TBasAttachment> implements ITBasAttachmentService {

    @Autowired
    private TBasAttachmentMapper mapper;
    @Autowired
    private FtpUtil ftpUtil;

    @Autowired
    private TConSupplierapplyMapper supplierapplyMapper;
    @Autowired
    private EasFileDownLoadUtil easFileDownLoadUtil;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<AttachmentsVO> uploadAttachment(AttachmentsVO vo) throws IOException {
        List<AttachmentsVO> attachmentsVOS = new ArrayList<>();
        String person = vo.getPerson();
        List<MultipartFile> multipartFileList = vo.getMultipartFileList();
        if (multipartFileList == null || multipartFileList.size() == 0) {
            return null;
        }
        boolean b = false;
        for (MultipartFile multipartFile : multipartFileList) {
            long size = multipartFile.getSize();
            String size1 = String.valueOf(size);
            String originalFilename = multipartFile.getOriginalFilename();
            String contentType = multipartFile.getContentType();
            String[] split = originalFilename.split("\\.");
            String fileType = originalFilename.split("\\.")[originalFilename.split("\\.").length - 1];
            String name = originalFilename.replace("." + fileType, "");
            InputStream inputStream = multipartFile.getInputStream();
            String type = multipartFile.getContentType();
            String num = null;

            Date date = new Date();
            String format = formatter.format(date);
            String s = format.replaceAll("-", "/");
            StringBuffer stringBuffer = new StringBuffer();
            long l = System.currentTimeMillis();
            String currentTime = String.valueOf(l);
//            ftp/2020/9/18/010000000/
            String filePath = stringBuffer.append("/").append(s).append("/").append(currentTime).toString();
            //TODO处理业务
//            生产随机id 用来对应ftp存储文件名
            String fileUUID = UUID.randomUUID().toString();
            StringBuffer sbf = new StringBuffer();
            fileUUID = sbf.append(fileUUID).append(".").append(fileType).toString();
            Logger logger = LoggerFactory.getLogger(TBasAttachmentServiceImpl.class);
            logger.info("WEB" + filePath);
            logger.info(fileUUID);
            boolean b1 = ftpUtil.uploadFile("WEB" + filePath, fileUUID, inputStream);
//            ftp/2020/9/18/010000000/zbcdasdasdasdasd.txt
            String webUrl = String.valueOf(new StringBuffer().append("WEB").append(filePath).append("/").append(fileUUID));
//            创建人
            String personName1 = null;
            if (vo.getPerson() != null) {
                String person1 = vo.getPerson();
                PersonsVO personsVO = supplierapplyMapper.selectPersonByNum(person1);
                if (personsVO != null) {
                    personName1 = personsVO.getPersonName();
                }
            }
//附件编码
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format1 = simpleDateFormat.format(date);
            String dateString = format1.replaceAll("-", "");
            DecimalFormat decimalFormat = new DecimalFormat("00000000");
            Integer value = 0;
            Integer numberRecord = supplierapplyMapper.selectFileNumberRecord();
            value = value + numberRecord + 1;

            numberRecord = numberRecord + 1;
            supplierapplyMapper.updateFileNumrecord(numberRecord);
            String numString = decimalFormat.format(value);
            StringBuffer sb1 = new StringBuffer();
            StringBuffer append = sb1.append(dateString).append(numString);
            String fileNum = append.toString();
//              如果上传成功 返回文件类型 url 大小 文件名
//            String s1 = "172.17.4.60:21/ftp";
            StringBuffer sb = new StringBuffer();
//            String url = sb.append(s1).append(filePath).append("/").append(originalFilename).toString();
            AttachmentsVO attachmentsVO = new AttachmentsVO();
//            attachmentsVO.setFileUrl(url);
            attachmentsVO.setWebUrl(webUrl);
            attachmentsVO.setContentType(contentType);
            attachmentsVO.setNum(fileNum);
            attachmentsVO.setPersonName(personName1);
            attachmentsVO.setPerson(person);
            attachmentsVO.setTitle(originalFilename);
            attachmentsVO.setFileSize(size1);
            attachmentsVO.setType(type);

            attachmentsVO.setFileUUID(fileUUID);
            attachmentsVO.setFileType(fileType);
            attachmentsVO.setOriginalFilename(originalFilename);
            attachmentsVO.setDescription("WEB");
            Date date1 = new Date();
            String format2 = formatter.format(date1);
            attachmentsVO.setCreateTime(format2);
            attachmentsVOS.add(attachmentsVO);
        }
        return attachmentsVOS;
    }

    @Override
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response, AttachmentsVO vo) throws IOException {
//        区分方法 根据 num  只有web的有num   eas 跟天联云 根据 fftpid 判断  eas没有fftpid  定位目标 用 weburl
        String webUrl = vo.getWebUrl();
        String fileName = webUrl.split("/")[webUrl.split("/").length - 1];
        String description = vo.getDescription();
        String storgeType = vo.getStorgeType();
        String ftpId = vo.getFtpId();
//        if (Util.isNotEmpty(description) && description.equals("WEB")) {
        String replace = webUrl.replace(fileName, "");
//          来自web ftp服务器
        ftpUtil.exportOutputStream(request, response, replace, fileName);
//        } else if (Util.isNotEmpty(storgeType) && storgeType.equals("1")) {
//            String replace = webUrl.replace(fileName, "");
////             天联云
//            ftpUtil.exportTLYOS(request, response, replace, fileName);
//        } else if (Util.isNotEmpty(storgeType) && storgeType.equals("2")) {
////             eas附件
//            easFileDownLoadUtil.login();
//            Connection connection = easFileDownLoadUtil.getConnection();
//            easFileDownLoadUtil.copyFile(connection, webUrl, response);
//        }
    }
}

