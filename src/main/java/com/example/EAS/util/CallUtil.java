package com.example.EAS.util;

import org.apache.axis.client.Call;

public class CallUtil {

    org.apache.axis.client.Service service = new org.apache.axis.client.Service();

    //    String url = "http://127.0.0.1:8088/ormrpc/services/WSOAContractFacade";
    String url = "http://172.17.4.63:6888/ormrpc/services/WSOAContractFacade";
//    String url = "http://2x743c4002.zicp.vip:37492/ormrpc/services/WSOAContractFacade";

    public Call getCall(String operationName) {
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
}
