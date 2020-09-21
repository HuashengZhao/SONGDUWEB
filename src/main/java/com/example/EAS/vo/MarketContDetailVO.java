package com.example.EAS.vo;

import lombok.Data;

/**
 * 营销项目分摊明细
 */
@Data
public class MarketContDetailVO {

//    t_con_contractMarketEntry

//    后评估审核
    private Integer isjt;
    private String id;
//    预计发生年月
    private String fsdate;
//    发生比例
    private String rate;
//    发生金额
    private String amount;
//    发生内容
    private String content;
//    备注
    private String remark;

}
