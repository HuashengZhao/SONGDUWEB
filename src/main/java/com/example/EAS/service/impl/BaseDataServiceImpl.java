package com.example.EAS.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.*;
import com.example.EAS.model.*;
import com.example.EAS.service.IBaseDataService;
import com.example.EAS.thread.AsyncExecutor;
import com.example.EAS.util.OaIdUtil;
import com.example.EAS.util.ServiceException;
import com.example.EAS.util.Util;
import com.example.EAS.util.WSLoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.apache.axis.message.SOAPHeaderElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-08-17
 */
@Slf4j
@Service
public class BaseDataServiceImpl extends ServiceImpl<BaseDataMapper, BaseData> implements IBaseDataService {

    @Autowired
    private WSLoginUtil wsLoginUtil;
    @Autowired
    private OaIdUtil oaIdUtil;
    @Autowired
    private TConSupplierapplyMapper supplierapplyMapper;
    @Autowired
    private TConContractbillMapper contractbillMapper;
    @Autowired
    private TConPayrequestbillMapper payrequestbillMapper;
    @Autowired
    private TConChangeauditbillMapper auditMapper;
    @Autowired
    private TConContractchangesettlebillMapper settleMapper;
    @Autowired
    private TConContractwithouttextMapper noTextMapper;


    org.apache.axis.client.Service service = new org.apache.axis.client.Service();

