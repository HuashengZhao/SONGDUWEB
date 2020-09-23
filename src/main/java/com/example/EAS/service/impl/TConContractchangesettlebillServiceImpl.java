package com.example.EAS.service.impl;

import com.example.EAS.mapper.TConChangeauditbillMapper;
import com.example.EAS.model.TConContractchangesettlebill;
import com.example.EAS.mapper.TConContractchangesettlebillMapper;
import com.example.EAS.service.ITConContractchangesettlebillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.Util;
import com.example.EAS.vo.ChangeAuditVO;
import com.example.EAS.vo.ChangeSettleVO;
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
public class TConContractchangesettlebillServiceImpl extends ServiceImpl<TConContractchangesettlebillMapper, TConContractchangesettlebill> implements ITConContractchangesettlebillService {

    @Autowired
    private TConContractchangesettlebillMapper mapper;
    @Autowired
    private TConChangeauditbillMapper auditMapper;

    @Override
    public PageBean<ChangeSettleVO> getChangeSettleList(ChangeSettleVO vo) {
        String orgId = vo.getOrgId();
        if (Util.isEmpty(orgId)){
            return null;
        }
        String projectId = vo.getProjectId();
//        如果是项目 获取项目下的分期id集合
        if (Util.isNotEmpty(projectId)){
            List<String> projectIds= auditMapper.selectProjectInfo(projectId);
            if (projectIds!=null && projectIds.size()>0){
                vo.setProjectIds(projectIds);
            }
        }
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<ChangeSettleVO> settleVOS = mapper.selectDatas(vo);
        PageBean<ChangeSettleVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) settleVOS).getTotal());
        pageBean.setPageData(settleVOS);
        return pageBean;
    }
}
