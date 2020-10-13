package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.*;
import com.example.EAS.model.TBcExpensetype;
import com.example.EAS.model.TConContractwithouttext;
import com.example.EAS.model.TConCwtextbgentry;
import com.example.EAS.service.ITConContractwithouttextService;
import com.example.EAS.util.FileContentTypeUtils;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.Util;
import com.example.EAS.vo.AttachmentsVO;
import com.example.EAS.vo.CWTextBgVO;
import com.example.EAS.vo.MarketContDetailVO;
import com.example.EAS.vo.NoTextContractVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-28
 */
@Service
public class TConContractwithouttextServiceImpl extends ServiceImpl<TConContractwithouttextMapper, TConContractwithouttext> implements ITConContractwithouttextService {

    @Autowired
    private TConContractwithouttextMapper mapper;
    @Autowired
    private TBasAttachmentMapper attachmentMapper;
    @Autowired
    private TConContractbillMapper contractbillMapper;
    @Autowired
    private TBcExpensetypeMapper expensetypeMapper;

    @Autowired
    private TConCwtextbgentryMapper cwtextbgentryMapper;


    @Override
    public PageBean<NoTextContractVO> getNoTextBills(NoTextContractVO vo) {
        String orgId = vo.getOrgId();
        //        項目id集合      有父節點則是分期 沒有是項目 id防入集合
        List<String> projectIdList = new ArrayList<>();
        if (Util.isNotEmpty(vo.getProjectId())) {
            String parentId = contractbillMapper.selectProject(vo.getProjectId());
            if (Util.isNotEmpty(parentId)) {
                projectIdList.add(vo.getProjectId());
            } else {
                List<String> ids = contractbillMapper.selectProjectIds(vo.getProjectId());
                projectIdList.addAll(ids);
            }
        }

        String state1 = vo.getState();
        if (Util.isNotEmpty(state1)) {
            if (state1.contains("已提交")) {
                vo.setState("2SUB");
            } else if (state1.contains("保存")) {
                vo.setState("1SAVED");
            } else if (state1.contains("审批中")) {
                vo.setState("3AUD");
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
        if (Util.isEmpty(orgId) || Util.isEmpty(projectIdList)) {
            return null;
        }
        vo.setProjectIdList(projectIdList);
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<NoTextContractVO> noTextContractVOList = mapper.selectDatas(vo);
        if (noTextContractVOList != null && noTextContractVOList.size() > 0) {
            for (NoTextContractVO noTextContractVO : noTextContractVOList) {
                String state = noTextContractVO.getState();
                if (Util.isNotEmpty(state)) {
                    if (state.contains("2SUBMIT")) {
                        noTextContractVO.setState("已提交");
                    } else if (state.contains("1SAVED")) {
                        noTextContractVO.setState("保存");
                    } else if (state.contains("3AUD")) {
                        noTextContractVO.setState("审批中");
                    } else if (state.contains("4")) {
                        noTextContractVO.setState("已审批");
                    } else if (state.contains("5")) {
                        noTextContractVO.setState("终止");
                    } else if (state.contains("7")) {
                        noTextContractVO.setState("已下发");
                    } else if (state.contains("8")) {
                        noTextContractVO.setState("已签证");
                    } else if (state.contains("9")) {
                        noTextContractVO.setState("作废");
                    } else if (state.contains("10")) {
                        noTextContractVO.setState("已上报");
                    } else if (state.contains("11")) {
                        noTextContractVO.setState("被打回");
                    } else if (state.contains("12REVISING")) {
                        noTextContractVO.setState("修订中");
                    } else if (state.contains("12REVISE")) {
                        noTextContractVO.setState("已修订");
                    } else if (state.contains("13")) {
                        noTextContractVO.setState("已确认");
                    }
                }
                String personName = noTextContractVO.getPersonName();
                if (Util.isNotEmpty(personName)) {
                    noTextContractVO.setReceiverName(personName);
                }
            }
        }
        PageBean<NoTextContractVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) noTextContractVOList).getTotal());
        pageBean.setPageData(noTextContractVOList);
        return pageBean;
    }

    @Override
    public NoTextContractVO viewNoTextBill(NoTextContractVO vo) {
        String id = vo.getId();
        if (Util.isNotEmpty(vo.getId())) {
            return null;
        }
        NoTextContractVO returnVO = mapper.selectDataByID(id);

        if (Util.isNotEmpty(returnVO)) {
            //            附件信息
            List<AttachmentsVO> attachmentsVOS = attachmentMapper.selectWEBAttach(id);
            if (attachmentsVOS != null && attachmentsVOS.size() > 0) {
                for (AttachmentsVO attachmentsVO : attachmentsVOS) {
                    if (Util.isNotEmpty(attachmentsVO.getOriginalFilename())) {
                        attachmentsVO.setTitle(attachmentsVO.getOriginalFilename());
                    }
                    if (Util.isNotEmpty(attachmentsVO.getFileType())) {
                        String s = FileContentTypeUtils.contentType("." + attachmentsVO.getFileType());
                        if (Util.isNotEmpty(s)) {
                            attachmentsVO.setContentType(s);
                        }
                    }
                }
                returnVO.setAttachmentsVOS(attachmentsVOS);
            }
//            期間
            String periodYear = returnVO.getPeriodYear();
            String periodNumber = returnVO.getPeriodNumber();
            if (Util.isNotEmpty(periodYear) && Util.isNotEmpty(periodNumber)) {
                String period = new StringBuffer().append(periodYear + "年").append(periodNumber + "期").toString();
                returnVO.setPeriod(period);
            }
//            納稅人
            String taxerQua = returnVO.getTaxerQua();
            if (Util.isNotEmpty(taxerQua)) {
                if (taxerQua.contains("NOMAL")) {
                    returnVO.setTaxerQua("一般纳税人");
                } else if (taxerQua.contains("SMALL")) {
                    returnVO.setTaxerQua("小规模纳税人");
                } else if (taxerQua.contains("UNNOMAL")) {
                    returnVO.setTaxerQua("非增值税纳税人");
                }
            }
//           收款人類型
            returnVO.setReceiverType("供应商");
            String personId = returnVO.getPersonId();
            if (Util.isNotEmpty(personId)) {
                returnVO.setReceiverType("职员");
            }
//          费用清单
            List<CWTextBgVO> cwTextBgVOS = new ArrayList<>();
            List<TConCwtextbgentry> entrys = cwtextbgentryMapper.selectList(new QueryWrapper<TConCwtextbgentry>()
                    .eq("FHEADID", id));
            if (entrys != null && entrys.size() > 0) {
                CWTextBgVO cwTextBgVO = new CWTextBgVO();
                for (TConCwtextbgentry entry : entrys) {
                    if (entry != null) {
                        if (entry.getFexpensetypeid() != null) {
                            TBcExpensetype tBcExpensetype = expensetypeMapper.selectById(entry.getFexpensetypeid());
                            if (tBcExpensetype != null) {
                                cwTextBgVO.setExpenseTypeName(tBcExpensetype.getFnameL2());
                            }
                        }
                        if (entry.getFrequestamount()!=null){
                            cwTextBgVO.setRequestAMT(new BigDecimal(entry.getFrequestamount()));
                        }
                    }
                    returnVO.setCwTextBgVOS(cwTextBgVOS);
                }
            }
            //        营销合同分摊明细
            List<MarketContDetailVO> marketContDetailVOS = contractbillMapper.selectMarketCons(vo.getId());
            if (marketContDetailVOS != null && marketContDetailVOS.size() > 0) {
                for (MarketContDetailVO marketContDetailVO : marketContDetailVOS) {

                }
                returnVO.setMarketContDetailVOS(marketContDetailVOS);
            }
        }
        return returnVO;
    }


}
