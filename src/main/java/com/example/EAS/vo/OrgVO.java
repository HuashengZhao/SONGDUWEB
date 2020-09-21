package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrgVO {

    private String id;
//    成本中心组织单元扩展id
    private String cbid;
    private String parentId;
    private Integer isLeaf;
    private String num;
    private String longNumber;
    private String title;
    private Integer isCompany;
    private String description;
    private Long flevel;
    private Integer isCost;
//    是否包含下级 0否1是
    private Integer hasNext;

    private List<OrgVO> orgVOList;
    private List<OrgVO> children;
}