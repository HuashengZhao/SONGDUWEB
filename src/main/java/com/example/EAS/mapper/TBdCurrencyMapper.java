package com.example.EAS.mapper;

import com.example.EAS.model.TBdCurrency;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.CurrencyVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
public interface TBdCurrencyMapper extends BaseMapper<TBdCurrency> {

    List<CurrencyVO> selectDatas(CurrencyVO vo);
}
