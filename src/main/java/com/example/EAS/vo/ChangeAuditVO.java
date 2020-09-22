package com.example.EAS.vo;

import com.example.EAS.util.DateJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;

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

    private LocalDateTime bizDate;

    //    变更状态
    private String changeState;
    //审批状态
    private String auditState;

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
    private String totalCost;
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


}
