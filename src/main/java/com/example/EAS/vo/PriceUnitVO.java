package com.example.EAS.vo;

import com.example.EAS.util.CustomBigDecimalSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 比价单位
 */
@Data
public class PriceUnitVO {
    //比价单位
    private String unit;

    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal amount;

    private String seq;

    private String remark;
}
