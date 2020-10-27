package com.example.EAS.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.model.TOrgBaseunit;
import com.example.EAS.vo.OrgVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface ITOrgBaseunitService extends IService<TOrgBaseunit> {

    OrgVO getData(OrgVO vo);

    List<OrgVO> getCostEntitys(OrgVO vo);

    List<OrgVO> getEntityFinalOrg(OrgVO vo);
}
