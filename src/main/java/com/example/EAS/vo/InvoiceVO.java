package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:InvoiceVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 18:01
 */
@Data
public class InvoiceVO {

    private String id;
    private String inNumber;
    private String type;
    private String bizDate;
    private String amount;
    private String rateAmount;
    private String paymentId;
    private Integer currentPage;
    private Integer pageSize;
    private List<InvoiceVO> invoiceVOList;

}
