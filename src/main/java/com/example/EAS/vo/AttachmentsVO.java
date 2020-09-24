package com.example.EAS.vo;

import com.example.EAS.util.DateJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
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
    private String easId;
    //    员工编号
    private String person;
    //    员工名称
    private String personName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime createTime;
    /**
     * 上传获取url接口 ，传文件集合
     */
    private List<MultipartFile> multipartFileList;

    private String fboid;

}
