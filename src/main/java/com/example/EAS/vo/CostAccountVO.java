package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:CostAccountVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 10:40
 */
@Data
public class CostAccountVO {

    private String marketId;
    private String title;
    private String orgId;
    private String orgName;
    private String num;
    private String longNumber;
    private String assigned;
    private String level;
    private String project;
    private String description;
    private List<CostAccountVO> costAccountVOList;
//    控制单据  CONTRACT  NOTEXTCONTRACT
    private String controlType;

}
