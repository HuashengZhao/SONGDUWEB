package com.example.EAS.vo;

import lombok.Data;

@Data
public class ChangeAuditContentVO {

    private String id;
    private String num;
//    变更内容
    private String changeContent;
//    对进度的影响
    private String effect;
//    是否返工
    private Integer isBack;
}
