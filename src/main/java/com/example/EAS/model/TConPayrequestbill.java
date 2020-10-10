package com.example.EAS.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_CON_PAYREQUESTBILL")
public class TConPayrequestbill implements Serializable {

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

    @TableField("FNAME")
    private String fname;

    @TableField("FAMOUNT")
    private Double famount;

    @TableField("FSTATE")
    private String fstate;

    @TableField("FORGUNITID")
    private String forgunitid;

    @TableField("FAUDITTIME")
    private LocalDateTime faudittime;

    @TableField("FFIVOUCHERED")
    private Long ffivouchered;

    @TableField("FUSEDEPARTMENTID")
    private String fusedepartmentid;

    @TableField("FPAYDATE")
    private LocalDateTime fpaydate;

    @TableField("FMONEYDESC")
    private String fmoneydesc;

    @TableField("FRECBANK")
    private String frecbank;

    @TableField("FRECACCOUNT")
    private String frecaccount;

    @TableField("FCONTRACTNO")
    private String fcontractno;

    @TableField("FATTACHMENT")
    private Long fattachment;

    @TableField("FEXCHANGERATE")
    private Double fexchangerate;

    @TableField("FCURRENCYID")
    private String fcurrencyid;

    @TableField("FSUPPLIERID")
    private String fsupplierid;

    @TableField("FSETTLEMENTTYPEID")
    private String fsettlementtypeid;

    @TableField("FCURPROJECTID")
    private String fcurprojectid;

    @TableField("FCONTRACTNAME")
    private String fcontractname;

    @TableField("FCONTRACTPRICE")
    private Double fcontractprice;

    @TableField("FLATESTPRICE")
    private Double flatestprice;

    @TableField("FADDPROJECTAMT")
    private Double faddprojectamt;

    @TableField("FCHANGEAMT")
    private Double fchangeamt;

    @TableField("FPAYEDAMT")
    private Double fpayedamt;

    @TableField("FPAYPARTAMATLAMT")
    private Double fpaypartamatlamt;

    @TableField("FPAYTIMES")
    private Long fpaytimes;

    @TableField("FPROJECTPRICEINCONTRACT")
    private Double fprojectpriceincontract;

    @TableField("FSCHEDULEAMT")
    private Double fscheduleamt;

    @TableField("FSETTLEAMT")
    private Double fsettleamt;

    @TableField("FURENTDEGREE")
    private Long furentdegree;

    @TableField("FCAPITALAMOUNT")
    private String fcapitalamount;

    @TableField("FPAYMENTTYPE")
    private String fpaymenttype;

    @TableField("FCURPLANNEDPAYMENT")
    private Double fcurplannedpayment;

    @TableField("FCURBACKPAY")
    private Double fcurbackpay;

    @TableField("FPAYMENTPLAN")
    private Double fpaymentplan;

    @TableField("FCURREQPERCENT")
    private Double fcurreqpercent;

    @TableField("FALLREQPERCENT")
    private Double fallreqpercent;

    @TableField("FIMAGESCHEDULE")
    private Double fimageschedule;

    @TableField("FCURPAID")
    private Double fcurpaid;

    @TableField("FPRJALLREQAMT")
    private Double fprjallreqamt;

    @TableField("FADDPRJALLREQAMT")
    private Double faddprjallreqamt;

    @TableField("FPAYPARTAMATLALLREQAMT")
    private Double fpaypartamatlallreqamt;

    @TableField("FLSTPRJALLREQAMT")
    private Double flstprjallreqamt;

    @TableField("FLSTADDPRJALLREQAMT")
    private Double flstaddprjallreqamt;

    @TableField("FLSTAMATLALLREQAMT")
    private Double flstamatlallreqamt;

    @TableField("FCONTRACTID")
    private String fcontractid;

    @TableField("FHASPAYOFF")
    private Long fhaspayoff;

    @TableField("FHASCLOSED")
    private Long fhasclosed;

    @TableField("FREALSUPPLIERID")
    private String frealsupplierid;

    @TableField("FLSTPRJALLPAIDAMT")
    private Double flstprjallpaidamt;

    @TableField("FLSTADDPRJALLPAIDAMT")
    private Double flstaddprjallpaidamt;

