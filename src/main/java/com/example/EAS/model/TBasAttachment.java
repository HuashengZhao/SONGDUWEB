package com.example.EAS.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author watson
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_BAS_ATTACHMENT")
public class TBasAttachment implements Serializable {

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

    @TableField("FTYPE")
    private String ftype;

    @TableField("FFILE")
    private Blob ffile;

    @TableField("FISSHARED")
    private Long fisshared;

    @TableField("FSHAREDDESC_L1")
    private String fshareddescL1;

    @TableField("FSHAREDDESC_L2")
    private String fshareddescL2;

    @TableField("FSHAREDDESC_L3")
    private String fshareddescL3;

    @TableField("FSIZE")
    private String fsize;

    @TableField("FSIZEINBYTE")
    private Long fsizeinbyte;

    @TableField("FSHARERANGE")
    private String fsharerange;

    @TableField("FATTACHID")
    private String fattachid;

    @TableField("FBEIZHU")
    private String fbeizhu;

    @TableField("FPERMISSION")
    private String fpermission;

    @TableField("FSTORAGETYPE")
    private Long fstoragetype;

    @TableField("FFTPID")
    private String fftpid;

    @TableField("FREMOTEPATH")
    private String fremotepath;


}
