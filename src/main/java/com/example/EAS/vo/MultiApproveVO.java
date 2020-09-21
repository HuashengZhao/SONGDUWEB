package com.example.EAS.vo;

import lombok.Data;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:MultiApproveVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/18 13:54
 */
@Data
public class MultiApproveVO {

    private String orgId;
    private Integer isPass;
    private String opinion;
    private String uName;
    private String content;
    private String createTime;
    private String userName;
    private String actName;
    private Integer currentPage;
    private Integer pageSize;

}
