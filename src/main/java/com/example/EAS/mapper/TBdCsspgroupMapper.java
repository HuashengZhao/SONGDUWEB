package com.example.EAS.mapper;

import com.example.EAS.model.TBdCsspgroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.SupplierTypeVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface TBdCsspgroupMapper extends BaseMapper<TBdCsspgroup> {

    List<SupplierTypeVO> selectDatas(SupplierTypeVO vo);

    List<SupplierTypeVO> selectStandards(SupplierTypeVO vo);
}
