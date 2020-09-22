package com.example.EAS.vo;

import com.example.EAS.util.CustomBigDecimalSerialize;
import com.example.EAS.util.DateJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:ChangeAuditVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/9/22 9:26
 */
@Data
public class ChangeAuditVO {

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bizDate;
    //    变更状态
    private String changeState;
    //审批状态
    private String auditState;
    //页面显示单据状态
    private String webState;

    private String num;

    private String title;

    private String projectId;

    private String projectName;
    //    变更原因
    private String changeReasonId;
    private String changeReason;
    //    变更类型
    private String auditTypeId;
    private String auditType;
    //    测算金额汇总
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal totalCost;
    //    变更事项说明
    private String reaDesc;
    //    审批人
    private String auditorId;
    private String auditor;
    //    审批日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime auditTime;
    private Integer currentPage;
    private Integer pageSize;

    //    变更内容集合
    private List<ChangeAuditContentVO> contentVOList;
    //    测算信息
    private List<CalculationInfoVO> calculationInfoVOS;
}
