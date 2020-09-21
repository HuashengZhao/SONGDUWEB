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
@TableName("T_CON_PROGRAMMINGCONTRACT")
public class TConProgrammingcontract implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("FPROGRAMMINGID")
    private String fprogrammingid;

    @TableField("FPARENTID")
    private String fparentid;

    @TableField("FAMOUNT")
    private Double famount;

    @TableField("FCONTROLAMOUNT")
    private Double fcontrolamount;

    @TableField("FBALANCE")
    private Double fbalance;

    @TableField("FCONTROLBALANCE")
    private Double fcontrolbalance;

    @TableField("FSIGNUPAMOUNT")
    private Double fsignupamount;

    @TableField("FCHANGEAMOUNT")
    private Double fchangeamount;

    @TableField("FSETTLEAMOUNT")
    private Double fsettleamount;

    @TableField("FISCITING")
    private Long fisciting;

    @TableField("FCITEVERSION")
    private Long fciteversion;

    @TableField("FSRCID")
    private String fsrcid;

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

    @TableField("FATTACHMENT")
    private String fattachment;

    @TableField("FSORTNUMBER")
    private Long fsortnumber;

    @TableField("FWORKCONTENT")
    private String fworkcontent;

    @TableField("FSUPMATERIAL")
    private String fsupmaterial;

    @TableField("FINVITEWAY")
    private String finviteway;

    @TableField("FINVITEORGID")
    private String finviteorgid;

    @TableField("FBUILDPERSQUARE")
    private Double fbuildpersquare;

    @TableField("FSOLDPERSQUARE")
    private Double fsoldpersquare;

    @TableField("FCOSTACCOUNTNAMES")
    private String fcostaccountnames;

    @TableField("FWITHOUTTEXTNAME")
    private String fwithouttextname;

    @TableField("FWITHOUTTEXTNUMBER")
    private String fwithouttextnumber;

    @TableField("FWITHOUTTEXTAMOUNT")
    private Double fwithouttextamount;

    @TableField("FBUDGETAMOUNT")
    private Double fbudgetamount;

    @TableField("FBILLID")
    private String fbillid;

    @TableField("FESTIMATEAMOUNT")
    private Double festimateamount;

    @TableField("FCONTRACTTYPEID")
    private String fcontracttypeid;

    @TableField("FUNAUDITCONTRACTEA")
    private Double funauditcontractea;

    @TableField("FUNAUDITCONTRACTSETTLEEA")
    private Double funauditcontractsettleea;

    @TableField("FISINVITE")
    private Long fisinvite;

    @TableField("FISWTCITING")
    private Long fiswtciting;

    @TableField("FCOMPARE")
    private String fcompare;

    @TableField("FBUILDPRICE")
    private Double fbuildprice;

    @TableField("FISINPUT")
    private Long fisinput;

    @TableField("FQUANTITIES")
    private Double fquantities;

    @TableField("FUNIT")
    private String funit;

    @TableField("FPRICE")
    private Double fprice;

    @TableField("FESTIMATEAWARDSTARTDATE")
    private LocalDateTime festimateawardstartdate;

    @TableField("FESTIMATEAWARDENDDATE")
    private LocalDateTime festimateawardenddate;

    @TableField("FINVITEMODEID")
    private String finvitemodeid;

    @TableField("FJOBTYPEID")
    private String fjobtypeid;

    @TableField("FISHASPLAN")
    private Long fishasplan;

    @TableField("FRESERVEDCHANGERATE")
    private Double freservedchangerate;

    @TableField("FBASEID")
    private String fbaseid;

    @TableField("FDOCUMENTSAUDITDATE")
    private LocalDateTime fdocumentsauditdate;

    @TableField("FRESULTAUDITDATE")
    private LocalDateTime fresultauditdate;

    @TableField("FCONTRACTAUDITDATE")
    private LocalDateTime fcontractauditdate;

    @TableField("FENTERAUDITDATE")
    private LocalDateTime fenterauditdate;

    @TableField("FPAPERDATE")
    private LocalDateTime fpaperdate;


}
