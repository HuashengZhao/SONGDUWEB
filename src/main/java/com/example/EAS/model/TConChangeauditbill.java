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
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_CON_CHANGEAUDITBILL")
public class TConChangeauditbill implements Serializable {

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

    @TableField("FCURPROJECTID")
    private String fcurprojectid;

    @TableField("FAUDITTYPEID")
    private String faudittypeid;

    @TableField("FCHANGEREASONID")
    private String fchangereasonid;

    @TableField("FCHANGESTATE")
    private String fchangestate;

    @TableField("FURGENTDEGREE")
    private String furgentdegree;

    @TableField("FJOBTYPEID")
    private String fjobtypeid;

    @TableField("FSPECIALTYTYPEID")
    private String fspecialtytypeid;

    @TableField("FCHANGESUBJECT")
    private String fchangesubject;

    @TableField("FGRAPHCOUNT")
    private String fgraphcount;

    @TableField("FSUPPCOUNT")
    private Long fsuppcount;

    @TableField("FISREGISTER")
    private Long fisregister;

    @TableField("FTOTALCOST")
    private Double ftotalcost;

    @TableField("FCOSTNOUSE")
    private Double fcostnouse;

    @TableField("FREASON")
    private String freason;

    @TableField("FAMOUNTA")
    private Double famounta;

    @TableField("FAMOUNTDUTYSUPP")
    private Double famountdutysupp;

    @TableField("FCONDUCTDEPTID")
    private String fconductdeptid;

    @TableField("FAHEADREASON")
    private String faheadreason;

    @TableField("FVALIDATOR")
    private String fvalidator;

    @TableField("FCONNECTTYPE")
    private String fconnecttype;

    @TableField("FCURPROJECTNAME")
    private String fcurprojectname;

    @TableField("FAUDITTYPENAME")
    private String faudittypename;

    @TableField("FJOBTYPENAME")
    private String fjobtypename;

    @TableField("FSPECIALTYTYPENAME")
    private String fspecialtytypename;

    @TableField("FISNOUSE")
    private Long fisnouse;

    @TableField("FINVALIDCOSTREASONID")
    private String finvalidcostreasonid;

    @TableField("FBOOKEDDATE")
    private LocalDateTime fbookeddate;

    @TableField("FPERIODID")
    private String fperiodid;

    @TableField("FORIGINALAMOUNT")
    private Double foriginalamount;

    @TableField("FSOURCETYPE")
    private Long fsourcetype;

    @TableField("FISRESPITE")
    private Long fisrespite;

    @TableField("FOFFER")
    private String foffer;

    @TableField("FCONSTRUNITID")
    private String fconstrunitid;

    @TableField("FDESIGNUNITID")
    private String fdesignunitid;

    @TableField("FCONSTRSITE")
    private String fconstrsite;

    @TableField("FREADESC")
    private String freadesc;

    @TableField("FCONDUCTUNITID")
    private String fconductunitid;

    @TableField("FISIMPORTCHANGE")
    private Long fisimportchange;

    @TableField("FSPECIALNAME")
    private String fspecialname;

    @TableField("FOWNID")
    private String fownid;

    @TableField("FISCHANGECONTRACT")
    private Long fischangecontract;

    @TableField("FISRECEIPTS")
    private Long fisreceipts;

    @TableField("FCOSTUNIT")
    private String fcostunit;

    @TableField("FISFEE")
    private String fisfee;

    @TableField("FWFTYPEID")
    private String fwftypeid;

    @TableField("FISALREADYEXECUTED")
    private Long fisalreadyexecuted;

    @TableField("FTOTALCONSTRUCTPRICE")
    private Double ftotalconstructprice;


}
