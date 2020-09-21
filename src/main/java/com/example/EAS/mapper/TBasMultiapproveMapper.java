package com.example.EAS.mapper;

import com.example.EAS.model.TBasMultiapprove;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.MultiApproveVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
public interface TBasMultiapproveMapper extends BaseMapper<TBasMultiapprove> {

    List<MultiApproveVO> selectDatas(MultiApproveVO vo);
}
