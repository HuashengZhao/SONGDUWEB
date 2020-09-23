package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TConChangeauditbillMapper;
import com.example.EAS.model.TConChangeauditbill;
import com.example.EAS.service.ITConChangeauditbillService;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.Util;
import com.example.EAS.vo.CalculationInfoVO;
import com.example.EAS.vo.ChangeAuditContentVO;
import com.example.EAS.vo.ChangeAuditVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-22
 */
@Service
public class TConChangeauditbillServiceImpl extends ServiceImpl<TConChangeauditbillMapper, TConChangeauditbill> implements ITConChangeauditbillService {

    @Autowired
    private TConChangeauditbillMapper mapper;

    @Override
    public PageBean<ChangeAuditVO> getChangeAuditList(ChangeAuditVO vo) {
        String orgId = vo.getOrgId();
        if (Util.isEmpty(orgId)) {
            return null;
        }
//        单据状态转换
        String webState = vo.getWebState();
        if (Util.isNotEmpty(webState)) {
            if (webState.contains("保存")) {
                vo.setChangeState("1Saved");
                vo.setAuditState("1SAVED");
            } else if (webState.contains("已提交")) {
                vo.setChangeState("3Submit");
                vo.setAuditState("2SUBMITTED");
            } else if (webState.contains("审批中")) {
                vo.setChangeState("4Auditting");
                vo.setAuditState("3AUDITTING");
            } else if (webState.contains("已审批")) {
                vo.setChangeState("5Audit");
                vo.setAuditState("4AUDITTED");
            } else if (webState.contains("已下发")) {
                vo.setChangeState("6Announce");
                vo.setAuditState("4AUDITTED");
            }
        }
        String projectId = vo.getProjectId();
//        如果是项目 获取项目下的分期id集合
        if (Util.isNotEmpty(projectId)) {
            List<String> projectIds = mapper.selectProjectInfo(projectId);
            if (projectIds != null && projectIds.size() > 0) {
                vo.setProjectIds(projectIds);
            }
        }
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<ChangeAuditVO> auditVOS = mapper.selectDatas(vo);
        if (!Util.isEmptyList(auditVOS)) {
            for (ChangeAuditVO auditVO : auditVOS) {
//                单据状态转换
                String changeState = auditVO.getChangeState();
                String auditState = auditVO.getAuditState();
                if (changeState.contains("1Saved") && auditState.contains("1SAVED")) {
                    auditVO.setWebState("保存");
                } else if (changeState.contains("3Submit") && auditState.contains("2SUBMITTED")) {
                    auditVO.setWebState("已提交");
                } else if (changeState.contains("4Auditting") && auditState.contains("3AUDITTING")) {
                    auditVO.setWebState("审批中");
                } else if (changeState.contains("5Audit") && auditState.contains("4AUDITTED")) {
                    auditVO.setWebState("已审批");
                } else if (changeState.contains("6Announce") && auditState.contains("4AUDITTED")) {
                    auditVO.setWebState("已下发");
                }
            }
        }
        PageBean<ChangeAuditVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) auditVOS).getTotal());
        pageBean.setPageData(auditVOS);
        return pageBean;
    }

    @Override
    public ChangeAuditVO viewChangeAudit(ChangeAuditVO vo) {
        ChangeAuditVO auditVO = new ChangeAuditVO();
        String id = vo.getId();
        if (Util.isNotEmpty(id)) {
            auditVO = mapper.selectDataById(id);
            if (Util.isNotEmpty(auditVO)) {
//                变更期间
                String s = auditVO.getBizDate().toString();
                if (Util.isNotEmpty(s)) {
                    String year = s.split("T")[0].substring(0, 7).replace("-0", "年");
                    StringBuffer sb = new StringBuffer();
                    String value = sb.append(year).append("期").toString();
                    auditVO.setChangeDates(value);
                }
//                  提出方
                String offer = auditVO.getOffer();
                if (Util.isNotEmpty(offer)) {
                    if (offer.contains("SELFCOM")) {
                        auditVO.setOffer("我方部门");
                    } else if (offer.contains("DESIGNCOM")) {
                        auditVO.setOffer("合作单位");
                    }
                }
//                单据状态转换
                String changeState = auditVO.getChangeState();
                String auditState = auditVO.getAuditState();
                if (changeState.contains("1Saved") && auditState.contains("1SAVED")) {
                    auditVO.setWebState("保存");
                } else if (changeState.contains("3Submit") && auditState.contains("2SUBMITTED")) {
                    auditVO.setWebState("已提交");
                } else if (changeState.contains("4Auditting") && auditState.contains("3AUDITTING")) {
                    auditVO.setWebState("审批中");
                } else if (changeState.contains("5Audit") && auditState.contains("4AUDITTED")) {
                    auditVO.setWebState("已审批");
                } else if (changeState.contains("6Announce") && auditState.contains("4AUDITTED")) {
                    auditVO.setWebState("已下发");
                }
                StringBuffer stringBuffer = new StringBuffer();
//                变更内容分录
                List<ChangeAuditContentVO> contentVOS = mapper.selectChangeContents(id);
                if (contentVOS != null && contentVOS.size() > 0) {
                    auditVO.setContentVOList(contentVOS);
                    for (ChangeAuditContentVO contentVO : contentVOS) {
                        String changeContent = contentVO.getChangeContent();
                        stringBuffer.append(changeContent + ";");
                    }
                }
                String content = stringBuffer.toString();
//                测算信息
                List<CalculationInfoVO> calculationInfoVOS = mapper.selectCalcuInfos(id);
                if (calculationInfoVOS != null && calculationInfoVOS.size() > 0) {
                    for (CalculationInfoVO calculationInfoVO : calculationInfoVOS) {
                        if (content != null && content != "") {
                            calculationInfoVO.setContent(content);
                        }
                    }
                    auditVO.setCalculationInfoVOS(calculationInfoVOS);
                }
            }
        }
        return auditVO;
    }
}
