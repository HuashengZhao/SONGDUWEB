package com.example.EAS.service;

import com.example.EAS.model.TConContractwftype;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.ContractProcessTypeVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface ITConContractwftypeService extends IService<TConContractwftype> {

    ContractProcessTypeVO getContractProcessType(ContractProcessTypeVO vo);
}