    public Call getCall(String type, String operationName) {

        String url = null;
        if (type.contains("EAS")) {
            url = supplierapplyMapper.selectEASURL();
        } else {
            url = supplierapplyMapper.selectOAURL();
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

    /**
     * oa审批后回调方法
     * 根据oa审批状态 修改eas单据状态
     * Oa审批通过 eas改成已审批,
     * oa审批驳回，eas改为已提交 此时可以修改并二次提交 二次提交时 携带oa原有id提交,
     * oa废弃流程，eas改为保存 此时为新流程
     * acceptinfo 日志表(billId varchar(255),accepttime varchar(255),billtype varchar(255)
     * ,optype varchar(255),state int,message varchar(555))
     *
     * @param body
     */

    @Override
    public JSONObject acceptHandle(JSONObject body) throws Exception {
//        回调接口调用时间
        String acceptTime = LocalDateTime.now().toString();
//        单据类型
        String billType = null;
//      接口返回参数
        JSONObject obj = new JSONObject();
        obj.put("code", "1");
        obj.put("msg", "success");
//      请求参数
        log.info(body.toString());

        if (Util.isEmpty(body.get("type"))) {
            obj.put("code", "2");
            obj.put("msg", "fault");
            obj.put("content", "回调单据type不能为空");
            return obj;
        } else if (Util.isEmpty(body.get("result"))) {
            obj.put("code", "2");
            obj.put("msg", "fault");
            obj.put("content", "回调单据result不能为空");
            return obj;
        } else if (Util.isEmpty(body.get("oaid"))) {
            obj.put("code", "2");
            obj.put("msg", "fault");
            obj.put("content", "回调单据oaid不能为空");
            return obj;
        } else if (Util.isEmpty(body.get("easid"))) {
            obj.put("code", "2");
            obj.put("msg", "fault");
            obj.put("content", "回调单据easid不能为空");
            return obj;
        }
        String oaid = body.get("oaid").toString();
        String attlink = body.get("attlink").toString();
//      01:审批通过,02:废弃,03:驳回,修订
        String result = body.get("result").toString();
//      type 01:合同、02:合同付款申请单、03:无合同付款;04：供应商申请，05变更审批单，06变更确认单
        String type = body.get("type").toString();
        String easid = body.get("easid").toString();

        if (Util.isNotEmpty(type)) {
            if (type.contains("01")) {
                billType = "合同";
            } else if (type.contains("02")) {
                billType = "合同付款申请单";
            } else if (type.contains("03")) {
                billType = "无文本合同";
            } else if (type.contains("04")) {
                billType = "供应商新增申请单";
            } else if (type.contains("05")) {
                billType = "变更审批单";
            } else if (type.contains("06")) {
                billType = "变更确认单";
            }
        }

//      操作类型 对应webservice 的方法
        JSONObject operation = getOperation(type);
        String saveOperation = operation.getString("saveOperation");
        String submitOperation = operation.getString("submitOperation");
        String auditOperation = operation.getString("auditOperation");
        String deleteOperation = operation.getString("deleteOperation");
//      根据类型验证eas单据是否存在
        JSONObject object = ifEasIdExists(easid, type);
        if (object.get("code") != null) {
            return object;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", easid);
        String finalBillType = billType;
        AsyncExecutor.executeTask(t -> {
            String back = null;
            String acceptType = null;
            //       如果审核成功 调用eas审核方法
            if (result.contains("01")) {
                acceptType = "审核";
                log.info("type为" + type + "流程调用了回调审批！");
                if (oaid != null && easid != null) {
                    oaIdUtil.getString(easid, oaid);
                }
                supplierapplyMapper.insertAcceptInfo(easid, acceptTime, finalBillType, acceptType, 1);
                //        调用eas登录获取sessionid
                JSONObject login = wsLoginUtil.login();//       登录
                String sessionId = login.getString("sessionId");
                Call call = (Call) login.get("call");
                if (Util.isNotEmpty(sessionId)) {
                    //清理
                    call.clearOperation();
                    String url = supplierapplyMapper.selectEASURL();
                    call.setOperationName(auditOperation);    //接口方法
                    call.setTargetEndpointAddress(url);   //对应接口地址
                    call.addParameter("arg0", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
                    call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
                    call.setTimeout(Integer.valueOf(1000 * 600000 * 60));
                    call.setMaintainSession(true);
                    call.setUseSOAPAction(true);
                    SOAPHeaderElement header = new SOAPHeaderElement("http://login.webservice.bos.kingdee.com", "SessionId", sessionId);
                    call.addHeader(header);
                    try {
                        back = (String) call.invoke(new Object[]{jsonObject.toString()});
                        log.info("新增供应商申请单返回结果:" + result);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        String message = e.getMessage();
                        supplierapplyMapper.updateAcceptInfo(easid, acceptTime, 2, message);
                        throw new ServiceException(message);
                    }
                    if (Util.isNotEmpty(back)) {
                        JSONObject backObj = JSONObject.parseObject(back);
                        String resultBack = backObj.getString("result");
                        if (Util.isNotEmpty(resultBack) && resultBack.contains("fault")) {
                            String message = backObj.getString("message");
                            supplierapplyMapper.updateAcceptInfo(easid, acceptTime, 2, message == null ? "eas审批时发生错误" : message);
                        } else if (Util.isNotEmpty(resultBack) && resultBack.contains("success")) {
                            supplierapplyMapper.updateAcceptInfo(easid, acceptTime, 1, "success");
                        }
                    } else {
                        supplierapplyMapper.updateAcceptInfo(easid, acceptTime, 2, "eas审批时发生错误,审批调用无返回");
                    }
                }
                wsLoginUtil.logout(call);//登出
            }
            try {
//        如果oa作废 修改状态为保存 删除easid 跟oaid的关联
                if (result.contains("02")) {
                    acceptType = "作废";
                    log.info("type为" + type + "流程调用了回调作废！");
                    if (oaid != null && easid != null) {
                        oaIdUtil.deleteId(easid, oaid);
                    }
                    supplierapplyMapper.insertAcceptInfo(easid, acceptTime, finalBillType, acceptType, 1);
                    if (type.contains("01")) {
                        contractbillMapper.updateData(easid);
                    } else if (type.contains("02")) {
                        payrequestbillMapper.updateData(easid);
                    } else if (type.contains("03")) {
                        noTextMapper.updateData(easid);
                    } else if (type.contains("04")) {
                        supplierapplyMapper.updateData(easid);
                    } else if (type.contains("05")) {
//                       删除单据里fsourcefunction存储的oaid
                        auditMapper.updateData(easid);
                    } else if (type.contains("06")) {
//                         删除单据里fsourcefunction存储的oaid
                        settleMapper.updateData(easid);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                String message = e.getMessage();
                supplierapplyMapper.updateAcceptInfo(easid, acceptTime, 2, message);
            }

//        如果驳回 修改状态为已提交 并保留oaid
            try {
            if (result.contains("03")) {
                acceptType = "驳回";
                log.info("type为" + type + "流程调用了回调驳回！");
                if (type.contains("01")) {
                    if (oaid != null && easid != null) {
                        oaIdUtil.getString(easid, oaid);
                    }
                    supplierapplyMapper.insertAcceptInfo(easid, acceptTime, finalBillType, acceptType, 1);
                    TConContractbill tConContractbill = contractbillMapper.selectById(easid);
                    tConContractbill.setFstate("2SUBMITTED");
                    contractbillMapper.updateById(tConContractbill);
                } else if (type.contains("02")) {
                    if (oaid != null && easid != null) {
                        oaIdUtil.getString(easid, oaid);
                    }
                    TConPayrequestbill tConPayrequestbill = payrequestbillMapper.selectById(easid);
                    tConPayrequestbill.setFstate("2SUBMITTED");
                    payrequestbillMapper.updateById(tConPayrequestbill);
                } else if (type.contains("03")) {
                    if (oaid != null && easid != null) {
                        oaIdUtil.getString(easid, oaid);
                    }
                    TConContractwithouttext tConContractwithouttext = noTextMapper.selectById(easid);
                    tConContractwithouttext.setFstate("2SUBMITTED");
                    noTextMapper.updateById(tConContractwithouttext);
                } else if (type.contains("04")) {
                    if (oaid != null && easid != null) {
                        oaIdUtil.getString(easid, oaid);
                    }
                    TConSupplierapply tConSupplierapply = supplierapplyMapper.selectById(easid);
                    tConSupplierapply.setFstate("2SUBMITTED");
                    supplierapplyMapper.updateById(tConSupplierapply);
                } else if (type.contains("05")) {
                    TConChangeauditbill tConChangeauditbill = auditMapper.selectById(easid);
                    tConChangeauditbill.setFstate("2SUBMITTED");
                    tConChangeauditbill.setFchangestate("3Submit");
                    auditMapper.updateById(tConChangeauditbill);
                } else if (type.contains("06")) {
                    TConContractchangesettlebill tConContractchangesettlebill = settleMapper.selectById(easid);
                    tConContractchangesettlebill.setFstate("2SUBMITTED");
                    settleMapper.updateById(tConContractchangesettlebill);
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
                String message = e.getMessage();
                supplierapplyMapper.updateAcceptInfo(easid, acceptTime, 2, message);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }, "1", "100,100");

        return obj;
    }

    public JSONObject getOperation(String type) {
//        type 01:合同、02:合同付款申请单、03:无合同付款;04：供应商申请，05变更审批单，06变更确认单
        JSONObject object = new JSONObject();
        if (type.contains("01")) {
            object.put("saveOperation", "saveContractBill");
            object.put("submitOperation", "submitContractBill");
            object.put("auditOperation", "auditContractBill");
            object.put("deleteOperation", "deleteContractBill");
        } else if (type.contains("02")) {
            object.put("saveOperation", "todo");
            object.put("submitOperation", "todo");
            object.put("auditOperation", "auditPayRequestBill");
            object.put("deleteOperation", "todo");
        } else if (type.contains("03")) {
            object.put("saveOperation", "saveContractwithouttext");
            object.put("submitOperation", "submitContractwithouttext");
            object.put("auditOperation", "auditContractwithouttext");
            object.put("deleteOperation", "deleteContractwithouttext");
        } else if (type.contains("04")) {
            object.put("saveOperation", "todo");
            object.put("submitOperation", "todo");
            object.put("auditOperation", "auditSupplierApply");
            object.put("deleteOperation", "todo");
        } else if (type.contains("05")) {
            object.put("saveOperation", "todo");
            object.put("submitOperation", "todo");
            object.put("auditOperation", "auditChangeAuditBill");
            object.put("deleteOperation", "todo");
        } else if (type.contains("06")) {
            object.put("saveOperation", "todo");
            object.put("submitOperation", "todo");
            object.put("auditOperation", "auditContractChangeSettleBill");
            object.put("deleteOperation", "todo");
        }
        return object;
    }

    public JSONObject ifEasIdExists(String easId, String type) {
//      type 01:合同、02:合同付款申请单、03:无合同付款;04：供应商申请，05变更审批单，06变更确认单
        JSONObject obj = new JSONObject();
        if (type.contains("01")) {
            TConContractbill tConContractbill = contractbillMapper.selectById(easId);
            if (Util.isEmpty(tConContractbill)) {
                obj.put("code", "2");
                obj.put("msg", "fault");
                obj.put("content", "目标eas单据不存在");
            }
        } else if (type.contains("02")) {

        } else if (type.contains("03")) {
            TConContractwithouttext tConContractwithouttext = noTextMapper.selectById(easId);
            if (Util.isEmpty(tConContractwithouttext)) {
                obj.put("code", "2");
                obj.put("msg", "fault");
                obj.put("content", "目标eas单据不存在");
            }
        } else if (type.contains("04")) {
            TConSupplierapply tConSupplierapply = supplierapplyMapper.selectById(easId);
            if (Util.isEmpty(tConSupplierapply)) {
                obj.put("code", "2");
                obj.put("msg", "fault");
                obj.put("content", "目标eas单据不存在");
            }
        } else if (type.contains("05")) {
            TConChangeauditbill tConChangeauditbill = auditMapper.selectById(easId);
            if (Util.isEmpty(tConChangeauditbill)) {
                obj.put("code", "2");
                obj.put("msg", "fault");
                obj.put("content", "目标eas单据不存在");
            }
        } else if (type.contains("06")) {
            TConContractchangesettlebill tConContractchangesettlebill = settleMapper.selectById(easId);
            if (Util.isEmpty(tConContractchangesettlebill)) {
                obj.put("code", "2");
                obj.put("msg", "fault");
                obj.put("content", "目标eas单据不存在");
            }
        }
        return obj;
    }
}
