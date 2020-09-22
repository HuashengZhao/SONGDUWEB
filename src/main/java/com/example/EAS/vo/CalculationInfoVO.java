package com.example.EAS.vo;

import com.example.EAS.util.CustomBigDecimalSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculationInfoVO {

    private String id;
    private String parentId;
    private String contractId;
    private String contractNumber;
    private String contractName;
    private String mainSuppId;
    private String mainSuppName;
    //    执行内容 来自变更审批单对应的 变更内容 用 ； 拼接展示
    private String content;
    private String currencyId;
    private String currencyName;
    private String exRate;
    //    测算人
    private String reckonorId;
    private String reckonorName;
    //    测算方式
    private String balanceType;
    //    测算金额/原币金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal costAmount;
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal oriCostAmount;
    //    责任归属部门
    private String dutyOrgID;
    private String dutyOrgName;
    private String description;
}
