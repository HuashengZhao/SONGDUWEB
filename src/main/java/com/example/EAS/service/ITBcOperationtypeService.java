package com.example.EAS.service;

import com.example.EAS.model.TBcOperationtype;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.OperationTypeVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
public interface ITBcOperationtypeService extends IService<TBcOperationtype> {

    OperationTypeVO getOperationType(OperationTypeVO vo);
}
