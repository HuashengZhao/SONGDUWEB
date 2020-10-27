package com.example.EAS.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Data
public class AttachmentsVO {

    @JsonIgnore
    private InputStream inputStream;
    private String fileUUID;
    private String num;
    private String title;
    private String originalFilename;
    //    类型
    private String type;
    private String contentType;
    private String fileSize;
    private String fileUrl;
    //    返回前段url 用作修改时 绝对路径
    private String webUrl;
    //    后缀名
    private String fileType;
//    单据id
    private String easId;
    //    员工编号
    private String person;
    //    员工名称
    private String personName;
    private String createTime;
    private String creatorName;
//    ftpId
    private String ftpId;
    /**
     * 上传获取url接口 ，传文件集合
     */
    private List<MultipartFile> multipartFileList;

    private String fboid;
//    附件预览地址
    private String attLink;

    private String description;

//    eas中附件来源类型
    private String storgeType;

}
