package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBcExpensetypeMapper;
import com.example.EAS.model.TBcExpensetype;
import com.example.EAS.service.ITBcExpensetypeService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.ExpenseTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
@Service
public class TBcExpensetypeServiceImpl extends ServiceImpl<TBcExpensetypeMapper, TBcExpensetype> implements ITBcExpensetypeService {

    @Autowired
    private TBcExpensetypeMapper mapper;

    @Override
    public ExpenseTypeVO getExpenseType(ExpenseTypeVO vo) {
        ExpenseTypeVO expenseTypeVO = new ExpenseTypeVO();
        List<ExpenseTypeVO> vos = mapper.selectDatas(vo);
        if (Util.isNotEmpty(vos)){
            expenseTypeVO.setExpenseTypeVOList(vos);
        }
        return expenseTypeVO;
    }
}
