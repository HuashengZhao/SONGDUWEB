package com.example.EAS.vo;

import com.example.EAS.util.CustomBigDecimalSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CWTextBgVO {


    private String id;
    private String headId;
    private String expenseTypeId;
    private String expenseTypeName;
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal requestAMT;
}
