package com.example.EAS.mapper;

import com.example.EAS.model.TBdSupplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.SupplierVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface TBdSupplierMapper extends BaseMapper<TBdSupplier> {

    List<SupplierVO> selectDatas(SupplierVO vo);
}
