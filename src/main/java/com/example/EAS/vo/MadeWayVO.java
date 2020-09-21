package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:MadeWayVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 11:21
 */
@Data
public class MadeWayVO {

    private String id;
    private String orgId;
    private String title;
    private String num;
    private String description;
    private Integer isEnabled;
    private List<MadeWayVO> madeWayVOList;

}
