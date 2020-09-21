package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBdSupplierMapper;
import com.example.EAS.model.TBdSupplier;
import com.example.EAS.service.ITBdSupplierService;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.Util;
import com.example.EAS.vo.SupplierVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */

@Service
public class TBdSupplierServiceImpl extends ServiceImpl<TBdSupplierMapper, TBdSupplier> implements ITBdSupplierService {

    @Autowired
    private TBdSupplierMapper supplierMapper;

    @Override
    public PageBean<SupplierVO> getSuppliers(SupplierVO vo) {
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<SupplierVO> supplierVOList = supplierMapper.selectDatas(vo);
        if (supplierVOList.size() > 0) {
            for (SupplierVO supplierVO1 : supplierVOList) {
                String longNumber = supplierVO1.getLongNumber();
                if (Util.isNotEmpty(longNumber)) {
                    supplierVO1.setLongNumber(longNumber
                            .replace("!", ".")
                            .replace("-", "."));
                }
            }
        }
        PageBean<SupplierVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) supplierVOList).getTotal());
        pageBean.setPageData(supplierVOList);
        return pageBean;
    }
}