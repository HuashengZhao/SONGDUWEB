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
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_CON_MARKETPROJECTCOSTENTRY")
public class TConMarketprojectcostentry implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("FHEADID")
    private String fheadid;

    @TableField("FCOSTACCOUNTID")
    private String fcostaccountid;

    @TableField("FSEQ")
    private Long fseq;

    @TableId("FID")
    private String fid;

    @TableField("FAMOUNT")
    private Double famount;

    @TableField("FTYPE")
    private String ftype;


}
