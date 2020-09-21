package com.example.EAS.service;

import com.example.EAS.model.TBdSupplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.SupplierVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface ITBdSupplierService extends IService<TBdSupplier> {

    PageBean<SupplierVO> getSuppliers(SupplierVO vo);
}
