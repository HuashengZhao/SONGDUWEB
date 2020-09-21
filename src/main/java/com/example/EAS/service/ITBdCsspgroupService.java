package com.example.EAS.service;

import com.example.EAS.model.TBdCsspgroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.SupplierTypeVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface ITBdCsspgroupService extends IService<TBdCsspgroup> {

    SupplierTypeVO getSupplierType(SupplierTypeVO vo);

    SupplierTypeVO getSupplierStandard(SupplierTypeVO vo);
}
