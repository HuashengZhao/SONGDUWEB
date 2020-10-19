package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrgVO {

    private String id;
    private Integer isLeaf;
    private String num;
    private String longNumber;
    private String title;
    //    是否财务室体组织 0否1是
    private Integer isCompany;
    private String description;
    private Long flevel;
    //    成本中心组织单元扩展id
    private String parentId;
    private String costCenterId;
    //    是否成本中心
    private Integer isCost;
    //    是否包含下级 0否1是
    private Integer hasNext;
    //    是否实体组织 0 否 1 是
    private Integer isBizUnit;

    private List<OrgVO> orgVOList;
    private List<OrgVO> children;

    //    成本中心類型 直接生产部门=0,辅助生产部门=1,管理部门=2,销售部门=3
    private String costCenterType;
    //    簡稱
    private String simpleName;
}