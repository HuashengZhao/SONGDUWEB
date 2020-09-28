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
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_CON_CONTRACTWITHOUTTEXT")
public class TConContractwithouttext implements Serializable {

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

    @TableField("FCURPROJECTID")
    private String fcurprojectid;

    @TableField("FNAME")
    private String fname;

    @TableField("FAMOUNT")
    private Double famount;

    @TableField("FSIGNDATE")
    private LocalDateTime fsigndate;

    @TableField("FRECEIVEUNITID")
    private String freceiveunitid;

    @TableField("FSETTLEMENTTYPEID")
    private String fsettlementtypeid;

    @TableField("FFISETTLENO")
    private String ffisettleno;

    @TableField("FAUDITTIME")
    private LocalDateTime faudittime;

    @TableField("FORGUNITID")
    private String forgunitid;

    @TableField("FGENPAYMENTPLAN")
    private Long fgenpaymentplan;

    @TableField("FGENPAYMENTREQUE")
    private Long fgenpaymentreque;

    @TableField("FGENPAYMENTBILL")
    private Long fgenpaymentbill;

    @TableField("FSTATE")
    private String fstate;

    @TableField("FBANK")
    private String fbank;

    @TableField("FBANKACCT")
    private String fbankacct;

    @TableField("FISCOSTSPLIT")
    private Long fiscostsplit;

    @TableField("FCONSPLITEXECSTATE")
    private String fconsplitexecstate;

    @TableField("FBOOKEDDATE")
    private LocalDateTime fbookeddate;

    @TableField("FPERIODID")
    private String fperiodid;

    @TableField("FNOPAIDREASON")
    private String fnopaidreason;

    @TableField("FISNEEDPAID")
    private Long fisneedpaid;

    @TableField("FORIGINALAMOUNT")
    private Double foriginalamount;

    @TableField("FCURRENCYID")
    private String fcurrencyid;

    @TableField("FSOURCETYPE")
    private Long fsourcetype;

    @TableField("FSPLITSTATE")
    private String fsplitstate;

    @TableField("FACCOUNTID")
    private String faccountid;

    @TableField("FCONTRACTTYPEID")
    private String fcontracttypeid;

    @TableField("FCONCHARGETYPEID")
    private String fconchargetypeid;

    @TableField("FINVOICEAMT")
    private Double finvoiceamt;

    @TableField("FINVOICENUMBER")
    private String finvoicenumber;

    @TableField("FALLINVOICEAMT")
    private Double fallinvoiceamt;

    @TableField("FINVOICEDATE")
    private LocalDateTime finvoicedate;

    @TableField("FCONTRACTBASEDATAID")
    private String fcontractbasedataid;

    @TableField("FISRESPITE")
    private Long fisrespite;

    @TableField("FUSEDEPARTMENTID")
    private String fusedepartmentid;

    @TableField("FPROGRAMMINGCONTRACT")
    private String fprogrammingcontract;

    @TableField("FFDCDEPCONPLANID")
    private String ffdcdepconplanid;

    @TableField("FDEPPLANSTATE")
    private Long fdepplanstate;

    @TableField("FPAYMENTTYPEID")
    private String fpaymenttypeid;

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

    @TableField("FCOMPANYID")
    private String fcompanyid;

    @TableField("FISINVOICE")
    private Long fisinvoice;

    @TableField("FPAYBILLTYPEID")
    private String fpaybilltypeid;

    @TableField("FPAYCONTENTTYPEID")
    private String fpaycontenttypeid;

    @TableField("FPERSONID")
    private String fpersonid;

    @TableField("FPAYREQUESTBILLID")
    private String fpayrequestbillid;

    @TableField("FCODETYPEID")
    private String fcodetypeid;

    @TableField("FFEETYPE")
    private String ffeetype;

    @TableField("FRATEAMOUNT")
    private Double frateamount;

    @TableField("FTAXERQUA")
    private String ftaxerqua;

    @TableField("FTAXERNUM")
    private String ftaxernum;

    @TableField("FMPCOSTACCOUNTID")
    private String fmpcostaccountid;

    @TableField("FMARKETPROJECTID")
    private String fmarketprojectid;

    @TableField("FLXNUMID")
    private String flxnumid;

    @TableField("FISJT")
    private Long fisjt;


}
