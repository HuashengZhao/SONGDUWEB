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
    private List<ProgramConVO> programConVOS;

}
