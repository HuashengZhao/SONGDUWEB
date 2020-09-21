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
@TableName("T_CON_CONTRACTINVOICE")
public class TConContractinvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("FCONTRACTID")
    private String fcontractid;

    @TableField("FINVOICETYPE")
    private String finvoicetype;

    @TableField("FBUYNAME")
    private String fbuyname;

    @TableField("FBUYTAXNO")
    private String fbuytaxno;

    @TableField("FBUYADDRESSANDPHONE")
    private String fbuyaddressandphone;

    @TableField("FBUYBANKNO")
    private String fbuybankno;

    @TableField("FSALENAME")
    private String fsalename;

    @TableField("FSALETAXNO")
    private String fsaletaxno;

    @TableField("FSALEADDRESSANDPHONE")
    private String fsaleaddressandphone;

    @TableField("FSALEBANKNO")
    private String fsalebankno;

    @TableField("FTOTALAMOUNT")
    private Double ftotalamount;

    @TableField("FTOTALAMOUNTCAPITAL")
    private String ftotalamountcapital;

    @TableField("FINVOICENUMBER")
    private String finvoicenumber;

    @TableField("FORGUNITID")
    private String forgunitid;

    @TableField("FSTATE")
    private String fstate;

    @TableField("FNAME")
    private String fname;

    @TableField("FORIGINALAMOUNT")
    private Double foriginalamount;

    @TableField("FAMOUNT")
    private Double famount;

    @TableField("FAUDITTIME")
    private LocalDateTime faudittime;

    @TableField("FBOOKEDDATE")
    private LocalDateTime fbookeddate;

    @TableField("FPERIODID")
    private String fperiodid;

    @TableField("FISRESPITE")
    private Long fisrespite;

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

    @TableField("FTOTALRATEAMOUNT")
    private Double ftotalrateamount;

    @TableField("FISVALID")
    private Long fisvalid;


}
