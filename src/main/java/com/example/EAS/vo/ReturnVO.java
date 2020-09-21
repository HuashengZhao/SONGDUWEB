package com.example.EAS.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:ReturnVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/12 10:14
 */
@Data
public class ReturnVO {

    private String userId;

    private String userName;

    private String userNumber;

    private LocalDateTime loadTime;

    private String org;

}
