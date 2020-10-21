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
 * @ClassName:ContractVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/22 9:28
 */
@Data
public class ContractVO {

    private String id;
    private String num;
    private String conName;
    private String orgId;
    private String orgName;
    private String state;
    private String projectId;
    private String projectName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bizDate;
    private String conTypeName;
    private String conTypeId;
    //    合约规划
    private String hygh;
    private String hyghId;
    private String mainNumber;
    //    币别
    private String currencyId;
    private String curName;
    //    汇率
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal exRate;
    //    原主合同金额
    private String srcAmount;
    //    原币金额
    private String originalAmount;
    //    大写
    private String originalAmountBIG;
    //    本位币
    private String amount;
    //    大写
    private String amountBIG;
    //    保修金比例
    private String grtRate;
    //    保修金金额
    private String grtAmount;
    //    变更提示比例  张佳萍说这个字段没有用
    private String chgPerForWarn;
    private String partAName;
    private String partA;
    private String partBName;
    private String partB;
    private String partC;
    private String partCName;
    //    中标审批
    private String taEntryId;
    //    营销立项
    private String marketProjectId;
    private String marketProjectName;
    //    是否有附件
    private Integer hasFile;
    //    是否结算
    private Integer hasSettled;
    //    形成方式
//委托=TRUST,招标=INVITE,议标=DISCUSS,战略合作=COOP",
    private String csName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime signDate;
    private String dept;
    private String deptName;
    private String person;
    private String personName;
    //    费用归属
    private String costAccountId;
    private String costAccountName;
    //     造价性质
//固定总价=COMP_COMFIRM,暂定总价=TEMP_EVAL,暂定总价转固定总价=BASE_CONFIRM
    private String costProperty;
    //    合同性质
//直接合同=DIRECT,三方合同=THREE_PARTY,补充合同=SUPPLY,战略协议=STRATEGY
    private String contractNature;
    //    采购类别
    private String isArchived;
    //    合同流程类型
    private String contractWFTypeId;
    private String contractWFTypeName;
    //    合同流程发起类型 ==审批流程发起组织
//    集团/事业部/城市公司=BIGRANGE,项目部=SMALLRANGE,集团/事业部/城市公司-项目部=ALLRANGE,
//    内部关联公司往来类=NEIBU,外部供应商客户往来类=WAIBU
    private String contractWFStartType;
    private String auditor;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime lastAuditTime;
    private String creator;
    private String creatDept;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime createTime;
    //    是否暂缓
    private String isRespite;
    //    link查看oa流程
    private String link;
    private String oaId;

    //    分页
    private Integer currentPage;
    private Integer pageSize;
    // 项目id集合
    private List<String> projectIds;
    //   合同类型id集合
    private List<String> conTypeIdList;
    //    返回信息
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime endDate;
    //税务信息+收款信息
    private String unionBankNum;
    private String unionBankId;
    private String bank;
    private String bankAccount;
    private String taxQua;
    private String taxNum;
    //    保存eas結果信息
    private String result;

    //    合同签订明细
    private List<ContractSignDetailVO> detailSignVOS;
    //    合同详情信息
    private List<ContractDetailVO> detailVOList;
    //    补充合同信息 合同审批后才可录入  新增方法进行
    private List<ContractAddVO> contractAddVOS;
    //
    private List<MarketContDetailVO> marketContDetailVOS;

    private List<String> idList;

    private Integer isjt;
    //附件
    private List<AttachmentsVO> attachmentsVOS;

    //    是否需要营销合同分摊明细
    private Integer isMarket;
    //    是否调用eas提交方法
    private Boolean flag;
    //   附件预览地址
    private String attLink;

}
