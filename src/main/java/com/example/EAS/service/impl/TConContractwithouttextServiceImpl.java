package com.example.EAS.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.*;
import com.example.EAS.model.*;
import com.example.EAS.service.ITConContractwithouttextService;
import com.example.EAS.util.*;
import com.example.EAS.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
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
    private TConMarketprojectMapper marketProjectMapper;
    @Autowired
    private TConSupplierapplyMapper csMapper;
    @Autowired
    private TBcExpensetypeMapper expensetypeMapper;
    @Autowired
    private TConCwtextbgentryMapper cwtextbgentryMapper;
    @Autowired
    private TOrgBaseunitMapper orgMapper;
    @Autowired
    private TConSupplierapplyMapper supplierapplyMapper;
    @Autowired
    private TConMarketprojectcostentryMapper tConMarketprojectcostentryMapper;
    @Autowired
    private FtpUtil ftpUtil;
    @Autowired
    private OaIdUtil oaIdUtil;

    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    org.apache.axis.client.Service service = new org.apache.axis.client.Service();


    public Call getCall(String type, String operationName) {

        String url = null;
        if (type.contains("EAS")) {
            url = csMapper.selectEASURL();
        } else {
            url = csMapper.selectOAURL();
        }
        Call call = null;
        try {
            call = (Call) service.createCall();
            call.setOperationName(operationName);
            call.setTargetEndpointAddress(new java.net.URL(url));
            call.addParameter("arg0", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            call.setUseSOAPAction(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return call;
    }

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
        if (Util.isEmpty(vo.getId())) {
            return null;
        }
        NoTextContractVO returnVO = mapper.selectDataByID(id);
        String state = returnVO.getState();
        if (Util.isNotEmpty(state)) {
            if (state.contains("2SUBMIT")) {
                returnVO.setState("已提交");
            } else if (state.contains("1SAVED")) {
                returnVO.setState("保存");
            } else if (state.contains("3AUD")) {
                returnVO.setState("审批中");
            } else if (state.contains("4")) {
                returnVO.setState("已审批");
            } else if (state.contains("5")) {
                returnVO.setState("终止");
            } else if (state.contains("7")) {
                returnVO.setState("已下发");
            } else if (state.contains("8")) {
                returnVO.setState("已签证");
            } else if (state.contains("9")) {
                returnVO.setState("作废");
            } else if (state.contains("10")) {
                returnVO.setState("已上报");
            } else if (state.contains("11")) {
                returnVO.setState("被打回");
            } else if (state.contains("12REVISING")) {
                returnVO.setState("修订中");
            } else if (state.contains("12REVISE")) {
                returnVO.setState("已修订");
            } else if (state.contains("13")) {
                returnVO.setState("已确认");
            }
        }
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
//
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
//           收款人類型 收款单位名称
//            当无文本单据中职员id不为空时 实际收款单位取职员名称，收款单位为空
//            当职员为空时，实际收款单位取申请单中的实际收款单位，收款单位是无文本单据中的收款单位
            returnVO.setReceiverType("供应商");
            String personId = returnVO.getPersonId();
            if (Util.isNotEmpty(personId)) {
                returnVO.setReceiverType("职员");
                returnVO.setReceiverName(null);
                returnVO.setRealReceiveUnitName(returnVO.getPersonName() == null ? null : returnVO.getPersonName());
            }
//          费用清单
            List<CWTextBgVO> cwTextBgVOS = new ArrayList<>();
            List<TConCwtextbgentry> entrys = cwtextbgentryMapper.selectList(new QueryWrapper<TConCwtextbgentry>()
                    .eq("FHEADID", id));
            if (entrys != null && entrys.size() > 0) {
                for (TConCwtextbgentry entry : entrys) {
                    CWTextBgVO cwTextBgVO = new CWTextBgVO();
                    if (entry != null) {
                        cwTextBgVO.setId(entry.getFid());
                        if (entry.getFexpensetypeid() != null) {
                            TBcExpensetype tBcExpensetype = expensetypeMapper.selectById(entry.getFexpensetypeid());
                            if (tBcExpensetype != null) {
                                cwTextBgVO.setExpenseTypeName(tBcExpensetype.getFnameL2());
                                cwTextBgVO.setExpenseTypeId(tBcExpensetype.getFid());
                            }
                        }
                        if (entry.getFamount() != null) {
                            cwTextBgVO.setAmount(new BigDecimal(entry.getFamount()));
                        }
                    }
                    cwTextBgVOS.add(cwTextBgVO);
                }
                returnVO.setCwTextBgVOS(cwTextBgVOS);
            }

            //        是否后评估审核
            Integer isjt = returnVO.getIsjt();
            if (Util.isEmpty(isjt)) {
                returnVO.setIsjt(0);
            }
//        根据营销立项中的后评估审核带出
            String marketProjectId = returnVO.getMarketProjectId();
            if (Util.isNotEmpty(marketProjectId)) {
                TConMarketproject mp = marketProjectMapper.selectOne(new QueryWrapper<TConMarketproject>()
                        .eq("FID", marketProjectId));
                Long fisjt = mp.getFisjt();
                if (Util.isEmpty(fisjt)) {
                    returnVO.setIsjt(0);
                } else {
                    returnVO.setIsjt(Math.toIntExact(mp.getFisjt()));
                }
            }
            //        营销合同分摊明细
            List<MarketContDetailVO> marketContDetailVOS = contractbillMapper.selectMarketCons(vo.getId());
            TConContractwithouttext tConContractwithouttext = mapper.selectById(id);
            Long fisjt = tConContractwithouttext.getFisjt();
            if (marketContDetailVOS != null && marketContDetailVOS.size() > 0) {
                for (MarketContDetailVO marketContDetailVO : marketContDetailVOS) {
                    returnVO.setIsjt(0);
                    if (Util.isNotEmpty(fisjt) && fisjt == 1) {
                        returnVO.setIsjt(1);
                    }
                }
                returnVO.setMarketContDetailVOS(marketContDetailVOS);
            }
        }
        return returnVO;
    }

    @Override
    public NoTextContractVO saveNoTextBill(NoTextContractVO vo) {
        JSONObject easJson = new JSONObject();
        NoTextContractVO noTextContractVO = new NoTextContractVO();
//        是否调用eas提交方法
        Boolean flag = vo.getFlag();
        String id = vo.getId();
        if (Util.isNotEmpty(id)) {
            easJson.put("id", id);
        }
        String num = vo.getNum();
        if (Util.isNotEmpty(num)) {
            easJson.put("number", num);
        }
        String title = vo.getTitle();
        if (Util.isNotEmpty(title)) {
            easJson.put("name", title);
        }
        String orgId = vo.getOrgId();
        if (Util.isNotEmpty(orgId)) {
            easJson.put("orgId", orgId);
        }
        String projectId = vo.getProjectId();
        if (Util.isNotEmpty(projectId)) {
            easJson.put("projectId", projectId);
        }
        String contractTypeId = vo.getContractTypeId();
        if (Util.isNotEmpty(contractTypeId)) {
            easJson.put("conTypeId", contractTypeId);
        }
        String programContractId = vo.getProgramContractId();
        if (Util.isNotEmpty(programContractId)) {
            easJson.put("hygh", programContractId);
        }
        String marketProjectId = vo.getMarketProjectId();
        if (Util.isNotEmpty(marketProjectId)) {
            easJson.put("marketProjectId", marketProjectId);
        }
//        立项金额控制合同金额
        if (Util.isNotEmpty(marketProjectId)) {
            TConMarketprojectcostentry contractmarketentry =
                    tConMarketprojectcostentryMapper.selectOne(new QueryWrapper<TConMarketprojectcostentry>()
                            .eq("FHEADID", marketProjectId)
                            .eq("FTYPE", "CONTRACT"));
            if (Util.isNotEmpty(contractmarketentry)) {
                Double famount = contractmarketentry.getFamount();
                BigDecimal voAmount = vo.getAmount();
                if (Util.isNotEmpty(famount) && Util.isNotEmpty(voAmount)) {
                    BigDecimal famountDe = new BigDecimal(famount);
                    if (voAmount.compareTo(famountDe) == 1) {
                        throw new ServiceException(UtilMessage.CONTRACT_AMOUNT_BEYOND);
                    }
                }
            }
        }
        String costAccountId = vo.getCostAccountId();
        if (Util.isNotEmpty(costAccountId)) {
            easJson.put("costAccountId", costAccountId);
        }
        String currencyId = vo.getCurrencyId();
        if (Util.isNotEmpty(currencyId)) {
            easJson.put("currencyId", currencyId);
        }
        BigDecimal oriAmount = vo.getOriAmount();
        if (Util.isEmpty(oriAmount)) {
            throw new ServiceException(UtilMessage.CONTRACT_AMOUNT_NOT_FOUND);
        }
        easJson.put("originalAmount", oriAmount.toString());
//        此处疑问 前端合同金额字段 存值到原币与本位币两个字段 加判断 若传本位币则赋值 否则都一样
//        BigDecimal amount = vo.getAmount();
//        if (Util.isNotEmpty(amount)) {
//            easJson.put("amount", amount.toString());
//        } else {
        easJson.put("amount", oriAmount.toString());
//        }
        String capitalAmount = vo.getBwbdx();
        if (Util.isNotEmpty(capitalAmount)) {
            easJson.put("capitalAmount", capitalAmount);
        }
        String payBillTypeId = vo.getPayBillTypeId();
        if (Util.isNotEmpty(payBillTypeId)) {
            easJson.put("payBillTypeId", payBillTypeId);
        }
        String payContentId = vo.getPayContentId();
        if (Util.isNotEmpty(payContentId)) {
            easJson.put("payContentTypeId", payContentId);
        }
        LocalDateTime bizDate = vo.getBizDate();
        if (Util.isNotEmpty(bizDate)) {
            easJson.put("bizDate", bizDate);
        }
//       转业务期间
        if (Util.isNotEmpty(bizDate)) {
            String format = df.format(bizDate);
            String[] split = format.split("-");
            String periodYear = split[0];
            String periodMonth = split[1];
            String periodNumber = new StringBuffer().append(periodYear).append(periodMonth).toString();
            String periodId = mapper.selectPeriodId(periodNumber);
            if (Util.isNotEmpty(periodId)) {
                easJson.put("periodId", periodId);
            }
        }
        String unionBankId = vo.getUnionBankId();
//        String unionBankId = mapper.selectUnionBankId(unionBankNum);
        if (Util.isNotEmpty(unionBankId)) {
            easJson.put("unionBankId", unionBankId);
        }
        String bank = vo.getBank();
        if (Util.isNotEmpty(bank)) {
            easJson.put("bank", bank);
        }
        String bankAccount = vo.getBankAccount();
        if (Util.isNotEmpty(bankAccount)) {
            easJson.put("bankAccount", bankAccount);
        }
        String taxerQua = vo.getTaxerQua();
        if (Util.isNotEmpty(taxerQua)) {
            easJson.put("taxerQua", taxerQua);
        }
        String taxerNumber = vo.getTaxerNumber();
        if (Util.isNotEmpty(taxerNumber)) {
            easJson.put("taxerNum", taxerNumber);
        }
        String settlementTypeId = vo.getSettlementTypeId();
        if (Util.isNotEmpty(settlementTypeId)) {
            easJson.put("settlementTypeId", settlementTypeId);
        }
        String applierId = vo.getApplierId();
        if (Util.isNotEmpty(applierId)) {
            easJson.put("applierId", applierId);
        }
        String useDeptId = vo.getUseDeptId();
        if (Util.isNotEmpty(useDeptId)) {
            easJson.put("useDepartmentId", useDeptId);
        }
        String costDeptId = vo.getCostDeptId();
        if (Util.isNotEmpty(costDeptId)) {
            easJson.put("costedDeptId", costDeptId);
        }
        String costCompanyId = vo.getCostCompanyId();
        if (Util.isNotEmpty(costCompanyId)) {
            easJson.put("costedCompanyId", costCompanyId);
        }
        Integer isjt = vo.getIsjt();
        if (isjt != null && isjt == 1) {
            easJson.put("isJT", "true");
        } else {
            easJson.put("isJT", "false");
        }
        BigDecimal invoiceAMT = vo.getInvoiceAMT();
        if (Util.isNotEmpty(invoiceAMT)) {
            easJson.put("invoiceAmt", invoiceAMT);
        }
        BigDecimal rateAmount = vo.getRateAmount();
        if (Util.isNotEmpty(rateAmount)) {
            easJson.put("rateAmount", rateAmount);
        }
        String description = vo.getDescription();
        if (Util.isNotEmpty(description)) {
            easJson.put("description", description);
        }

        String receiverType = vo.getReceiverType();
        if (Util.isNotEmpty(receiverType)) {
            easJson.put("receiverType", receiverType);
            //        当收款人类型为供应商时，实际收款单位存到付款申请单中，收款单位存无文本中
//        收款人为职员时，只存personId到无文本合同
            if (receiverType.equals("供应商")) {
                String receiveUnitID = vo.getReceiveUnitID();
                if (Util.isNotEmpty(receiveUnitID)) {
                    easJson.put("realSupplierId", receiveUnitID);
                }
                String supplierId = vo.getSupplierId();
                if (Util.isNotEmpty(supplierId)) {
                    easJson.put("receiveUnitId", supplierId);
                }
            } else if (receiverType.equals("职员")) {
                String personId = vo.getPersonId();
                if (Util.isNotEmpty(personId)) {
                    easJson.put("personId", personId);
                }
            }
        }

//      立项分录
        JSONArray marketConArray = new JSONArray();
        List<MarketContDetailVO> marketContDetailVOS = vo.getMarketContDetailVOS();
        if (marketContDetailVOS != null && marketContDetailVOS.size() > 0) {
            for (MarketContDetailVO marketContDetailVO : marketContDetailVOS) {
                JSONObject marketObj = new JSONObject();
                marketObj.put("rate", marketContDetailVO.getRate());
                marketObj.put("remark", marketContDetailVO.getRemark());
                marketObj.put("date", marketContDetailVO.getFsdate());
                marketObj.put("amount", marketContDetailVO.getAmount());
                marketObj.put("content", marketContDetailVO.getContent());
                marketConArray.add(marketObj);
            }
        }
        if (marketConArray != null && marketConArray.size() > 0) {
            easJson.put("marketConArray", marketConArray);
        }
//       费用清单
        JSONArray costArray = new JSONArray();
        List<CWTextBgVO> cwTextBgVOS = vo.getCwTextBgVOS();
        if (cwTextBgVOS != null && cwTextBgVOS.size() > 0) {
            for (CWTextBgVO cwTextBgVO : cwTextBgVOS) {
                JSONObject costObj = new JSONObject();
                costObj.put("amount", cwTextBgVO.getAmount());
                String expenseTypeName = cwTextBgVO.getExpenseTypeName();
                if (Util.isNotEmpty(expenseTypeName)) {
                    TBcExpensetype expensetype = expensetypeMapper.selectOne(new QueryWrapper<TBcExpensetype>()
                            .eq("FNAME_L2", expenseTypeName));
                    if (Util.isNotEmpty(expensetype)) {
                        costObj.put("expenseTypeId", expensetype.getFid());
                    }
                }
                costArray.add(costObj);
            }
        }
        if (costArray != null && costArray.size() > 0) {
            easJson.put("bgArray", costArray);
        }

//      保存到EAS附件
        JSONArray attach = new JSONArray();
        List<AttachmentsVO> attachmentsVOS = vo.getAttachmentsVOS();
        if (attachmentsVOS != null && attachmentsVOS.size() > 0) {
            for (AttachmentsVO attachmentsVO : attachmentsVOS) {
                JSONObject object = new JSONObject();
                String attachNum = attachmentsVO.getNum();
                String webUrl = attachmentsVO.getWebUrl();
                String fileUUID = attachmentsVO.getFileUUID();
                String originalFilename = attachmentsVO.getOriginalFilename();
//                    List<AttachmentsVO> attachmentsVOSList = attachmentMapper.selectByNumber(attachNum);
//                    if (attachmentsVOSList != null && attachmentsVOSList.size() > 0) {
                if (Util.isNotEmpty(webUrl) && Util.isNotEmpty(fileUUID) && Util.isNotEmpty(originalFilename)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    String s = stringBuffer.append(webUrl).append("/").append(fileUUID).toString();
                    object.put("FName", originalFilename == null ? "none" : originalFilename);
                    object.put("FRemotePath", s == null ? "none" : s);
                    attach.add(object);
//                        }
                }
            }
        }
        if (attach != null && attach.size() > 0) {
            easJson.put("attach", attach);
        }
        String result = null;
        Call call = null;
//        调用eas 保存方法进行保存
        if (Util.isEmpty(flag) || flag.compareTo(false) == 0) {
            call = getCall("EASURL", "saveContractwithouttext");
            try {
                System.out.println("无文本保存信息：" + easJson.toString());
                result = (String) call.invoke(new Object[]{easJson.toString()});
            } catch (RemoteException e) {
                e.printStackTrace();
                throw new ServiceException(e.getMessage());
            }
        } else {
            //            如果是驳回后重新提交 调用eas合同提交方法
            call = getCall("EASURL", "submitContractwithouttext");
            try {
                System.out.println(easJson.toJSONString());
                result = (String) call.invoke(new Object[]{easJson.toString()});
            } catch (RemoteException e) {
                e.printStackTrace();
                throw new ServiceException(e.getMessage());
            }
        }
//        接收返回eas信息
        JSONObject object = JSONObject.parseObject(result);
        if (result != null && object.get("result").toString().contains("fault")) {
            noTextContractVO.setResult("fault");
            throw new ServiceException(object.getString("message"));
        } else if (result != null && object.get("result").toString().contains("success")) {
//            成功保存后更改创建人为当前登录人
            id = object.getString("id");
            noTextContractVO.setId(id);
            noTextContractVO.setResult("success");
            TConContractwithouttext tConContractwithouttext = mapper.selectById(id);
            JSONObject token = getToken();
            String person = token.getString("person");
            String creatorId = mapper.selectPersonId(person);
            tConContractwithouttext.setFcreatorid(creatorId);
            mapper.updateById(tConContractwithouttext);
        }
        //        保存附件到web表
        if (Util.isNotEmpty(id) && vo.getAttachmentsVOS() != null && vo.getAttachmentsVOS().size() > 0) {
            supplierapplyMapper.deletAttach(id);
            ftpUtil.saveAttachMent(attachmentsVOS, id);
        } else {
            supplierapplyMapper.deletAttach(id);
        }
        return noTextContractVO;
    }

    @Override
    public NoTextContractVO submitNoTextBill(NoTextContractVO vo) {
        NoTextContractVO noTextContractVO = new NoTextContractVO();
        String id = vo.getId();
        vo.setFlag(true);

        NoTextContractVO noTextContractVO1 = saveNoTextBill(vo);
        String saveResult = noTextContractVO1.getResult();
        if (Util.isNotEmpty(saveResult) && saveResult.contains("fault")) {
            throw new ServiceException(UtilMessage.SUBMIT_FAULT);
        }
        //      判断是否提交过被驳回  需要携带oaid
        JSONObject obj = new JSONObject();
        String oaId = null;
        oaId = supplierapplyMapper.selectOaid(id);
        //      基本参数
        obj.put("id", id);
        obj.put("tmplateId", "17400f0f65621b8cae9869445db9c6f6");
        obj.put("fdType", "1");
        obj.put("docSubject", vo.getTitle());
        //        表单参数
        JSONObject data = new JSONObject();
        if (Util.isNotEmpty(vo.getPayContentId())) {
            String payContentName = mapper.selectPayContentName(vo.getPayContentId());
            data.put("fd_38cf18370f3976", payContentName);
        }
        if (Util.isNotEmpty(vo.getOriAmount())) {
            Double aDouble = vo.getOriAmount().doubleValue();
            data.put("fd_38cf1871775004", aDouble);
            System.out.println("oa新增流程新家字段：" + aDouble);
        }
        if (Util.isNotEmpty(vo.getMarketProjectId())) {
            String marketProjectName = mapper.selectMarketProjectName(vo.getMarketProjectId());
            data.put("fd_38cf18383073ec", marketProjectName);
            System.out.println(marketProjectName);
        }
        if (Util.isNotEmpty(vo.getContractTypeId())) {
            String contractTypeName = mapper.selectContractType(vo.getContractTypeId());
            data.put("fd_38cf183640fe40", contractTypeName);
            System.out.println(contractTypeName);
        }
        StringBuffer sb = new StringBuffer();
        String token = RequestHolder.getCurrentUser().getToken();
        String dencrypt = null;
        try {
            dencrypt = RSAUtil.dencrypt(token, "pri.key");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] split = dencrypt.split("&&");
        String personNum = split[1];
        PersonsVO person = supplierapplyMapper.selectCreator(personNum);

        try {
            token = URLEncoder.encode(token, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //        http://172.17.4.125:8082/easWeb/#/
        String sendUrl = null;
        StringBuffer sbv = new StringBuffer();
        String appendUrl = supplierapplyMapper.selectViewUrl();
//					审批单 approval
        if (Util.isNotEmpty(appendUrl)) {
            String appendType = "notext/view?from=oa&id=";
            String appendId = null;
            try {
                appendId = URLEncoder.encode(id, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
//					token
            String appendToken = "&token=";
            sendUrl = String.valueOf(sbv.append(appendUrl).append(appendType).append(appendId)
                    .append(appendToken).append(token));
        }
//        sb.append("http://172.17.4.125:8082/easWeb/#/supplier").append("?token=").append(token);
        System.out.println("easweb详情link：" + sendUrl);
        obj.put("loginName", "00561");
//        附件参数 todo
        JSONObject attFile = new JSONObject();
//        obj.put("attFile", attFile);

        data.put("fd_link", sendUrl);
        data.put("fd_person", "谢凯伦");
//        data.put("createTime", vo.getCreateTime());
        obj.put("data", data.toString());
        //        当当前流程未提交时 oaidrecord没有对应oaid 调用oa新增提交方法
        String result = null;
        JSONObject str = null;
        if (Util.isEmpty(oaId)) {
            Call call = getCall("OAURL", "addEkpReview");
            try {
                result = (String) call.invoke(new Object[]{obj.toString()});
                System.out.println(vo.getTitle() + "oa新增流程参数：" + obj.toString());
                str = JSONObject.parseObject(result);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Call call = getCall("OAURL", "updateEkpReview");
            try {
                obj.put("id", oaId);
                result = (String) call.invoke(new Object[]{obj.toString()});
                System.out.println(vo.getTitle() + "oa新增流程参数：" + obj.toString());
                str = JSONObject.parseObject(result);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
//        code 1 success 2 fault
        String code = str.getString("code");
        String oaid = str.getString("oaid");
        if (Util.isNotEmpty(oaid) && Util.isNotEmpty(id)) {
            oaIdUtil.getString(id, oaid);
        }
//       成功提交后，修改eas当前状态为审批中0
        if (code != null && code.contains("1")) {
//            修改单据状态为审批中
            TConContractwithouttext tConContractbill = mapper.selectById(id);
            tConContractbill.setFstate("3AUDITTING");
            mapper.updateById(tConContractbill);
        } else {
            throw new ServiceException(UtilMessage.SUBMIT_FAULT);
        }
        return noTextContractVO;
    }

    @Override
    public String getNoTextNum(NoTextContractVO vo) {
        //        生成合同编码规则额："web"+组织编码+8位数流水号
        DecimalFormat decimalFormat = new DecimalFormat("00000000");
        Integer numRecord = mapper.selectNewNum();
        int value = 0;
        String format = null;
        if (numRecord != null) {
            value = value + numRecord + 1;
            mapper.updateNumRecord(value);
            format = decimalFormat.format(value);
        }
        JSONObject token = getToken();
        String org = token.getString("org");
        StringBuffer sb = new StringBuffer();
        String newNumber = sb.append("WEB.").append(org).append(format).toString();

        return newNumber;
    }


    @Override
    public void deleteNoTextNum(NoTextContractVO vo) {
        JSONObject jsonObject = new JSONObject();
        JSONArray idArray = new JSONArray();
        List<String> idList = vo.getIdList();
        if (idList != null && idList.size() > 0) {
            for (String s : idList) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("id", s);
                idArray.add(jsonObject1);
            }
        }
        jsonObject.put("idArray", idArray);
        Call call = getCall("EASURL", "deleteContractwithouttext");
        String result = null;
        try {
            result = (String) call.invoke(new Object[]{jsonObject.toString()});
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //    获取系统登录信息
    private JSONObject getToken() {
        JSONObject object = new JSONObject();

        //       获取当前用户组织
        String token = RequestHolder.getCurrentUser().getToken();
        String dencrypt = null;
        try {
            dencrypt = RSAUtil.dencrypt(token, "pri.key");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] split = dencrypt.split("&&");
        String org = split[0];
        String person = split[1];
        object.put("org", org);
        object.put("person", person);
        return object;
    }

}
