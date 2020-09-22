package com.example.EAS.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author watson
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_CON_CONTRACTCHANGESETTLEBILL")
public class TConContractchangesettlebill implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("FISFINISH")
    private Long fisfinish;

    @TableField("FRESPONSIBLESTYLE")
    private String fresponsiblestyle;

    @TableField("FLASTAMOUNT")
    private Double flastamount;

    @TableField("FREASONDESCRIPTION")
    private String freasondescription;

    @TableField("FCOLSEDESCRIPTION")
    private String fcolsedescription;

    @TableField("FCHANGERESON")
    private String fchangereson;

    @TableField("FSUPPLIERID")
    private String fsupplierid;

    @TableField("FCONTRACTBILLID")
    private String fcontractbillid;

    @TableField("FCURPROJECTID")
    private String fcurprojectid;

    @TableField("FREPORTAMOUNT")
    private Double freportamount;

    @TableField("FALLOWAMOUNT")
    private Double fallowamount;

    @TableField("FCONCHANGEBILLID")
    private String fconchangebillid;

    @TableField("FORGUNITID")
    private String forgunitid;

    @TableField("FSTATE")
    private String fstate;

    @TableField("FNAME")
    private String fname;

    @TableField("FORIGINALAMOUNT")
    private Double foriginalamount;

    @TableField("FAMOUNT")
    private Double famount;

    @TableField("FAUDITTIME")
    private LocalDateTime faudittime;

    @TableField("FBOOKEDDATE")
    private LocalDateTime fbookeddate;

    @TableField("FPERIODID")
    private String fperiodid;

    @TableField("FISRESPITE")
    private Long fisrespite;

    @TableField("FNUMBER")
    private String fnumber;

    @TableField("FBIZDATE")
    private LocalDateTime fbizdate;

    @TableField("FHANDLERID")
    private String fhandlerid;

    @TableField("FDESCRIPTION")
    private String fdescription;

    @TableField("FHASEFFECTED")
    private Long fhaseffected;

    @TableField("FAUDITORID")
    private String fauditorid;

    @TableField("FSOURCEBILLID")
    private String fsourcebillid;

    @TableField("FSOURCEFUNCTION")
    private String fsourcefunction;

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

    @TableId("FID")
    private String fid;

    @TableField("FWXCB")
    private Double fwxcb;

    @TableField("FISFEE")
    private Long fisfee;


}
