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
@TableName("T_CON_CONTRACTBILL")
public class TConContractbill implements Serializable {

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

    @TableField("FCHGPERCFORWARN")
    private Double fchgpercforwarn;

    @TableField("FPAYPERCFORWARN")
    private Double fpaypercforwarn;

    @TableField("FSIGNDATE")
    private LocalDateTime fsigndate;

    @TableField("FLOWESTPRICE")
    private Double flowestprice;

    @TableField("FLOWERPRICE")
    private Double flowerprice;

    @TableField("FMIDDLEPRICE")
    private Double fmiddleprice;

    @TableField("FHIGHERPRICE")
    private Double fhigherprice;

    @TableField("FHIGHESTPRICE")
    private Double fhighestprice;

    @TableField("FWINPRICE")
    private Double fwinprice;

    @TableField("FQUANTITY")
    private Double fquantity;

    @TableField("FFILENO")
    private String ffileno;

    @TableField("FBASEPRICE")
    private Double fbaseprice;

    @TableField("FSECONDPRICE")
    private Double fsecondprice;

    @TableField("FLANDDEVELOPERID")
    private String flanddeveloperid;

    @TableField("FCONTRACTTYPEID")
    private String fcontracttypeid;

    @TableField("FINVITETYPEID")
    private String finvitetypeid;

    @TableField("FLOWESTPRICEUNITID")
    private String flowestpriceunitid;

    @TableField("FMIDDLEPRICEUNITID")
    private String fmiddlepriceunitid;

    @TableField("FLOWERPRICEUNITID")
    private String flowerpriceunitid;

    @TableField("FHIGHERPRICEUNITID")
    private String fhigherpriceunitid;

    @TableField("FHIGHESTPRICEUNIID")
    private String fhighestpriceuniid;

    @TableField("FWINUNITID")
    private String fwinunitid;

    @TableField("FCOSTPROPERTY")
    private String fcostproperty;

    @TableField("FCONTRACTPROPERT")
    private String fcontractpropert;

    @TableField("FCONTRACTSOURCE")
    private String fcontractsource;

    @TableField("FPARTBID")
    private String fpartbid;

    @TableField("FPARTCID")
    private String fpartcid;

    @TableField("FCURPROJECTID")
    private String fcurprojectid;

    @TableField("FISCOSTSPLIT")
    private Long fiscostsplit;

    @TableField("FHASSETTLED")
    private Long fhassettled;

    @TableField("FPRJPRICEINCONPAID")
    private Double fprjpriceinconpaid;

    @TableField("FADDPRJAMTPAID")
    private Double faddprjamtpaid;

    @TableField("FPAIDPARTAMATLAMT")
    private Double fpaidpartamatlamt;

    @TableField("FSETTLEAMT")
    private Double fsettleamt;

    @TableField("FCURRENCYID")
    private String fcurrencyid;

    @TableField("FLOCALAMOUNT")
    private Double flocalamount;

    @TableField("FGRTAMOUNT")
    private Double fgrtamount;

    @TableField("FGRTLOCALAMOUNT")
    private Double fgrtlocalamount;

    @TableField("FEXRATE")
    private Double fexrate;

    @TableField("FREMARK")
    private String fremark;

    @TableField("FSPLITSTATE")
    private String fsplitstate;

    @TableField("FRESPDEPTID")
    private String frespdeptid;

    @TableField("FPAYSCALE")
    private Double fpayscale;

    @TableField("FISAMTWITHOUTCOST")
    private Long fisamtwithoutcost;

    @TableField("FSTAMPTAXRATE")
    private Double fstamptaxrate;

    @TableField("FSTAMPTAXAMT")
    private Double fstamptaxamt;

    @TableField("FRESPPERSONID")
    private String fresppersonid;

    @TableField("FCODETYPEID")
    private String fcodetypeid;

    @TableField("FMAINCONTRACTNUMBER")
    private String fmaincontractnumber;

    @TableField("FISARCHIVED")
    private Long fisarchived;

    @TableField("FEXECSTATE")
    private String fexecstate;

    @TableField("FCOOPLEVEL")
    private String fcooplevel;

    @TableField("FPRICETYPE")
    private String fpricetype;

    @TableField("FGRTRATE")
    private Double fgrtrate;

    @TableField("FCONSPLITEXECSTATE")
    private String fconsplitexecstate;

    @TableField("FCOSTPROPERTYID")
    private String fcostpropertyid;

    @TableField("FWFLASTAUDITTIME")
    private LocalDateTime fwflastaudittime;

    @TableField("FWFLASTAUDITORID")
    private String fwflastauditorid;

