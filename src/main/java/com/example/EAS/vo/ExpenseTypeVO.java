package com.example.EAS.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:ExpenseTypeVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 20:52
 */
@Data
public class ExpenseTypeVO {

    private String operationTypeId;
    private String id;
    private String num;
    private String title;
    private String unitName;
    private String typeSimpleName;
    private List<ExpenseTypeVO> expenseTypeVOList;

}
