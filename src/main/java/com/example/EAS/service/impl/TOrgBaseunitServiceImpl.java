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
        OrgVO orgVO1 = new OrgVO();
        List<OrgVO> orgVOS = new ArrayList<>();
        long st = System.currentTimeMillis();
        String id = vo.getId();
        if (Util.isNotEmpty(id)) {
            OrgVO orgVO = baseunitMapper.selectDatas(vo);
            if (Util.isNotEmpty(orgVO)) {
                orgVOS.add(orgVO);
            }
        } else {
            List<OrgVO> vos = baseunitMapper.selectALLLeafOrgs(vo);
            Map<String, OrgVO> map = Maps.newHashMap();
            if (vos != null && vos.size() > 0) {
                for (OrgVO orgVO : vos) {
                    if (Util.isNotEmpty(orgVO)) {
                        getOrgs(map, orgVO);
                    }
                }
            }
            OrgVO total = map.get("topOrgVO");
            orgVOS.add(total);
        }
        orgVO1.setOrgVOList(orgVOS);
        long et = System.currentTimeMillis();
        System.out.println("组织查询耗时：" + (et - st) + "ms");
        return orgVO1;
    }

    @Override
    public List<OrgVO> getCostEntitys(OrgVO vo) {
        List<OrgVO> orgVOS = new ArrayList<>();
        String id = vo.getId();
        if (Util.isNotEmpty(id)) {
            List<String> counts = new ArrayList<>();
            OrgVO orgVO1 = baseunitMapper.selectCostById(id);
            if (Util.isNotEmpty(orgVO1)) {
                orgVO1.setDisabled(true);
                getChildIds(id, counts);
                List<OrgVO> vos = baseunitMapper.selectCostEntities(counts);
                if (vos != null && vos.size() > 0) {
                    for (OrgVO orgVO : vos) {
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
                    orgVO1.setChildren(vos);
                    orgVOS.add(orgVO1);
                }
            }
        } else {
            List<OrgVO> orgs = baseunitMapper.selectALLCostEntities(vo);
            Map<String, OrgVO> map = Maps.newHashMap();
            if (orgs != null && orgs.size() > 0) {
                for (OrgVO org : orgs) {
                    if (Util.isNotEmpty(org)) {
                        org.setDisabled(false);
                        getCostParents(map, org);
                    }
                }
            }
            OrgVO total = map.get("topOrgVO");
            orgVOS.add(total);
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
        if (Util.isNotEmpty(vo.getId())) {
            OrgVO orgvo = baseunitMapper.selectDataById(vo.getId());
            if (Util.isNotEmpty(orgvo)) {
                vos.add(orgvo);
            }
        } else {
            List<OrgVO> orgVOS = baseunitMapper.selectALLCWSTS(vo);
            Map<String, OrgVO> map = Maps.newHashMap();
            if (orgVOS != null && orgVOS.size() > 0) {
                for (OrgVO orgVO1 : orgVOS) {
                    if (Util.isNotEmpty(orgVO1)) {
                        orgVO1.setDisabled(false);
                        getParents(map, orgVO1);
                    }
                }
            }
            OrgVO total = map.get("topOrgVO");
            vos.add(total);
        }
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
                if (Util.isNotEmpty(orgVO1)) {
                    List<OrgVO> os = new ArrayList<>();
                    os.add(orgVO);
                    orgVO1.setChildren(os);
                    if (Util.isEmpty(orgVO1.getParentId())) {
                        map.put("topOrgVO", orgVO1);
                        map.put(parentId, orgVO1);
                    } else {
                        map.put(parentId, orgVO1);
                    }
                    Integer isCompany = orgVO1.getIsCompany();
                    if (Util.isEmpty(isCompany) || isCompany == 0) {
                        orgVO1.setDisabled(true);
                    } else if (isCompany == 1) {
                        orgVO1.setDisabled(false);
                    }
                    getParents(map, orgVO1);
                }
            } else {
                orgVO1.getChildren().add(orgVO);
                Integer isCompany = orgVO1.getIsCompany();
                if (Util.isEmpty(isCompany) || isCompany == 0) {
                    orgVO1.setDisabled(true);
                } else if (isCompany == 1) {
                    orgVO1.setDisabled(false);
                }
            }
        }
    }

    /**
     * 实体成本中心获取上级 设置children
     *
     * @param
     * @return
     */
    public void getCostParents(Map<String, OrgVO> map, OrgVO orgVO) {
        String parentId = orgVO.getParentId();
        if (Util.isNotEmpty(parentId)) {
            OrgVO orgVO1 = map.get(parentId);
            if (Util.isEmpty(orgVO1)) {
                orgVO1 = baseunitMapper.selectCostById(parentId);
                if (Util.isNotEmpty(orgVO1)) {
                    List<OrgVO> os = new ArrayList<>();
                    os.add(orgVO);
                    orgVO1.setChildren(os);
                    if (Util.isEmpty(orgVO1.getParentId())) {
                        map.put("topOrgVO", orgVO1);
                        map.put(parentId, orgVO1);
                    } else {
                        map.put(parentId, orgVO1);
                    }
                    orgVO1.setDisabled(true);
                    getCostParents(map, orgVO1);
                }
            } else {
                orgVO1.getChildren().add(orgVO);
                orgVO1.setDisabled(true);
            }
        }
    }

    /**
     * 获取组织children
     *
     * @param map
     * @param orgVO
     */
    public void getOrgs(Map<String, OrgVO> map, OrgVO orgVO) {
        String parentId = orgVO.getParentId();
        if (Util.isNotEmpty(parentId)) {
            OrgVO orgVO1 = map.get(parentId);
            if (Util.isEmpty(orgVO1)) {
                orgVO1 = baseunitMapper.selectByOId(parentId);
                if (Util.isNotEmpty(orgVO1)) {
                    List<OrgVO> os = new ArrayList<>();
                    os.add(orgVO);
                    orgVO1.setChildren(os);
                    if (Util.isEmpty(orgVO1.getParentId())) {
                        map.put("topOrgVO", orgVO1);
                        map.put(parentId, orgVO1);
                    } else {
                        map.put(parentId, orgVO1);
                    }
                    getOrgs(map, orgVO1);
                }
            } else {
                orgVO1.getChildren().add(orgVO);
            }
        }
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


}
