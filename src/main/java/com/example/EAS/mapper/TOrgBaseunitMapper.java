package com.example.EAS.mapper;

import com.example.EAS.model.TOrgBaseunit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.OrgVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface TOrgBaseunitMapper extends BaseMapper<TOrgBaseunit> {

    List<OrgVO> selectDatas(OrgVO vo);

    Integer selectByOrgId(String id);

    Integer selectIsCost(String cbId);

    List<OrgVO> selectNexts(String id);

    List<String> selectNextIds(String orgId);


    List<OrgVO> selectCostEntities(List<String> list);

    List<String> selectNextCostIds(String orgId);

    List<OrgVO> selectEntitiesFinalOrgs(OrgVO vo);

    List<OrgVO> selectNextFinalOrgs(OrgVO orgVO);

    List<OrgVO> selectALLCWSTS(OrgVO vo);

    OrgVO selectDataById(String parentId);

    List<Long> selectFlevels();

    OrgVO selectFirstLevel();

    List<OrgVO> selectDatasByParentID(String id);
}