    @TableField("FBOOKEDDATE")
    private LocalDateTime fbookeddate;

    @TableField("FPERIODID")
    private String fperiodid;

    @TableField("FWEBSRVNUMBER")
    private String fwebsrvnumber;

    @TableField("FSOURCETYPE")
    private Long fsourcetype;

    @TableField("FORIGINALAMOUNT")
    private Double foriginalamount;

    @TableField("FARCHIVINGNUMBER")
    private String farchivingnumber;

    @TableField("FISPARTAMATERIALCON")
    private Long fispartamaterialcon;

    @TableField("FWFLASTAUDITNAME")
    private String fwflastauditname;

    @TableField("FCONCHARGETYPEID")
    private String fconchargetypeid;

    @TableField("FISMEASURECONTRACT")
    private Long fismeasurecontract;

    @TableField("FCONTRACTSOURCEID")
    private String fcontractsourceid;

    @TableField("FAMTWITHOUTCOST")
    private Double famtwithoutcost;

    @TableField("FCONTRACTBASEDATAID")
    private String fcontractbasedataid;

    @TableField("FISRESPITE")
    private Long fisrespite;

    @TableField("FCODINGNUMBER")
    private String fcodingnumber;

    @TableField("FBAIL")
    private String fbail;

    @TableField("FOVERRATE")
    private Double foverrate;

    @TableField("FARCHIVEDNUMBER")
    private String farchivednumber;

    @TableField("FISSUBCONTRACT")
    private Long fissubcontract;

    @TableField("FMAINCONTRACTID")
    private String fmaincontractid;

    @TableField("FEFFECTIVESTARTDATE")
    private LocalDateTime feffectivestartdate;

    @TableField("FEFFECTIVEENDDATE")
    private LocalDateTime feffectiveenddate;

    @TableField("FINFORMATION")
    private String finformation;

    @TableField("FPROGRAMMINGCONTRACT")
    private String fprogrammingcontract;

    @TableField("FISRENEWRELATEPROG")
    private Long fisrenewrelateprog;

    @TableField("FSRCPROID")
    private String fsrcproid;

    @TableField("FMODELID")
    private String fmodelid;

    @TableField("FCREATORPOSITIONID")
    private String fcreatorpositionid;

    @TableField("FMODEL")
    private String fmodel;

    @TableField("FCEREMONYB")
    private Double fceremonyb;

    @TableField("FCEREMONYBB")
    private Double fceremonybb;

    @TableField("FISSTARDCONTRACT")
    private Long fisstardcontract;

    @TableField("FISOPENCONTRACT")
    private Long fisopencontract;

    @TableField("FCONTRACTSETTLETYPE")
    private String fcontractsettletype;

    @TableField("FCREATEDEPTID")
    private String fcreatedeptid;

    @TableField("FCONTRACTMODEL")
    private String fcontractmodel;

    @TableField("FAGREEMENTID")
    private String fagreementid;

    @TableField("FORGTYPE")
    private String forgtype;

    @TableField("FCONTRACTWFTYPEID")
    private String fcontractwftypeid;

    @TableField("FSRCAMOUNT")
    private Double fsrcamount;

    @TableField("FISHASCOSTINDEX")
    private Long fishascostindex;

    @TableField("FENTRUSTREASON")
    private String fentrustreason;

    @TableField("FINVITEBILLENTRYID")
    private String finvitebillentryid;

    @TableField("FINVITEBILLID")
    private String finvitebillid;

    @TableField("FINVITEPROJECTID")
    private String finviteprojectid;

    @TableField("FSTRATEGYPACTID")
    private String fstrategypactid;

    @TableField("FTENDERDISCUSSTIONID")
    private String ftenderdiscusstionid;

    @TableField("FNEEDDEPTID")
    private String fneeddeptid;

    @TableField("FNEEDPERSONID")
    private String fneedpersonid;

    @TableField("FTAXERQUA")
    private String ftaxerqua;

    @TableField("FTAXERNUM")
    private String ftaxernum;

    @TableField("FBANK")
    private String fbank;

    @TableField("FBANKACCOUNT")
    private String fbankaccount;

    @TableField("FMARKETPROJECTID")
    private String fmarketprojectid;

    @TableField("FMPCOSTACCOUNTID")
    private String fmpcostaccountid;

    @TableField("FTAENTRYID")
    private String ftaentryid;

    @TableField("FSTARTDATE")
    private LocalDateTime fstartdate;

    @TableField("FENDDATE")
    private LocalDateTime fenddate;

    @TableField("FLXNUMID")
    private String flxnumid;

    @TableField("FISJT")
    private Long fisjt;


}
