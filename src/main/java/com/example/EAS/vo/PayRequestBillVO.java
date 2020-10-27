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

@Data
public class PayRequestBillVO {
    private String authorNum;
    //     基参
    private String id;
    private String title;
    private String num;
    private String orgId;
    private String orgNumber;
    private String orgName;
    private String projectId;
    private String projectNumber;
    private String projectName;
    private String creatorId;
    private String creatorName;
    private String auditorId;
    private String auditorName;

    //    付款类别
    private String paymentTypeName;
    //    状态
    private String state;
    //    合同信息
    private String contractId;
    private String contractNumber;
    private String contractName;
    //   收款单位信息
    private String supplierId;
    private String supplierName;
    private String realSupplierId;
    private String realSupplierName;
//    预算承担公司、
//    部门
    private String costCompanyName;
    private String costDeptName;
    //  币别
    private String currencyId;
    private String currencyName;
    //    扣款金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal deductAmt;
    //    本位币
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal amount;
    private String capitalAmount;
    //    原币
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal oriAmount;
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal grtAmount;
    //    最新造价
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal latestPrice;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime auditTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bizDate;
    private LocalDateTime bookDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime payDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime endDate;
//    起止日期拼凑
    private String startEndDate;

    //    银行
    private String recBank;
    private String recAccount;
//    联行号
    private String unionBankNum;
    //    备注
    private String remark;

    //    分页参数
    private Integer currentPage;
    private Integer pageSize;
    //    附件
    private Integer ifHasAttach;
    private Integer attNums;
    private List<AttachmentsVO> attachmentsVOS;

    //    付款事项
    private String payContentTypeId;
    private String payContentTypeName;

    //    纳税人
    private String taxerQua;
    private String taxerNum;
    //    结算方式
    private String settleMentTypeName;
    //    汇率
    private String exRate;

    //    申请人信息
    private String applierId;
    private String applierName;
    //    用款部门
    private String useDepartMentName;
    //   内外部合同
//    集团/事业部/城市公司=BIGRANGE,项目部=SMALLRANGE,集团/事业部/城市公司-项目部=ALLRANGE,内部关联公司往来类=NEIBU,外部供应商客户往来类=WAIBU
    private String orgType;
    //后评估审核
    private Integer isJT;

    //    本次完工工程量
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal thisAMT;
    //    累计完工工程量
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal allAMT;
    //    累计完工工程比例
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal allAMTRATE;
    //    累计进度款付款比例   (张说不要了)
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal allProRATE;
    //    合同结算价
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal settleAMT;
    //    申请金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal requestAMT;
    // 同城异地
    private String isDifPlace;
    //    是否加急
    private String urtDegree;
//    是否提交付款
    private Integer isPay;

    //    付款申请金额集合
    private PaymentApplyVO paymentApplyVO;

    private List<PaymentApplyVO> payMentApplyList;

    //    费用类别
    private String costType;
    //    会计科目
    private String costAccountId;
    private String costAccountName;
    //    link查看oa流程
    private String link;
    private String oaId;
    //    申請金額
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal applyAMT;

    private String sourceFunction;
}
