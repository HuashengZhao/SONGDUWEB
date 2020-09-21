package com.example.EAS.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class FileUploadVO {

    private List<MultipartFile> multipartFileList;
    private String fileUrl;
    private String targetPath;
    private String port;
    private String address;
}
