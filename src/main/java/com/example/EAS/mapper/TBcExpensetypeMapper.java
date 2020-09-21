package com.example.EAS.mapper;

import com.example.EAS.model.TBcExpensetype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.ExpenseTypeVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
public interface TBcExpensetypeMapper extends BaseMapper<TBcExpensetype> {

    List<ExpenseTypeVO> selectDatas(ExpenseTypeVO vo);
}
