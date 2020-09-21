package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TConContractinvoiceMapper;
import com.example.EAS.model.TConContractinvoice;
import com.example.EAS.service.ITConContractinvoiceService;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.InvoiceVO;
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
public class TConContractinvoiceServiceImpl extends ServiceImpl<TConContractinvoiceMapper, TConContractinvoice> implements ITConContractinvoiceService {

    @Autowired
    private TConContractinvoiceMapper mapper;

    @Override
    public PageBean<InvoiceVO> getInvoice(InvoiceVO vo) {

        PageHelper.startPage(vo.getCurrentPage(),vo.getPageSize());
        List<InvoiceVO> vos =  mapper.selectDatas(vo);
        PageBean<InvoiceVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) vos).getTotal());
        pageBean.setPageData(vos);
        return pageBean;
    }
}
