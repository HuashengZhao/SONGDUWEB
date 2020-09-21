package com.example.EAS.vo;

import lombok.Data;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:LoginVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/24 14:29
 */
@Data
public class LoginVO {

    private String userName;
//    当前用户的编码
    private String person;
    private String personName;
//    当前用户的部门编码
    private String org;
    private String orgId;
    private String orgName;
//    登陆物业/地产信息
    private String sysName;
    private String dateTime;
    private String type;
    private String token;

}
