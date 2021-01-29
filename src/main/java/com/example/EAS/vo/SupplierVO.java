package com.example.EAS.vo;

import com.example.EAS.util.CustomBigDecimalSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
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
    private String bankAccount;
    private String orgId;
    //    签约单位商定价
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal amount;
    private String remark;

}
