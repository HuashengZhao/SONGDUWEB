package com.example.EAS.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.*;
import com.example.EAS.model.TBcExpensetype;
import com.example.EAS.model.TConContractwithouttext;
import com.example.EAS.model.TConCwtextbgentry;
import com.example.EAS.service.ITConContractwithouttextService;
import com.example.EAS.util.*;
import com.example.EAS.vo.AttachmentsVO;
import com.example.EAS.vo.CWTextBgVO;
import com.example.EAS.vo.MarketContDetailVO;
import com.example.EAS.vo.NoTextContractVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private FtpUtil ftpUtil;

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
                        if (entry.getFrequestamount() != null) {
                            cwTextBgVO.setAmount(new BigDecimal(entry.getFamount()));
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

    @Override
    public NoTextContractVO saveNoTextBill(NoTextContractVO vo) {
        JSONObject easJson = new JSONObject();
        NoTextContractVO noTextContractVO = new NoTextContractVO();
//        是否调用eas提交方法
        Boolean flag = vo.getFlag();
        String id = vo.getId();
        easJson.put("id", id);
        String num = vo.getNum();
        easJson.put("number", num);
        String title = vo.getTitle();
        easJson.put("name", title);
        String orgId = vo.getOrgId();
        easJson.put("orgId", orgId);
        String projectId = vo.getProjectId();
        easJson.put("projectId", projectId);
        String contractTypeId = vo.getContractTypeId();
        easJson.put("conTypeId", contractTypeId);
        String programContractId = vo.getProgramContractId();
        easJson.put("hygh", programContractId);
        String marketProjectId = vo.getMarketProjectId();
        easJson.put("marketProjectId", marketProjectId);
        String costAccountId = vo.getCostAccountId();
        easJson.put("costAccountId", costAccountId);
        String currencyId = vo.getCurrencyId();
        easJson.put("currencyId", currencyId);
        BigDecimal oriAmount = vo.getOriAmount();
        easJson.put("originalAmount", oriAmount.toString());
//        BigDecimal amount = vo.getAmount();
        easJson.put("amount", oriAmount.toString());
        String payBillTypeId = vo.getPayBillTypeId();
        easJson.put("payBillTypeId", payBillTypeId);
        String payContentId = vo.getPayContentId();
        easJson.put("payContentTypeId", payContentId);
        LocalDateTime bizDate = vo.getBizDate();
        easJson.put("bizDate", bizDate);
        String unionBankId = vo.getUnionBankId();
        easJson.put("unionBankId", unionBankId);
        String bank = vo.getBank();
        easJson.put("bank", bank);
        String bankAccount = vo.getBankAccount();
        easJson.put("bankAccount", bankAccount);
        String taxerQua = vo.getTaxerQua();
        if (Util.isNotEmpty(taxerQua)) {
            if (taxerQua.contains("NOMAL")) {
                easJson.put("taxerQua", "一般纳税人");
            } else if (taxerQua.contains("SMALL")) {
                easJson.put("taxerQua", "小规模纳税人");
            } else if (taxerQua.contains("UNNOMAL")) {
                easJson.put("taxerQua", "非增值税纳税人");
            }
        }
        String taxerNumber = vo.getTaxerNumber();
        easJson.put("taxerNum", taxerNumber);
        String receiveUnitID = vo.getReceiveUnitID();
        easJson.put("receiveUnitId", receiveUnitID);
        String personId = vo.getPersonId();
        easJson.put("personId", personId);
        String settlementTypeId = vo.getSettlementTypeId();
        easJson.put("settlementTypeId", settlementTypeId);
        String applierId = vo.getApplierId();
        easJson.put("applierId", applierId);
        String useDeptId = vo.getUseDeptId();
        easJson.put("useDepartmentId", useDeptId);
        String costDeptId = vo.getCostDeptId();
        easJson.put("costedDeptId", costDeptId);
        String costCompanyId = vo.getCostCompanyId();
        easJson.put("costedCompanyId", costCompanyId);
        Integer isJT = vo.getIsJT();
        easJson.put("isJT", isJT);
        BigDecimal invoiceAMT = vo.getInvoiceAMT();
        easJson.put("invoiceAmt", invoiceAMT);
        BigDecimal rateAmount = vo.getRateAmount();
        easJson.put("rateAmount", rateAmount);
        String description = vo.getDescription();
        easJson.put("description",description);
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
        easJson.put("marketConArray", marketConArray);
//       费用清单
        JSONArray costArray = new JSONArray();
        List<CWTextBgVO> cwTextBgVOS = vo.getCwTextBgVOS();
        if (cwTextBgVOS != null && cwTextBgVOS.size() > 0) {
            for (CWTextBgVO cwTextBgVO : cwTextBgVOS) {
                JSONObject costObj = new JSONObject();
                costObj.put("amount", cwTextBgVO.getAmount());
                costObj.put("expenseTypeId", cwTextBgVO.getExpenseTypeId());
                costArray.add(costArray);
            }
        }
        easJson.put("bgArray", costArray);

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
        easJson.put("attach", attach);
        String result = null;
        Call call = null;
//        调用eas 保存方法进行保存
        if (Util.isEmpty(flag) || flag.compareTo(false) == 0) {
            call = getCall("EASURL", "saveContractwithouttext");
            try {
                System.out.println(easJson.toJSONString());
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
        String newNumber = sb.append("WEB").append(org).append(format).toString();

        return newNumber;
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
