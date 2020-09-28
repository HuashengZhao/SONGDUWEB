package com.example.EAS.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContractAddVO {

    private String id;
    private String contractNature;
    private String contractType;
    private String num;
    private String title;
    private LocalDateTime bizDate;
    private String partB;
//    原币金额
    private String originalAmount;
//    本位币金额
    private String amount;

}
