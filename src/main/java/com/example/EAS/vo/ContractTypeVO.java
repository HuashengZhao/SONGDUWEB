package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:ContractTypeVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 13:08
 */
@Data
public class ContractTypeVO {

    private String orgId;
    private String title;
    private String num;
    private String longNumber;
    private String id;
    private String orgType;
    private String isCost;
    private String enabled;
    private String description;
//自动生成的合同编码 web+组织编码+合同类型编码+四位数序列号
    private String contractNumber;
//    操作类型  1：新增合同 0：查询
    private Integer operatType;
    private List<ContractTypeVO> contractTypeVOList;

}
