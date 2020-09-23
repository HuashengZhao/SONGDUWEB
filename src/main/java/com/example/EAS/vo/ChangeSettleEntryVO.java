package com.example.EAS.vo;

import com.example.EAS.util.CustomBigDecimalSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChangeSettleEntryVO {

    //    变动项目内容
    private String changeContent;
    //    单价
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal amount;
    //    单位
    private String unit;
    //    合计金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal totalAmount;
    //    工程量
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal proNumber;
    //    备注信息
    private String remark;


}
