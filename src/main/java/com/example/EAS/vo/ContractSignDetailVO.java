package com.example.EAS.vo;


import lombok.Data;

@Data
public class ContractSignDetailVO {

//    含税金额
    private String totalAmount;
    private String id;
//    不含税金额
    private String amount;

    private String rate;

    private String description;

//    货物或应税劳务名称
    private String detail;
}
