package com.example.EAS.mapper;

import com.example.EAS.model.TFdcCostaccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.CostAccountVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface TFdcCostaccountMapper extends BaseMapper<TFdcCostaccount> {

    List<CostAccountVO> selectDatas(CostAccountVO vo);

    List<CostAccountVO> selectUnUseCostAccount(CostAccountVO vo);
}
