package com.example.EAS.vo;

import lombok.Data;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:BaseDataVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/17 10:13
 */
@Data
public class BaseDataVO {

    private String title;
    private String number;
    private String longNumber;
    private String id;
//    是否公司  0否 1 是
    private String company;
    private String cost;
    private String description;

}
