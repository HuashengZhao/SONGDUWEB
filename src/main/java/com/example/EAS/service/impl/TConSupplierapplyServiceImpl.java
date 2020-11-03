package com.example.EAS.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.mapper.TConSupplierapplyMapper;
import com.example.EAS.mapper.TFdcContracttypeMapper;
import com.example.EAS.model.TConSupplierapply;
import com.example.EAS.service.ITConSupplierapplyService;
import com.example.EAS.util.*;
import com.example.EAS.vo.AttachmentsVO;
import com.example.EAS.vo.ContractTypeVO;
import com.example.EAS.vo.PersonsVO;
import com.example.EAS.vo.SupplierApplyVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.example.EAS.util.UtilMessage.EXIST_TAX_NUM;
import static com.example.EAS.util.UtilMessage.SOURCE_FUNCATION_NOT_EXIST;

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
public class TConSupplierapplyServiceImpl extends ServiceImpl<TConSupplierapplyMapper, TConSupplierapply> implements ITConSupplierapplyService {

    @Autowired
    private TConSupplierapplyMapper mapper;
    @Autowired
    private TFdcContracttypeMapper contracttypeMapper;
    @Autowired
    private OaIdUtil oaIdUtil;
    @Autowired
    private FtpUtil ftpUtil;
    @Autowired
    private TBasAttachmentMapper attachmentMapper;
    @Autowired
    private LoginInfoUtil loginInfoUtil;


    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    org.apache.axis.client.Service service = new org.apache.axis.client.Service();

