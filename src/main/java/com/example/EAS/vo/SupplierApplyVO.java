package com.example.EAS.vo;

import com.example.EAS.util.DateJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:SupplierApplyVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/26 9:19
 */
@Data
public class SupplierApplyVO {

    private String id;
    private String orgId;
    private String num;
    private String title;
    private String bankAccount;
    private String bank;
    private String taxerQua;
    private String taxerNum;
    private String sourceFunction;

    //    保存=1SAVED,已提交=2SUBMITTED,审批中=3AUDITTING,已审批=4AUDITTED,终止=5CANCEL,已下发=7ANNOUNCE,已签证=8VISA,作废=9INVALID,已上报=10PUBLISH,被打回=11BACK,修订中=12REVISING,已修订=12REVISE,已确认=13CONFIRMED
    private String state;
    private String description;
    @JsonFormat( pattern= "yyyy-MM-dd")
    @JsonDeserialize(using= DateJsonDeserializer.class)
    private LocalDateTime createTime;
    @JsonFormat( pattern= "yyyy-MM-dd")
    @JsonDeserialize(using= DateJsonDeserializer.class)
    private LocalDateTime auditTime;
    private String creator;
//    private String creatorNum;
    private String auditor;
    private String auditorNum;
    private String remark;
    private Integer currentPage;
    private Integer pageSize;
//   流程回掉信息i
    private String easid;
    private String oaId;
    private String type;
    private String attlink;
    private String result;

//    请求信息
    private String token ;
    private String message ;

    private List<String> idList ;
    private List<AttachmentsVO> attachmentsVOS ;
    private String personName ;
    private String person ;
    private String fileUrl ;
    private Long size;
    private String fileType;
    private String easId;
    private String fileName;
//    link查看oa流程
    private String link;

}