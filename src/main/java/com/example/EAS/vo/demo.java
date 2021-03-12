package com.example.EAS.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.EAS.dto.WSContext;
import org.apache.axis.client.Call;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.math.BigDecimal;
import java.rmi.RemoteException;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:demo
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2021/3/10 9:19
 */
public class demo {

    public static void main(String[] args) throws Exception {
        org.apache.axis.client.Service service = new org.apache.axis.client.Service();
        String loginUrl = "http://43.254.149.179:16888/ormrpc/services/EASLogin?wsdl";
        String dataUrl = "http://43.254.149.179:16888/ormrpc/services/WSBizAccountMTFacade?wsdl";

        Call call = (Call) service.createCall();
        call.setOperationName("login");
        call.setTargetEndpointAddress(loginUrl);
        call.setReturnType(new QName("urn:client", "WSContext"));
        call.setReturnClass(WSContext.class);
        call.setReturnQName(new QName("", "loginReturn"));
        //超时
        call.setTimeout(Integer.valueOf(1000 * 600000 * 60));
        call.setMaintainSession(true);
        WSContext rs = (WSContext) call.invoke(new Object[]{"webservice-pmir", "456789", "eas", "A11", "L2", Integer.valueOf(2)}); //测试地址

        call.clearOperation();
        call.setOperationName("sendData");    //接口方法
        call.setTargetEndpointAddress(dataUrl);   //对应接口地址
        call.addParameter("arg0", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
        call.setTimeout(Integer.valueOf(1000 * 600000 * 60));
        call.setMaintainSession(true);
        call.setUseSOAPAction(true);

        JSONObject obj = new JSONObject();
        obj.put("id", "guessWhat");
        obj.put("fdType", "ConPay");
        obj.put("templateId", "templateId");
        obj.put("docSubject", "docSubject");
        obj.put("personNo", "008819-1");
        JSONObject data = new JSONObject();
        data.put("personNum", "026010");
        data.put("deptNum", "R0101");
        data.put("posNum", "00000000");
        data.put("comNum", "R01");
        data.put("applyDate", "2021-03-10");
        data.put("cause", "测试事由");
        data.put("costComNum", "R0101");
        data.put("rate", new BigDecimal(1));
        data.put("currency", "BB01");
        data.put("costCenter", "W010B68");
        JSONArray entrys = new JSONArray();
        JSONObject entry = new JSONObject();
        entry.put("payType", "201");
        entry.put("costProNum", "F37.001");
//        entry.put("moneyTypeNum",);
        entry.put("supplierNum","G0005909");
        entry.put("supplierName", "往来用户甲");
        entry.put("supplierBankNum", "0000313684145816");
        entry.put("supplierBank", "浦发银行");
        entry.put("applyAmount", new BigDecimal(10000));
//        entry.put("appAmount",);
//        entry.put("appBWAmount",);
        entry.put("payDate", "2021-03-10");
        entry.put("settlement", "02");
        entry.put("remark", "测试备注");
        entry.put("payAccount", "224114");
//        entry.put("ableBalance",);
//        entry.put("unPayAmount",);
//        entry.put("allPatAmount",);
//        entry.put("ogPayAmount",);
        entry.put("abstract", "2021-03-10");
//        entry.put("ogUnAoplyAmount",);
        entry.put("moneyPro", "1.2.3");
        entrys.add(entry);

        obj.put("data", data);
        obj.put("entrys", entrys);
        String back = (String) call.invoke(new Object[]{obj.toString()});
        System.out.println(back+"==================================================================================================================================================================================================================" +
                "==================================================================================================================================================================================================================" +
                "==================================================================================================================================================================================================================" +
                "==================================================================================================================================================================================================================" +
                "==================================================================================================================================================================================================================" +
                "==================================================================================================================================================================================================================");

        call.clearOperation();
        call.setOperationName("logout");
        call.setTargetEndpointAddress(loginUrl);
        call.invoke(new Object[]{"webservice-pmir", "eas", "A11", "L2"});//测试地址
    }


}
