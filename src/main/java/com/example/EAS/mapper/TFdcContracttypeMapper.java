package com.example.EAS.mapper;

import com.example.EAS.model.TFdcContracttype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.ContractTypeVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-08-29
 */
public interface TFdcContracttypeMapper extends BaseMapper<TFdcContracttype> {

    List<ContractTypeVO> selectData(ContractTypeVO vo);

    Integer selectNumberRecord();

    String selectOrgNumber(ContractTypeVO vo);
}
