package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:ProgramConVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 13:43
 */
@Data
public class ProgramConVO {

    private String contractTypeId;
    private String projectId;
    private String id;
    private String title;
    private String num;
//    private Integer currentPage;
//    private Integer pageSize;
    private String longNumber;
    private String amount;
    private String balance;
//    关联的单据id  合同与无文本
    private String billId;
    private List<ProgramConVO> programConVOS;
//    能否够关联合同
    private Integer ifBeLinked;

    private Integer currentPage;
    private Integer pageSize;

    private List<String> ids;
}
