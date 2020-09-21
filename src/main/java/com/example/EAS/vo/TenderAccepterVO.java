package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:TenderAccepterVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 13:26
 */
@Data
public class TenderAccepterVO {

    private String orgId;
    private String title;
    private String num;
    private String seq;
    private String votes;
    private String lastAmount;
    private String afterFixAmount;
    private String bidAmount;
    private String consCost;
    private String description;
    private String supplierName;
    private String isLowest;
    private List<TenderAccepterVO> accepterVOList;

}
