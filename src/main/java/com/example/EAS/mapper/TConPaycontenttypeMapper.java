package com.example.EAS.mapper;

import com.example.EAS.model.TConPaycontenttype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.PayContentTypeVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
public interface TConPaycontenttypeMapper extends BaseMapper<TConPaycontenttype> {

    List<PayContentTypeVO> selectDatas(PayContentTypeVO vo);
}
