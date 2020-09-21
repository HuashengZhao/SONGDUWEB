package com.example.EAS.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.model.TConContractwftype;
import com.example.EAS.vo.ContractProcessTypeVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface TConContractwftypeMapper extends BaseMapper<TConContractwftype> {


    List<ContractProcessTypeVO> selectDatas(ContractProcessTypeVO vo);
}
