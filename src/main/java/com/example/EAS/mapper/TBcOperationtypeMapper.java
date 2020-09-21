package com.example.EAS.mapper;

import com.example.EAS.model.TBcOperationtype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.OperationTypeVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
public interface TBcOperationtypeMapper extends BaseMapper<TBcOperationtype> {

    List<OperationTypeVO> selectDatas(OperationTypeVO vo);
}
