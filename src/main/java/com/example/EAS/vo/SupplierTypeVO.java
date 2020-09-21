package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:SupplierTypeVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/17 18:44
 */
@Data
public class SupplierTypeVO {

    private String id;
    private String orgId;
    private String standardId;
    private String title;
    private String num;
    private String flevel;
    private String longNumber;
//    分類標準 -1信用 1客戶 2供應商
    private Integer type;
    private List<SupplierTypeVO> supplierTypeVOList;

}
