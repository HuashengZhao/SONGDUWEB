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
@TableName("T_BD_SUPPLIER")
public class TBdSupplier implements Serializable {

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

    @TableField("FARTIFICIALPERSON")
    private String fartificialperson;

    @TableField("FBARCODE")
    private String fbarcode;

    @TableField("FBIZANALYSISCODEID")
    private String fbizanalysiscodeid;

    @TableField("FBROWSEGROUPID")
    private String fbrowsegroupid;

    @TableField("FBUSIEXEQUATUR")
    private String fbusiexequatur;

    @TableField("FBIZREGISTERNO")
    private String fbizregisterno;

    @TableField("FBUSILICENCE")
    private String fbusilicence;

    @TableField("FCITYID")
    private String fcityid;

    @TableField("FCOUNTRYID")
    private String fcountryid;

    @TableField("FFREEZEORGUNIT")
    private String ffreezeorgunit;

    @TableField("FGSPAUTHENTICATION")
    private String fgspauthentication;

    @TableField("FINDUSTRYID")
    private String findustryid;

    @TableField("FINTERNALCOMPANYID")
    private String finternalcompanyid;

    @TableField("FISINTERNALCOMPANY")
    private Long fisinternalcompany;

    @TableField("FPARENTID")
    private String fparentid;

    @TableField("FPROVINCEID")
    private String fprovinceid;

    @TableField("FREGIONID")
    private String fregionid;

    @TableField("FTAXDATAID")
    private String ftaxdataid;

    @TableField("FTAXREGISTERNO")
    private String ftaxregisterno;

    @TableField("FUSEDSTATUS")
    private Long fusedstatus;

    @TableField("FVERSION")
    private Long fversion;

    @TableField("FEFFECTEDSTATUS")
    private Long feffectedstatus;

    @TableField("FMNEMONICCODE")
    private String fmnemoniccode;

    @TableField("FFOREIGNNAME")
    private String fforeignname;

    @TableField("FADMINCUID")
    private String fadmincuid;

    @TableField("FADDRESS")
    private String faddress;

    @TableField("FTAXRATE")
    private Double ftaxrate;

    @TableField("FISMULTICOPY")
    private Long fismulticopy;

    @TableField("FISCARRIER")
    private Long fiscarrier;

    @TableField("FISOUTER")
    private Long fisouter;

    @TableField("FOLDNUMBER")
    private String foldnumber;


}
