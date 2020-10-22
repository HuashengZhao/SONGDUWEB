package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBdPersonMapper;
import com.example.EAS.mapper.TOrgBaseunitMapper;
import com.example.EAS.model.TBdPerson;
import com.example.EAS.service.ITBdPersonService;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.Util;
import com.example.EAS.vo.PersonsVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
public class TBdPersonServiceImpl extends ServiceImpl<TBdPersonMapper, TBdPerson> implements ITBdPersonService {

    @Autowired
    private TBdPersonMapper personMapper;
    @Autowired
    private TOrgBaseunitMapper baseunitMapper;

    @Override
    public PageBean<PersonsVO> getPersons(PersonsVO vo) {
        if (Util.isEmpty(vo.getOrgId())) {
            return null;
        }
        List<String> counts=new ArrayList<>();
        counts.add(vo.getOrgId());
//      判断是否获取当前组织下所有组织对应的员工 ，以下为获取当前组织下的所有组织id
        if (Util.isNotEmpty(vo.getHasNext()) && vo.getHasNext() == 1) {
            String orgId = vo.getOrgId();
            List<String> orgIds = new ArrayList<>();
            orgIds.add(orgId);
            if (orgIds.size() > 0){
                counts = getChildren(orgIds, counts);
            }
    }
        vo.setOrgidList(counts);
        if (counts!=null && counts.size()>=1000){
            int size = counts.size();
        }
//      根据组织ids 获取对应的员工集合
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<PersonsVO> personsVOList = personMapper.selectDataByOrgIds(vo);
        if (personsVOList.size()>0){
                for(int i=0;i<personsVOList.size();i++){
                    PersonsVO personsVO = personsVOList.get(i);
                    if (Util.isNotEmpty(personsVO.getDeleteStatus())) {
                    if (personsVO.getDeleteStatus() == 1) {
                        personsVO.setStatus("普通");
                    } else if (personsVO.getDeleteStatus() == 2) {
                        personsVO.setStatus("禁用");
                    }
                }
                String unitId = personsVO.getUnitId();
                if (Util.isNotEmpty(unitId)){
                    if (!counts.contains(unitId)){
                        personsVOList.remove(personsVO);
                    }
                }
            }
        }
        PageBean<PersonsVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) personsVOList).getTotal());
        pageBean.setPageData(personsVOList);
        return pageBean;
    }

//    获取children
    public List<String> getChildren(List<String> list,List<String> counts) {//参数为数据库的（原数据，一级id）
        for (String orgId : list) {
            List<String> orgIds = baseunitMapper.selectNextIds(orgId);
            if (orgIds != null && orgIds.size() > 0) {
                counts.addAll(orgIds);
                getChildren(orgIds,counts);
            }
        }
        return counts;
    }
}
