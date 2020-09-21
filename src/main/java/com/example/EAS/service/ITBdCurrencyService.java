package com.example.EAS.service;

import com.example.EAS.model.TBdCurrency;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.CurrencyVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
public interface ITBdCurrencyService extends IService<TBdCurrency> {

    CurrencyVO getCurrency(CurrencyVO vo);
}
