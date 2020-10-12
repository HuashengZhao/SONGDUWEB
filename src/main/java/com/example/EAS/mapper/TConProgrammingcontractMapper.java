package com.example.EAS.mapper;

import com.example.EAS.model.TConProgrammingcontract;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.ProgramConVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
public interface TConProgrammingcontractMapper extends BaseMapper<TConProgrammingcontract> {

    List<ProgramConVO> selectDatas(ProgramConVO vo);

    List<ProgramConVO> selectDataCanBeLinked(ProgramConVO vo);

    List<String> selectIdsFromContract();


}
