package com.example.EAS.service;

import com.example.EAS.model.TConChangeauditbill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.ChangeAuditVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-22
 */
public interface ITConChangeauditbillService extends IService<TConChangeauditbill> {

    PageBean<ChangeAuditVO> getChangeAuditList(ChangeAuditVO vo);

    ChangeAuditVO viewChangeAudit(ChangeAuditVO vo);
}
