package com.example.EAS.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_ORG_BASEUNIT")
public class TOrgBaseunit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("FPARTHRID")
    private String fparthrid;

    @TableField("FISHRORGUNIT")
    private Long fishrorgunit;

    @TableId("FID")
    private String fid;

    @TableField("FNAME_L1")
    private String fnameL1;

    @TableField("FNAME_L2")
    private String fnameL2;

    @TableField("FNAME_L3")
    private String fnameL3;

    @TableField("FNUMBER")
    private String fnumber;

    @TableField("FDESCRIPTION_L1")
    private String fdescriptionL1;

    @TableField("FDESCRIPTION_L2")
    private String fdescriptionL2;

    @TableField("FDESCRIPTION_L3")
    private String fdescriptionL3;

    @TableField("FSIMPLENAME")
    private String fsimplename;

    @TableField("FISLEAF")
    private Long fisleaf;

    @TableField("FLEVEL")
    private Long flevel;

    @TableField("FLONGNUMBER")
    private String flongnumber;

    @TableField("FISGROUPING")
    private Long fisgrouping;

    @TableField("FEFFECTDATE")
    private LocalDateTime feffectdate;

    @TableField("FINVALIDDATE")
    private LocalDateTime finvaliddate;

    @TableField("FISFREEZE")
    private Long fisfreeze;

    @TableField("FISCOMPANYORGUNIT")
    private Long fiscompanyorgunit;

    @TableField("FISADMINORGUNIT")
    private Long fisadminorgunit;

    @TableField("FISSALEORGUNIT")
    private Long fissaleorgunit;

    @TableField("FISPURCHASEORGUNIT")
    private Long fispurchaseorgunit;

    @TableField("FISSTORAGEORGUNIT")
    private Long fisstorageorgunit;

    @TableField("FISPROFITORGUNIT")
    private Long fisprofitorgunit;

    @TableField("FISCOSTORGUNIT")
    private Long fiscostorgunit;

    @TableField("FISCU")
    private Long fiscu;

    @TableField("FISUNION")
    private Long fisunion;

    @TableField("FCREATORID")
    private String fcreatorid;

    @TableField("FCREATETIME")
    private LocalDateTime fcreatetime;

    @TableField("FLASTUPDATEUSERID")
    private String flastupdateuserid;

    @TableField("FLASTUPDATETIME")
    private LocalDateTime flastupdatetime;

    @TableField("FCONTROLUNITID")
    private String fcontrolunitid;

    @TableField("FPARENTID")
    private String fparentid;

    @TableField("FPARTCTRLID")
    private String fpartctrlid;

    @TableField("FPARTADMINID")
    private String fpartadminid;

    @TableField("FPARTFIID")
    private String fpartfiid;

    @TableField("FPARTSALEID")
    private String fpartsaleid;

    @TableField("FPARTSTORAGEID")
    private String fpartstorageid;

    @TableField("FPARTPURCHASEID")
    private String fpartpurchaseid;

    @TableField("FPARTCOSTCENTERID")
    private String fpartcostcenterid;

    @TableField("FPARTPROFITCENTERID")
    private String fpartprofitcenterid;

    @TableField("FISSTART")
    private Long fisstart;

    @TableField("FISOUSEALUP")
    private Long fisousealup;

    @TableField("FPARTUNIONGROUPID")
    private String fpartuniongroupid;

    @TableField("FDISPLAYNAME_L1")
    private String fdisplaynameL1;

    @TableField("FDISPLAYNAME_L2")
    private String fdisplaynameL2;

    @TableField("FDISPLAYNAME_L3")
    private String fdisplaynameL3;

    @TableField("FCODE")
    private String fcode;

    @TableField("FENGLISHNAME")
    private String fenglishname;

    @TableField("FVERSIONNUMBER")
    private String fversionnumber;

    @TableField("FMAINTAINCUID")
    private String fmaintaincuid;

    @TableField("FISASSISTANTORG")
    private Long fisassistantorg;

    @TableField("FMAINORGID")
    private String fmainorgid;

    @TableField("FACCOUNTSCHEMEID")
    private String faccountschemeid;

    @TableField("FISTRANSPORTORGUNIT")
    private Long fistransportorgunit;

    @TableField("FPARTTRANSPORTID")
    private String fparttransportid;

    @TableField("FISQUALITYORGUNIT")
    private Long fisqualityorgunit;

    @TableField("FPARTQUALITYID")
    private String fpartqualityid;

    @TableField("FORGTYPESTR")
    private String forgtypestr;


}
