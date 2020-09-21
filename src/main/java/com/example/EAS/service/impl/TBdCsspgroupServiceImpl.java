package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBdCsspgroupMapper;
import com.example.EAS.model.TBdCsspgroup;
import com.example.EAS.service.ITBdCsspgroupService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.SupplierTypeVO;
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
public class TBdCsspgroupServiceImpl extends ServiceImpl<TBdCsspgroupMapper, TBdCsspgroup> implements ITBdCsspgroupService {

    @Autowired
    private TBdCsspgroupMapper tBdCsspgroupMapper;

    @Override
    public SupplierTypeVO getSupplierType(SupplierTypeVO vo) {
        if (Util.isNotEmpty(vo)&&Util.isEmpty(vo.getStandardId())){
            return  null;
        }
        SupplierTypeVO supplierTypeVO1 = new SupplierTypeVO();
        List<SupplierTypeVO> voList = tBdCsspgroupMapper.selectDatas(vo);
        if (voList.size() > 0) {
            for (SupplierTypeVO supplierTypeVO : voList) {
                String longNumber = supplierTypeVO.getLongNumber();
                if (Util.isNotEmpty(longNumber)) {
                    supplierTypeVO.setLongNumber(longNumber
                            .replace("-", ".")
                            .replace("!", "."));
                }
            }
            supplierTypeVO1.setSupplierTypeVOList(voList);
        }

        return supplierTypeVO1;
    }

    @Override
    public SupplierTypeVO getSupplierStandard(SupplierTypeVO vo) {
        SupplierTypeVO supplierTypeVO = new SupplierTypeVO();
        List<SupplierTypeVO> supplierTypeVOS = tBdCsspgroupMapper.selectStandards(vo);
        if (Util.isNotEmpty(supplierTypeVOS)){
            supplierTypeVO.setSupplierTypeVOList(supplierTypeVOS);
        }
        return supplierTypeVO;
    }
}
