package com.example.EAS.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TConSupplierapplyMapper;
import com.example.EAS.mapper.TOrgBaseunitMapper;
import com.example.EAS.model.TOrgBaseunit;
import com.example.EAS.service.ITOrgBaseunitService;
import com.example.EAS.util.ServiceException;
import com.example.EAS.util.Util;
import com.example.EAS.vo.OrgVO;
import com.example.EAS.vo.PersonIdentityVO;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
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
@Slf4j
@Service
public class TOrgBaseunitServiceImpl extends ServiceImpl<TOrgBaseunitMapper, TOrgBaseunit> implements ITOrgBaseunitService {

    @Autowired
    private TConSupplierapplyMapper mapper;
    @Autowired
    private TOrgBaseunitMapper baseunitMapper;
    org.apache.axis.client.Service service = new org.apache.axis.client.Service();

    public Call getCall(String type, String operationName) {

        String url = null;
        if (type.contains("EAS")) {
            url = mapper.selectEASURL();
        } else if (type.contains("personPost")) {
            url=mapper.selectPersonPost();
        } else {
            url = mapper.selectOAURL();
        }
        Call call = null;
        try {
            call = (Call) service.createCall();
            call.setOperationName(operationName);
            call.setTargetEndpointAddress(new java.net.URL(url));
            call.addParameter("arg0", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            call.setTimeout(Integer.valueOf(1000*600000*60));
            call.setUseSOAPAction(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return call;
    }

    @Override
    public OrgVO getData(OrgVO vo) {
        long st = System.currentTimeMillis();
        OrgVO orgVO1 = new OrgVO();
        List<OrgVO> orgVOS = new ArrayList<>();
        if (Util.isNotEmpty(vo.getId())) {
            orgVOS = baseunitMapper.selectDatas(vo);
            if (orgVOS != null && orgVOS.size() > 0) {
                orgVOS = getChildren(orgVOS);
            }
        } else {
            orgVOS = baseunitMapper.selectALLLeafOrgs(vo);
            Map<String, OrgVO> map = Maps.newHashMap();
            if (orgVOS != null && orgVOS.size() > 0) {
                for (OrgVO orgVO : orgVOS) {
                    if (Util.isNotEmpty(orgVO)) {
                        getUpOrgs(map, orgVO);
                    }
                }
            }
            OrgVO total = map.get("topOrgVO");
            orgVOS.clear();
            orgVOS.add(total);
        }
        orgVO1.setOrgVOList(orgVOS);
        long et = System.currentTimeMillis();
        log.info("组织查询耗时：" + (et - st) + "ms");
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
        log.info("获取预算公司、财务组织耗时：" + (et - st) + "ms");
        return vo;
    }

    /**
     * 财务组织获取上级 设置children
     */
    public void getUpOrgs(Map<String, OrgVO> map, OrgVO orgVO) {
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
                    getUpOrgs(map, orgVO1);
                }
            } else {
                orgVO1.getChildren().add(orgVO);
            }
        }
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


    @Override
    public List<PersonIdentityVO> getPersonIdentities(PersonIdentityVO vo) {
        String personNum = vo.getPerson();
        if (Util.isEmpty(personNum)) {
            return null;
        }
        JSONObject obj = new JSONObject();
        List<PersonIdentityVO> returnList = new ArrayList<>();
        String result = null;
        JSONObject str = null;
        Call call = getCall("personPost", "outPersonPost");
        try {
//            obj.put("fdLoginName", personNum);
            result = (String) call.invoke(new Object[]{personNum});
            if (Util.isNotEmpty(result)) {
                str = JSONObject.parseObject(result);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (Util.isNotEmpty(str)) {
            String status = str.getString("status");
            String message = str.getString("message");
            JSONArray returnMessage = str.getJSONArray("ReturnMessage");
            if (Util.isNotEmpty(status) && status.equals("0")) {
                if (Util.isNotEmpty(returnMessage)) {
                    for (int i = 0; i < returnMessage.size(); i++) {
                        PersonIdentityVO personIdentityVO = new PersonIdentityVO();
                        JSONObject json = returnMessage.getJSONObject(i);
                        String fdId = json.getString("fdId");
                        String fdName = json.getString("fdName");
                        if (Util.isNotEmpty(fdId)) {
                            personIdentityVO.setId(fdId);
                        }
                        if (Util.isNotEmpty(fdName)) {
                            personIdentityVO.setTitle(fdName);
                        }
                        returnList.add(personIdentityVO);
                    }
                }
            } else {
                String errorMsg = "获取oa岗位请求失败！";
                if (Util.isNotEmpty(message)) {
                    errorMsg = message;
                }
                throw new ServiceException(errorMsg);
            }
        }
        return returnList;
    }
}
