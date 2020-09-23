package com.example.EAS.vo;

import com.example.EAS.util.CustomBigDecimalSerialize;
import com.example.EAS.util.DateJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:ChangeSettleVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/9/23 10:50
 */
@Data
public class ChangeSettleVO {

    private String id;
    private String orgId;
    private String orgName;
    //    保存=1SAVED,已提交=2SUBMITTED,审批中=3AUDITTING,已审批=4AUDITTED,终止=5CANCEL,已下发=7ANNOUNCE,已签证=8VISA,作废=9INVALID,已上报=10PUBLISH,被打回=11BACK,修订中=12REVISING,已修订=12REVISE,已确认=13CONFIRMED
    private String state;
    private String num;
    private String title;
    //    承包单位  施工单位
    private String supplierName;
    //    完成描述
    private String colseDesc;
    //    是否已完成施工
    private Integer isFinish;
    //    是否费用已核定
    private Integer isFee;
    //    无效成本
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal wxcb;


    private String contractId;
    private String contractName;
    private String contractNumber;

    //    合同原币金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal oriAmount;
    //    合同本位币金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal amount;
    //    原主合同金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal srcAmount;
    //    测算原币金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal calOriAmount;
    //    合同最新金额   合同最新造价
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal lastAmount;

    private String projectId;
    private String projectName;
    private List<String> projectIds;

    //     施工方报审金额  /施工单位预算造价
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal reportAmount;
    //    最终审定金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal allowAmount;
    //变更内容
    private String changeReason;
    //    审核信息
    private String auditorId;
    private String auditorName;
    private String creatorName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime auditTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bizDate;
    //    分页
    private Integer currentPage;
    private Integer pageSize;

//    分录信息
    private List<ChangeSettleEntryVO> entryVOS;
}
