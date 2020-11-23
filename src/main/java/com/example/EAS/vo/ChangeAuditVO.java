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
//    权限名称
    private String authorNum;

    private String id;
    private String num;

    private String title;

    private String projectId;
    private List<String> projectIds;
    private String orgId;
    private String orgName;

    private String contractId;
    private String contractName;
    private String contractNumber;

    //    执行内容
    private String executeContent;
    private String projectName;
    //    变更状态
    private String changeState;
    //审批状态
    private String auditState;
    //页面显示单据状态
    private String webState;
    //    专业类型
    private String specialtyTypeName;
    //   流程类型
    private String wfType;
    //提出方 我方部门=SELFCOM,合作单位=DESIGNCOM
    private String offer;
    //    提出部门
    private String conductDeptID;
    private String conductDeptName;
    //提出单位
    private String conductUnitName;
    //    提出部门/单位
    private String conductName;
    //    是否费用审定
    private Integer isFee;
    //    费用承担单位
    private String costUnit;
    //    是否增减合同
    private Integer isChangeContract;
    //    是否回执单
    private Integer isReceipts;
    //    是否已执行
    private Integer isAlreadyDo;
    //    是否重大变更
    private Integer isImportChange;
    //    主送单位
    private String mainSupplierName;
    //    抄送单位
    private String copySupplierName;
    //    总承包商
    private String generalSupplierName;

    //    变更原因
    private String changeReasonId;
    private String changeReason;
    //    变更类型
    private String auditTypeId;
    private String auditType;
    //    测算金额汇总
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal totalCost;
    //    测算金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal oriCostAmount;
    //    变更事项说明
    private String reaDesc;
    //    备注
    private String description;
    //    审批人
    private String creatorId;
    private String auditorId;
    private String auditor;
    //    审批日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime auditTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bizDate;
    //    变更期间 如 2020 1期 到 12 期 根据业务日期来转义
    private String changeDates;
    private Integer currentPage;
    private Integer pageSize;

    //    变更内容集合
    private ChangeAuditContentVO contentVO;
    private List<ChangeAuditContentVO> contentVOList;
    //    测算信息
    private List<CalculationInfoVO> calculationInfoVOS;
    //    附件信息集合
    private List<AttachmentsVO> attachmentsVOS;
    //    来源功能===记录了提交oa流程后返回的oaid
    private String sourceFunction;
    //    查看返回的oa流程link
    private String oaId;
    private String link;
    //OA岗位职位信息
    private String identityId;
    private String identityName;
    private String foaposition;
}
