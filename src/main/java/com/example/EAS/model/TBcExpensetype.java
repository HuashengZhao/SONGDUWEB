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
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_BC_EXPENSETYPE")
public class TBcExpensetype implements Serializable {

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

    @TableField("FTYPENAME_L1")
    private String ftypenameL1;

    @TableField("FTYPENAME_L2")
    private String ftypenameL2;

    @TableField("FTYPENAME_L3")
    private String ftypenameL3;

    @TableField("FISSTART")
    private Long fisstart;

    @TableField("FTYPESIMPLENAME_L1")
    private String ftypesimplenameL1;

    @TableField("FTYPESIMPLENAME_L2")
    private String ftypesimplenameL2;

    @TableField("FTYPESIMPLENAME_L3")
    private String ftypesimplenameL3;

    @TableField("FOPERATIONTYPEID")
    private String foperationtypeid;

    @TableField("FCOMPANYID")
    private String fcompanyid;

    @TableField("FBILLTYPENAME")
    private String fbilltypename;


}
