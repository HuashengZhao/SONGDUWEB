package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:PaymentTypeVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 16:47
 */
@Data
public class PaymentTypeVO {

    private String id;
    private String title;
    private String num;
    private String description;
    private String type;
    private String isEnabled;
    private List<PaymentTypeVO> paymentTypeVOS;

}
