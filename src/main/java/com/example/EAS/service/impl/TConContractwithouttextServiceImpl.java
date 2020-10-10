package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.mapper.TConContractwithouttextMapper;
import com.example.EAS.model.TConContractwithouttext;
import com.example.EAS.service.ITConContractwithouttextService;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.Util;
import com.example.EAS.vo.NoTextContractVO;
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
 * @since 2020-09-28
 */
@Service
public class TConContractwithouttextServiceImpl extends ServiceImpl<TConContractwithouttextMapper, TConContractwithouttext> implements ITConContractwithouttextService {

    @Autowired
    private TConContractwithouttextMapper mapper;
    @Autowired
    private TBasAttachmentMapper attachmentMapper;

    @Override
    public PageBean<NoTextContractVO> getNoTextBills(NoTextContractVO vo) {
        String orgId = vo.getOrgId();
        String projectId = vo.getProjectId();
        if (Util.isEmpty(orgId)||Util.isEmpty(projectId)){
            return null;
        }
        PageHelper.startPage(vo.getCurrentPage(),vo.getPageSize());
        List<NoTextContractVO> noTextContractVOList = mapper.selectDatas(vo);
        if (noTextContractVOList!=null && noTextContractVOList.size()>0){
            for (NoTextContractVO noTextContractVO : noTextContractVOList) {

            }
        }
        PageBean<NoTextContractVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) noTextContractVOList).getTotal());
        pageBean.setPageData(noTextContractVOList);
        return pageBean;
    }
}
