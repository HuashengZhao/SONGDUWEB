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
@TableName("T_FDC_COSTACCOUNT")
public class TFdcCostaccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("FID")
    private String fid;

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

    @TableField("FDISPLAYNAME_L1")
    private String fdisplaynameL1;

    @TableField("FDISPLAYNAME_L2")
    private String fdisplaynameL2;

    @TableField("FDISPLAYNAME_L3")
    private String fdisplaynameL3;

    @TableField("FASSIGNED")
    private Long fassigned;

    @TableField("FISENABLE")
    private Long fisenable;

    @TableField("FCURPROJECT")
    private String fcurproject;

    @TableField("FPARENTID")
    private String fparentid;

    @TableField("FFULLORGUNIT")
    private String ffullorgunit;

    @TableField("FISSOURCE")
    private Long fissource;

    @TableField("FSRCCOSTACCOUNTID")
    private String fsrccostaccountid;

    @TableField("FTYPE")
    private String ftype;

    @TableField("FFLAG")
    private Long fflag;

    @TableField("FISENTERDB")
    private Long fisenterdb;

    @TableField("FISCOSTACCOUNT")
    private Long fiscostaccount;

    @TableField("FCODINGNUMBER")
    private String fcodingnumber;

    @TableField("FISCANADD")
    private Long fiscanadd;

    @TableField("FCREATEORG")
    private String fcreateorg;

    @TableField("FISSPLIT")
    private Long fissplit;

    @TableField("FISPROGRAMMING")
    private Long fisprogramming;

    @TableField("FRATE")
    private Double frate;

    @TableField("FISMARKET")
    private Long fismarket;


}
