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

    private String authorNum;
    //    基参
    private Boolean flag;
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
    private Integer attNums;
    private List<AttachmentsVO> attachmentsVOS;
    //    本位币金额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal amount;
    //    大写金额
    private String bwbdx;
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
    //    规划余额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal balance;
    //    费用归属
    private String costAccountId;
    private String costAccountName;
    //  币别
    private String currencyId;
    private String currencyName;
    //收款人  收款单位 收款单位来自对应的付款申请单的receiveUnitId
//    收款单位id  存到付款申请单中的id
    private String supplierId;
    private String receiverName;
    private String receiverType;
    private String personId;
    private String personName;
    //    实际收款单位 存到表中的 freceiveUnitId
    private String receiveUnitID;
    private String realReceiveUnitName;
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
    private String costDeptName;
    private String costCompanyId;
    private String costCompanyName;
    //是否后评估审核 0 1
    private Integer isjt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bizDate;
    private LocalDateTime bookDate;
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
    //    营销立项可用余额
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal marProBalance;
    private List<MarketContDetailVO> marketContDetailVOS;
    //    eas返回
    private String result;
    private String message;

    private List<String> idList;

    //    link查看oa流程
    private String link;
    private String oaId;
}
