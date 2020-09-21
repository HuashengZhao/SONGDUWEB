package com.example.EAS.service;

import com.example.EAS.model.TFdcCurproject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.ProjectVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-08-30
 */
public interface ITFdcCurprojectService extends IService<TFdcCurproject> {

    ProjectVO getProjects(ProjectVO vo);
}
