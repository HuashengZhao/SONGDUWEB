package com.example.EAS.vo;

import com.example.EAS.util.DateJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

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
    private String title;
    private String num;
    private String amount;
    private String isSub;
    private String state;
    @JsonFormat( pattern= "yyyy-MM-dd")
    @JsonDeserialize(using= DateJsonDeserializer.class)
    private LocalDateTime bizDate;
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

}
