package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:BankNum
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 11:07
 */

@Data
public class BankNumberVO {

    private String id;
    private String orgId;
    private String title;
    private String num;
    private String orgName;
    private String description;
    private String isEnabled;
    private Integer currentPage;
    private Integer pageSize;
    private List<BankNumberVO> bankNumberVOList;

}
