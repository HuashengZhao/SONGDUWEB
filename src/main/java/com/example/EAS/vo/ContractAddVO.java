package com.example.EAS.vo;

import com.example.EAS.util.DateJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContractAddVO {

    private String id;
    private String contractNature;
    private String contractType;
    private String num;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bizDate;
    private String partB;
    //    原币金额
    private String originalAmount;
    //    本位币金额
    private String amount;
    private String deptName;
    private String currencyName;
    private String remark;

}
