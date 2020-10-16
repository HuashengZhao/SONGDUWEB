package com.example.EAS.vo;


import lombok.Data;

@Data
public class ContractDetailVO {

    private String id;
    private String contractTypeId;

//    名称
    private String detailInfo;

//    内容
    private String content;

    private String description;

}
