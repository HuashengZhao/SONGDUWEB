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
 * @since 2020-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_FDC_CONTRACTTYPE")
public class TFdcContracttype implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("FID")
    private String fid;

    @TableField("FNUMBER")
    private String fnumber;

    @TableField("FNAME_L1")
    private String fnameL1;

    @TableField("FNAME_L2")
    private String fnameL2;

    @TableField("FNAME_L3")
    private String fnameL3;

    @TableField("FISCOST")
    private Long fiscost;

    @TableField("FLONGNUMBER")
    private String flongnumber;

    @TableField("FISLEAF")
    private Long fisleaf;

    @TableField("FLEVEL")
    private Long flevel;

    @TableField("FPARENTID")
    private String fparentid;

    @TableField("FISENABLED")
    private Long fisenabled;

    @TableField("FCONTROLUNITID")
    private String fcontrolunitid;

    @TableField("FDESCRIPTION_L1")
    private String fdescriptionL1;

    @TableField("FDESCRIPTION_L2")
    private String fdescriptionL2;

    @TableField("FDESCRIPTION_L3")
    private String fdescriptionL3;

    @TableField("FSIMPLENAME")
    private String fsimplename;

    @TableField("FDISPLAYNAME_L1")
    private String fdisplaynameL1;

    @TableField("FDISPLAYNAME_L2")
    private String fdisplaynameL2;

    @TableField("FDISPLAYNAME_L3")
    private String fdisplaynameL3;

    @TableField("FCREATORID")
    private String fcreatorid;

    @TableField("FCREATETIME")
    private LocalDateTime fcreatetime;

    @TableField("FLASTUPDATEUSERID")
    private String flastupdateuserid;

    @TableField("FLASTUPDATETIME")
    private LocalDateTime flastupdatetime;

    @TableField("FDUTYORGUNIT")
    private String fdutyorgunit;

    @TableField("FPAYSCALE")
    private Double fpayscale;

    @TableField("FSTAMPTAXRATE")
    private Double fstamptaxrate;

    @TableField("FFORSUPPORTLONGNUMBERCODING")
    private String fforsupportlongnumbercoding;

    @TableField("FISCONTROLBYQUANLITY")
    private Long fiscontrolbyquanlity;

    @TableField("FISREFPROGRAM")
    private Long fisrefprogram;

    @TableField("FISWORKLOADCONFIRM")
    private Long fisworkloadconfirm;

    @TableField("FORGTYPE")
    private String forgtype;

    @TableField("FHELPERNUMBER")
    private String fhelpernumber;

    @TableField("FISACCOUNTVIEW")
    private Long fisaccountview;

    @TableField("FSINGLEPAYMENT")
    private Long fsinglepayment;

    @TableField("FISMARKET")
    private Long fismarket;

    @TableField("FISTA")
    private Long fista;


}
