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
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_CON_CONTRACTMARKETENTRY")
public class TConContractmarketentry implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("FHEADID")
    private String fheadid;

    @TableField("FDATE")
    private LocalDateTime fdate;

    @TableField("FRATE")
    private Double frate;

    @TableField("FAMOUNT")
    private Double famount;

    @TableField("FCONTENT")
    private String fcontent;

    @TableField("FREMARK")
    private String fremark;

    @TableField("FSEQ")
    private Long fseq;

    @TableId("FID")
    private String fid;


}
