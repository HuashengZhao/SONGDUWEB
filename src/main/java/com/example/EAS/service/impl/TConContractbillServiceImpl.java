package com.example.EAS.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.mapper.TConContractbillMapper;
import com.example.EAS.mapper.TConSupplierapplyMapper;
import com.example.EAS.model.TConContractbill;
import com.example.EAS.service.ITConContractbillService;
import com.example.EAS.util.*;
import com.example.EAS.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
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
                if (costProperty.contains("COMP_COMFIRM")) {
                    contractVO.setCostProperty("固定总价");
                } else if (costProperty.contains("TEMP_EVAL")) {
                    contractVO.setCostProperty("暂定总价");
                } else if (costProperty.contains("BASE_CONFIRM")) {
                    contractVO.setCostProperty("暂定总价转固定总价");
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
        String hygh = vo.getHygh();
        easJson.put("hygh", hygh);
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
            easJson.put("startDate", startDate.toString());
        }
        LocalDateTime endDate = vo.getEndDate();
        if (Util.isNotEmpty(endDate)) {
            easJson.put("endDate", endDate.toString());
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
//        合同流程发起类型 前端写死 后端存值
        String contractWFStartType = vo.getContractWFStartType();
        easJson.put("contractWFStartType", contractWFStartType);
        String creator = vo.getCreator();
        String creatorId = null;
        if (creator != null) {
            creatorId = mapper.selectPersonByNum(creator);
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
        easJson.put("createTime", createTime);

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
        JSONObject jsonObject = new JSONObject();
        String bank = vo.getBank();
        jsonObject.put("bank", bank);
        String bankAccount = vo.getBankAccount();
        jsonObject.put("bankAccount", bankAccount);
        String taxNum = vo.getTaxNum();
        jsonObject.put("taxNum", taxNum);
        String taxQua = vo.getTaxQua();
        jsonObject.put("taxQua", taxQua);
        String unionBankNum = vo.getUnionBankNum();
        jsonObject.put("unionBankNum", unionBankNum);
        if (Util.isNotEmpty(unionBankNum)) {
            String unionBankId = mapper.selectUnionBankId(unionBankNum);
        }
        jsonObject.put("unionBankId", unionBankNum);
        easJson.put("taxBill", jsonObject);
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
                objects.add(jsonObject);
                allDetailAmount.add(new BigDecimal(totalAmount));
            }
        }
//        判断金额
//        if (Util.isNotEmpty(originalAmount)){
//            BigDecimal bigDecimal = new BigDecimal(originalAmount);
//            if (allDetailAmount.compareTo(bigDecimal) == 1) {
//                throw new ServiceException(UtilMessage.DETAIL_AMOUNT_BEYOND);
//            }
//        }
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
                String descrip = contractDetailVO.getDescription();
                json.put("description", descrip);
                details.add(jsonObject);
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
                BigDecimal bigDecimal1 = new BigDecimal(rate);
                totalRate.add(bigDecimal1);
            }
        }
//        if (totalRate.compareTo(BigDecimal.ONE) != 0) {
//            throw new ServiceException(UtilMessage.TOTAL_RATE_NOT_ONE);
//        }
        easJson.put("marketConArray", details);
//        调用eas 保存方法进行保存
        Call call = getCall("EASURL", "saveContractBill");
        String result = null;
        try {
            result = (String) call.invoke(new Object[]{easJson.toString()});
        } catch (RemoteException e) {
            throw new ServiceException(e.getMessage());
        }
        if (Util.isNotEmpty(result)){
            JSONObject resultObject = JSONObject.parseObject(result);
            String result1 = resultObject.get("result").toString();
            String message = resultObject.get("message").toString();
            if (result1!=null&&result1.contains("fault")){
                throw new ServiceException(UtilMessage.SAVE_MSG_ERROR);
            }
        }
        return contractVO;
    }

    @Override
    public ContractVO viewContractBill(ContractVO vo) {
        String id = vo.getId();
        if (Util.isEmpty(id)) {
            return null;
        }
        ContractVO contractVO = mapper.viewContractVO(vo);
        if (Util.isEmpty(contractVO)) {
            return null;
        }
//       是否有附件

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
     * submit in eas system
     *
     * @param vo
     */
    @Override
    public void submitToOa(ContractVO vo) {

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
        return null;
    }
}
