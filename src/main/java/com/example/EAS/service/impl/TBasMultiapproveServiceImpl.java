package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasMultiapproveMapper;
import com.example.EAS.model.TBasMultiapprove;
import com.example.EAS.service.ITBasMultiapproveService;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.MultiApproveVO;
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
 * @since 2020-09-02
 */
@Service
public class TBasMultiapproveServiceImpl extends ServiceImpl<TBasMultiapproveMapper, TBasMultiapprove> implements ITBasMultiapproveService {

    @Autowired
    private TBasMultiapproveMapper mapper;

    @Override
    public PageBean<MultiApproveVO> getMultiApprove(MultiApproveVO vo) {
        PageHelper.startPage(vo.getCurrentPage(),vo.getPageSize());
        List<MultiApproveVO> multiApproveVOList = mapper.selectDatas(vo);
        PageBean<MultiApproveVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) multiApproveVOList).getTotal());
        pageBean.setPageData(multiApproveVOList);
        return pageBean;
    }
}