    public Call getCall(String type, String operationName) {

        String url = null;
        if (type.contains("EAS")) {
            url = mapper.selectEASURL();
        } else {
            url = mapper.selectOAURL();
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
    public PageBean<SupplierApplyVO> getSupplierApply(SupplierApplyVO vo) throws Exception {
        String state1 = vo.getState();
        //获取登录信息
        JSONObject token = loginInfoUtil.getToken();
        //        过权限
        Boolean aBoolean = loginInfoUtil.ifInItDept();
        if (aBoolean == false) {
            vo.setAuthorNum(token.getString("person"));
        }
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
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<SupplierApplyVO> supplierApplyVOS = mapper.selectDatas(vo);
        if (supplierApplyVOS != null && supplierApplyVOS.size() > 0) {
            for (SupplierApplyVO supplierApplyVO : supplierApplyVOS) {
                if (Util.isNotEmpty(vo.getId())) {
                    String easId = vo.getId();
                    //                    获取对应的oaid
                    String oaid = null;
                    List<String> oaids = mapper.selectOaid(easId);
                    if (oaids != null && oaids.size() > 0) {
                        oaid = oaids.get(0);
                    }
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
                        supplierApplyVO.setLink(link);
                        supplierApplyVO.setOaId(oaid);
                    }
//                       附件信息
                    List<AttachmentsVO> easAttFiles = attachmentMapper.selectAttachMent(easId);
                    if (easAttFiles != null && easAttFiles.size() > 0) {
                        for (AttachmentsVO easAttFile : easAttFiles) {
                            String fileType = easAttFile.getFileType();
                            if (Util.isNotEmpty(fileType)) {
                                String s = FileContentTypeUtils.contentType("." + fileType);
                                easAttFile.setContentType(s);
                            }
                        }
                        supplierApplyVO.setAttachmentsVOS(easAttFiles);
                    }
                }
//                纳税人资质 一般纳税人=NOMAL,小规模纳税人=SMALL,非增值税纳税人=UNNOMAL
                String taxerQua = supplierApplyVO.getTaxerQua();
                if (Util.isNotEmpty(taxerQua)) {
                    if (taxerQua.contains("NOMAL")) {
                        supplierApplyVO.setTaxerQua("一般纳税人");
                    } else if (taxerQua.contains("SMALL")) {
                        supplierApplyVO.setTaxerQua("小规模纳税人");
                    } else if (taxerQua.contains("UNNOMAL")) {
                        supplierApplyVO.setTaxerQua("非增值税纳税人");
                    }
                }
                String state = supplierApplyVO.getState();
                if (Util.isNotEmpty(state)) {
                    if (state.contains("2SUB")) {
                        supplierApplyVO.setState("已提交");
                    } else if (state.contains("1SAVED")) {
                        supplierApplyVO.setState("保存");
                    } else if (state.contains("3AUD")) {
                        supplierApplyVO.setState("审批中");
                    } else if (state.contains("4")) {
                        supplierApplyVO.setState("已审批");
                    } else if (state.contains("5")) {
                        supplierApplyVO.setState("终止");
                    } else if (state.contains("7")) {
                        supplierApplyVO.setState("已下发");
                    } else if (state.contains("8")) {
                        supplierApplyVO.setState("已签证");
                    } else if (state.contains("9")) {
                        supplierApplyVO.setState("作废");
                    } else if (state.contains("10")) {
                        supplierApplyVO.setState("已上报");
                    } else if (state.contains("11")) {
                        supplierApplyVO.setState("被打回");
                    } else if (state.contains("12REVISING")) {
                        supplierApplyVO.setState("修订中");
                    } else if (state.contains("12REVISE")) {
                        supplierApplyVO.setState("已修订");
                    } else if (state.contains("13")) {
                        supplierApplyVO.setState("已确认");
                    }
                }

            }
        }
        PageBean<SupplierApplyVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) supplierApplyVOS).getTotal());
        pageBean.setPageData(supplierApplyVOS);
        return pageBean;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public JSONObject addSupplierApply(SupplierApplyVO vo) {
        //获取登录信息
        JSONObject token = loginInfoUtil.getToken();
        JSONObject obj = new JSONObject();
        if (Util.isNotEmpty(vo.getBankAccount())) {
            obj.put("bankAccount", vo.getBankAccount());
        }
        if (Util.isNotEmpty(vo.getBank())) {
            obj.put("bank", vo.getBank());
        }
        if (Util.isNotEmpty(vo.getTitle())) {
            String title = vo.getTitle();
            List<SupplierApplyVO> supplierApplyVOS = mapper.selectByName(title);
            if (supplierApplyVOS != null && supplierApplyVOS.size() > 0) {
                throw new ServiceException(UtilMessage.SUPPLIER_NAME_REPEAT);
            }
            List<Object> objs = mapper.selectSupplierByName(title);
            if (objs != null && objs.size() > 0) {
                throw new ServiceException(UtilMessage.SUPPLIER_NAME_REPEAT);
            }
            obj.put("name", vo.getTitle());
        }
        if (Util.isNotEmpty(vo.getNum())) {
            obj.put("number", vo.getNum());
        }
        if (Util.isNotEmpty(vo.getDescription())) {
            obj.put("remark", vo.getDescription());
        }
        if (Util.isNotEmpty(vo.getAuditorNum())) {
            String auditorId = mapper.selectCreatorId(vo.getAuditorNum());
            obj.put("auditor", auditorId);
        }
        if (Util.isNotEmpty(vo.getCreateTime())) {
            obj.put("createTime", LocalDateTime.now());
        }
        if (Util.isNotEmpty(vo.getAuditTime())) {
            obj.put("lastUpdateTime", vo.getAuditTime());
        }
        if (Util.isNotEmpty(vo.getSourceFunction())) {
//                        判断是否存在
            String sourceFunction = vo.getSourceFunction();
            List<SupplierApplyVO> supplierApplyVOS = mapper.selectByNum(sourceFunction);
            if (supplierApplyVOS.size() <= 0) {
                throw new ServiceException(UtilMessage.SOURCE_FUNCATION_NOT_EXIST);
            }
            obj.put("sourceFunction", vo.getSourceFunction());
        }
        if (Util.isNotEmpty(vo.getTaxerQua())) {
            obj.put("taxerQua", vo.getTaxerQua());
        }
        if (Util.isNotEmpty(vo.getTaxerNum())) {
//            判断是否存在
            String taxerNum = vo.getTaxerNum();
            List<SupplierApplyVO> supplierApplyVOS = mapper.selectByTaxNum(taxerNum);
            if (supplierApplyVOS.size() > 0) {
                throw new ServiceException(UtilMessage.TAXERNUMBER_EXIST);
            }
            obj.put("taxerNum", taxerNum);
        }
        if (Util.isNotEmpty(vo.getOrgId())) {
            obj.put("orgId", vo.getOrgId());
        }
        //        获取当前登录人id
        String person = token.getString("person");
        String creatorId = mapper.selectCreatorId(person);
//      保存到EAS附件
        JSONArray attach = new JSONArray();
        List<AttachmentsVO> attachmentsVOS = vo.getAttachmentsVOS();
        if (attachmentsVOS != null && attachmentsVOS.size() > 0) {
            for (AttachmentsVO attachmentsVO : attachmentsVOS) {
                JSONObject object = new JSONObject();
                String attachNum = attachmentsVO.getNum();
                String webUrl = attachmentsVO.getWebUrl();
                String fileSize = attachmentsVO.getFileSize();
                String descp = attachmentsVO.getDescription();
                String originalFilename = attachmentsVO.getOriginalFilename() == null ? attachmentsVO.getTitle() : attachmentsVO.getOriginalFilename();
                StringBuffer stringBuffer = new StringBuffer();
                object.put("FName", originalFilename == null ? null : originalFilename);//文件名称含后缀
                object.put("FNumber", attachNum == null ? "upLoadFromEas" : attachNum);//附件编码
                object.put("FRemotePath", webUrl == null ? null : webUrl);//文件相对路径
                object.put("FSize", fileSize == null ? null : fileSize);// 附件大小
                object.put("FDescription", descp == null ? null : descp);//附件来源类型
                object.put("FCreatorId", creatorId == null ? null : creatorId); //创建人id
                attach.add(object);
            }
        }

        obj.put("attach", attach);
        Call call = getCall("EASURL", "saveSupplierApply");
        String result = null;
        try {
            long startTime = System.currentTimeMillis();
            result = (String) call.invoke(new Object[]{obj.toString()});
            long endTime = System.currentTimeMillis();
            System.out.println("供应商保存eas方法时长：" + (endTime - startTime) + "ms");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        JSONObject str = JSONObject.parseObject(result);
        String state = str.getString("result");
        if (Util.isNotEmpty(state) && state.contains("fault")) {
            throw new ServiceException("保存失败：" + str.getString("message"));
        }
        String id = str.getString("id");
//更新eas创建人为当前登录人
        if (Util.isNotEmpty(vo.getPerson()) && Util.isNotEmpty(id)) {
            if (creatorId != null) {
                mapper.updateCreatorId(creatorId, id);
            }
        }

        return str;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public JSONObject suplierUpdate(SupplierApplyVO vo) {
        //获取登录信息
        JSONObject token = loginInfoUtil.getToken();
        String id = vo.getId();
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        if (Util.isNotEmpty(vo.getBankAccount())) {
            obj.put("bankAccount", vo.getBankAccount());
        }
        if (Util.isNotEmpty(vo.getBank())) {
            obj.put("bank", vo.getBank());
        }
        if (Util.isNotEmpty(vo.getTitle())) {
            String title = vo.getTitle();
            List<SupplierApplyVO> supplierApplyVOS = mapper.selectByNameId(title, id);
            if (supplierApplyVOS != null && supplierApplyVOS.size() > 0) {
                throw new ServiceException(UtilMessage.SUPPLIER_NAME_REPEAT);
            }
            List<Object> objs = mapper.selectSupplierByName(title);
            if (objs != null && objs.size() > 0) {
                throw new ServiceException(UtilMessage.SUPPLIER_NAME_REPEAT);
            }
            obj.put("name", vo.getTitle());
        }
        if (Util.isNotEmpty(vo.getNum())) {
            obj.put("number", vo.getNum());
        }
        if (Util.isNotEmpty(vo.getDescription())) {
            obj.put("remark", vo.getDescription());
        }
        if (Util.isNotEmpty(vo.getAuditorNum())) {
            String auditorId = mapper.selectCreatorId(vo.getAuditorNum());
            obj.put("auditor", auditorId);
        }
        obj.put("lastUpdateTime", LocalDateTime.now());
        if (Util.isNotEmpty(vo.getTaxerQua())) {
            obj.put("taxerQua", vo.getTaxerQua());
        }
        if (Util.isNotEmpty(vo.getSourceFunction())) {
//                        判断是否存在
            String sourceFunction = vo.getSourceFunction();
            List<SupplierApplyVO> supplierApplyVOS = mapper.selectByNum(sourceFunction);
            if (supplierApplyVOS.size() <= 0) {
                throw new ServiceException(SOURCE_FUNCATION_NOT_EXIST);
            }
            obj.put("sourceFunction", vo.getSourceFunction());
        }
        if (Util.isNotEmpty(vo.getTaxerNum())) {
//            判断是否存在
            String taxerNum = vo.getTaxerNum();
            List<SupplierApplyVO> supplierApplyVOS = mapper.selectByNumId(taxerNum, id);
            if (supplierApplyVOS.size() > 0) {
                throw new ServiceException(EXIST_TAX_NUM);
            }
            obj.put("taxerNum", taxerNum);
        }
        String orgId = mapper.selectOrgId(id);
        if (Util.isNotEmpty(orgId)) {
            obj.put("orgId", orgId);
        }

        //        获取当前登录人id
        String person = token.getString("person");
        String creatorId = mapper.selectCreatorId(person);
//        EAS附件
        JSONArray attach = new JSONArray();
        List<AttachmentsVO> attachmentsVOS = vo.getAttachmentsVOS();
        if (attachmentsVOS != null && attachmentsVOS.size() > 0) {
            for (AttachmentsVO attachmentsVO : attachmentsVOS) {
                JSONObject object = new JSONObject();
                String attachNum = attachmentsVO.getNum();
                String webUrl = attachmentsVO.getWebUrl();
                String fileSize = attachmentsVO.getFileSize();
                String descp = attachmentsVO.getDescription();
                String originalFilename = attachmentsVO.getOriginalFilename() == null ? attachmentsVO.getTitle() : attachmentsVO.getOriginalFilename();
                StringBuffer stringBuffer = new StringBuffer();
                object.put("FName", originalFilename == null ? null : originalFilename);//文件名称含后缀
                object.put("FNumber", attachNum == null ? "upLoadFromEas" : attachNum);//附件编码
                object.put("FRemotePath", webUrl == null ? null : webUrl);//文件相对路径
                object.put("FSize", fileSize == null ? null : fileSize);// 附件大小
                object.put("FDescription", descp == null ? null : descp);//附件来源类型
                object.put("FCreatorId", creatorId == null ? null : creatorId); //创建人id
                attach.add(object);
            }
        }
        obj.put("attach", attach);

        Call call = getCall("EASURL", "saveSupplierApply");
        String result = null;
        try {
            result = (String) call.invoke(new Object[]{obj.toString()});
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new ServiceException(UtilMessage.SAVE_MSG_ERROR);
        }

        JSONObject str = JSONObject.parseObject(result);
        String state = str.getString("result");
        if (Util.isNotEmpty(state) && state.contains("fault")) {
            throw new ServiceException("保存失败：" + str.getString("message"));
        }
//更新eas创建人为当前登录人
        if (Util.isNotEmpty(vo.getPerson()) && Util.isNotEmpty(id)) {
            if (creatorId != null) {
                mapper.updateCreatorId(creatorId, id);
            }
        }

        return str;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteSupplierApply(SupplierApplyVO vo) {
        List<String> idList = vo.getIdList();
        if (idList != null && idList.size() > 0) {
            JSONArray idArray = new JSONArray();
            for (String s : idList) {
                JSONObject object = new JSONObject();
                object.put("id", s);
                idArray.add(object);
//                掉用oa删除流程
//                String oaid = mapper.selectOaid(s);
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("id", oaid);
//                Call call1 = getCall("OAURL", "deleteEkpFlow");
//                String result1 = null;
//                try {
//                    result1 = (String) call1.invoke(new Object[]{jsonObject.toString()});
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                    throw new ServiceException(UtilMessage.DELETE_ERROR);
//                }
            }
//            调用eas批量删除
            if (idArray.size() > 0) {
                JSONObject object = new JSONObject();
                object.put("idArray", idArray);
                Call call = getCall("EASURL", "deleteSupplierApply");
                String result = null;
                try {
                    result = (String) call.invoke(new Object[]{object.toString()});
                } catch (RemoteException e) {
                    e.printStackTrace();
                    throw new ServiceException(UtilMessage.DELETE_ERROR);
                }
            }
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public String getNewNumber(SupplierApplyVO vo) {
        DecimalFormat decimalFormat = new DecimalFormat("00000000");
        Date date = new Date();
        String dateInfo = formatter.format(date);
        int value = 0;
        String format = "'errorNum'";
        //     供应商新增申请单编码生成规则 WEB-时间格式YYYY-MM-DD-8位流水号
        String orgNumber = "errorNumber";
        if (vo.getOrgId() != null) {
            ContractTypeVO contractTypeVO = new ContractTypeVO();
            contractTypeVO.setOrgId(vo.getOrgId());
            orgNumber = contracttypeMapper.selectOrgNumber(contractTypeVO);
        }
        Integer numberRecord = mapper.selectNumberRecord();
        if (numberRecord != null) {
            value = value + numberRecord + 1;
            format = decimalFormat.format(value);
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer conNumber = stringBuffer.append("WEB.").append(dateInfo + "-")
                .append(format);
        //       if  add operation do success then update recordNumber +1
        numberRecord = numberRecord + 1;
        mapper.updateNumberRecord(numberRecord);
        return conNumber.toString();
    }

    /**
     * 提交供应商申请
     * 1.获取对应的单据信息
     * 2.调用oa流程接口，进行传参
     * 3.后续提供提供oa审批回调接口
     * 需要验证联行号 纳税人等信息
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public SupplierApplyVO supplierSubmit(SupplierApplyVO vo) {
        JSONObject token = loginInfoUtil.getToken();
        SupplierApplyVO supplierApplyVO1 = new SupplierApplyVO();
        String message = null;
        String id = vo.getId();
//      保存提交修改的内容
        JSONObject json = null;
        if (id != null) {
            json = suplierUpdate(vo);
        } else {
            json = addSupplierApply(vo);
            id = json.get("id").toString();
            String message1 = json.getString("message");
            if (Util.isNotEmpty(message1)) {
                throw new ServiceException(message1);
            }
        }
//        判断是否提交过被驳回  需要携带oaid
        JSONObject obj = new JSONObject();
        SupplierApplyVO supplierApplyVO = mapper.selectDataById(id);
        String oaId = null;
        List<String> oaIds = mapper.selectOaid(id);
        if (oaIds != null && oaIds.size() > 0) {
            oaId = oaIds.get(0);
        }
//        基本参数
//        obj.put("oaid", oaId);
        obj.put("id", id);
        obj.put("tmplateId", "17400f46dd2db720a8bfcf348c1984dc");
        obj.put("fdType", "1");
        obj.put("docSubject", vo.getTitle());
        StringBuffer sb = new StringBuffer();
        //获取登录信息

        String personNum = token.getString("person");
        PersonsVO person = mapper.selectCreator(personNum);
//        提交之前保存附件与单据关联
        if (vo.getAttachmentsVOS() != null && vo.getAttachmentsVOS().size() > 0) {
            List<AttachmentsVO> attachmentsVOS = vo.getAttachmentsVOS();
            mapper.deletAttach(id);
            ftpUtil.saveAttachMent(attachmentsVOS, id);
        } else {
            mapper.deletAttach(id);
        }

        //        http://172.17.4.125:8082/easWeb/#/
        //        http://172.17.4.125:8082/easApp/#/
        String sendUrl = null;
        String sendAppUrl = null;
        StringBuffer sbv = new StringBuffer();
        StringBuffer sba = new StringBuffer();
        String appendUrl = mapper.selectViewUrl();
        String appUrl = mapper.selectAppUrl();
//					审批单 approval
        if (Util.isEmpty(appendUrl) || Util.isEmpty(appUrl)) {
            throw new ServiceException(UtilMessage.VIEW_URL_NOT_FOUND);
        }
        String appendType = "supplier/view?from=oa&id=";
        String appAppendType = "supplier/?from=oa&id=";
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
        System.out.println("合同单据web端详情查看地址：" + sendUrl);
        System.out.println(" 合同单据app端详情查看地址：" + sendAppUrl);
        obj.put("loginName", "00561");
        JSONObject data = new JSONObject();
        data.put("fd_link", sendUrl);
        data.put("fd_mobile_link", sendAppUrl);
        obj.put("data", data.toString());

        //      附件参数
        JSONArray attFile = new JSONArray();
        List<AttachmentsVO> easFiles = attachmentMapper.selectAttachMent(id);
        if (easFiles != null && easFiles.size() > 0) {
            for (AttachmentsVO easFile : easFiles) {
                JSONObject attObj = new JSONObject();
                attObj.put("filename", easFile.getTitle());
                attObj.put("filepath", easFile.getWebUrl());
                attObj.put("fileType", "04");
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
                str = JSONObject.parseObject(result);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Call call = getCall("OAURL", "updatetestEkpReview");
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
            TConSupplierapply tConSupplierapply = mapper.selectById(id);
            tConSupplierapply.setFsourcefunction(oaid);
            mapper.updateById(tConSupplierapply);
        }
//       成功提交后，修改eas当前状态为审批中0
        if (code != null && code.contains("1")) {
//            修改单据状态为审批中
            TConSupplierapply tConSupplierapply = mapper.selectById(supplierApplyVO.getId());
            tConSupplierapply.setFstate("3AUDITTING");
            mapper.updateById(tConSupplierapply);
            //            获取返回的附件查看路径   预览
            JSONArray attUrlArray = str.getJSONArray("atturl");
            if (attUrlArray!=null && attUrlArray.size()>0){
////               附件预览地址
                for(int i=0;i<attUrlArray.size();i++){
                    JSONObject atturlObj = attUrlArray.getJSONObject(i);
                    String attName = atturlObj.getString("name");
                    String atturl = atturlObj.getString("url");
//                 取用户账号用作oa流程查看登录
                    if (Util.isEmpty(personNum)) {
                        throw new ServiceException(UtilMessage.PERSON_MISSING);
                    }
                    try {
                        String s2 = "&MtFdLoinName=";
                        String mtLoginNum = OaUtil.encrypt(personNum);
                        String attLink = new StringBuffer().append(atturl).append(s2).append(mtLoginNum).toString();
                        attachmentMapper.updateAttLink(id,attName,attLink);
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
        return supplierApplyVO1;
    }

}
