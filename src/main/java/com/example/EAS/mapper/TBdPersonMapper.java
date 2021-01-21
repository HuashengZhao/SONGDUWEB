package com.example.EAS.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.model.TBdPerson;
import com.example.EAS.vo.PersonsVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface TBdPersonMapper extends BaseMapper<TBdPerson> {

    List<PersonsVO> selectDatas(PersonsVO vo);

    List<PersonsVO> selectDataByOrgIds(PersonsVO vo);

    String selectNameByNum(String person);

    List<String> selectITPersonNumbers();

    List<String> selectTestUserName();


}
