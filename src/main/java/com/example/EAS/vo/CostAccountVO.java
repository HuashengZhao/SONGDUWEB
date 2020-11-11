package com.example.EAS.vo;

import com.example.EAS.util.CustomBigDecimalSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:CostAccountVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 10:40
 */
@Data
public class CostAccountVO {

    private String id;
    private String marketId;
    private String title;
    private String orgId;
    private String orgName;
    private String num;
    private String longNumber;
    private String assigned;
    private String level;
    private String project;
    private String projectName;
    private String description;
    private List<CostAccountVO> costAccountVOList;
    private List<String> costIDs;
    //    控制单据  CONTRACT  NOTEXTCONTRACT
    private String controlType;
    //  费用归属余额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal balance;
    //    营销立项是否负数立项
    private Integer isSub;
    //    立项金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal mpAmount;
}
