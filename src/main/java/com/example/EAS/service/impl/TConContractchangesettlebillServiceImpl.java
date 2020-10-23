package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.mapper.TConChangeauditbillMapper;
import com.example.EAS.mapper.TConContractchangesettlebillMapper;
import com.example.EAS.mapper.TConSupplierapplyMapper;
import com.example.EAS.model.TConContractchangesettlebill;
import com.example.EAS.service.ITConContractchangesettlebillService;
import com.example.EAS.util.*;
import com.example.EAS.vo.AttachmentsVO;
import com.example.EAS.vo.ChangeSettleEntryVO;
import com.example.EAS.vo.ChangeSettleVO;
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
public class TConContractchangesettlebillServiceImpl extends ServiceImpl<TConContractchangesettlebillMapper, TConContractchangesettlebill> implements ITConContractchangesettlebillService {

    @Autowired
    private TConContractchangesettlebillMapper mapper;
    @Autowired
    private TConChangeauditbillMapper auditMapper;
    @Autowired
    private TBasAttachmentMapper attachmentMapper;
    @Autowired
    private TConSupplierapplyMapper supplierapplyMapper;

    @Override
    public PageBean<ChangeSettleVO> getChangeSettleList(ChangeSettleVO vo) {
        String orgId = vo.getOrgId();
        if (Util.isEmpty(orgId)) {
            return null;
        }
        String state1 = vo.getState();
        if (Util.isNotEmpty(state1)) {
            if (state1.contains("已提交")) {
                vo.setState("2");
            } else if (state1.contains("保存")) {
                vo.setState("1");
            } else if (state1.contains("审批中")) {
                vo.setState("3");
            } else if (state1.contains("已审批")) {
                vo.setState("4");
            } else if (state1.contains("终止")) {
                vo.setState("5");
            } else if (state1.contains("已下发")) {
                vo.setState("7");
            } else if (state1.contains("已签证")) {
                vo.setState("8");
            } else if (state1.contains("作废")) {
                vo.setState("9");
            } else if (state1.contains("已上报")) {
                vo.setState("10");
            } else if (state1.contains("被打回")) {
                vo.setState("11");
            } else if (state1.contains("修订中")) {
                vo.setState("12REVISING");
            } else if (state1.contains("已修订")) {
                vo.setState("12REVISE");
            } else if (state1.contains("已确认")) {
                vo.setState("13");
            }
        }
        String projectId = vo.getProjectId();
//        如果是项目 获取项目下的分期id集合
        if (Util.isNotEmpty(projectId)) {
            List<String> projectIds = auditMapper.selectProjectInfo(projectId);
            if (projectIds != null && projectIds.size() > 0) {
                vo.setProjectIds(projectIds);
            }
        }
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<ChangeSettleVO> settleVOS = mapper.selectDatas(vo);
        if (settleVOS != null && settleVOS.size() > 0) {
            for (ChangeSettleVO settleVO : settleVOS) {
                String state = settleVO.getState();
                if (Util.isNotEmpty(state)) {
                    if (state.contains("2SUB")) {
                        settleVO.setState("已提交");
                    } else if (state.contains("1SAVED")) {
                        settleVO.setState("保存");
                    } else if (state.contains("3AUD")) {
                        settleVO.setState("审批中");
                    } else if (state.contains("4")) {
                        settleVO.setState("已审批");
                    } else if (state.contains("5")) {
                        settleVO.setState("终止");
                    } else if (state.contains("7")) {
                        settleVO.setState("已下发");
                    } else if (state.contains("8")) {
                        settleVO.setState("已签证");
                    } else if (state.contains("9")) {
                        settleVO.setState("作废");
                    } else if (state.contains("10")) {
                        settleVO.setState("已上报");
                    } else if (state.contains("11")) {
                        settleVO.setState("被打回");
                    } else if (state.contains("12REVISING")) {
                        settleVO.setState("修订中");
                    } else if (state.contains("12REVISE")) {
                        settleVO.setState("已修订");
                    } else if (state.contains("13")) {
                        settleVO.setState("已确认");
                    }
                }
            }
        }
        PageBean<ChangeSettleVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) settleVOS).getTotal());
        pageBean.setPageData(settleVOS);
        return pageBean;
    }

    @Override
    public ChangeSettleVO viewChangeSettle(ChangeSettleVO vo) throws Exception {
        ChangeSettleVO settleVO = new ChangeSettleVO();
        String id = vo.getId();
        if (Util.isNotEmpty(id)) {
            settleVO = mapper.viewChangeSettle(vo);
            if (Util.isNotEmpty(settleVO)) {
//                提出部门、单位
                String offer = settleVO.getOffer();
                if (Util.isNotEmpty(offer)) {
                    if (offer.contains("SELFCOM")) {
                        settleVO.setOffer("我方部门");
                        settleVO.setConductName(settleVO.getConductDeptName() == null ? null : settleVO.getConductDeptName());
                    } else if (offer.contains("DESIGNCOM")) {
                        settleVO.setOffer("合作单位");
                        settleVO.setConductName(settleVO.getConductUnitName() == null ? null : settleVO.getConductUnitName());
                    }
                }
//                变更分录
                List<ChangeSettleEntryVO> entryVOS = mapper.selectEntryInfo(id);
                if (entryVOS != null && entryVOS.size() > 0) {
                    settleVO.setEntryVOS(entryVOS);
                }
//                查看oa流程link
                String oaId = settleVO.getSourceFunction();
                if (Util.isNotEmpty(oaId)) {
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
                    settleVO.setLink(link);
                    settleVO.setOaId(oaId);
                }
            }
            //            附件信息
            //                    宋都ftp服务器上的附件
            List<AttachmentsVO> ftpvos = supplierapplyMapper.selectAttachments(id);
            if (ftpvos != null && ftpvos.size() > 0) {
                for (AttachmentsVO attachmentsVO : ftpvos) {
                    attachmentsVO.setEasId(id);
                    String fileType = attachmentsVO.getFileType();
                    String title = attachmentsVO.getTitle();
                    attachmentsVO.setOriginalFilename(new StringBuffer().append(title).append(".").append(fileType).toString());
                }
            }
//            from eas
            List<AttachmentsVO> attachmentsVOS = attachmentMapper.selectAttachMent(id);
            if (attachmentsVOS != null && attachmentsVOS.size() > 0) {
                for (AttachmentsVO attachmentsVO : attachmentsVOS) {
                    String fileUrl = attachmentsVO.getFileUrl();
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
                ftpvos.addAll(attachmentsVOS);
            }
            settleVO.setAttachmentsVOS(ftpvos);
        }
        return settleVO;
    }
}
