package com.example.EAS.vo;

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

    private String changeState;

    private String auditState;

    private String webState;

    private String num;

    private String title;

    private String projectId;
    private String projectName;
//    变更原因
    private String reasonID;
    private String reason;
    private String changeType;
    private String totalCost;
//    变更事项说明
    private String reaDesc;
//    审批人
    private String auditor;
//    审批日期
    private LocalDateTime auditDate;


    private Integer currentPage;
    private Integer pageSize;


}
