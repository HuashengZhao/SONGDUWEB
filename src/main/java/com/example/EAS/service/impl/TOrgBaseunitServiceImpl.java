package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TOrgBaseunitMapper;
import com.example.EAS.model.TOrgBaseunit;
import com.example.EAS.service.ITOrgBaseunitService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.OrgVO;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
@Service
public class TOrgBaseunitServiceImpl extends ServiceImpl<TOrgBaseunitMapper, TOrgBaseunit> implements ITOrgBaseunitService {

    @Autowired
    private TOrgBaseunitMapper baseunitMapper;

    @Override
    public OrgVO getData(OrgVO vo) {
        long st = System.currentTimeMillis();
        OrgVO orgVO1 = new OrgVO();
        List<OrgVO> orgVOS = baseunitMapper.selectDatas(vo);
        if (orgVOS.size() > 0) {
            orgVOS = getChildren(orgVOS);
            for (OrgVO orgVO : orgVOS) {
//                是否成本实体中心
                if (Util.isNotEmpty(vo.getIsSTCost())&&vo.getIsSTCost()==1) {
                    Integer isSTCost = orgVO.getIsSTCost();
                    if (Util.isEmpty(isSTCost) || isSTCost == 0) {
                        orgVO.setIsSTCost(0);
                        orgVO.setDisabled(false);
                    } else if (isSTCost == 1) {
                        orgVO.setIsSTCost(1);
                        orgVO.setDisabled(true);
                    }
                }
//                是否实体财务组织
                Integer isCompany = orgVO.getIsCompany();
                if (Util.isEmpty(isCompany)) {
                    orgVO.setIsCompany(0);
                }
                String longNumber = orgVO.getLongNumber();
                if (Util.isNotEmpty(longNumber)) {
                    orgVO.setLongNumber(longNumber
                            .replace("-", ".")
                            .replace("!", "."));
                }
            }
        }
        orgVO1.setOrgVOList(orgVOS);
        long et = System.currentTimeMillis();
        System.out.println("组织查询耗时：" + (et - st) + "ms");
        return orgVO1;
    }

    @Override
    public List<OrgVO> getCostEntitys(OrgVO vo) {
        String id = vo.getId();
        List<String> counts = new ArrayList<>();
        if (Util.isNotEmpty(id)) {
            counts.add(id);
            getChildIds(id, counts);
        }
        List<OrgVO> orgVOS = baseunitMapper.selectCostEntities(counts);
        if (orgVOS != null && orgVOS.size() > 0) {
            for (OrgVO orgVO : orgVOS) {
                //                成本中心
                String costCenterType = orgVO.getCostCenterType();
                if (Util.isNotEmpty(costCenterType)) {
                    if (costCenterType.contains("0")) {
                        orgVO.setCostCenterType("直接生产部门");
                    } else if (costCenterType.contains("1")) {
                        orgVO.setCostCenterType("辅助生产部门");
                    } else if (costCenterType.contains("2")) {
                        orgVO.setCostCenterType("管理部门");
                    } else if (costCenterType.contains("3")) {
                        orgVO.setCostCenterType("销售部门");
                    }
                }
            }
        }
        return orgVOS;
    }

    /**
     * 获取财务组织 标记出财务实体组织isCompany
     *
     * @param vo
     * @return
     */
    @Override
    public OrgVO getEntityFinalOrg(OrgVO vo) {
        long st = System.currentTimeMillis();
        List<OrgVO> vos = new ArrayList<>();
        List<OrgVO> orgVOS = baseunitMapper.selectALLCWSTS(vo);
        Map<String, OrgVO> map = Maps.newHashMap();
        if (orgVOS != null && orgVOS.size() > 0) {
            for (OrgVO orgVO1 : orgVOS) {
                if (Util.isNotEmpty(orgVO1)) {
                    getParents(map, orgVO1);
                }
            }
        }
        OrgVO total = map.get("topOrgVO");
        vos.add(total);
        vo.setOrgVOList(vos);
        long et = System.currentTimeMillis();
        System.out.println("获取预算公司、财务组织耗时：" + (et - st) + "ms");
        return vo;
    }

    /**
     * 财务实体组织获取上级 设置children
     *
     * @param
     * @return
     */
    public void getParents(Map<String, OrgVO> map, OrgVO orgVO) {
        String parentId = orgVO.getParentId();
        if (Util.isNotEmpty(parentId)) {
            OrgVO orgVO1 = map.get(parentId);
            if (Util.isEmpty(orgVO1)) {
                orgVO1 = baseunitMapper.selectDataById(parentId);
                List<OrgVO> os = new ArrayList<>();
                os.add(orgVO);
                orgVO1.setChildren(os);
                if (Util.isEmpty(orgVO1.getParentId())) {
                    map.put("topOrgVO", orgVO1);
                    map.put(parentId, orgVO1);
                } else {
                    map.put(parentId, orgVO1);
                }
                getParents(map, orgVO1);
            } else {
                orgVO1.getChildren().add(orgVO);
            }
        }
    }


    //    获取children
    public List<OrgVO> getChildren(List<OrgVO> list) {//参数为数据库的（原数据，一级id）
        for (OrgVO orgVO : list) {
            String id = orgVO.getId();
            List<OrgVO> orgVOS = baseunitMapper.selectNexts(id);
            if (orgVOS != null && orgVOS.size() > 0) {
                orgVO.setChildren(orgVOS);
                getChildren(orgVOS);
            }
        }
        return list;
    }

    //           获取下级组织是成本中心的id集合counts
    public List<String> getChildIds(String orgId, List<String> counts) {//参数为数据库的（原数据，一级id）
        List<String> orgIds = baseunitMapper.selectNextCostIds(orgId);
        if (orgIds != null && orgIds.size() > 0) {
            counts.addAll(orgIds);
            if (orgIds != null && orgIds.size() > 0) {
                for (String id : orgIds) {
                    getChildIds(id, counts);
                }
            }
        }
        return counts;
    }

    //    财务实体组织children
    public OrgVO getEFChildren(OrgVO orgVO, String id) {
        List<OrgVO> orgVOS = baseunitMapper.selectDatasByParentID(id);
        if (orgVOS != null && orgVOS.size() > 0) {
            orgVO.setChildren(orgVOS);
            for (OrgVO vo : orgVOS) {
                String id1 = vo.getId();
                if (Util.isNotEmpty(id1)) {
                    getEFChildren(vo, id1);
                }
            }
        }
        return orgVO;
    }
}
