package com.example.EAS.service;

import com.example.EAS.model.TFdcCostaccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.CostAccountVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface ITFdcCostaccountService extends IService<TFdcCostaccount> {

    CostAccountVO getCostAccount(CostAccountVO vo);

    List<CostAccountVO> unUseCostAccount(CostAccountVO vo);
}
