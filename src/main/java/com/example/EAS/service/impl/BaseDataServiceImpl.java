package com.example.EAS.service.impl;

import com.example.EAS.service.IBaseDataService;
import com.example.EAS.vo.FileUploadVO;
import org.apache.axis.client.Call;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-08-17
 */

@Service
public class BaseDataServiceImpl implements IBaseDataService {

    org.apache.axis.client.Service service = new org.apache.axis.client.Service();

    //    String url = "http://127.0.0.1:8088/ormrpc/services/WSOAContractFacade";
    String url = "http://172.17.4.69:6888/ormrpc/services/WSOAContractFacade";
//    String url = "http://2x743c4002.zicp.vip:37492/ormrpc/services/WSOAContractFacade";

    /*
     * 生成调用webservice方法
     */
    private Call getCall(String operationName) {
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
