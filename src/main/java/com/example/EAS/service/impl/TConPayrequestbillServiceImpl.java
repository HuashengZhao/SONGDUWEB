package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.mapper.TConDeductofpayreqbillMapper;
import com.example.EAS.mapper.TConPayrequestbillMapper;
import com.example.EAS.model.TConDeductofpayreqbill;
import com.example.EAS.model.TConPayrequestbill;
import com.example.EAS.service.ITConPayrequestbillService;
import com.example.EAS.util.FileContentTypeUtils;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.Util;
import com.example.EAS.vo.AttachmentsVO;
import com.example.EAS.vo.PayRequestBillVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-10-09
 */
@Service
public class TConPayrequestbillServiceImpl extends ServiceImpl<TConPayrequestbillMapper, TConPayrequestbill> implements ITConPayrequestbillService {

    @Autowired
    private TConPayrequestbillMapper mapper;

    @Autowired
    private TConDeductofpayreqbillMapper deductofpayreqbillMapper;
    @Autowired
    private TBasAttachmentMapper attachmentMapper;


    @Override
    public PageBean<PayRequestBillVO> getPayRequestBillVO(PayRequestBillVO vo) {
        String orgId = vo.getOrgId();
        if (Util.isEmpty(orgId)) {
            return null;
        }
        String projectId = vo.getProjectId();
        if (Util.isEmpty(projectId)) {
            return null;
        }
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<PayRequestBillVO> payRequestBillVOS = mapper.selectDatas(vo);
        if (payRequestBillVOS != null && payRequestBillVOS.size() > 0) {
            for (PayRequestBillVO payRequestBillVO : payRequestBillVOS) {
//                状态
                String state = payRequestBillVO.getState();
                if (Util.isNotEmpty(state)) {
                    if (state.contains("2SUBMIT")) {
                        payRequestBillVO.setState("已提交");
                    } else if (state.contains("1SAVED")) {
                        payRequestBillVO.setState("保存");
                    } else if (state.contains("3AUD")) {
                        payRequestBillVO.setState("审批中");
                    } else if (state.contains("4")) {
                        payRequestBillVO.setState("已审批");
                    } else if (state.contains("5")) {
                        payRequestBillVO.setState("终止");
                    } else if (state.contains("7")) {
                        payRequestBillVO.setState("已下发");
                    } else if (state.contains("8")) {
                        payRequestBillVO.setState("已签证");
                    } else if (state.contains("9")) {
                        payRequestBillVO.setState("作废");
                    } else if (state.contains("10")) {
                        payRequestBillVO.setState("已上报");
                    } else if (state.contains("11")) {
                        payRequestBillVO.setState("被打回");
                    } else if (state.contains("12REVISING")) {
                        payRequestBillVO.setState("修订中");
                    } else if (state.contains("12REVISE")) {
                        payRequestBillVO.setState("已修订");
                    } else if (state.contains("13")) {
                        payRequestBillVO.setState("已确认");
                    }
                }
//                扣款金额
                BigDecimal deductAmt = BigDecimal.ZERO;
                String id = payRequestBillVO.getId();
                if (Util.isNotEmpty(id)) {
//                    是否有附件
                    payRequestBillVO.setIfHasAttach(0);
                    List<AttachmentsVO> attachmentsVOS = attachmentMapper.selectAttachMent(id);
                    if (attachmentsVOS != null && attachmentsVOS.size() > 0) {
                        payRequestBillVO.setIfHasAttach(1);
                    }
                    List<TConDeductofpayreqbill> billList = deductofpayreqbillMapper.selectList
                            (new QueryWrapper<TConDeductofpayreqbill>()
                                    .eq("FPAYMENTBILLID", id));
                    if (billList != null && billList.size() > 0) {
                        for (TConDeductofpayreqbill tConDeductofpayreqbill : billList) {
                            Double famount = tConDeductofpayreqbill.getFamount();
                            if (Util.isNotEmpty(famount)) {
                                deductAmt = deductAmt.add(BigDecimal.valueOf(famount));
                            }
                        }
                    }
                    payRequestBillVO.setDeductAmt(deductAmt);
                }
            }
        }
        PageBean<PayRequestBillVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) payRequestBillVOS).getTotal());
        pageBean.setPageData(payRequestBillVOS);
        return pageBean;
    }

    @Override
    public PayRequestBillVO viewPayRequestBill(PayRequestBillVO vo) {
        String id = vo.getId();
        if (Util.isEmpty(id)) {
            return null;
        }
        PayRequestBillVO payRequestBillVO = mapper.selectDataById(vo);
        if (Util.isNotEmpty(payRequestBillVO)) {
//           内外部合同
            String orgType = payRequestBillVO.getOrgType();
            if (Util.isNotEmpty(orgType)) {
                if (orgType.contains("BIGRANGE")) {
                    payRequestBillVO.setOrgType("集团/事业部/城市公司");
                } else if (orgType.contains("SMALLRANGE")) {
                    payRequestBillVO.setOrgType("项目部");
                } else if (orgType.contains("ALLRANGE")) {
                    payRequestBillVO.setOrgType("集团/事业部/城市公司-项目部");
                } else if (orgType.contains("NEIBU")) {
                    payRequestBillVO.setOrgType("内部关联公司往来类");
                }
            }
//          扣款金额
            BigDecimal deductAmt = BigDecimal.ZERO;
            List<TConDeductofpayreqbill> billList = deductofpayreqbillMapper.selectList
                    (new QueryWrapper<TConDeductofpayreqbill>()
                            .eq("FPAYMENTBILLID", id));
            if (billList != null && billList.size() > 0) {
                for (TConDeductofpayreqbill tConDeductofpayreqbill : billList) {
                    Double famount = tConDeductofpayreqbill.getFamount();
                    if (Util.isNotEmpty(famount)) {
                        deductAmt = deductAmt.add(BigDecimal.valueOf(famount));
                    }
                }
            }
            payRequestBillVO.setDeductAmt(deductAmt);
//          状态
            String state = payRequestBillVO.getState();
            if (Util.isNotEmpty(state)) {
                if (state.contains("2SUBMIT")) {
                    payRequestBillVO.setState("已提交");
                } else if (state.contains("1SAVED")) {
                    payRequestBillVO.setState("保存");
                } else if (state.contains("3AUD")) {
                    payRequestBillVO.setState("审批中");
                } else if (state.contains("4")) {
                    payRequestBillVO.setState("已审批");
                } else if (state.contains("5")) {
                    payRequestBillVO.setState("终止");
                } else if (state.contains("7")) {
                    payRequestBillVO.setState("已下发");
                } else if (state.contains("8")) {
                    payRequestBillVO.setState("已签证");
                } else if (state.contains("9")) {
                    payRequestBillVO.setState("作废");
                } else if (state.contains("10")) {
                    payRequestBillVO.setState("已上报");
                } else if (state.contains("11")) {
                    payRequestBillVO.setState("被打回");
                } else if (state.contains("12REVISING")) {
                    payRequestBillVO.setState("修订中");
                } else if (state.contains("12REVISE")) {
                    payRequestBillVO.setState("已修订");
                } else if (state.contains("13")) {
                    payRequestBillVO.setState("已确认");
                }
            }
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
                payRequestBillVO.setAttachmentsVOS(attachmentsVOS);
            }
//            纳税人
            String taxerQua = payRequestBillVO.getTaxerQua();
            if (Util.isNotEmpty(taxerQua)) {
                if (taxerQua.contains("NOMAL")) {
                    payRequestBillVO.setTaxerQua("一般纳税人");
                } else if (taxerQua.contains("SMALL")) {
                    payRequestBillVO.setTaxerQua("小规模纳税人");
                } else if (taxerQua.contains("UNNOMAL")) {
                    payRequestBillVO.setTaxerQua("非增值税纳税人");
                }
            }
//    累计完工工程量  合同对应的所有付款申请单本次完工工程量的和
            BigDecimal allAMT = BigDecimal.ZERO;
            String contractId = payRequestBillVO.getContractId();
            if (Util.isNotEmpty(contractId)){
                List<TConPayrequestbill> bills = mapper.selectList(new QueryWrapper<TConPayrequestbill>()
                        .eq("FCONTRACTID", contractId));
                if (bills!=null && bills.size()>0){
                    for (TConPayrequestbill bill : bills) {
                        Double fcompleteprjamt = bill.getFcompleteprjamt();
                        if (Util.isNotEmpty(fcompleteprjamt)){
                            allAMT=allAMT.add(new BigDecimal(fcompleteprjamt));
                        }
                    }
                }
                payRequestBillVO.setAllAMT(allAMT);
            }
//            累计完工工程比例
//            累计进度款付款比例  （张佳萍说不要了）
//            付款申请金额
//            PaymentApplyVO paymentApplyVO = new PaymentApplyVO();
//            BigDecimal requestAMT = payRequestBillVO.getRequestAMT();
//            if (Util.isNotEmpty(requestAMT)){
//                paymentApplyVO.setApplyAMT(requestAMT);
//            }
        }

        return payRequestBillVO;
    }
}
