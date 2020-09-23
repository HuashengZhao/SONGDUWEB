package com.example.EAS.mapper;

import com.example.EAS.model.TConContractchangesettlebill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.ChangeSettleVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-22
 */
public interface TConContractchangesettlebillMapper extends BaseMapper<TConContractchangesettlebill> {

    List<ChangeSettleVO> selectDatas(ChangeSettleVO vo);
}