    @TableField("FLSTAMATLALLPAIDAMT")
    private Double flstamatlallpaidamt;

    @TableField("FPAYMENTPROPORTION")
    private Double fpaymentproportion;

    @TableField("FCOMPLETEPRJAMT")
    private Double fcompleteprjamt;

    @TableField("FCOSTAMOUNT")
    private Double fcostamount;

    @TableField("FGRTAMOUNT")
    private Double fgrtamount;

    @TableField("FBOOKEDDATE")
    private LocalDateTime fbookeddate;

    @TableField("FPERIODID")
    private String fperiodid;

    @TableField("FISPAY")
    private Long fispay;

    @TableField("FORIGINALAMOUNT")
    private Double foriginalamount;

    @TableField("FSOURCETYPE")
    private Long fsourcetype;

    @TableField("FISDIFFERPLACE")
    private Long fisdifferplace;

    @TableField("FUSAGE")
    private String fusage;

    @TableField("FPAYREQUESTSPLITID")
    private String fpayrequestsplitid;

    @TableField("FTOTALSETTLEPRICE")
    private Double ftotalsettleprice;

    @TableField("FACTPAIEDAMOUNT")
    private Double factpaiedamount;

    @TableField("FCONPAYPLANID")
    private String fconpayplanid;

    @TableField("FPROJECTPRICEINCONTRACTORI")
    private Double fprojectpriceincontractori;

    @TableField("FPAYPARTAMATLORIAMT")
    private Double fpaypartamatloriamt;

    @TableField("FGUERDONORIGINALAMT")
    private Double fguerdonoriginalamt;

    @TableField("FCOMPENSATIONORIGINALAMT")
    private Double fcompensationoriginalamt;

    @TableField("FCURPAIDORIGINAL")
    private Double fcurpaidoriginal;

    @TableField("FSOURCE")
    private String fsource;

    @TableField("FCONTRACTBASEID")
    private String fcontractbaseid;

    @TableField("FINVOICEAMT")
    private Double finvoiceamt;

    @TableField("FINVOICENUMBER")
    private String finvoicenumber;

    @TableField("FALLINVOICEAMT")
    private Double fallinvoiceamt;

    @TableField("FINVOICEDATE")
    private LocalDateTime finvoicedate;

    @TableField("FACTPAIEDLOCAMOUNT")
    private Double factpaiedlocamount;

    @TableField("FPRJPAYENTRYID")
    private String fprjpayentryid;

    @TableField("FISRESPITE")
    private Long fisrespite;

    @TableField("FLOCALCURRENCYID")
    private String flocalcurrencyid;

    @TableField("FPROCESS")
    private String fprocess;

    @TableField("FPLANUNCON")
    private String fplanuncon;

    @TableField("FPLANHASCON")
    private String fplanhascon;

    @TableField("FINVOICEORIAMT")
    private Double finvoiceoriamt;

    @TableField("FALLINVOICEORIAMT")
    private Double fallinvoiceoriamt;

    @TableField("FDEPPLANSTATE")
    private Long fdepplanstate;

    @TableField("FISBGCONTROL")
    private Long fisbgcontrol;

    @TableField("FAPPLIERID")
    private String fapplierid;

    @TableField("FAPPLIERORGUNITID")
    private String fapplierorgunitid;

    @TableField("FAPPLIERCOMPANYID")
    private String fappliercompanyid;

    @TableField("FCOSTEDDEPTID")
    private String fcosteddeptid;

    @TableField("FCOSTEDCOMPANYID")
    private String fcostedcompanyid;

    @TableField("FISINVOICE")
    private Long fisinvoice;

    @TableField("FPAYBILLTYPEID")
    private String fpaybilltypeid;

    @TableField("FPAYCONTENTTYPEID")
    private String fpaycontenttypeid;

    @TableField("FPERSONID")
    private String fpersonid;

    @TableField("FRATEAMOUNT")
    private Double frateamount;

    @TableField("FAPPAMOUNT")
    private Double fappamount;

    @TableField("FORGTYPE")
    private String forgtype;

    @TableField("FISJT")
    private Long fisjt;

    @TableField("FLXNUMID")
    private String flxnumid;


}
