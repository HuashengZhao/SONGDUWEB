package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TOrgBaseunitMapper;
import com.example.EAS.model.TOrgBaseunit;
import com.example.EAS.service.ITOrgBaseunitService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.OrgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<OrgVO> orgVOS = baseunitMapper.selectDatas(vo);
        if (orgVOS.size() > 0) {
            for (OrgVO orgVO : orgVOS) {
//                是否展示下级，前提是传入orgID; 注意成本中心不需要展示下級 直接返回
                if (Util.isNotEmpty(vo.getId())) {
                    getChildren(orgVOS);
                }
//                是否实体财务组织
                orgVO.setIsCompany(0);
                String id = orgVO.getId();
                if (Util.isNotEmpty(id)) {
                    Integer bizUnit = baseunitMapper.selectByOrgId(id);
                    if (bizUnit != null && bizUnit == 1) {
                        orgVO.setIsCompany(1);
                    }
                }
                orgVO.setIsCost(0);
                String cbId = orgVO.getCostCenterId();
                Integer pc = baseunitMapper.selectIsCost(cbId);
                if (pc != null && pc == 1) {
                    orgVO.setIsCost(1);
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
        return orgVO1;
    }

    @Override
    public List<OrgVO> getCostEntitys(OrgVO vo) {
        String id = vo.getId();
        List<String> counts = new ArrayList<>();
        if (Util.isNotEmpty(id)) {
            counts.add(id);
            getChildIds(id,counts);
        }
        List<OrgVO> orgVOS = baseunitMapper.selectCostEntities(counts);
        if (orgVOS!=null && orgVOS.size()>0){
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

    //           获取下级组织id集合counts
    public List<String> getChildIds(String orgId,List<String> counts) {//参数为数据库的（原数据，一级id）
            List<String> orgIds = baseunitMapper.selectNextIds(orgId);
            if (orgIds != null && orgIds.size() > 0) {
                counts.addAll(orgIds);
                if (orgIds!=null && orgIds.size()>0) {
                    for (String id : orgIds) {
                        getChildIds(id, counts);
                    }
                }
            }
        return counts;
    }
}
