package com.example.EAS.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.model.TConContractwithouttext;
import com.example.EAS.vo.NoTextContractVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-28
 */
public interface TConContractwithouttextMapper extends BaseMapper<TConContractwithouttext> {


    List<NoTextContractVO> selectDatas(NoTextContractVO vo);

    NoTextContractVO selectDataByID(String id);

    Integer selectNewNum();

    void updateNumRecord(int value);
}
