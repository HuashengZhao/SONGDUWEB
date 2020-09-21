package com.example.EAS.mapper;

import com.example.EAS.model.TFdcCurproject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.ProjectVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-08-30
 */
public interface TFdcCurprojectMapper extends BaseMapper<TFdcCurproject> {

    List<ProjectVO> selectData(ProjectVO vo);
}
