package com.example.EAS.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @PackageName:com.example.EAS.dto
 * @ClassName:OrgDto
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/17 10:43
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgDto {
    private String name;

    private String number;

    private String id;
    /*
    是否公司  0否 1 是
     */
    private String company;
    /*
    是否成本中心
     */
    private String cost;
}
