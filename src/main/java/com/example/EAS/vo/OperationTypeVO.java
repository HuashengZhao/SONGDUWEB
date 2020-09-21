package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:OperationTypeVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 20:49
 */
@Data
public class OperationTypeVO {

    private String id;
    private String title;
    private String num;
    private Integer isEnabled;
    private List<OperationTypeVO> vos;

}
