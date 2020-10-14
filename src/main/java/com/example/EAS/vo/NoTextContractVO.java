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
public class NoTextContractVO {

    //    基参
    private String id;
    private String title;
    private String num;
    private String orgId;
    private String orgName;
    private String orgNumber;
    private String projectId;
    private String projectName;
    private String projectNumber;
    private String state;
    private String creatorId;
    private String creatorName;
    private String auditorId;
    private String auditorName;
//    款项说明
    private String description;
    //    附件
    private Integer ifHasAttach;
    private List<AttachmentsVO> attachmentsVOS;

    //    本位币金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal amount;
    //    原币金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal oriAmount;
    //    发票金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal invoiceAMT;
    //    税额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal rateAmount;
    //合同类型
    private String contractTypeId;
    private String contractTypeName;
    //框架合约
    private String programContractId;
    private String programContractName;
    //  币别
    private String currencyId;
    private String currencyName;
    //收款人  收款单位
    private String receiverName;
    private String receiverType;
    private String personId;
    private String personName;
    private String FReceiveUnitID;
    //付款类别
    private String payBillTypeId;
    private String payBillTypeName;
    //    付款事项
    private String payContentId;
    private String payContentName;
    //    期間
    private String period;
    private String periodId;
    private String periodYear;
    private String periodNumber;
    //    納稅人
//    一般纳税人=NOMAL,小规模纳税人=SMALL,非增值税纳税人=UNNOMAL
    private String taxerQua;
    private String taxerNumber;
    //联行号
    private String unionBankId;
    private String unionBankNum;
    //    银行
    private String bank;
    private String bankAccount;

    private String exRate;
    //结算方式
    private String settlementTypeId;
    private String settlementTypeName;
    //   申请人
    private String applierId;
    private String applierName;
    //用款部门
    private String useDeptId;
    private String useDeptName;

    //    预算承担公司、部门
    private String costDeptId;
    private String costCompanyId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bizDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime auditTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime signDate;

    //    分页
    private Integer currentPage;
    private Integer pageSize;
    private List<String> projectIdList;

    //    费用清单
    private List<CWTextBgVO> cwTextBgVOS;
    //    营销立项
    private String marketProjectId;
    private String marketProjectName;
    private List<MarketContDetailVO> marketContDetailVOS;
}
