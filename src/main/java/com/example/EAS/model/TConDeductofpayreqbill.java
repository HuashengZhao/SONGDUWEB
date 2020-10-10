package com.example.EAS.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author watson
 * @since 2020-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_CON_DEDUCTOFPAYREQBILL")
public class TConDeductofpayreqbill implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("FID")
    private String fid;

    @TableField("FDEDUCTTYPEID")
    private String fdeducttypeid;

    @TableField("FAMOUNT")
    private Double famount;

    @TableField("FALLREQAMT")
    private Double fallreqamt;

    @TableField("FALLPAIDAMT")
    private Double fallpaidamt;

    @TableField("FPASPAID")
    private Long fpaspaid;

    @TableField("FPAYREQUESTBILLID")
    private String fpayrequestbillid;

    @TableField("FPAYMENTBILLID")
    private String fpaymentbillid;

    @TableField("FORIGINALAMOUNT")
    private Double foriginalamount;

    @TableField("FALLREQORIAMT")
    private Double fallreqoriamt;

    @TableField("FALLPAIDORIAMT")
    private Double fallpaidoriamt;


}
