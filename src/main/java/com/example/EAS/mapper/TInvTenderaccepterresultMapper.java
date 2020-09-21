package com.example.EAS.mapper;

import com.example.EAS.model.TInvTenderaccepterresult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.TenderAccepterVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-01
 */
public interface TInvTenderaccepterresultMapper extends BaseMapper<TInvTenderaccepterresult> {

    List<TenderAccepterVO> selectDatas(TenderAccepterVO vo);
}
