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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
//                是否实体财务组织
                orgVO.setIsCompany(0);
                String id = orgVO.getId();
                if (Util.isNotEmpty(id)) {
                    Integer bizUnit = baseunitMapper.selectByOrgId(id);
                    if (bizUnit != null && bizUnit == 1) {
                        orgVO.setIsCompany(1);
                    }
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
        OrgVO orgVO = new OrgVO();
        List<OrgVO> cwsts = baseunitMapper.selectALLCWSTS();
        if (cwsts != null && cwsts.size() > 0) {
            Map<String, List<OrgVO>> map = Maps.newHashMap();
            Map<String, Integer> mapRe = Maps.newHashMap();
            for (OrgVO cwst : cwsts) {
                getParent(cwst, map, mapRe);
            }
//            取map信息
            List<OrgVO> orgVOS = getKeyAndValue(map);
            if (orgVOS != null && orgVOS.size() > 0) ;
            orgVO.setChildren(orgVOS);
        }
        long et = System.currentTimeMillis();
        System.out.println("获取预算公司、财务组织耗时：" + (et - st) + "ms");

//        List<Long> flevels= baseunitMapper.selectFlevels();
        return orgVO;
    }

    //    财务组织
    public void getParent(OrgVO orgVO, Map<String, List<OrgVO>> map, Map<String, Integer> mapRe) {
        String parentId = orgVO.getParentId();
        if (Util.isNotEmpty(parentId)) {
            TOrgBaseunit parentOrg = baseunitMapper.selectById(parentId);
            if (Util.isNotEmpty(parentOrg) && Util.isNotEmpty(parentOrg.getFpartfiid())) {
                String fpartfiid = parentOrg.getFpartfiid();
                if (Util.isNotEmpty(fpartfiid)) { //判断是否财务组织
                    List<OrgVO> orgVOS = map.get(parentId);
                    Integer integer = mapRe.get(parentId);
                    if (Util.isEmpty(integer)) {
                        if (orgVOS != null && orgVOS.size() > 0) {
                            orgVOS.add(orgVO);
                            map.put(parentId, orgVOS);
                        } else {
                            List<OrgVO> orgVOList = new ArrayList<>();
                            orgVOList.add(orgVO);
                            map.put(parentId, orgVOList);
                        }
                    }
                    mapRe.put(parentId, 1);
                    OrgVO org = baseunitMapper.selectDataById(parentId);
                    if (Util.isNotEmpty(org)) {
                        List<OrgVO> orgVOS1 = map.get(parentId);
                        org.setChildren(orgVOS1);
                        getParent(org, map, mapRe);
                    }
                }
            }
        }
    }

    //获取key和value
    public List<OrgVO> getKeyAndValue(Map<String, List<OrgVO>> map) {
        List<OrgVO> orgVOS = new ArrayList<>();
        Iterator<Entry<String, List<OrgVO>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().getKey();
            List<OrgVO> value = iterator.next().getValue();
            OrgVO orgVO = baseunitMapper.selectDataById(key);
            orgVO.setChildren(value);
            orgVOS.add(orgVO);
        }
        return orgVOS;
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

    //    获取下级是财务组织的children
    public List<OrgVO> getFinalChildren(List<OrgVO> list) {//参数为数据库的（原数据，一级id）
        for (OrgVO orgVO : list) {
            List<OrgVO> orgVOS = baseunitMapper.selectNextFinalOrgs(orgVO);
            if (orgVOS != null && orgVOS.size() > 0) {
                orgVO.setChildren(orgVOS);
                getChildren(orgVOS);
            }
        }
        return list;
    }
}
