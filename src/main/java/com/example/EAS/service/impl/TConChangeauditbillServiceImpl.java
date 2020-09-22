package com.example.EAS.service.impl;

import com.example.EAS.model.TConChangeauditbill;
import com.example.EAS.mapper.TConChangeauditbillMapper;
import com.example.EAS.service.ITConChangeauditbillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.ChangeAuditVO;
import com.example.EAS.vo.ContractVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-22
 */
@Service
public class TConChangeauditbillServiceImpl extends ServiceImpl<TConChangeauditbillMapper, TConChangeauditbill> implements ITConChangeauditbillService {

    @Autowired
    private TConChangeauditbillMapper mapper;

    @Override
    public PageBean<ChangeAuditVO> getChangeAuditList(ChangeAuditVO vo) {
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<ChangeAuditVO> auditVOS = mapper.selectDatas(vo);
        PageBean<ChangeAuditVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) auditVOS).getTotal());
        pageBean.setPageData(auditVOS);
        return pageBean;
    }
}
