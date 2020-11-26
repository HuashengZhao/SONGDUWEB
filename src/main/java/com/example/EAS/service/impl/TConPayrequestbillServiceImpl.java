package com.example.EAS.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.*;
import com.example.EAS.model.TConDeductofpayreqbill;
import com.example.EAS.model.TConPayrequestbill;
import com.example.EAS.service.ITConPayrequestbillService;
import com.example.EAS.util.*;
import com.example.EAS.vo.AttachmentsVO;
import com.example.EAS.vo.PayRequestBillVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    @Autowired
    private TConSupplierapplyMapper supplierapplyMapper;
    @Autowired
    private LoginInfoUtil loginInfoUtil;
    @Autowired
    private TConContractbillMapper contractbillMapper;

    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public PageBean<PayRequestBillVO> getPayRequestBillVO(PayRequestBillVO vo) {
        //获取登录信息
        JSONObject token = loginInfoUtil.getToken();

        String orgId = vo.getOrgId();
        if (Util.isEmpty(orgId)) {
            return null;
        }
//        項目id集合      有父節點則是分期 沒有是項目 id防入集合
        List<String> projectIdList = new ArrayList<>();
        if (Util.isNotEmpty(vo.getProjectId())) {
            projectIdList.add(vo.getProjectId());
            String parentId = contractbillMapper.selectProject(vo.getProjectId());
            if (Util.isEmpty(parentId)) {
                List<String> ids = contractbillMapper.selectProjectIds(vo.getProjectId());
                projectIdList.addAll(ids);
            }
        }
        if(Util.isEmpty(projectIdList)) {
            return null;
        }
        vo.setProjectIds(projectIdList);
        //        过权限
        Boolean aBoolean = loginInfoUtil.ifInItDept();
        if (aBoolean == false) {
            vo.setAuthorNum(token.getString("person"));
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
        LocalDateTime bizDate = vo.getBizDate();
        if (Util.isNotEmpty(bizDate)) {
            LocalDateTime bornDate = bizDate.plusDays(1).minusSeconds(1);
            LocalDateTime bookDate = bizDate.minusSeconds(1);
            vo.setBizDate(bornDate);
            vo.setBookDate(bookDate);
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
                    if (payRequestBillVO.getAttNums() != null && payRequestBillVO.getAttNums().compareTo(0) == 1) {
                        payRequestBillVO.setIfHasAttach(1);
                    }
                    List<TConDeductofpayreqbill> billList = deductofpayreqbillMapper.selectList
                            (new QueryWrapper<TConDeductofpayreqbill>()
                                    .eq("FPAYREQUESTBILLID", id));
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
    public PayRequestBillVO viewPayRequestBill(PayRequestBillVO vo) throws Exception {
        //获取登录信息
        JSONObject token = loginInfoUtil.getToken();

        String id = vo.getId();
        if (Util.isEmpty(id)) {
            return null;
        }
        PayRequestBillVO payRequestBillVO = mapper.selectDataById(vo);
        if (Util.isNotEmpty(payRequestBillVO)) {
            String foaposition = payRequestBillVO.getFoaposition();
            if (Util.isNotEmpty(foaposition)) {
                String identityId = foaposition.split("\\.")[0];
                String identityName = foaposition.split("\\.")[1];
                payRequestBillVO.setIdentityId(identityId);
                payRequestBillVO.setIdentityName(identityName);
            }
            //                    获取对应的oaid
            String oaid = payRequestBillVO.getSourceFunction();
            if (Util.isNotEmpty(oaid)) {
//            获取当前登录信息 取用户账号用作oa流程查看登录
                String person = token.getString("person");
                String mtLoginNum = OaUtil.encrypt(person);

//          返回oa流程link
//          http://122.224.88.138:58080/km/review/km_review_main/
//          kmReviewMain.do?method=view&fdId=173c6b9e6dd55fccb9a0be942b2b074d&MtFdLoinName
//          =gdjjXmldhhTqgDyrFOTunA==
                String s1 = "http://122.224.88.138:58080/km/review/km_review_main/kmReviewMain.do?method=view&fdId=";
                String s2 = "&MtFdLoinName=";
                StringBuffer stringBuffer = new StringBuffer();
                oaid = URLEncoder.encode(oaid);
                String link = String.valueOf(stringBuffer.append(s1).append(oaid).append(s2).append(mtLoginNum));
                System.out.println("OA流程路径：" + link);
                payRequestBillVO.setLink(link);
                payRequestBillVO.setOaId(oaid);
            }

//            本位币金转大写
            BigDecimal amount = payRequestBillVO.getAmount();
            if (Util.isNotEmpty(amount)) {
                String s = TransToBigAmount.toChinese(String.valueOf(amount));
                payRequestBillVO.setCapitalAmount(s);
            }
//            合同起止时间转换
            String start = "";
            String end = "";
            LocalDateTime startDate = payRequestBillVO.getStartDate();
            if (Util.isNotEmpty(startDate)) {
                start = df.format(startDate);
            }
            LocalDateTime endDate = payRequestBillVO.getEndDate();
            if (Util.isNotEmpty(endDate)) {
                end = df.format(endDate);
            }
            StringBuffer sb = new StringBuffer();
            String startEndDate = sb.append(start).append("--").append(end).toString();
            payRequestBillVO.setStartEndDate(startEndDate);
//            是否提交付款
            Integer isPay = payRequestBillVO.getIsPay();
            if (Util.isEmpty(isPay)) {
                payRequestBillVO.setIsPay(0);
            }
//            是否后评估审核
            Integer isJT = payRequestBillVO.getIsJT();
            if (Util.isEmpty(isJT)) {
                payRequestBillVO.setIsJT(0);
            }
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
                } else if (orgType.contains("WAIBU")) {
                    payRequestBillVO.setOrgType("外部供应商客户往来类");
                }
            }
//          扣款金额
            BigDecimal deductAmt = BigDecimal.ZERO;
            List<TConDeductofpayreqbill> billList = deductofpayreqbillMapper.selectList
                    (new QueryWrapper<TConDeductofpayreqbill>()
                            .eq("FPAYREQUESTBILLID", id));
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
            List<AttachmentsVO> attachmentsVOS = attachmentMapper.selectAttachMent(id);
            if (attachmentsVOS != null && attachmentsVOS.size() > 0) {
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
            if (Util.isNotEmpty(contractId)) {
                List<TConPayrequestbill> bills = mapper.selectList(new QueryWrapper<TConPayrequestbill>()
                        .eq("FCONTRACTID", contractId));
                if (bills != null && bills.size() > 0) {
                    for (TConPayrequestbill bill : bills) {
                        Double fcompleteprjamt = bill.getFcompleteprjamt();
                        if (Util.isNotEmpty(fcompleteprjamt)) {
                            allAMT = allAMT.add(new BigDecimal(fcompleteprjamt));
                        }
                    }
                }
                payRequestBillVO.setAllAMT(allAMT);
            }
//            累计完工工程比例  累计完工工程量  / 合同最新造价
            BigDecimal allAMTRATE = BigDecimal.ZERO;
            if (Util.isNotEmpty(payRequestBillVO.getLatestPrice()) && payRequestBillVO.getLatestPrice().compareTo(BigDecimal.ZERO) != 0
                    && allAMT.compareTo(BigDecimal.ZERO) == 1) {
                allAMTRATE = allAMT.divide(payRequestBillVO.getLatestPrice(), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                payRequestBillVO.setAllAMTRATE(allAMTRATE);
            }

//
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
