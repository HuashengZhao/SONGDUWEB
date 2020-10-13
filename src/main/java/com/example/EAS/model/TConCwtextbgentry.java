package com.example.EAS.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_CON_CWTEXTBGENTRY")
public class TConCwtextbgentry implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("FHEADID")
    private String fheadid;

    @TableField("FREQUESTAMOUNT")
    private Double frequestamount;

    @TableField("FAMOUNT")
    private Double famount;

    @TableField("FACCOUNTVIEWID")
    private String faccountviewid;

    @TableField("FEXPENSETYPEID")
    private String fexpensetypeid;

    @TableField("FSEQ")
    private Long fseq;

    @TableId("FID")
    private String fid;

    @TableField("FBGITEMID")
    private String fbgitemid;


}
