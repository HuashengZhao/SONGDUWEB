package com.example.EAS.mapper;

import com.example.EAS.model.TConMarketproject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.MarketProjectVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface TConMarketprojectMapper extends BaseMapper<TConMarketproject> {

    List<MarketProjectVO> selectDatas(MarketProjectVO vo);
}
