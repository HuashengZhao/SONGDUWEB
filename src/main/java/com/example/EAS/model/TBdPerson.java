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
@TableName("T_BD_PERSON")
public class TBdPerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("FID")
    private String fid;

    @TableField("FIDNUM")
    private String fidnum;

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

    @TableField("FGENDER")
    private Long fgender;

    @TableField("FBIRTHDAY")
    private LocalDateTime fbirthday;

    @TableField("FEMAIL")
    private String femail;

    @TableField("FADDRESS_L1")
    private String faddressL1;

    @TableField("FADDRESS_L2")
    private String faddressL2;

    @TableField("FADDRESS_L3")
    private String faddressL3;

    @TableField("FSTATE")
    private Long fstate;

    @TableField("FHOMEPHONE")
    private String fhomephone;

    @TableField("FOFFICEPHONE")
    private String fofficephone;

    @TableField("FCELL")
    private String fcell;

    @TableField("FBACKUPCELL")
    private String fbackupcell;

    @TableField("FBACKUPEMAIL")
    private String fbackupemail;

    @TableField("FRTX")
    private String frtx;

    @TableField("FIDCARDNO")
    private String fidcardno;

    @TableField("FIDCARDADRESS_L1")
    private String fidcardadressL1;

    @TableField("FIDCARDADRESS_L2")
    private String fidcardadressL2;

    @TableField("FIDCARDADRESS_L3")
    private String fidcardadressL3;

    @TableField("FPASSPORTNO")
    private String fpassportno;

    @TableField("FOLDNAME_L1")
    private String foldnameL1;

    @TableField("FOLDNAME_L2")
    private String foldnameL2;

    @TableField("FOLDNAME_L3")
    private String foldnameL3;

    @TableField("FHEIGHT")
    private Long fheight;

    @TableField("FNATIVEPLACE_L1")
    private String fnativeplaceL1;

    @TableField("FNATIVEPLACE_L2")
    private String fnativeplaceL2;

    @TableField("FNATIVEPLACE_L3")
    private String fnativeplaceL3;

    @TableField("FBLOODTYPE")
    private Long fbloodtype;

    @TableField("FCREATORID")
    private String fcreatorid;

    @TableField("FCREATETIME")
    private LocalDateTime fcreatetime;

    @TableField("FLASTUPDATEUSERID")
    private String flastupdateuserid;

    @TableField("FLASTUPDATETIME")
    private LocalDateTime flastupdatetime;

    @TableField("FKACLASSFICATIONID")
    private String fkaclassficationid;

    @TableField("FWEDID")
    private String fwedid;

    @TableField("FHEALTHID")
    private String fhealthid;

    @TableField("FPOLITICALFACEID")
    private String fpoliticalfaceid;

    @TableField("FSTANDINGID")
    private String fstandingid;

    @TableField("FFOLKID")
    private String ffolkid;

    @TableField("FBIRTHID")
    private String fbirthid;

    @TableField("FCONTROLUNITID")
    private String fcontrolunitid;

    @TableField("FHRORGUNITID")
    private String fhrorgunitid;

    @TableField("FRESIDENCEPLACE")
    private String fresidenceplace;

    @TableField("FLENOFACTUALSERVICE")
    private Long flenofactualservice;

    @TableField("FEMPLOYEETYPEID")
    private String femployeetypeid;

    @TableField("FHIGHESTTECHPOSTID")
    private String fhighesttechpostid;

    @TableField("FHIGHESTDEGREEID")
    private String fhighestdegreeid;

    @TableField("FEMPLOYEECLASSIFYID")
    private String femployeeclassifyid;

    @TableField("FDELETEDSTATUS")
    private Long fdeletedstatus;

    @TableField("FLCMRATIONLEVELID")
    private String flcmrationlevelid;

    @TableField("FNATIONALITYID")
    private String fnationalityid;

    @TableField("FCHECKSTATE")
    private Long fcheckstate;

    @TableField("FINDEX")
    private Long findex;

    @TableField("FEMPLOYTECHPOSTID")
    private String femploytechpostid;

    @TableField("FHIGHESTSUBDEGREEID")
    private String fhighestsubdegreeid;

    @TableField("FZDY1")
    private String fzdy1;

    @TableField("FZDY2")
    private String fzdy2;

    @TableField("FZDY3")
    private String fzdy3;

    @TableField("FZDY4")
    private String fzdy4;

    @TableField("FZDY5")
    private String fzdy5;

    @TableField("FZDY6")
    private String fzdy6;

    @TableField("FZDY7")
    private String fzdy7;

    @TableField("FZDY8")
    private String fzdy8;

    @TableField("FZDY9")
    private LocalDateTime fzdy9;

    @TableField("FZDY10")
    private LocalDateTime fzdy10;

    @TableField("FZDY11")
    private LocalDateTime fzdy11;

    @TableField("FZDY12")
    private LocalDateTime fzdy12;

    @TableField("FZDY13")
    private LocalDateTime fzdy13;

    @TableField("FZDY14")
    private LocalDateTime fzdy14;

    @TableField("FZDY15")
    private String fzdy15;

    @TableField("FZDY16")
    private String fzdy16;

    @TableField("FZDY17")
    private String fzdy17;

    @TableField("FZDY18")
    private Double fzdy18;

    @TableField("FZDY19")
    private Double fzdy19;

    @TableField("FZDY20")
    private Long fzdy20;

    @TableField("FZDY21")
    private Long fzdy21;

    @TableField("FZDY22")
    private String fzdy22;

    @TableField("FZDY23")
    private String fzdy23;

    @TableField("FZDY24")
    private String fzdy24;

    @TableField("FZDY25")
    private String fzdy25;

    @TableField("FZDY26")
    private Long fzdy26;

    @TableField("FFULLNAMEPINGYIN")
    private String ffullnamepingyin;

    @TableField("FSIMPLENAMEPINGYIN")
    private String fsimplenamepingyin;

    @TableField("FISSTANDBY")
    private Long fisstandby;

    @TableField("FISSTANDBYCADRE")
    private Long fisstandbycadre;

    @TableField("FHIGHESTCOMPETENCYID")
    private String fhighestcompetencyid;

    @TableField("CFSYZKID")
    private String cfsyzkid;

    @TableField("CFKDTEXTFIELD")
    private String cfkdtextfield;

    @TableField("CFKDDATEPICKER")
    private LocalDateTime cfkddatepicker;

    @TableField("CFKDDATEPICKER1")
    private LocalDateTime cfkddatepicker1;

    @TableField("CFKDDATEPICKER2")
    private LocalDateTime cfkddatepicker2;

    @TableField("CFKDDATEPICKER3")
    private LocalDateTime cfkddatepicker3;

    @TableField("CFKDDATEPICKER4")
    private LocalDateTime cfkddatepicker4;

    @TableField("CFSBJNDWID")
    private String cfsbjndwid;

    @TableField("CFKDTEXTFIELD1")
    private String cfkdtextfield1;

    @TableField("CFKDTEXTFIELD2")
    private String cfkdtextfield2;

    @TableField("CFKDDATEPICKER5")
    private LocalDateTime cfkddatepicker5;

    @TableField("CFKDDATEPICKER6")
    private LocalDateTime cfkddatepicker6;

    @TableField("CFGJJNDWID")
    private String cfgjjndwid;

    @TableField("CFKDTEXTFIELD3")
    private String cfkdtextfield3;

    @TableField("CFSYZKCXID")
    private String cfsyzkcxid;

    @TableField("CFYGGWID")
    private String cfyggwid;


}
