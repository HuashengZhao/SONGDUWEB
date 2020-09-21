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
 * @since 2020-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_BAS_MULTIAPPROVE")
public class TBasMultiapprove implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("FID")
    private String fid;

    @TableField("FISPASS")
    private String fispass;

    @TableField("FOPINION_L1")
    private String fopinionL1;

    @TableField("FOPINION_L2")
    private String fopinionL2;

    @TableField("FOPINION_L3")
    private String fopinionL3;

    @TableField("FHANDLEROPTION")
    private Long fhandleroption;

    @TableField("FHANDLERCONTENT")
    private String fhandlercontent;

    @TableField("FMULTIHANDLEOPINION")
    private String fmultihandleopinion;

    @TableField("FBILLID")
    private String fbillid;

    @TableField("FCREATORID")
    private String fcreatorid;

    @TableField("FCREATETIME")
    private LocalDateTime fcreatetime;

    @TableField("FLASTUPDATEUSERID")
    private String flastupdateuserid;

    @TableField("FLASTUPDATETIME")
    private LocalDateTime flastupdatetime;

    @TableField("FBOSTYPE")
    private String fbostype;

    @TableField("FCONTROLUNITID")
    private String fcontrolunitid;

    @TableField("FNEXTHANDLERPERSONID")
    private String fnexthandlerpersonid;

    @TableField("FASSIGNMENTID")
    private String fassignmentid;

    @TableField("FSTATUS")
    private Long fstatus;

    @TableField("FTYPE")
    private Long ftype;

    @TableField("FNUMBER")
    private String fnumber;

    @TableField("FISMAILNOTIFYNEXT")
    private Long fismailnotifynext;

    @TableField("FISMOBELNOTIFYNEXT")
    private Long fismobelnotifynext;

    @TableField("FPOSITIONNAME_L1")
    private String fpositionnameL1;

    @TableField("FPOSITIONNAME_L2")
    private String fpositionnameL2;

    @TableField("FPOSITIONNAME_L3")
    private String fpositionnameL3;

    @TableField("FORGNAME_L1")
    private String forgnameL1;

    @TableField("FORGNAME_L2")
    private String forgnameL2;

    @TableField("FORGNAME_L3")
    private String forgnameL3;

    @TableField("FORGDISPLAYNAME_L1")
    private String forgdisplaynameL1;

    @TableField("FORGDISPLAYNAME_L2")
    private String forgdisplaynameL2;

    @TableField("FORGDISPLAYNAME_L3")
    private String forgdisplaynameL3;


}
