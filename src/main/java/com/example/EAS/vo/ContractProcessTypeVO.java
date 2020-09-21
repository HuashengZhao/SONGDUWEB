package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:BaseDataVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/17 10:13
 */
@Data
public class ContractProcessTypeVO {

    private String id;
    private String title;
    private String num;
    private String description;
    private String contractTypeId;
    private Integer isEnabled;
    private List<ContractProcessTypeVO> contractProcessTypeVOList;

}
