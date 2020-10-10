package com.example.EAS.vo;

import com.example.EAS.util.CustomBigDecimalSerialize;
import com.example.EAS.util.DateJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PayRequestBillVO {

    //     基参
    private String id;
    private String title;
    private String num;
    private String orgId;
    private String orgNumber;
    private String orgName;
    private String projectId;
    private String projectNumber;
    private String projectName;
    private String creatorId;
    private String creatorName;
    private String auditorId;
    private String auditorName;

    //    付款类别
    private String paymentTypeName;
    //    状态
    private String state;
    //    合同信息
    private String contractId;
    private String contractNumber;
    private String contractName;
    //   收款单位信息
    private String supplierId;
    private String supplierName;
    //  币别
    private String currencyId;
    private String currencyName;
    //    扣款金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal deductAmt;
    //    本位币
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal amount;
    //    原币
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal oriAmount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime auditTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bizDate;

    //    银行
    private String recBank;
    private String recAccount;
    //    备注
    private String remark;

    //    分页参数
    private Integer currentPage;
    private Integer pageSize;
}
