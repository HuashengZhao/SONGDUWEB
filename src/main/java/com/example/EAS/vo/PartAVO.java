package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:PartAVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 13:20
 */
@Data
public class PartAVO {

    private String id;
    private String orgId;
    private String title;
    private String num;
    private Integer isEnabled;
    private String description;
    private List<PartAVO> partAVOList;


}
