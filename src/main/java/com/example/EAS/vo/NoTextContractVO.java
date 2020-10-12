package com.example.EAS.vo;

import com.example.EAS.util.CustomBigDecimalSerialize;
import com.example.EAS.util.DateJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class NoTextContractVO {

    //    基参
    private String id;
    private String title;
    private String num;
    private String orgId;
    private String orgName;
    private String orgNumber;
    private String projectId;
    private String projectName;
    private String projectNumber;
    private String state;
    private String creatorId;
    private String creatorName;
    private String auditor;
    private String auditorId;
    //    本位币金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal amount;
    //    原币金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal oriAmount;
    //合同类型
    private String contractTypeId;
    private String contractTypeName;
    //框架合约
    private String programContractId;
    private String programContractName;
    //  币别
    private String currencyId;
    private String currencyName;
    //收款人
    private String receiverName;
    private String personId;
    private String personName;
    private String FReceiveUnitID;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bizDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime auditTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime signDate;


    //    分页
    private Integer currentPage;
    private Integer pageSize;


    private List<String> projectIdList;
}
