package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TFdcCurprojectMapper;
import com.example.EAS.model.TFdcCurproject;
import com.example.EAS.service.ITFdcCurprojectService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-08-30
 */
@Service
public class TFdcCurprojectServiceImpl extends ServiceImpl<TFdcCurprojectMapper, TFdcCurproject> implements ITFdcCurprojectService {

    @Autowired
    private TFdcCurprojectMapper tFdcCurprojectMapper;
    @Override
    public ProjectVO getProjects(ProjectVO vo) {
        if (Util.isEmpty(vo.getOrgId())){
            return null;
        }
        ProjectVO projectVO = new ProjectVO();
        List<ProjectVO> projectVOS = tFdcCurprojectMapper.selectData(vo);
        if (projectVOS!=null&&projectVOS.size()>0){
            for (ProjectVO projectVO1 : projectVOS) {
                if (projectVO1.getLongNumber()!=null){
                    projectVO1.setLongNumber(projectVO1.getLongNumber()
                            .replace("-", ".")
                            .replace("!", "."));
                }
            }
            projectVO.setProjectVOList(projectVOS);
        }
        return projectVO;
    }
}
