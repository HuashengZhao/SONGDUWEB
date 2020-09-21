package com.example.EAS.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.model.TFdcContracttype;
import com.example.EAS.vo.ContractTypeVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-08-29
 */

public interface ITFdcContracttypeService extends IService<TFdcContracttype> {

    ContractTypeVO getContractType(ContractTypeVO vo);

    String getNewContractNumber(ContractTypeVO vo);

}
