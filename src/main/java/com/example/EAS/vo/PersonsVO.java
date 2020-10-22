package com.example.EAS.vo;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:PersonsVO
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/8/17 17:25
 */
@Data
public class PersonsVO {

    private String id;
    private String personName;
    private String personNumber;
//   编号
    private String person;
    private String zwName;
//    行政组织
    private String orgName;
    private String orgLongNumber;
    private String unitId;
//    员工类型
    private String typeName;
    private String userId;
    private String company;
//    1正常 2禁用
    private Integer deleteStatus;
    private String status;
//    是否包含组织下级对应的员工
    private Integer hasNext;
    private String cost;
    private String orgId;
    private List<PersonsVO> personsVOList;
    private Integer currentPage;

    private Integer pageSize;
    private List<OrgVO> orgVOList;
    private List<String> orgidList;

}
