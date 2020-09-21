package com.example.EAS.service;

import com.example.EAS.model.TConBanknum;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.BankNumberVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface ITConBanknumService extends IService<TConBanknum> {

    PageBean<BankNumberVO> getBankNum(BankNumberVO vo);

}
