package com.example.EAS.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.*;
import com.example.EAS.model.*;
import com.example.EAS.service.IBaseDataService;
import com.example.EAS.thread.AsyncExecutor;
import com.example.EAS.util.OaIdUtil;
import com.example.EAS.util.Util;
import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-08-17
 */

@Service
public class BaseDataServiceImpl extends ServiceImpl<BaseDataMapper, BaseData> implements IBaseDataService {

    @Autowired
    private OaIdUtil oaIdUtil;
    @Autowired
    private TConSupplierapplyMapper supplierapplyMapper;
    @Autowired
    private TConContractbillMapper contractbillMapper;
    @Autowired
    private TConChangeauditbillMapper auditMapper;
    @Autowired
    private TConContractchangesettlebillMapper settleMapper;


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
     *
     * @param body
     */

    @Override
    public JSONObject acceptHandle(JSONObject body) {
//      接口返回参数
        JSONObject obj = new JSONObject();
        obj.put("code", "1");
        obj.put("msg", "success");
//      请求参数
        String oaid = body.get("oaid").toString();
        String attlink = body.get("attlink").toString();
//      01:审批通过,02:废弃,03:驳回,修订
        String result = body.get("result").toString();
//      type 01:合同、02:合同付款申请单、03:无合同付款;04：供应商申请，05变更审批单，06变更确认单
        String type = body.get("type").toString();
        String easid = body.get("easid").toString();
        if (oaid == null || type == null || easid == null) {
            obj.put("code", "2");
            obj.put("msg", "fault");
            return obj;
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
//        开线程调用eas audit
//        供应商的状态修改逻辑较为简易 直接mybatis修改fstate
        jsonObject.put("id", easid);
        AsyncExecutor.executeTask(t -> {
            //       如果审核成功 调用eas审核方法
            if (result.contains("01")) {
                if (oaid != null && easid != null) {
                    oaIdUtil.getString(easid, oaid);
                }
                Call call = getCall("EASURL", auditOperation);
                String back = null;
                try {
                    back = (String) call.invoke(new Object[]{jsonObject.toString()});
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            try {

//        如果oa作废 修改状态为保存
                if (result.contains("02")) {
                    if (oaid != null && easid != null) {
                        oaIdUtil.getString(easid, oaid);
                    }
                    if (type.contains("01")) {
                    } else if (type.contains("02")) {

                    } else if (type.contains("03")) {

                    } else if (type.contains("04")) {
                        TConSupplierapply tConSupplierapply = supplierapplyMapper.selectById(easid);
                        tConSupplierapply.setFstate("1SAVED");
                        supplierapplyMapper.updateById(tConSupplierapply);
                    } else if (type.contains("05")) {
                        TConChangeauditbill tConChangeauditbill = auditMapper.selectById(easid);
                        tConChangeauditbill.setFstate("1SAVED");
                        tConChangeauditbill.setFchangestate("1Saved");
                        tConChangeauditbill.setFsourcefunction(null);
                        auditMapper.updateById(tConChangeauditbill);
                    } else if (type.contains("06")) {
                        TConContractchangesettlebill tConContractchangesettlebill = settleMapper.selectById(easid);
                        tConContractchangesettlebill.setFstate("1SAVED");
                        tConContractchangesettlebill.setFsourcefunction(null);
                        settleMapper.updateById(tConContractchangesettlebill);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//        如果驳回 修改状态为已提交 并保留oaid
            if (result.contains("03")) {
                if (type.contains("01")) {
                    TConContractbill tConContractbill = contractbillMapper.selectById(easid);
                    tConContractbill.setFstate("2SUBMITTED");
                    contractbillMapper.updateById(tConContractbill);
                } else if (type.contains("02")) {

                } else if (type.contains("03")) {

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
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
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
            object.put("auditOperation", "todo");
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
