package com.example.EAS.mapper;

import com.example.EAS.model.TConChangeauditbill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.ChangeAuditVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-22
 */
public interface TConChangeauditbillMapper extends BaseMapper<TConChangeauditbill> {

    List<ChangeAuditVO> selectDatas(ChangeAuditVO vo);
}
