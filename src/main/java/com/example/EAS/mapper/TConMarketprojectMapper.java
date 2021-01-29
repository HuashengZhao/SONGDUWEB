package com.example.EAS.mapper;

import com.example.EAS.model.TConMarketproject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.CostAccountVO;
import com.example.EAS.vo.MarketProjectVO;
import com.example.EAS.vo.PriceUnitVO;
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
public interface TConMarketprojectMapper extends BaseMapper<TConMarketproject> {

    List<MarketProjectVO> selectDatas(MarketProjectVO vo);

    MarketProjectVO selectDataById(String id);


    List<CostAccountVO> selectCostAccounts(String mpId);

    List<PriceUnitVO> selectPriceUnits(String mpId);

    List<SupplierVO> selectSignSuppliers(String mpId);

    void updateData(String easid);

}
