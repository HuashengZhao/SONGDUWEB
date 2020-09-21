package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:ProjectVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 9:21
 */
@Data
public class ProjectVO {

    private String id;
    private String orgId;
//    名称 name=>title
    private String title;
    private String num;
    private Integer isLeaf;
    private String taxInfo;
    private String longNumber;
    private List<ProjectVO> projectVOList;

}