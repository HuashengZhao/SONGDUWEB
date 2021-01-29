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
 * @ClassName:MarketProjectVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 9:40
 */
@Data
public class MarketProjectVO {

    private String id;
    private String orgId;
    private String orgName;
    private String title;
    private String num;
    @JsonSerialize(using = CustomBigDecimalSerialize.class, nullsUsing = CustomBigDecimalSerialize.class)
    private BigDecimal amount;
    //    营销立项是否负数立项 0 否 1 是
    private Integer isSub;
    //是否后评估审核 0 否  1 是
    private Integer isJt;
    private String state;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bizDate;
    //预估发生日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private LocalDateTime bookDate;
    private String creator;
    private String creatorId;
    private String creatorNum;
    private String auditor;
    private String auditorId;
    private String auditorNum;
    private String auditTime;
    private String createTime;
    private List<MarketProjectVO> marketProjectVOList;
    //操作類型 1 合同获取 2无文本获取
    private Integer operationType;

    private String projectName;
    //    集团/事业部/城市公司=BIGRANGE,项目部=SMALLRANGE,集团/事业部/城市公司-项目部=ALLRANGE,内部关联公司往来类=NEIBU,外部供应商客户往来类=WAIBU
    private String orgType;
    //立项来源 综合立项 ZHLX  单项立项 DXLX
    private String source;

    private String description;
    //费用归属
    private String costAccountId;
    private String costAccountName;

    private List<CostAccountVO> costAccountVOS;

    private List<PriceUnitVO> priceUnitVOS;

    //附件
    private List<AttachmentsVO> attachmentsVOS;

    private List<SupplierVO> supplierVOS;

}
