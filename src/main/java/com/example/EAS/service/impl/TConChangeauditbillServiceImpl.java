package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.mapper.TConChangeauditbillMapper;
import com.example.EAS.model.TConChangeauditbill;
import com.example.EAS.service.ITConChangeauditbillService;
import com.example.EAS.util.*;
import com.example.EAS.vo.AttachmentsVO;
import com.example.EAS.vo.ChangeAuditContentVO;
import com.example.EAS.vo.ChangeAuditVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
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
    @Autowired
    private TBasAttachmentMapper attachmentMapper;


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
    public ChangeAuditVO viewChangeAudit(ChangeAuditVO vo) throws Exception {
        ChangeAuditVO auditVO = new ChangeAuditVO();
        String id = vo.getId();
        if (Util.isNotEmpty(id)) {
            auditVO = mapper.selectDataById(id);
            if (Util.isNotEmpty(auditVO)) {
                //                查看oa流程link
                String oaId = auditVO.getSourceFunction();
                if (Util.isNotEmpty(oaId)){
//                                获取当前登录信息 取用户账号用作oa流程查看登录
                    String token = RequestHolder.getCurrentUser().getToken();
                    String dencrypt = RSAUtil.dencrypt(token, "pri.key");
                    String[] split = dencrypt.split("&&");
                    String org = split[0];
                    String person = split[1];
                    if (Util.isEmpty(person)) {
                        throw new ServiceException(UtilMessage.PERSON_MISSING);
                    }
                    String mtLoginNum = OaUtil.encrypt(person);

//          返回oa流程link 例如
//          http://122.224.88.138:58080/km/review/km_review_main/
//          kmReviewMain.do?method=view&fdId=173c6b9e6dd55fccb9a0be942b2b074d&MtFdLoinName
//          =gdjjXmldhhTqgDyrFOTunA==
                    String s1 = "http://122.224.88.138:58080/km/review/km_review_main/kmReviewMain.do?method=view&fdId=";
                    String s2 = "&MtFdLoinName=";
                    StringBuffer stringBuffer = new StringBuffer();
                    oaId = URLEncoder.encode(oaId);
                    String link = String.valueOf(stringBuffer.append(s1).append(oaId).append(s2).append(mtLoginNum));
                    System.out.println("OA流程路径：" + link);
                    auditVO.setLink(link);
                    auditVO.setOaId(oaId);
                }
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
                        auditVO.setConductName(auditVO.getConductDeptName() == null ? null : auditVO.getConductDeptName());
                    } else if (offer.contains("DESIGNCOM")) {
                        auditVO.setOffer("合作单位");
                        auditVO.setConductName(auditVO.getConductUnitName() == null ? null : auditVO.getConductUnitName());
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
                    for (ChangeAuditContentVO contentVO : contentVOS) {
                        String changeContent = contentVO.getChangeContent();
                        if (Util.isNotEmpty(changeContent)) {
                            stringBuffer.append(changeContent);
                        }
                    }
                }
                String content = stringBuffer.toString();
                if (Util.isNotEmpty(content)) {
                    auditVO.setExecuteContent(content);
                }
            }
//            附件信息
            List<AttachmentsVO> attachmentsVOS = attachmentMapper.selectAttachMent(id);
            if (attachmentsVOS != null && attachmentsVOS.size() > 0) {
                for (AttachmentsVO attachmentsVO : attachmentsVOS) {
                    String fileUrl = attachmentsVO.getWebUrl();
                    if (Util.isNotEmpty(fileUrl)) {
                        String type = fileUrl.split("\\.")[fileUrl.split("\\.").length - 1];
                        attachmentsVO.setFileType(type);
                        if (Util.isNotEmpty(type)) {
                            String s = FileContentTypeUtils.contentType("." + type);
                            if (Util.isNotEmpty(s)) {
                                attachmentsVO.setContentType(s);
                            }
                        }
                    }
                }
                auditVO.setAttachmentsVOS(attachmentsVOS);
            }
        }
        return auditVO;
    }
}
