package com.example.EAS.mapper;

import com.example.EAS.model.TFdcLanddeveloper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.PartAVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-01
 */
public interface TFdcLanddeveloperMapper extends BaseMapper<TFdcLanddeveloper> {

    List<PartAVO> selectDatas(PartAVO vo);


}
