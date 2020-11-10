package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TConMarketprojectMapper;
import com.example.EAS.mapper.TConMarketprojectcostentryMapper;
import com.example.EAS.mapper.TFdcCostaccountMapper;
import com.example.EAS.model.TConMarketproject;
import com.example.EAS.model.TConMarketprojectcostentry;
import com.example.EAS.model.TFdcCostaccount;
import com.example.EAS.service.ITFdcCostaccountService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.CostAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private TConMarketprojectMapper marketProjectMapper;

    @Autowired
    private TConMarketprojectcostentryMapper tConMarketprojectcostentryMapper;

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
                BigDecimal mkAmount = BigDecimal.ZERO;//        立项金额
                BigDecimal negAmount = BigDecimal.ZERO;//        负数金额
                BigDecimal usedAmount = BigDecimal.ZERO;//已关联改科目的无文本所用金额
                BigDecimal balance = BigDecimal.ZERO;  //余额
                mkAmount = costAccountVO.getMpAmount();
                usedAmount = mapper.selectUsedNTAmount(costAccountVO.getId());
                if (Util.isEmpty(usedAmount)){
                    usedAmount = BigDecimal.ZERO;
                }
                List<String> mpIds = marketProjectMapper.selectList(new QueryWrapper<TConMarketproject>()
                        .eq("FMPID", vo.getMarketId())
                        .eq("FISSUB", 1)
                        .lambda())
                        .stream().map(TConMarketproject::getFid)
                        .collect(Collectors.toList());
                if (mpIds != null && mpIds.size() > 0) {
                    List<Double> collect = tConMarketprojectcostentryMapper.selectList(new QueryWrapper<TConMarketprojectcostentry>()
                            .lambda()
                            .in(TConMarketprojectcostentry::getFheadid, mpIds))
                            .stream()
                            .map(TConMarketprojectcostentry::getFamount)
                            .collect(Collectors.toList());
                    if (collect != null && collect.size() > 0) {
                        for (Double aDouble : collect) {
                            negAmount = negAmount.add(new BigDecimal(aDouble));
                        }
                    }
                }
                balance =mkAmount.add(negAmount).subtract(usedAmount);
                if (balance.compareTo(BigDecimal.ZERO)!=1){
                    costAccountVOList.remove(costAccountVO);
                }
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
