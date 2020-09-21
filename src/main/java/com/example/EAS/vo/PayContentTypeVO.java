package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:PayContentTypeVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 16:04
 */
@Data
public class PayContentTypeVO {

    private String id;
    private String title;
    private String num;
    private String description;
    private Integer flevel;
    private String contractTypeId;
    private Integer isEnabled;
    private List<PayContentTypeVO> payContentTypeVOList;

}
