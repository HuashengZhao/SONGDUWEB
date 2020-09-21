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
 * @since 2020-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_INV_TENDERACCEPTERRESULT")
public class TInvTenderaccepterresult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("FMARKET")
    private String fmarket;

    @TableField("FBASEPRICE")
    private Double fbaseprice;

    @TableField("FCOSTDATA")
    private String fcostdata;

    @TableField("FCONTRACTPRICE")
    private Double fcontractprice;

    @TableField("FDDIFFERENCE")
    private String fddifference;

    @TableField("FSPECIALNOTE")
    private String fspecialnote;

    @TableField("FREASON")
    private String freason;

    @TableField("FDEPLOYTYPEID")
    private String fdeploytypeid;

    @TableField("FDRAWINGDEPTHID")
    private String fdrawingdepthid;

    @TableField("FREFPRICE")
    private String frefprice;

    @TableField("FINVITEPROJECTID")
    private String finviteprojectid;

    @TableField("FRESPDEPTID")
    private String frespdeptid;

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

    @TableField("FCOMPAREAIM")
    private String fcompareaim;

    @TableField("FSTARTDATE")
    private LocalDateTime fstartdate;

    @TableField("FENDDATE")
    private LocalDateTime fenddate;


}
