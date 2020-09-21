package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBdCurrencyMapper;
import com.example.EAS.model.TBdCurrency;
import com.example.EAS.service.ITBdCurrencyService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.CurrencyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
@Service
public class TBdCurrencyServiceImpl extends ServiceImpl<TBdCurrencyMapper, TBdCurrency> implements ITBdCurrencyService {
    @Autowired
    private TBdCurrencyMapper mapper;

    @Override
    public CurrencyVO getCurrency(CurrencyVO vo) {
        CurrencyVO currencyVO = new CurrencyVO();
        List<CurrencyVO> currencyVOList=mapper.selectDatas(vo);
        if (Util.isNotEmpty(currencyVOList)){
            currencyVO.setCurrencyVOS(currencyVOList);
        }
        return currencyVO;
    }
}
