package com.example.EAS.service;

import com.example.EAS.model.TBcExpensetype;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.ExpenseTypeVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
public interface ITBcExpensetypeService extends IService<TBcExpensetype> {

    ExpenseTypeVO getExpenseType(ExpenseTypeVO vo);
}
