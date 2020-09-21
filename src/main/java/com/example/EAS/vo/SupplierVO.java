package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.model
 * @ClassName:Supplier
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/17 18:57
 */
@Data
public class SupplierVO {

    private String id;
    private String title;
    private String num;
    private String longNumber;
    private String memNumber;
    private String supplierTypeId;
    private List<SupplierVO> supplierVOList;
    private Integer currentPage;
    private Integer pageSize;
//    是否集團內部 0否1是
    private Integer isInner;
    private String taxNum;
    private String bank;
    private String bankNum;
    private String orgId;

}
