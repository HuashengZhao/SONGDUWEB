package com.example.EAS.mapper;

import com.example.EAS.model.TConChangeauditbill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.CalculationInfoVO;
import com.example.EAS.vo.ChangeAuditContentVO;
import com.example.EAS.vo.ChangeAuditVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-22
 */
@Mapper
public interface TConChangeauditbillMapper extends BaseMapper<TConChangeauditbill> {

    List<ChangeAuditVO> selectDatas(ChangeAuditVO vo);

    ChangeAuditVO selectDataById(String id);

    List<ChangeAuditContentVO> selectChangeContents(String id);

    List<CalculationInfoVO> selectCalcuInfos(String id);
}
