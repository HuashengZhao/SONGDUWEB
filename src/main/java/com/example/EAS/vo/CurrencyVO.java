package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:CurrencyVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 20:55
 */
@Data
public class CurrencyVO {

    private String id;
    private String title;
    private String num;
    private String description;
    private String baseUnit;
    private List<CurrencyVO> currencyVOS;

}
