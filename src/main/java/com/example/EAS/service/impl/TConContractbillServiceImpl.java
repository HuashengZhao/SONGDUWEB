package com.example.EAS.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.mapper.TConContractbillMapper;
import com.example.EAS.mapper.TConMarketprojectMapper;
import com.example.EAS.mapper.TConSupplierapplyMapper;
import com.example.EAS.model.TConContractbill;
import com.example.EAS.model.TConMarketproject;
import com.example.EAS.service.ITConContractbillService;
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
@Service
public class TConContractbillServiceImpl extends ServiceImpl<TConContractbillMapper, TConContractbill> implements ITConContractbillService {

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
    private FtpUtil ftpUtil;
    @Autowired
    private OaIdUtil oaIdUtil;
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    org.apache.axis.client.Service service = new org.apache.axis.client.Service();

    //    String url = "http://127.0.0.1:8088/ormrpc/services/WSOAContractFacade";
    String url = "http://172.17.4.69:6888/ormrpc/services/WSOAContractFacade";
//    String url = "http://2x743c4002.zicp.vip:37492/ormrpc/services/WSOAContractFacade";

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
//        項目id集合      有父節點則是分期 沒有是項目 id防入集合
        List<String> projectIdList = new ArrayList<>();
        if (Util.isNotEmpty(vo.getProjectId())) {
            String parentId = mapper.selectProject(vo.getProjectId());
            if (Util.isNotEmpty(parentId)) {
                projectIdList.add(vo.getProjectId());
            } else {
                List<String> ids = mapper.selectProjectIds(vo.getProjectId());
                projectIdList.addAll(ids);
            }
        }
        if (projectIdList != null && projectIdList.size() > 0) {
            vo.setProjectIds(projectIdList);
        }
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
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<ContractVO> contractVOList = mapper.selectDatas(vo);
        if (Util.isNotEmpty(contractVOList)) {
            for (ContractVO contractVO : contractVOList) {
                //                查看是否有附件
                contractVO.setHasFile(0);
                Call call = getCall("EASURL", "ifHasAttachFile");
                JSONObject data = new JSONObject();
                data.put("contractBillId", contractVO.getId() == null ? null : contractVO.getId());
                String result = (String) call.invoke(new Object[]{data.toString()});
                JSONObject str = JSONObject.parseObject(result);
                String file = str.getString("file");
                if (file != null && file.contains("T")) {
                    contractVO.setHasFile(1);
                }
//                保存=1SAVED,已提交=2SUBMITTED,审批中=3AUDITTING,已审批=4AUDITTED,终止=5CANCEL,已下发=7ANNOUNCE,已签证=8VISA,
//                作废=9INVALID,已上报=10PUBLISH,被打回=11BACK,修订中=12REVISING,已修订=12REVISE,已确认=13CONFIRMED
                String state = contractVO.getState();
                if (state.contains("2")) {
                    contractVO.setState("已提交");
                } else if (state.contains("1")) {
                    contractVO.setState("保存");
                } else if (state.contains("3")) {
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
        return pageBean;
    }

    /**
     * 保存合同单据
     */
    @Override
    public ContractVO saveContractBill(ContractVO vo) {
//        总JSONOBJECT
        ContractVO contractVO = new ContractVO();
        JSONObject easJson = new JSONObject();
        //        根据是否携带单据id 判断新增 修改
        String contractBillId = vo.getId();
        easJson.put("id", contractBillId);
        String orgId = vo.getOrgId();
        easJson.put("orgId", orgId);
        String projectId = vo.getProjectId();
        easJson.put("projectId", projectId);
        String conName = vo.getConName();
        easJson.put("conName", conName);
        String num = vo.getNum();
        easJson.put("num", num);
        String conTypeId = vo.getConTypeId();
        easJson.put("conTypeId", conTypeId);
        String hyghId = vo.getHyghId();
        easJson.put("hygh", hyghId);
        String partA = vo.getPartA();
        easJson.put("partA", partA);
        String partB = vo.getPartB();
        easJson.put("partB", partB);
        String partC = vo.getPartC();
        easJson.put("partC", partC);
        String taEntryId = vo.getTaEntryId();
        easJson.put("taEntryId", taEntryId);
        String marketProjectId = vo.getMarketProjectId();
        easJson.put("marketProjectId", marketProjectId);
        if (Util.isNotEmpty(marketProjectId)) {
            TConMarketproject tConMarketproject = marketProjectMapper.selectById(marketProjectId);
            Long fisjt = tConMarketproject.getFisjt();
            if (fisjt != null && fisjt == 1) {
                easJson.put("isJT", "true");
            } else if (fisjt != null && fisjt == 0) {
                easJson.put("isJT", "false");
            }
        }
//       费用归属 需要验证是否被关联过  关联关系： 合同关联立项 以及组织，获取对应的费用归属信息  选择其中一个
//       合同费用归属为一对一 且被关联的费用归属科目不可再被关联  费用归属区分 合同 跟无文本合同 两个类型
        String costAccountId = vo.getCostAccountId();
        easJson.put("costAccountId", costAccountId);
        String currencyId = vo.getCurrencyId();
        easJson.put("currencyId", currencyId);
        String originalAmount = vo.getOriginalAmount();
        easJson.put("originalAmount", originalAmount);
        String originalAmountBIG = vo.getOriginalAmountBIG();
        String amount = vo.getAmount();
        easJson.put("amount", amount);
        String amountBIG = vo.getAmountBIG();
        String exRate = vo.getExRate();
        easJson.put("exRate", exRate);
        LocalDateTime bizDate = vo.getBizDate();
        easJson.put("bizDate", bizDate);
        String grtAmount = vo.getGrtAmount();
        easJson.put("grtAmount", grtAmount);
        String grtRate = vo.getGrtRate();
        easJson.put("grtRate", grtRate);
        String csName = vo.getCsName();
        easJson.put("csName", csName);
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
        easJson.put("contractWFTypeId", contractWFTypeId);
//        合同流程发起类型 前端写死 后端存值
        String contractWFStartType = vo.getContractWFStartType();
        easJson.put("contractWFStartType", contractWFStartType);
        String creator = vo.getCreator();
        String creatorId = null;
        if (creator != null) {
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
        easJson.put("description", description);
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
                String fileUUID = attachmentsVO.getFileUUID();
                String originalFilename = attachmentsVO.getOriginalFilename();
                if (Util.isNotEmpty(attachNum)) {
                    List<AttachmentsVO> attachmentsVOSList = attachmentMapper.selectByNumber(attachNum);
                    if (attachmentsVOSList != null && attachmentsVOSList.size() > 0) {
                        if (Util.isNotEmpty(webUrl) && Util.isNotEmpty(fileUUID) && Util.isNotEmpty(originalFilename)) {
                            StringBuffer stringBuffer = new StringBuffer();
                            String s = stringBuffer.append(webUrl).append("/").append(fileUUID).toString();
                            object.put("FName", originalFilename == null ? "none" : originalFilename);
                            object.put("FRemotePath", s == null ? "none" : s);
                            attach.add(object);
                        }
                    }
                }
            }
        }

//       税务信息+收款信息
        String bank = vo.getBank();
        easJson.put("bank", bank);
        String bankAccount = vo.getBankAccount();
        easJson.put("bankAccount", bankAccount);
        String taxNum = vo.getTaxNum();
        easJson.put("taxerNum", taxNum);
        String taxQua = vo.getTaxQua();
        easJson.put("taxerQua", taxQua);
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
        if (Util.isNotEmpty(originalAmount)){
            BigDecimal bigDecimal = new BigDecimal(originalAmount);
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
        BigDecimal totalRate = BigDecimal.ZERO;
        if (Util.isNotEmpty(marketContDetailVOS)) {
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
                BigDecimal bigDecimal1 = new BigDecimal(rate).divide(new BigDecimal("100"));
                totalRate = totalRate.add(bigDecimal1);
                jsonArray.add(json);
            }
        }
        if (totalRate.compareTo(BigDecimal.ONE) != 0) {
            throw new ServiceException(UtilMessage.TOTAL_RATE_NOT_ONE);
        }
        easJson.put("marketConArray", jsonArray);
//        调用eas 保存方法进行保存
        Call call = getCall("EASURL", "saveContractBill");
        String result = null;
        try {
            System.out.println(easJson.toJSONString());
            result = (String) call.invoke(new Object[]{easJson.toString()});
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
//        接收返回eas信息
        JSONObject object = JSONObject.parseObject(result);
        if (result != null && object.get("result").toString().contains("fault")) {
            contractVO.setResult("fault");
            throw new ServiceException(object.getString("message"));
        } else if (result != null && object.get("result").toString().contains("success")) {
            String id = object.getString("id");
            contractVO.setId(id);
            contractVO.setResult("success");
            TConContractbill tConContractbill = mapper.selectById(id);
            tConContractbill.setFcreatorid(creatorId);
            mapper.updateById(tConContractbill);
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
        //                保存=1SAVED,已提交=2SUBMITTED,审批中=3AUDITTING,已审批=4AUDITTED,终止=5CANCEL,已下发=7ANNOUNCE,已签证=8VISA,
//                作废=9INVALID,已上报=10PUBLISH,被打回=11BACK,修订中=12REVISING,已修订=12REVISE,已确认=13CONFIRMED
        String state = contractVO.getState();
        if (state.contains("2")) {
            contractVO.setState("已提交");
        } else if (state.contains("1")) {
            contractVO.setState("保存");
        } else if (state.contains("3")) {
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
        String oaid = supplierapplyMapper.selectOaid(id);
        if (Util.isNotEmpty(oaid)) {
//            获取当前登录信息 取用户账号用作oa流程查看登录
            String token = RequestHolder.getCurrentUser().getToken();
            String dencrypt = RSAUtil.dencrypt(token, "pri.key");
            String[] split = dencrypt.split("&&");
            String org = split[0];
            String person = split[1];
            if (Util.isEmpty(person)) {
                throw new ServiceException(UtilMessage.PERSON_MISSING);
            }
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
                contractVO.setDetailVOList(detailVOS);
            }
//        附件信息
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
                contractVO.setAttachmentsVOS(attachmentsVOS);
            }

//        补充合同信息
        TConContractbill tConContractbill1 = mapper.selectById(vo.getId());
        String fnumber = tConContractbill1.getFnumber();
        List<ContractAddVO> contractAddVOS = mapper.selectContractAdds(fnumber);
        if (Util.isNotEmpty(contractAddVOS)) {
            contractVO.setContractAddVOS(contractAddVOS);
        }
//        营销合同分摊明细
        List<MarketContDetailVO> marketContDetailVOS = mapper.selectMarketCons(vo.getId());
        TConContractbill tConContractbill = mapper.selectById(vo.getId());
        Long fisjt = tConContractbill.getFisjt();
        if (marketContDetailVOS != null && marketContDetailVOS.size() > 0) {
            for (MarketContDetailVO marketContDetailVO : marketContDetailVOS) {
                if (Util.isNotEmpty(fisjt)) {
                    marketContDetailVO.setIsjt(1);
                }
            }
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
        public ContractVO submitToOa (ContractVO vo){
            ContractVO contractVO = new ContractVO();
            String id = vo.getId();
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
            oaId = supplierapplyMapper.selectOaid(id);
//      基本参数
            obj.put("id", id);
            obj.put("tmplateId", "174046df325987eb1d487be4026b1b64");
            obj.put("fdType", "1");
            obj.put("docSubject", vo.getConName());
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
            if (vo.getAttachmentsVOS() != null && vo.getAttachmentsVOS().size() > 0) {
                List<AttachmentsVO> attachmentsVOS = vo.getAttachmentsVOS();
                supplierapplyMapper.deletAttach(id);
                ftpUtil.saveAttachMent(attachmentsVOS, id);
            } else {
                supplierapplyMapper.deletAttach(id);
            }
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
                String appendType = "contract/view?from=oa&id=";

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
//        表单参数
            JSONObject data = new JSONObject();
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
                    str = JSONObject.parseObject(result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                Call call = getCall("OAURL", "updateEkpReview");
                try {
                    obj.put("id", oaId);
                    result = (String) call.invoke(new Object[]{obj.toString()});
                    str = JSONObject.parseObject(result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
//        code 1 success 2 fault
            String code = str.getString("code");
            String oaid = str.getString("oaid");
            if (oaid != null && id != null) {
                oaIdUtil.getString(id, oaid);
            }
//       成功提交后，修改eas当前状态为审批中0
            if (code != null && code.contains("1")) {
//            修改单据状态为审批中
                TConContractbill tConContractbill = mapper.selectById(id);
                tConContractbill.setFstate("3AUDITTING");
                mapper.updateById(tConContractbill);
            } else {
                throw new ServiceException(UtilMessage.SUBMIT_FAULT);
            }
            return contractVO;
        }

        @Override
        public void deleteContractBills (ContractVO vo){
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
        public List<ContractVO> getMainContractNums (ContractVO vo){
            if (Util.isEmpty(vo.getOrgId()) || Util.isEmpty(vo.getProjectId())) {
                return null;
            } else if (Util.isEmpty(vo.getConTypeId())) {
                throw new ServiceException(UtilMessage.MISS_CONTRACTTYPE);
            }
            List<ContractVO> contracts = mapper.selectMainNums(vo);
            return contracts;
        }
    }
