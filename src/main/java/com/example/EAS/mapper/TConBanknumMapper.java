package com.example.EAS.mapper;

import com.example.EAS.model.TConBanknum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.BankNumberVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface TConBanknumMapper extends BaseMapper<TConBanknum> {

    List<BankNumberVO> selectDatas(BankNumberVO vo);
}
