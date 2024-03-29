package com.example.EAS.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.*;
import com.example.EAS.model.TConContractbill;
import com.example.EAS.model.TConMarketproject;
import com.example.EAS.model.TConMarketprojectcostentry;
import com.example.EAS.model.TFdcCurproject;
import com.example.EAS.service.ITConContractbillService;
import com.example.EAS.util.*;
import com.example.EAS.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.apache.axis.message.SOAPHeaderElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.rmi.RemoteException;
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
 * @since 2020-09-03
 */
@Slf4j
@Service
public class TConContractbillServiceImpl extends ServiceImpl<TConContractbillMapper, TConContractbill> implements ITConContractbillService {

    @Autowired
    private WSLoginUtil wsLoginUtil;
    @Autowired
    private TConContractbillMapper mapper;
    @Autowired
    private TConSupplierapplyMapper csMapper;
    @Autowired
    private TBasAttachmentMapper attachmentMapper;
    @Autowired
    private TConMarketprojectMapper marketProjectMapper;
    @Autowired
    private TConSupplierapplyMapper supplierapplyMapper;
    @Autowired
    private TFdcCurprojectMapper tFdcCurprojectMapper;

    private TConContractmarketentryMapper contractmarketentryMapper;
    @Autowired
    private TConMarketprojectcostentryMapper tConMarketprojectcostentryMapper;
    @Autowired
    private FtpUtil ftpUtil;
    @Autowired
    private OaIdUtil oaIdUtil;
    @Autowired
    private LoginInfoUtil loginInfoUtil;

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
    public PageBean<ContractVO> getContractList(ContractVO vo) throws Exception {
        log.info("合同列表查看开始");
        long startTime = System.currentTimeMillis();
        //获取登录信息
        JSONObject token = loginInfoUtil.getToken();
//        項目id集合      有父節點則是分期 沒有是項目 id防入集合
        List<String> projectIdList = new ArrayList<>();
        if (Util.isNotEmpty(vo.getProjectId())) {
            projectIdList.add(vo.getProjectId());
            String parentId = mapper.selectProject(vo.getProjectId());
            if (Util.isEmpty(parentId)) {
                List<String> ids = mapper.selectProjectIds(vo.getProjectId());
                projectIdList.addAll(ids);
            }
        }

        if (Util.isEmpty(projectIdList)) {
            return null;
        }
        vo.setProjectIds(projectIdList);
//        根据合同类型id 查询 子类id集合
        String conTypeId = vo.getConTypeId();
        List<String> conTypeIdList = new ArrayList<>();
        if (Util.isNotEmpty(conTypeId)) {
            conTypeIdList.add(conTypeId);
            List<String> conTypeIds = mapper.selectConTypeById(conTypeId);
            if (conTypeIds != null && conTypeIds.size() > 0) {
                conTypeIdList.addAll(conTypeIds);
            }
        }
        if (conTypeIdList != null && conTypeIdList.size() > 0) {
            vo.setConTypeIdList(conTypeIdList);
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
//        过权限
        Boolean aBoolean = loginInfoUtil.ifInItDept();
        if (aBoolean == false) {
            vo.setAuthorNum(token.getString("person"));
        }
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<ContractVO> contractVOList = mapper.selectDatas(vo);
        if (Util.isNotEmpty(contractVOList)) {
            for (ContractVO contractVO : contractVOList) {
                //       形成方式
                String csName = contractVO.getCsName();
                if (Util.isNotEmpty(csName)) {
                    if (csName.contains("TRUST")) {
                        contractVO.setCsName("委托");
                    }
                    if (csName.contains("INVITE")) {
                        contractVO.setCsName("招标");
                    }
                    if (csName.contains("DISCUSS")) {
                        contractVO.setCsName("仪标");
                    }
                    if (csName.contains("COOP")) {
                        contractVO.setCsName("战略合作");
                    }
                }
                contractVO.setHasFile(0);
                Integer attNums = contractVO.getAttNums();
                if (Util.isNotEmpty(attNums) && attNums.compareTo(0) == 1) {
                    contractVO.setHasFile(1);
                }
//                保存=1SAVED,已提交=2SUBMITTED,审批中=3AUDITTING,已审批=4AUDITTED,终止=5CANCEL,已下发=7ANNOUNCE,已签证=8VISA,
//                作废=9INVALID,已上报=10PUBLISH,被打回=11BACK,修订中=12REVISING,已修订=12REVISE,已确认=13CONFIRMED
                String state = contractVO.getState();
                if (state.contains("2SUB")) {
                    contractVO.setState("已提交");
                } else if (state.contains("1SAVED")) {
                    contractVO.setState("保存");
                } else if (state.contains("3AUD")) {
                    contractVO.setState("审批中");
                } else if (state.contains("4")) {
                    contractVO.setState("已审批");
                } else if (state.contains("5")) {
                    contractVO.setState("终止");
                } else if (state.contains("7")) {
                    contractVO.setState("已下发");
                } else if (state.contains("8")) {
                    contractVO.setState("已签证");
                } else if (state.contains("9")) {
                    contractVO.setState("作废");
                } else if (state.contains("10")) {
                    contractVO.setState("已上报");
                } else if (state.contains("11")) {
                    contractVO.setState("被打回");
                } else if (state.contains("12REVISING")) {
                    contractVO.setState("修订中");
                } else if (state.contains("12REVISE")) {
                    contractVO.setState("已修订");
                } else if (state.contains("13")) {
                    contractVO.setState("已确认");
                }
//                固定总价=COMP_COMFIRM,暂定总价=TEMP_EVAL,暂定总价转固定总价=BASE_CONFIRM
                String costProperty = contractVO.getCostProperty();
                if (Util.isNotEmpty(costProperty)) {
                    if (costProperty.contains("COMP_COMFIRM")) {
                        contractVO.setCostProperty("固定总价");
                    } else if (costProperty.contains("TEMP_EVAL")) {
                        contractVO.setCostProperty("暂定总价");
                    } else if (costProperty.contains("BASE_CONFIRM")) {
                        contractVO.setCostProperty("暂定总价转固定总价");
                    }
                }
            }
        }

        PageBean<ContractVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) contractVOList).getTotal());
        pageBean.setPageData(contractVOList);
        long endTime = System.currentTimeMillis();
        log.info("总耗时：" + (endTime - startTime) + "ms");
        return pageBean;
    }

    /**
     * 保存合同单据
     */
    @Override
    public ContractVO saveContractBill(ContractVO vo) {
        long startTime = System.currentTimeMillis();
        //获取登录信息
        JSONObject token = loginInfoUtil.getToken();
        String contractBillId = vo.getId();
//        总JSONOBJECT
        ContractVO contractVO = new ContractVO();
        JSONObject easJson = new JSONObject();
        //判断合约规划有没有被关联
        String hyghId = vo.getHyghId();
        if (Util.isNotEmpty(hyghId)) {
            easJson.put("hygh", hyghId);
            List<String> billIds = mapper.selectHYGHInContract(hyghId, contractBillId == null ? "" : contractBillId);
            if (billIds != null && billIds.size() > 0) {
                throw new ServiceException(UtilMessage.HYGH_HAS_BELINKED);
            }
        }
//        是否调用eas提交方法
        Boolean flag = vo.getFlag();
        //        根据是否携带单据id 判断新增 修改
        if (Util.isNotEmpty(contractBillId)) {
            easJson.put("id", contractBillId);
        }
        String conName = vo.getConName();
        if (Util.isNotEmpty(conName)) {
            easJson.put("conName", conName);
        }
        String projectId = vo.getProjectId();
        if (Util.isEmpty(projectId)) {
            throw new ServiceException(UtilMessage.MISS_PROJECT_INFO);
        }
        easJson.put("projectId", projectId);
        TFdcCurproject tFdcCurproject = tFdcCurprojectMapper.selectById(projectId);
        String fcostcenterid = tFdcCurproject.getFcostcenterid();
        if (Util.isNotEmpty(fcostcenterid)) {
            easJson.put("orgId", fcostcenterid);
        }
        String num = vo.getNum();
        if (Util.isNotEmpty(num)) {
            if (Util.isEmpty(contractBillId)) {
                List<TConContractbill> nums = mapper.selectList(new QueryWrapper<TConContractbill>()
                        .eq("fnumber", num));
                if (nums != null && nums.size() > 0) {
                    List<TConContractbill> conContractbills = mapper.selectList(new QueryWrapper<TConContractbill>()
                            .eq("fnumber", num)
                            .eq("fname", conName));
                    if (Util.isEmpty(conContractbills)) {
                        throw new ServiceException(901, UtilMessage.NUMBER_EXIST);
                    } else {
                        throw new ServiceException(900, UtilMessage.DATA_DOES_EXIST);
                    }
                }
            }
            easJson.put("num", num);
        }
        String conTypeId = vo.getConTypeId();
        if (Util.isNotEmpty(conTypeId)) {
            easJson.put("conTypeId", conTypeId);
        }
        String partA = vo.getPartA();
        if (Util.isNotEmpty(partA)) {
            easJson.put("partA", partA);
        }
        String partB = vo.getPartB();
        if (Util.isNotEmpty(partB)) {
            easJson.put("partB", partB);
        }
        String partC = vo.getPartC();
        if (Util.isNotEmpty(partC)) {
            easJson.put("partC", partC);
        }
        String taEntryId = vo.getTaEntryId();
        if (Util.isNotEmpty(taEntryId)) {
            easJson.put("taEntryId", taEntryId);
        }
        String marketProjectId = vo.getMarketProjectId();
        if (Util.isNotEmpty(marketProjectId)) {
            easJson.put("marketProjectId", marketProjectId);
        }
        Integer isjt = vo.getIsjt();
        if (isjt != null && isjt == 1) {
            easJson.put("isJT", "true");
        } else {
            easJson.put("isJT", "false");
        }
        if (Util.isNotEmpty(marketProjectId)) {
            TConMarketprojectcostentry contractmarketentry =
                    tConMarketprojectcostentryMapper.selectOne(new QueryWrapper<TConMarketprojectcostentry>()
                            .eq("FHEADID", marketProjectId)
                            .eq("FTYPE", "CONTRACT")
                            .eq("fcostaccountid", vo.getCostAccountId() == null ? null : vo.getCostAccountId()));

//            营销立项金额控制合同金额
            if (Util.isNotEmpty(contractmarketentry)) {
                Double famount = contractmarketentry.getFamount();
                BigDecimal voAmount = vo.getAmount();
                if (Util.isNotEmpty(famount) && Util.isNotEmpty(voAmount)) {
                    BigDecimal famountDe = new BigDecimal(famount);
                    BigDecimal voAmountDe = voAmount;
                    if (voAmountDe.compareTo(famountDe) == 1) {
                        throw new ServiceException(UtilMessage.CONTRACT_AMOUNT_BEYOND);
                    }
                }
            }
        }
//       费用归属 需要验证是否被关联过  关联关系： 合同关联立项 以及组织，获取对应的费用归属信息  选择其中一个
//       合同费用归属为一对一 且被关联的费用归属科目不可再被关联  费用归属区分 合同 跟无文本合同 两个类型
        String costAccountId = vo.getCostAccountId();
        if (Util.isNotEmpty(costAccountId)) {
            easJson.put("costAccountId", costAccountId);
        }
        String currencyId = vo.getCurrencyId();
        if (Util.isNotEmpty(currencyId)) {
            easJson.put("currencyId", currencyId);
        }
        BigDecimal originalAmount = vo.getOriginalAmount();
        if (Util.isNotEmpty(originalAmount)) {
            easJson.put("originalAmount", originalAmount.toString());
        }
        String originalAmountBIG = vo.getOriginalAmountBIG();
        BigDecimal amount = vo.getAmount();
        if (Util.isNotEmpty(amount)) {
            easJson.put("amount", amount.toString());
        }
        String amountBIG = vo.getAmountBIG();
        BigDecimal exRate = vo.getExRate();
        if (Util.isNotEmpty(exRate)) {
            easJson.put("exRate", exRate.toString());
        }
        LocalDateTime bizDate = vo.getBizDate();
        if (Util.isNotEmpty(bizDate)) {
            easJson.put("bizDate", bizDate);
        }
        BigDecimal grtAmount = vo.getGrtAmount();
        if (Util.isNotEmpty(grtAmount)) {
            easJson.put("grtAmount", grtAmount.toString());
        }
        BigDecimal grtRate = vo.getGrtRate();
        if (Util.isNotEmpty(grtRate)) {
            easJson.put("grtRate", grtRate.toString());
        }
//        是否进入动态成本
        Integer isCost = vo.getIsCost();
        if (Util.isNotEmpty(isCost) && isCost == 0) {
            easJson.put("isCoseSplit", "false");
        } else {
            easJson.put("isCoseSplit", "true");
        }
        String csName = vo.getCsName();
        if (Util.isNotEmpty(csName)) {
            String contractSourceId = mapper.selectContractSourceId(csName);
            if (Util.isNotEmpty(contractSourceId)) {
                easJson.put("contractSourceId", contractSourceId);
            }
        }
        LocalDateTime startDate = vo.getStartDate();
        if (Util.isNotEmpty(startDate)) {
            String format = df.format(startDate);
            easJson.put("startDate", format);
        }
        LocalDateTime endDate = vo.getEndDate();
        if (Util.isNotEmpty(endDate)) {
            String format = df.format(endDate);
            easJson.put("endDate", format);
        }
        String contractNature = vo.getContractNature();
        String person = vo.getPerson();
        String personId = null;
        if (person != null) {
            personId = mapper.selectPersonByNum(person);
            easJson.put("personId", personId);
        }
        String dept = vo.getDept();
        String deptId = null;
        if (dept != null) {
            deptId = mapper.selectDeptByNum(dept);
            easJson.put("deptId", deptId);
        }
        if (Util.isNotEmpty(contractNature)) {
            easJson.put("contractNature", contractNature);
        }
        String contractWFTypeId = vo.getContractWFTypeId();
        if (Util.isNotEmpty(contractWFTypeId)) {
            easJson.put("contractWFTypeId", contractWFTypeId);
        }
//        合同流程发起类型 前端写死 后端存值
        String contractWFStartType = vo.getContractWFStartType();
        if (Util.isNotEmpty(contractWFStartType)) {
            easJson.put("contractWFStartType", contractWFStartType);
        }
//        获取当前登录人
        String creator = token.getString("person");
        String creatorId = null;
        if (Util.isNotEmpty(creator)) {
            creatorId = mapper.selectUserByNum(creator);
            easJson.put("creatorId", creatorId);
        }
        String creatDept = vo.getCreatDept();
        String createDeptId = null;
        if (creatDept != null) {
            createDeptId = mapper.selectDeptByNum(creatDept);
            easJson.put("createDeptId", createDeptId);
        }
        String description = vo.getDescription();
        if (Util.isNotEmpty(description)) {
            easJson.put("description", description);
        }
        LocalDateTime createTime = LocalDateTime.now();
        String format = df.format(createTime);
        easJson.put("createTime", format);

//      保存到EAS附件
        JSONArray attach = new JSONArray();
        List<AttachmentsVO> attachmentsVOS = vo.getAttachmentsVOS();
        if (attachmentsVOS != null && attachmentsVOS.size() > 0) {
            for (AttachmentsVO attachmentsVO : attachmentsVOS) {
                JSONObject object = new JSONObject();
                String attachNum = attachmentsVO.getNum();
                String webUrl = attachmentsVO.getWebUrl();
                String title = attachmentsVO.getTitle();
                if (title.contains(".")) {
                    title = title.replace("." + title.split("\\.")[title.split("\\.").length - 1], "");
                }
                String fileSize = attachmentsVO.getFileSize();
                String descp = attachmentsVO.getDescription();
                String originalFilename = attachmentsVO.getOriginalFilename() == null ? attachmentsVO.getTitle() : attachmentsVO.getOriginalFilename();
                StringBuffer stringBuffer = new StringBuffer();
                object.put("FName", title == null ? null : title);//文件名称不含含后缀
                object.put("FNumber", attachNum == null ? "upLoadFromEas" : attachNum);//附件编码
                object.put("FRemotePath", webUrl == null ? null : webUrl);//文件相对路径
                object.put("FSize", fileSize == null ? null : fileSize);// 附件大小
                object.put("FDescription", descp == null ? "EAS" : descp);//附件来源类型
                object.put("FCreatorId", creatorId == null ? null : creatorId); //创建人id
                attach.add(object);
            }
        }
        if (attach != null && attach.size() > 0) {
            easJson.put("attach", attach);
        }
//       税务信息+收款信息
        String bank = vo.getBank();
        if (Util.isNotEmpty(bank)) {
            easJson.put("bank", bank);
        }
        String bankAccount = vo.getBankAccount();
        if (Util.isNotEmpty(bankAccount)) {
            easJson.put("bankAccount", bankAccount);
        }
        String taxNum = vo.getTaxNum();
        if (Util.isNotEmpty(taxNum)) {
            easJson.put("taxerNum", taxNum);
        }
        String taxQua = vo.getTaxQua();
        if (Util.isNotEmpty(taxQua)) {
            easJson.put("taxerQua", taxQua);
        }
        String unionBankNum = vo.getUnionBankNum();
        if (Util.isNotEmpty(unionBankNum)) {
            String unionBankId = mapper.selectUnionBankId(unionBankNum);
            easJson.put("unionBankId", unionBankId);
        }
//      合同签订明细  含税金额总和不能大于合同原币金额
        List<ContractSignDetailVO> detailVOS = vo.getDetailSignVOS();
        JSONArray objects = new JSONArray();
//        总的明细含税金额
        BigDecimal allDetailAmount = BigDecimal.ZERO;
        if (Util.isNotEmpty(detailVOS)) {
            for (ContractSignDetailVO detailVO : detailVOS) {
                JSONObject json = new JSONObject();
                String detail = detailVO.getDetail();
                json.put("detail", detail);
                String totalAmount = detailVO.getTotalAmount();
                json.put("totalAmount", totalAmount);
                String voAmount = detailVO.getAmount();
                json.put("amount", voAmount);
                String voDescription = detailVO.getDescription();
                json.put("description", voDescription);
                String rate = detailVO.getRate();
                json.put("rate", rate);
                objects.add(json);
                allDetailAmount.add(new BigDecimal(totalAmount));
            }
        }
//        判断金额
        if (Util.isNotEmpty(originalAmount)) {
            BigDecimal bigDecimal = originalAmount;
            if (allDetailAmount.compareTo(bigDecimal) == 1) {
                throw new ServiceException(UtilMessage.DETAIL_AMOUNT_BEYOND);
            }
        }
        easJson.put("signDetailArray", objects);
//      合同详情信息
        List<ContractDetailVO> detailVOList = vo.getDetailVOList();
        JSONArray details = new JSONArray();
        if (Util.isNotEmpty(detailVOList)) {
            for (ContractDetailVO contractDetailVO : detailVOList) {
                JSONObject json = new JSONObject();
                String detailInfo = contractDetailVO.getDetailInfo();
                json.put("detailInfo", detailInfo);
                String content = contractDetailVO.getContent();
                json.put("content", content);
                String desp = contractDetailVO.getDescription();
                json.put("description", desp);
                details.add(json);
            }
        }
        easJson.put("conDetailArray", details);
//      补充合同信息  在合同审批之后才可 新增补充合同
        List<ContractAddVO> contractAddVOS = vo.getContractAddVOS();
        if (contractAddVOS != null && contractAddVOS.size() > 0) {
            mapper.insertAddContract(contractAddVOS);
        }
//      营销合同分摊明细 分摊比例加起来必须百分百
        List<MarketContDetailVO> marketContDetailVOS = vo.getMarketContDetailVOS();
        JSONArray jsonArray = new JSONArray();
        if (Util.isNotEmpty(marketContDetailVOS)) {
            BigDecimal totalRate = BigDecimal.ZERO;
            for (MarketContDetailVO marketContDetailVO : marketContDetailVOS) {
                JSONObject json = new JSONObject();
                String date = marketContDetailVO.getFsdate();
                json.put("date", date);
                String amount1 = marketContDetailVO.getAmount();
                json.put("amount", amount1);
                String content = marketContDetailVO.getContent();
                json.put("content", content);
                String remark = marketContDetailVO.getRemark();
                json.put("remark", remark);
                String rate = marketContDetailVO.getRate();
                json.put("rate", rate);
                if (Util.isNotEmpty(rate)) {
                    BigDecimal bigDecimal1 = new BigDecimal(rate).divide(new BigDecimal("100"));
                    totalRate = totalRate.add(bigDecimal1);
                }
                jsonArray.add(json);
            }
//            根据合同类型 是否需要分摊明细 去判断是否验证比例和为1
            if (Util.isNotEmpty(vo.getIsMarket()) && vo.getIsMarket() == 1 && totalRate.compareTo(BigDecimal.ONE) != 0) {
                throw new ServiceException(UtilMessage.TOTAL_RATE_NOT_ONE);
            }
        }
        easJson.put("marketConArray", jsonArray);

        JSONObject login = wsLoginUtil.login();//        登录
        String sessionId = login.getString("sessionId");
        Call call = (Call) login.get("call");

        String result = null;
        if (Util.isNotEmpty(sessionId)) {
            //清理
            call.clearOperation();
            String url = supplierapplyMapper.selectEASURL();
            if (Util.isEmpty(flag) || flag.compareTo(false) == 0) {
                call.setOperationName("saveContractBill");    //接口方法
            } else {
                call.setOperationName("submitContractBill");    //接口方法
            }
            call.setTargetEndpointAddress(url);   //对应接口地址
            call.addParameter("arg0", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            call.setTimeout(Integer.valueOf(1000 * 600000 * 60));
            call.setMaintainSession(true);
            call.setUseSOAPAction(true);
            SOAPHeaderElement header = new SOAPHeaderElement("http://login.webservice.bos.kingdee.com", "SessionId", sessionId);
            call.addHeader(header);
            // TODO call.invoke
        }
//        wsLoginUtil.logout(call);//登出
//        接收返回eas信息
        JSONObject object = JSONObject.parseObject(result);
        if (result != null && object.get("result") != null) {
            if (object.get("result").toString().contains("fault")) {
                //            contractVO.setResult("fault");
                throw new ServiceException(object.getString("message") == null ? "保存失败" : object.getString("message"));
            } else if (object.get("result").toString().contains("success")) {
                String id = object.getString("id");
                contractBillId = id;
                contractVO.setId(id);
//            contractVO.setResult("success");
                if (Util.isEmpty(vo.getId())) {
                    TConContractbill tConContractbill = mapper.selectById(id);
                    tConContractbill.setFcreatorid(creatorId);
                    mapper.updateById(tConContractbill);
                }
            }
        }
        return contractVO;
    }

    @Override
    public ContractVO viewContractBill(ContractVO vo) throws Exception {
        String id = vo.getId();
        if (Util.isEmpty(id)) {
            return null;
        }
        ContractVO contractVO = mapper.viewContractVO(vo);
        if (Util.isEmpty(contractVO)) {
            return null;
        }
        String foaposition = contractVO.getFoaposition();
//        if (Util.isNotEmpty(foaposition)) {
//            String identityId = foaposition.split(";")[0];
//            String identityName = foaposition.split(";")[foaposition.split(";").length-1];
//            contractVO.setIdentityId(identityId);
//            contractVO.setIdentityName(identityName);
//        }
        Integer isMarket = contractVO.getIsMarket();
        if (Util.isEmpty(isMarket)) {
            contractVO.setIsMarket(0);
        }
        //获取登录信息
        JSONObject token = loginInfoUtil.getToken();

        //                保存=1SAVED,已提交=2SUBMITTED,审批中=3AUDITTING,已审批=4AUDITTED,终止=5CANCEL,已下发=7ANNOUNCE,已签证=8VISA,
//                作废=9INVALID,已上报=10PUBLISH,被打回=11BACK,修订中=12REVISING,已修订=12REVISE,已确认=13CONFIRMED
        String state = contractVO.getState();
        if (state.contains("2SUB")) {
            contractVO.setState("已提交");
        } else if (state.contains("1SAVED")) {
            contractVO.setState("保存");
        } else if (state.contains("3AUD")) {
            contractVO.setState("审批中");
        } else if (state.contains("4")) {
            contractVO.setState("已审批");
        } else if (state.contains("5")) {
            contractVO.setState("终止");
        } else if (state.contains("7")) {
            contractVO.setState("已下发");
        } else if (state.contains("8")) {
            contractVO.setState("已签证");
        } else if (state.contains("9")) {
            contractVO.setState("作废");
        } else if (state.contains("10")) {
            contractVO.setState("已上报");
        } else if (state.contains("11")) {
            contractVO.setState("被打回");
        } else if (state.contains("12REVISING")) {
            contractVO.setState("修订中");
        } else if (state.contains("12REVISE")) {
            contractVO.setState("已修订");
        } else if (state.contains("13")) {
            contractVO.setState("已确认");
        }
        //                    获取对应的oaid
        String oaid = null;
        List<String> oaids = supplierapplyMapper.selectOaid(id);
//        判断是否在eas里提交的
        TConContractbill conContractbill = mapper.selectById(id);
        if (Util.isNotEmpty(conContractbill)) {
            String fsourcefunction = conContractbill.getFsourcefunction();
            if (Util.isNotEmpty(fsourcefunction)) {
                oaid = fsourcefunction;
            } else if (oaids != null && oaids.size() > 0) {
                oaid = oaids.get(0);
            }
        }
        if (Util.isNotEmpty(oaid)) {
//            获取当前登录信息 取用户账号用作oa流程查看登录
            String person = token.getString("person");
            String mtLoginNum = OaUtil.encrypt(person);

//          返回oa流程link
//          http://122.224.88.138:58080/km/review/km_review_main/
//          kmReviewMain.do?method=view&fdId=173c6b9e6dd55fccb9a0be942b2b074d&MtFdLoinName
//          =gdjjXmldhhTqgDyrFOTunA==
            String s1 = supplierapplyMapper.selectOAINFO();
            String s2 = "&MtFdLoinName=";
            StringBuffer stringBuffer = new StringBuffer();
            oaid = URLEncoder.encode(oaid);
            String link = String.valueOf(stringBuffer.append(s1).append(oaid).append(s2).append(mtLoginNum));
            log.info("OA流程路径：" + link);
            contractVO.setLink(link);
            contractVO.setOaId(oaid);
        }
        //       审批流程发起组织
        //    集团/事业部/城市公司=BIGRANGE,项目部=SMALLRANGE,集团/事业部/城市公司-项目部=ALLRANGE,内部关联公司往来类=NEIBU,
        //    外部供应商客户往来类=WAIBU
        String contractWFStartType = contractVO.getContractWFStartType();
        if (Util.isNotEmpty(contractWFStartType)) {
            if (contractWFStartType.contains("BIGRANGE")) {
                contractVO.setContractWFStartType("集团/事业部/城市公司");
            } else if (contractWFStartType.contains("SMALLRANGE")) {
                contractVO.setContractWFStartType("项目部");
            } else if (contractWFStartType.contains("ALLRANGE")) {
                contractVO.setContractWFStartType("集团/事业部/城市公司-项目部");
            } else if (contractWFStartType.contains("NEIBU")) {
                contractVO.setContractWFStartType("内部关联公司往来类");
            } else if (contractWFStartType.contains("WAIBU")) {
                contractVO.setContractWFStartType("外部供应商客户往来类");
            }
        }
//       形成方式
        String csName = contractVO.getCsName();
        if (Util.isNotEmpty(csName)) {
            if (csName.contains("TRUST")) {
                contractVO.setCsName("委托");
            }
            if (csName.contains("INVITE")) {
                contractVO.setCsName("招标");
            }
            if (csName.contains("DISCUSS")) {
                contractVO.setCsName("仪标");
            }
            if (csName.contains("COOP")) {
                contractVO.setCsName("战略合作");
            }
        }
//        造价性质
        String costProperty = contractVO.getCostProperty();
        if (Util.isNotEmpty(costProperty)) {
            if (costProperty.contains("COMP_COMFIRM")) {
                contractVO.setCostProperty("固定总价");
            }
            if (costProperty.contains("COMP_COMFIRM")) {
                contractVO.setCostProperty("固定总价");
            }
            if (costProperty.contains("COMP_COMFIRM")) {
                contractVO.setCostProperty("固定总价");
            }
            if (costProperty.contains("COMP_COMFIRM")) {
                contractVO.setCostProperty("固定总价");
            }
        }
//        合同性质
        String contractNature = contractVO.getContractNature();
        if (Util.isNotEmpty(contractNature)) {
            if (contractNature.contains("DIRECT")) {
                contractVO.setContractNature("直接合同");
            }
            if (contractNature.contains("THREE_PARTY")) {
                contractVO.setContractNature("三方合同");
            }
            if (contractNature.contains("SUPPLY")) {
                contractVO.setContractNature("补充合同");
            }
            if (contractNature.contains("STRATEGY")) {
                contractVO.setContractNature("战略协议");
            }
        }
//       纳税人资质 一般纳税人=NOMAL,小规模纳税人=SMALL,非增值税纳税人=UNNOMAL
        String taxerQua = contractVO.getTaxQua();
        if (Util.isNotEmpty(taxerQua)) {
            if (taxerQua.contains("NOMAL")) {
                contractVO.setTaxQua("一般纳税人");
            } else if (taxerQua.contains("SMALL")) {
                contractVO.setTaxQua("小规模纳税人");
            } else if (taxerQua.contains("UNNOMAL")) {
                contractVO.setTaxQua("非增值税纳税人");
            }
        }
//        合同签订明细
        List<ContractSignDetailVO> contractSignDetailVOS = mapper.selectSignInfos(vo.getId());
        if (Util.isNotEmpty(contractSignDetailVOS)) {
            contractVO.setDetailSignVOS(contractSignDetailVOS);
        }
//        合同详情信息
        List<ContractDetailVO> detailVOS = mapper.selectDetails(vo.getId());
        if (Util.isNotEmpty(detailVOS)) {
            for (ContractDetailVO detailVO : detailVOS) {
                String detailInfo = detailVO.getDetailInfo();
                if (Util.isNotEmpty(detailInfo) && detailInfo.contains("对应主合同编码")) {
                    String content = detailVO.getContent();
                    if (Util.isNotEmpty(content)) {
                        TConContractbill tConContractbill = mapper.selectById(content);
                        if (Util.isNotEmpty(tConContractbill)) {
                            String fnumber = tConContractbill.getFnumber();
                            detailVO.setContent(fnumber == null ? null : fnumber);
                        }
                    }
                }
            }
            contractVO.setDetailVOList(detailVOS);
        }
//        附件信息
        List<AttachmentsVO> attachmentsVOS = attachmentMapper.selectAttachMent(vo.getId());
        if (attachmentsVOS != null && attachmentsVOS.size() > 0) {
            for (AttachmentsVO easAttFile : attachmentsVOS) {
                String fileType = easAttFile.getFileType();
                if (Util.isNotEmpty(fileType)) {
                    String s = FileContentTypeUtils.contentType("." + fileType);
                    easAttFile.setContentType(s);
                }
            }
            contractVO.setAttachmentsVOS(attachmentsVOS);
        }

//        补充合同信息
        TConContractbill tConContractbill1 = mapper.selectById(vo.getId());
        String fnumber = tConContractbill1.getFnumber();
        List<ContractAddVO> contractAddVOS = mapper.selectContractAdds(fnumber);
        if (Util.isNotEmpty(contractAddVOS)) {
            contractVO.setContractAddVOS(contractAddVOS);
        }

        //        是否后评估审核
        Integer isjt = contractVO.getIsjt();
        if (Util.isEmpty(isjt)) {
            contractVO.setIsjt(0);
        }
//        根据营销立项中的后评估审核带出
        String marketProjectId = contractVO.getMarketProjectId();
        if (Util.isNotEmpty(marketProjectId)) {
            TConMarketproject mp = marketProjectMapper.selectOne(new QueryWrapper<TConMarketproject>()
                    .eq("FID", marketProjectId));
            Long fisjt = mp.getFisjt();
            if (Util.isEmpty(fisjt)) {
                contractVO.setIsjt(0);
            } else {
                contractVO.setIsjt(Math.toIntExact(mp.getFisjt()));
            }
        }
//        营销合同分摊明细
        List<MarketContDetailVO> marketContDetailVOS = mapper.selectMarketCons(vo.getId());
        TConContractbill tConContractbill = mapper.selectById(vo.getId());
        Long fisjt = tConContractbill.getFisjt();
        if (Util.isNotEmpty(fisjt) && fisjt == 1) {
            contractVO.setIsjt(1);
        }
        if (marketContDetailVOS != null && marketContDetailVOS.size() > 0) {
            contractVO.setMarketContDetailVOS(marketContDetailVOS);
        }
        return contractVO;
    }

    @Override
    public List<ContractDetailVO> getContractDetails(ContractDetailVO vo) {
        String contractTypeId = vo.getContractTypeId();
        if (Util.isEmpty(contractTypeId)) {
            return null;
        }
        List<ContractDetailVO> detailVOS = mapper.selectConDetailsByCT(contractTypeId);
        if (Util.isEmpty(detailVOS)) {
            return null;
        }
        return detailVOS;
    }

    /**
     * submit to oa  system
     *
     * @param vo
     */
    @Override
    public ContractVO submitToOa(ContractVO vo) {
        //获取登录信息
        JSONObject token = loginInfoUtil.getToken();
        ContractVO contractVO = new ContractVO();
        String id = vo.getId();
        vo.setFlag(true);

        ContractVO contractVO1 = saveContractBill(vo);
        if (contractVO1.getId() != null) {
            id = contractVO1.getId();
        }
        String saveResult = contractVO1.getResult();
        if (Util.isNotEmpty(saveResult) && saveResult.contains("fault")) {
            throw new ServiceException(UtilMessage.SUBMIT_FAULT);
        }
//      判断是否提交过被驳回  需要携带oaid
        JSONObject obj = new JSONObject();
        String oaId = null;
        List<String> oaIds = supplierapplyMapper.selectOaid(id);
        if (oaIds != null && oaIds.size() > 0) {
            oaId = oaIds.get(0);
        }
//      基本参数
        obj.put("id", id);
        String tId = supplierapplyMapper.selectTemplateId("contract");
        obj.put("tmplateId", tId);
        obj.put("fdType", "1");
        obj.put("docSubject", vo.getConName());
        //        表单参数
        JSONObject data = new JSONObject();
        String identityId = vo.getIdentityId();
        String identityName = vo.getIdentityName();
//        obj.put("fd_application", identityId); //职位参数放data外
        if (Util.isNotEmpty(identityId) && Util.isNotEmpty(identityName) && Util.isNotEmpty(id)) {
            StringBuffer stringBuffer = new StringBuffer();
            String foaposition = stringBuffer.append(identityId).append(";" + identityName).toString();
            mapper.updatePersonPost(id, foaposition);
        }
//       合同流程类型id
        if (Util.isNotEmpty(vo.getContractWFTypeId())) {
            String contractWFTypeName = mapper.selectContractType(vo.getContractWFTypeId());
            data.put("fd_38cf1780c1c14a", contractWFTypeName);
        }
//        后评估审核
        if (Util.isNotEmpty(vo.getMarketProjectId())) {
            TConMarketproject tConMarketproject = marketProjectMapper.selectById(vo.getMarketProjectId());
            Long fisjt = tConMarketproject.getFisjt();
            if (Util.isEmpty(fisjt) || fisjt == 0) {
                data.put("fd_38f672bcb4a998", "否");
            } else {
                data.put("fd_38f672bcb4a998", "是");
            }
        }
//        原币金额
        if (Util.isNotEmpty(vo.getOriginalAmount())) {
            Double aDouble = Double.valueOf(String.valueOf(vo.getOriginalAmount()));
            data.put("fd_38cf1798043f94", aDouble);
        }
//        审批流程发起组织
        data.put("fd_38cf17bb650026", vo.getContractWFStartType());
//        是否使用电子章
        List<ContractDetailVO> detailVOList = vo.getDetailVOList();
        if (detailVOList != null && detailVOList.size() > 0) {
            for (ContractDetailVO contractDetailVO : detailVOList) {
                String detailInfo = contractDetailVO.getDetailInfo();
                String content = contractDetailVO.getContent();
                if (Util.isNotEmpty(detailInfo) && Util.isNotEmpty(content)) {
                    if (detailInfo.contains("电子章")) {
                        data.put("fd_38f02d7df82f3e", content);
                    }
                }
            }
        }
        String personNum = token.getString("person");
        PersonsVO person = supplierapplyMapper.selectCreator(personNum);

        //        http://172.17.4.125:8082/easWeb/#/
        //        http://172.17.4.125:8082/easApp/#/
        String sendUrl = null;
        String sendAppUrl = null;
        StringBuffer sbv = new StringBuffer();
        StringBuffer sba = new StringBuffer();
        String appendUrl = supplierapplyMapper.selectViewUrl();
        String appUrl = supplierapplyMapper.selectAppUrl();
//					审批单 approval
        if (Util.isEmpty(appendUrl) || Util.isEmpty(appUrl)) {
            throw new ServiceException(UtilMessage.VIEW_URL_NOT_FOUND);
        }
        String appendType = "contract/view?from=oa&id=";
        String appAppendType = "contract/?from=oa&id=";
        String appendId = null;
        try {
            appendId = URLEncoder.encode(id, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//					token
        String tokenAppend = RequestHolder.getCurrentUser().getToken();
        try {
            tokenAppend = URLEncoder.encode(tokenAppend, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String appendToken = "&token=";
//        web端详情查看地址
        sendUrl = String.valueOf(sbv.append(appendUrl).append(appendType).append(appendId)
                .append(appendToken).append(tokenAppend));
//        app端详情查看地址
        sendAppUrl = String.valueOf(sba.append(appUrl).append(appAppendType).append(appendId)
                .append(appendToken).append(tokenAppend));
//        sb.append("http://172.17.4.125:8082/easWeb/#/supplier").append("?token=").append(token);
        log.info("合同单据web端详情查看地址：" + sendUrl);
        log.info(" 合同单据app端详情查看地址：" + sendAppUrl);
        obj.put("loginName", personNum);

        obj.put("fdPcViewLink", sendUrl);
        obj.put("fdMobileViewLink", sendAppUrl);
//        data.put("createTime", vo.getCreateTime());
        obj.put("data", data.toString());

        //                附件参数
        JSONArray attFile = new JSONArray();
        List<AttachmentsVO> easFiles = attachmentMapper.selectAttachMent(id);
        if (easFiles != null && easFiles.size() > 0) {
            for (AttachmentsVO easFile : easFiles) {
                JSONObject attObj = new JSONObject();
                String title = easFile.getTitle();
                String webUrl = easFile.getWebUrl();
                String fileType = easFile.getFileType();
                if (Util.isNotEmpty(title) && title.contains(".")) {//oa预览需要后缀名文件名，拼接
                    String hz = title.split("\\.")[title.split("\\.").length - 1];
                    title = title.replace("." + hz, "");
                }
                if (Util.isNotEmpty(fileType)) {
                    title = new StringBuffer().append(title).append("." + fileType).toString();
                }
                attObj.put("filename", title);
                attObj.put("filepath", webUrl);
                attObj.put("fileType", "01");
                attFile.add(attObj);
            }
        }
        obj.put("attFile", attFile);
        //        当当前流程未提交时 oaidrecord没有对应oaid 调用oa新增提交方法
        String result = null;
        JSONObject str = null;
        if (Util.isEmpty(oaId)) {
            Call call = getCall("OAURL", "addtestEkpReview");
            try {
                result = (String) call.invoke(new Object[]{obj.toString()});
                log.info(vo.getConName() + "本次提交传给oa的参数" + obj.toString());
                str = JSONObject.parseObject(result);
                log.info("这次是新增流程：" + str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Call call = getCall("OAURL", "updatetestEkpReview");
            try {
                obj.put("id", oaId);
                result = (String) call.invoke(new Object[]{obj.toString()});
                log.info(vo.getConName() + "本次提交传给oa的参数" + obj.toString());
                str = JSONObject.parseObject(result);
                log.info("这次是修改流程：" + str + "原流程id" + oaId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
//        code 1 success 2 fault
        String code = str.getString("code");
        String oaid = str.getString("oaid");
        if (Util.isNotEmpty(oaid) && Util.isNotEmpty(id)) {
            oaIdUtil.getString(id, oaid);
            TConContractbill conContractbill = mapper.selectById(id);
            conContractbill.setFsourcefunction(oaid);
            mapper.updateById(conContractbill);
        }
//       成功提交后，修改eas当前状态为审批中0
        if (code != null && code.contains("1")) {
//            修改单据状态为审批中
            TConContractbill tConContractbill = mapper.selectById(id);
            tConContractbill.setFstate("3AUDITTING");
            mapper.updateById(tConContractbill);
//            获取返回的附件查看路径   预览
            JSONArray attUrlArray = str.getJSONArray("atturl");
            if (attUrlArray != null && attUrlArray.size() > 0) {
////               附件预览地址
                for (int i = 0; i < attUrlArray.size(); i++) {
                    JSONObject atturlObj = attUrlArray.getJSONObject(i);
                    String attName = atturlObj.getString("name");
                    if (Util.isNotEmpty(attName) && attName.contains(".")) {
                        String hz = attName.split("\\.")[attName.split("\\.").length - 1];
                        attName = attName.replace("." + hz, "");
                    }
                    String atturl = atturlObj.getString("url");
                    log.info("附件返回地址" + attName + ":" + atturl);
//                 取用户账号用作oa流程查看登录
                    if (Util.isEmpty(personNum)) {
                        throw new ServiceException(UtilMessage.PERSON_MISSING);
                    }
                    try {
                        String s2 = "&MtFdLoinName=";
                        String mtLoginNum = OaUtil.encrypt(personNum);
                        String attLink = new StringBuffer().append(atturl).append(s2).append(mtLoginNum).toString();
                        attachmentMapper.updateAttLink(id, attName, attLink);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            if (str.getString("massage") != null) {
                throw new ServiceException(str.getString("massage"));
            } else {
                throw new ServiceException(UtilMessage.SUBMIT_FAULT);
            }
        }
        return contractVO;
    }

    @Override
    public void deleteContractBills(ContractVO vo) {
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
        Call call = getCall("EASURL", "deleteContractBill");
        String result = null;
        try {
            result = (String) call.invoke(new Object[]{jsonObject.toString()});
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ContractVO> getMainContractNums(ContractVO vo) {
        if (Util.isEmpty(vo.getOrgId()) || Util.isEmpty(vo.getProjectId())) {
            return null;
        } else if (Util.isEmpty(vo.getConTypeId())) {
            throw new ServiceException(UtilMessage.MISS_CONTRACTTYPE);
        }
        List<ContractVO> contracts = mapper.selectMainNums(vo);
        return contracts;
    }

}
