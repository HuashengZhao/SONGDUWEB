package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TConBanknumMapper;
import com.example.EAS.model.TConBanknum;
import com.example.EAS.service.ITConBanknumService;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.BankNumberVO;
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
 * @since 2020-08-31
 */

@Service
public class TConBanknumServiceImpl extends ServiceImpl<TConBanknumMapper, TConBanknum> implements ITConBanknumService {

    @Autowired
    private TConBanknumMapper bankNumMapper;

    @Override
    public PageBean<BankNumberVO> getBankNum(BankNumberVO vo) {
        PageHelper.startPage(vo.getCurrentPage(),vo.getPageSize());
        List<BankNumberVO> bankNumberVOList = bankNumMapper.selectDatas(vo);
        PageBean<BankNumberVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) bankNumberVOList).getTotal());
        pageBean.setPageData(bankNumberVOList);
        return pageBean;
    }
}
