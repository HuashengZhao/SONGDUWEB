package com.example.EAS.mapper;

import com.example.EAS.model.TFdcContractsource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.MadeWayVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-01
 */
public interface TFdcContractsourceMapper extends BaseMapper<TFdcContractsource> {

    List<MadeWayVO> selectDatas(MadeWayVO vo);

}
