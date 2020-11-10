package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TFdcCostaccountMapper;
import com.example.EAS.model.TFdcCostaccount;
import com.example.EAS.service.ITFdcCostaccountService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.CostAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class TFdcCostaccountServiceImpl extends ServiceImpl<TFdcCostaccountMapper, TFdcCostaccount> implements ITFdcCostaccountService {

    @Autowired
    private TFdcCostaccountMapper mapper;

    @Override
    public CostAccountVO getCostAccount(CostAccountVO vo) {
        if (Util.isEmpty(vo.getMarketId())) {
            return null;
        }
        List<CostAccountVO> costAccountVOList = new ArrayList<>();
        String controlType = vo.getControlType();
        if (Util.isNotEmpty(controlType) && controlType.equals("CONTRACT")) {
            List<String> costIDs = mapper.selectCostAccountId(vo);
            vo.setCostIDs(costIDs);
            costAccountVOList = mapper.selectUnUseCostAccount(vo);
        } else if (Util.isNotEmpty(controlType) && controlType.equals("NOTEXTCONTRACT")) {
            costAccountVOList = mapper.selectCostAccountHasBalance(vo);
        } else {
            costAccountVOList = mapper.selectDatas(vo);
        }
        if (costAccountVOList != null && costAccountVOList.size() > 0) {
            for (CostAccountVO costAccountVO : costAccountVOList) {
                String longNumber = costAccountVO.getLongNumber();
                if (longNumber != null) {
                    costAccountVO.setLongNumber(longNumber
                            .replace("!", ".")
                            .replace("-", "."));
                }
//                计算费用归属可用余额

            }
            vo.setCostAccountVOList(costAccountVOList);
        }
        return vo;
    }

    @Override
    public List<CostAccountVO> unUseCostAccount(CostAccountVO vo) {
        String marketId = vo.getMarketId();
        if (Util.isEmpty(marketId)) {
            return null;
        }
        List<CostAccountVO> costAccountVOS = mapper.selectUnUseCostAccount(vo);
        if (costAccountVOS == null || costAccountVOS.size() == 0) {
            return null;
        }
        return costAccountVOS;
    }
}
